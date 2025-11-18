# 📘 Big Data Labs Repository  
### Global Guide for Hands-On Big Data Experiments (2025 Edition)

**Welcome to the Big Data Labs Repo!**  
This repository is your one-stop resource for practical, step-by-step labs in Big Data technologies. Designed for students, developers, and enthusiasts worldwide, it covers essential tools like Hadoop, HDFS, Spark, Kafka, and more.  

**Key Features:**  
- **Universal Setup:** Works on Ubuntu/Linux, macOS, Windows (WSL2).  
- **Docker-Based:** Easy, isolated environments—no messing with your local machine.  
- **Progressive Labs:** Start simple, build to advanced distributed systems.  
- **Global Focus:** Clear English instructions, adaptable for any region or institution.  

**Repo Structure:**  
```
bigdata-labs/  
├── README.md              # This file – start here!  
├── lab1/             # Lab 1: HDFS Programming (Java API)  
│   ├── pom.xml  
│   ├── src/...  
│   └── README.md          # Lab-specific guide  
├── README.md 
```  

---

## 🌍 Why This Repo?  
Big Data is exploding globally—from AI-driven analytics in Asia to cloud processing in Europe and real-time streaming in the Americas. This repo provides:  
- **Hands-On Practice:** Code, run, and debug real Big Data apps.  
- **Scalable Learning:** Labs build on each other, from file systems to machine learning pipelines.  
- **Open Source Friendly:** Fork, contribute, or adapt for your needs.  
- **No Vendor Lock-In:** Uses open-source tools like Apache Hadoop/Spark.  

If you're in a course (e.g., "Big Data Programming" at universities worldwide), this aligns with standard curricula.  

---

## 🚀 Getting Started (Prerequisites)  
Install these globally for all labs:  

| Tool                  | Version/Notes                  | Installation (Ubuntu Example)              |  
|-----------------------|--------------------------------|--------------------------------------------|  
| OS                    | Ubuntu/Linux/WSL2/macOS        | —                                          |  
| Docker + Compose      | Latest                         | `sudo apt install docker.io docker-compose` |  
| Java JDK              | 8 (for Hadoop compatibility)   | `sudo apt install openjdk-8-jdk`           |  
| Maven                 | 3.6+                           | `sudo apt install maven`                   |  
| Code Editor           | VS Code / IntelliJ             | Download from official site                |  

**Verify Setup:**  
```bash  
docker --version && docker compose version  
java -version  # Should show version 1.8.x  
mvn -v  
```  

**Pro Tip:** For Windows/macOS users, enable WSL2 or use Docker Desktop for best performance.  

---

## 🐳 Shared Docker Environment  
All labs use a common Dockerized Hadoop/Spark cluster. Edit `docker-compose.yml` to include a shared volume:  

```yaml  
services:  
  hadoop-master:  
    volumes:  
      - ./shared_volume:/global_shared  
  # Add similar for Spark/Kafka services as needed  
```  

Create the shared folder:  
```bash  
mkdir -p shared_volume  
```  

Launch the cluster (run once per session):  
```bash  
docker compose up -d  
```  

Stop when done: `docker compose down`.  

---

## 📚 Available Labs  
Start with Lab 1 and progress. Each lab has its own subfolder and README.md with detailed steps.  

### Lab 1: HDFS Programming (Java API)  
- **Focus:** Read, write, rename files in HDFS using Java.  
- **Tools:** Java 8, Maven, Hadoop Docker.  
- **Folder:** `lab1/`  
- **Quick Start:** Compile with `mvn clean package`, build JARs, run in container.  
- **Link:** [Lab 1 README](./lab1/README.md)  

<!--
### Lab 2: Spark Basics (RDDs & DataFrames)  
- **Focus:** Process data with Spark in Scala/Python.  
- **Tools:** Spark Docker, Scala/Python.  
- **Folder:** `lab2-spark/` (Coming soon – contribute if interested!)  

### Lab 3: MapReduce Jobs  
- **Focus:** Custom mappers/reducers for data aggregation.  
- **Tools:** Hadoop MapReduce.  
- **Folder:** `lab3-mapreduce/` -->
More labs incoming: Kafka Streaming, Hive Queries, Pig Scripting, Big Data ML with Spark MLlib.  

---

## ⚙️ General Workflow for Any Lab  
1. **Clone Repo:** `git clone https://github.com/yourusername/bigdata-labs.git`  
2. **Setup Environment:** Install prerequisites, launch Docker cluster.  
3. **Navigate to Lab:** `cd labX-xyz/`  
4. **Build & Compile:** Use Maven/SBT as specified.  
5. **Transfer Files:** Copy artifacts to `shared_volume/`.  
6. **Enter Container:** `docker exec -it hadoop-master bash` (or spark-master).  
7. **Run Services:** `start-dfs.sh && start-yarn.sh` (Hadoop-specific).  
8. **Execute Lab:** Follow lab README for commands.  
9. **Verify & Cleanup:** Check outputs, download results if needed.  

---

## 🧰 Useful Commands Cheat Sheet  
| Category             | Command Example                              | Description                          |  
|----------------------|----------------------------------------------|--------------------------------------|  
| Docker               | `docker compose up -d`                       | Start cluster                        |  
| Container Access     | `docker exec -it hadoop-master bash`         | Enter shell                          |  
| Hadoop Services      | `start-dfs.sh && start-yarn.sh`              | Start HDFS/YARN                      |  
| HDFS Basics          | `hdfs dfs -ls /`                             | List root                            |  
| Maven Build          | `mvn clean package`                          | Compile Java project                 |  
| JAR Creation         | `jar cfm app.jar manifest.txt -C target/classes .` | Build executable JAR            |  
| Spark Submit         | `spark-submit --class Main app.jar`          | Run Spark job (for future labs)      |  

---

## 🔧 Troubleshooting  
- **Docker Issues:** Ensure ports are free (e.g., 9870 for NameNode UI). Restart with `docker compose down -v`.  
- **Java Version Mismatch:** Stick to JDK 8—newer versions may break Hadoop.  
- **HDFS Errors:** Run `hdfs namenode -format` (caution: wipes data).  
- **Resource Limits:** Allocate more CPU/RAM to Docker if jobs fail.  
- **Global Adaptations:** If in a restricted network (e.g., some countries), use VPN for Docker pulls.  

For help, open an issue or check Stack Overflow/Hadoop docs.  

---

## 🤝 Contributing  
This is a global community repo!  
- **Add Labs:** Submit PRs for new labs (e.g., AWS EMR, Google Dataproc variants).  
- **Improve Docs:** Fix typos, add translations (e.g., French/Spanish READMEs).  
- **Star & Share:** Help spread the word to Big Data learners worldwide.  

License: MIT – Free to use, modify, distribute.  

---

## 🎓 Final Notes  
Dive in with Lab 1, and you'll be handling petabytes in no time! If this repo helps your studies or projects, drop a ⭐ on GitHub.  

**Happy Big Data Exploring!** 🚀  
*Curated for global learners · Updated November 2025*