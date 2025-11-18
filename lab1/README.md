# 📘 Hadoop HDFS Programming Lab  
*A Complete Step-by-Step Guide*

This document explains how to build, package, and run Java applications that interact with **HDFS** inside a Hadoop **Docker cluster**.  
It is written in clear and universal Markdown that any student can follow.

---

## 🧩 Table of Contents

1. [Prerequisites](#-prerequisites)  
2. [Docker Hadoop Environment](#-docker-hadoop-environment)  
3. [Start the Hadoop Cluster](#️-start-the-hadoop-cluster)  
4. [Java Project Structure](#-java-project-structure)  
5. [Compile Using Maven](#-compile-using-maven)  
6. [Build Executable JAR Files](#-build-executable-jar-files)  
7. [Copy JARs to Shared Folder](#-copy-jars-to-shared-folder)  
8. [Enter the Hadoop Container](#️-enter-the-hadoop-container)  
9. [Start Hadoop Services](#-start-hadoop-services)  
10. [Upload Input File to HDFS](#-upload-input-file-to-hdfs)  
11. [Run Java Programs on Hadoop](#-run-java-programs-on-hadoop)  
12. [Download Files from HDFS](#-download-files-from-hdfs)  
13. [Useful HDFS Commands](#-useful-hdfs-commands)  
14. [Summary](#-summary)

---

## 📦 Prerequisites

Ensure the following tools are installed:

- **Ubuntu / Linux**
- **Docker & Docker Compose**
- **Java JDK 8**
- **Maven 3+**
- A text editor (VSCode recommended)

---

## 🐳 Docker Hadoop Environment

Your `docker-compose.yml` should map a shared folder:

```yaml
volumes:
  - ./hadoop_project:/shared_volume
This gives:

Host path	Container path
./hadoop_project	/shared_volume

Create your shared folder:

bash
Copier le code
mkdir -p hadoop_project
▶️ Start the Hadoop Cluster
In the same folder as docker-compose.yml:

bash
Copier le code
docker compose up -d
Verify containers:

bash
Copier le code
docker ps
🧱 Java Project Structure
Standard Maven structure:

swift
Copier le code
lab1/
 ├── pom.xml
 └── src/
     ├── main/java/edu/supmti/hadoop/
     │     ├── HadoopFileStatus.java
     │     ├── ReadHDFS.java
     │     └── WriteHDFS.java
     └── test/java/edu/supmti/hadoop/
           └── (remove AppTest.java if unused)
🛠 Compile Using Maven
Inside your project:

bash
Copier le code
mvn clean package
Output classes go to:

bash
Copier le code
target/classes/
🏷 Build Executable JAR Files
Each Java file needs a JAR with a manifest.

1️⃣ HadoopFileStatus.jar
bash
Copier le code
echo "Main-Class: edu.supmti.hadoop.HadoopFileStatus" > manifest_hfs.txt
jar cfm target/HadoopFileStatus.jar manifest_hfs.txt -C target/classes .
2️⃣ ReadHDFS.jar
bash
Copier le code
echo "Main-Class: edu.supmti.hadoop.ReadHDFS" > manifest_read.txt
jar cfm target/ReadHDFS.jar manifest_read.txt -C target/classes .
3️⃣ WriteHDFS.jar
bash
Copier le code
echo "Main-Class: edu.supmti.hadoop.WriteHDFS" > manifest_write.txt
jar cfm target/WriteHDFS.jar manifest_write.txt -C target/classes .
📤 Copy JARs to Shared Folder
bash
Copier le code
cp target/*.jar hadoop_project/
Check:

bash
Copier le code
ls hadoop_project
🖥️ Enter the Hadoop Container
bash
Copier le code
docker exec -it hadoop-master bash
⚙ Start Hadoop Services
Inside the container:

bash
Copier le code
start-dfs.sh
start-yarn.sh
Check running services:

bash
Copier le code
jps
You should see:

NameNode

DataNode

ResourceManager

NodeManager

SecondaryNameNode

📁 Upload Input File to HDFS
Create input directory:

bash
Copier le code
hdfs dfs -mkdir -p /user/root/input
Upload input file:

bash
Copier le code
hdfs dfs -put -f /shared_volume/purchases.txt /user/root/input/
Verify:

bash
Copier le code
hdfs dfs -ls /user/root/input
🚀 Run Java Programs on Hadoop
1️⃣ HadoopFileStatus — rename file + show metadata
bash
Copier le code
hadoop jar /shared_volume/HadoopFileStatus.jar /user/root/input purchases.txt achats.txt
2️⃣ ReadHDFS — display file content
bash
Copier le code
hadoop jar /shared_volume/ReadHDFS.jar /user/root/input/achats.txt
3️⃣ WriteHDFS — write text into HDFS
bash
Copier le code
hadoop jar /shared_volume/WriteHDFS.jar /user/root/input/msg.txt "Hello from Hadoop!"
Display the file:

bash
Copier le code
hdfs dfs -cat /user/root/input/msg.txt
📥 Download Files from HDFS
Inside the container:

bash
Copier le code
hdfs dfs -get /user/root/input/achats.txt /shared_volume/achats_local.txt
Then on host:

bash
Copier le code
ls hadoop_project/
🧹 Useful HDFS Commands
Action	Command
List root	hdfs dfs -ls /
Upload file	hdfs dfs -put local.txt /user/root/
Read file	hdfs dfs -cat /path/file.txt
Remove file	hdfs dfs -rm /path/file.txt
Remove folder	hdfs dfs -rm -r /path

🎓 Summary
✔ Start the Docker Hadoop cluster
✔ Compile Java project with Maven
✔ Create executable JAR files
✔ Copy JARs to shared folder
✔ Start Hadoop inside container
✔ Upload files to HDFS
✔ Run the 3 Hadoop Java programs
✔ Download results back to host