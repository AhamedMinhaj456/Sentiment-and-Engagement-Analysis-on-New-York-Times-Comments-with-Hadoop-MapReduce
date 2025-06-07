# 🗽 Sentiment and Engagement Analysis on New York Times Comments with Hadoop MapReduce 💬

This project explores user comments from The New York Times, focusing on **sentiment analysis** and **engagement trends**. The dataset contains **over 2 million comments**, enabling insights into reader opinions and interaction patterns on news articles.

## 📂 Files

- `preprocess_comments.py` – 🧹 Script to clean and structure the NYT comment dataset
- `mapper.py` – 🔄 MapReduce mapper script (extracts sentiment or comment patterns)
- `reducer.py` – 🔢 MapReduce reducer script (aggregates results by sentiment or topic)
- `nyt_output.txt` – 📄 Output file retrieved from HDFS
- `analyze_sentiment.py` – 📊 Python script to parse and visualize sentiment distribution
- `sentiment_summary.csv` – 📑 CSV file showing sentiment-based counts or averages
- `sentiment_chart.png` – 🖼️ Bar chart of sentiment counts (positive/neutral/negative)

## 🛠️ Steps to Set Up Hadoop in Ubuntu

> 📚 Refer: [phoenixNAP Hadoop Setup Guide](https://phoenixnap.com/kb/install-hadoop-ubuntu)  
> 📚 Refer: [Medium Article on Hadoop Setup](https://medium.com/@wijebandara.ashan/how-to-install-hadoop-on-ubuntu-94fc9c4845ed)

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
python3 preprocess_comments.py
