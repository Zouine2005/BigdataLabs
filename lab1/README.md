# 📘 Hadoop HDFS Programming Lab — Global Guide

This guide explains how to complete the **Big Data – HDFS Programming Lab** using:

- Java 8  
- Maven  
- Hadoop (Docker cluster)  
- A shared folder between host & container  

Clear, universal, and easy to follow for all students.

---

## 🚀 1. Prerequisites

Make sure you have installed:

- **Ubuntu / Linux**
- **Docker & Docker Compose**
- **Java JDK 8**
- **Maven**
- A source code editor (VSCode recommended)

---

## 🐳 2. Hadoop Docker Environment

Your `docker-compose.yml` must include a shared directory:

```yaml
volumes:
  - ./hadoop_project:/shared_volume
```

This means:

- Host folder: `./hadoop_project`
- Inside container: `/shared_volume`

Create the folder:

```bash
mkdir -p hadoop_project
```

---

## ▶️ 3. Start the Hadoop Cluster

In the directory where `docker-compose.yml` is located:

```bash
docker compose up -d
```

Check running containers:

```bash
docker ps
```

---

## 🧱 4. Java Project Structure

Your Maven project should look like:

```
lab1/
 ├── pom.xml
 └── src/
     ├── main/java/edu/supmti/hadoop/
     │     ├── HadoopFileStatus.java
     │     ├── ReadHDFS.java
     │     └── WriteHDFS.java
     └── test/java/edu/supmti/hadoop/
           └── (remove AppTest.java if unused)
```

---

## 📦 5. Compile the Project

Inside your project folder:

```bash
mvn clean package
```

The compiled classes will be inside:

```
target/classes/
```

---

## 🏷️ 6. Build Executable JAR Files

Each Java program needs a JAR with a Main-Class manifest.

### 6.1 Create HadoopFileStatus.jar

```bash
echo "Main-Class: edu.supmti.hadoop.HadoopFileStatus" > manifest_hfs.txt
jar cfm target/HadoopFileStatus.jar manifest_hfs.txt -C target/classes .
```

### 6.2 Create ReadHDFS.jar

```bash
echo "Main-Class: edu.supmti.hadoop.ReadHDFS" > manifest_read.txt
jar cfm target/ReadHDFS.jar manifest_read.txt -C target/classes .
```

### 6.3 Create WriteHDFS.jar

```bash
echo "Main-Class: edu.supmti.hadoop.WriteHDFS" > manifest_write.txt
jar cfm target/WriteHDFS.jar manifest_write.txt -C target/classes .
```

---

## 📤 7. Copy JAR Files to Shared Folder

```bash
cp target/*.jar hadoop_project/
```

Verify:

```bash
ls hadoop_project
```

---

## 🖥️ 8. Enter the Hadoop Master Container

```bash
docker exec -it hadoop-master bash
```

---

## ⚙️ 9. Start Hadoop Services (Inside Container)

```bash
start-dfs.sh
start-yarn.sh
```

Verify:

```bash
jps
```

You should see:

- NameNode
- DataNode
- ResourceManager
- NodeManager
- SecondaryNameNode

---

## 📁 10. Prepare HDFS Input Directory

```bash
hdfs dfs -mkdir -p /user/root/input
```

Upload the input file:

```bash
hdfs dfs -put -f /shared_volume/purchases.txt /user/root/input/
```

Check:

```bash
hdfs dfs -ls /user/root/input
```

---

## 🏷️ 11. Run HadoopFileStatus (Rename File + Show Info)

```bash
hadoop jar /shared_volume/HadoopFileStatus.jar /user/root/input purchases.txt achats.txt
```

Verify:

```bash
hdfs dfs -ls /user/root/input
```

---

## 📖 12. Run ReadHDFS (Read File Content)

```bash
hadoop jar /shared_volume/ReadHDFS.jar /user/root/input/achats.txt
```

---

## ✍️ 13. Run WriteHDFS (Write Text to HDFS)

```bash
hadoop jar /shared_volume/WriteHDFS.jar /user/root/input/msg.txt "Hello from Hadoop!"
```

Read it:

```bash
hdfs dfs -cat /user/root/input/msg.txt
```

---

## 📥 14. Download a File from HDFS to Host

```bash
hdfs dfs -get /user/root/input/achats.txt /shared_volume/achats_local.txt
```

On the host:

```bash
ls hadoop_project/
```

---

## 🧹 15. Useful HDFS Commands

| Action              | Command                              |
|---------------------|--------------------------------------|
| List root directory | `hdfs dfs -ls /`                     |
| Create directory    | `hdfs dfs -mkdir /path`              |
| Upload file         | `hdfs dfs -put file.txt /path`       |
| Read file           | `hdfs dfs -cat /path/file.txt`       |
| Delete file         | `hdfs dfs -rm /path/file.txt`        |
| Delete folder       | `hdfs dfs -rm -r /path`              |

---

## 🎓 Summary

- Start Docker Hadoop cluster
- Compile Maven project
- Create JARs
- Copy JARs to shared folder
- Enter container
- Start HDFS + YARN
- Upload input file
- Run the Hadoop programs
- Verify results
- Download output if needed