# 🗽 Sentiment and Engagement Analysis on New York Times Comments with Hadoop MapReduce 💬

This project explores user comments from The New York Times, focusing on **sentiment analysis** and **engagement trends**. The dataset contains **over 2 million comments**, enabling insights into reader opinions and interaction patterns on news articles.

## 📂 Files

- `preprocess_comments.py` – 🧹 Script to clean and structure the NYT comment dataset
- `mapper.py` – 🔄 MapReduce mapper script (extracts sentiment or comment patterns)
- `reducer.py` – 🔢 MapReduce reducer script (aggregates results by sentiment or topic)
- `nyt_output.txt` – 📄 Output file retrieved from HDFS
- `analyze_sentiment.py` – 📊 Python script to parse and visualize sentiment distribution
- `sentiment_summary.csv` – 📑 CSV file showing sentiment-based counts or averages
- `sentiment_chart.png` – 🖼️ Bar chart of sentiment counts (positive/negative)

## 🛠️ Steps to Set Up Hadoop in Ubuntu

> 📚 Refer: [phoenixNAP Hadoop Setup Guide](https://phoenixnap.com/kb/install-hadoop-ubuntu)  

1. ☕ **Install JDK** on Ubuntu
2. 🔐 **Configure a Hadoop user and SSH**
3. 📦 **Download and install Hadoop**
4. 🔧 **Configure single-node cluster settings**
5. 💾 **Format HDFS NameNode**
6. ▶️ **Start Hadoop daemons**
7. 🌐 **Verify using Web UI (localhost:9870)**

## 📥 Download and Clean Data

> 📚 Dataset: [New York Times Comments](https://www.kaggle.com/datasets/aashita/nyt-comments)

1. Download the dataset from Kaggle and extract the relevant file (e.g., `CommentsMarch2018.csv`)
2. Run the preprocessing script to clean and simplify the dataset:
```bash

🚀 Steps to Run
Step 1: Download and Clean Data
📥 Download the New York Times Comments dataset from Kaggle: https://www.kaggle.com/datasets/aashita/nyt-comments
📁 Extract the relevant file (e.g., CommentsMarch2018.csv)
🔄 Run the preprocessing script to clean and simplify the dataset: python3 preprocess_comments.py
Step 2: Set up Hadoop
📚 Follow the steps to set up Hadoop in Ubuntu: https://phoenixnap.com/kb/install-hadoop-ubuntu
📦 Install JDK, configure a Hadoop user and SSH, download and install Hadoop, configure single-node cluster settings, format HDFS NameNode, and start Hadoop daemons
Step 3: Run MapReduce Job
📤 Upload the cleaned dataset to HDFS: hdfs dfs -mkdir -p /nyt_input and hdfs dfs -put CommentsMarch2018.csv /nyt_input/
🏃 Run the MapReduce job: hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-*.jar -input /nyt_input/CommentsMarch2018.csv -output /nyt_output -mapper mapper.py -reducer reducer.py
Step 4: Get Output from HDFS
📥 Get the output from HDFS: hdfs dfs -get /nyt_output/part-00000 nyt_output.txt
Step 5: Analyze Sentiment
📊 Run the Python script to parse and visualize sentiment distribution: python3 analyze_sentiment.py
Step 6: Setup Python Requirements (If not installed)
🛠️ Install Python requirements: sudo apt install python3-pip and pip3 install --user pandas matplotlib

python3 preprocess_comments.py
