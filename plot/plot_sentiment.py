import pandas as pd
import matplotlib.pyplot as plt

# Load the output file (if space separated, use delim_whitespace=True)
df = pd.read_csv('output_sentiment.txt', delim_whitespace=True, header=None, names=['Sentiment', 'Count'])

# Plotting a bar chart
plt.figure(figsize=(8,5))
plt.bar(df['Sentiment'], df['Count'], color=['green', 'red', 'gray'])

plt.title('Sentiment Analysis Results')
plt.xlabel('Sentiment')
plt.ylabel('Count')

# Optionally add the number labels on top of bars
for i, count in enumerate(df['Count']):
    plt.text(i, count + 10, str(count), ha='center')

plt.tight_layout()
plt.savefig('sentiment_analysis_graph.png')  # Saves the plot as a PNG file
plt.show()  # Displays the plot
