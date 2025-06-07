import java.io.*;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SentimentAnalysis {

    public static class SentimentMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        private Set<String> positiveWords = new HashSet<>();
        private Set<String> negativeWords = new HashSet<>();
        private final static IntWritable one = new IntWritable(1);
        private Text sentiment = new Text();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            FileSystem fs = FileSystem.get(conf);
            URI[] cacheFiles = context.getCacheFiles();

            if (cacheFiles != null && cacheFiles.length > 0) {
                for (URI uri : cacheFiles) {
                    Path path = new Path(uri.getPath());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(path)));
                    String fileName = path.getName();
                    String word;

                    if (fileName.equals("positive-words.txt")) {
                        while ((word = reader.readLine()) != null) {
                            positiveWords.add(word.trim().toLowerCase());
                        }
                    } else if (fileName.equals("negative-words.txt")) {
                        while ((word = reader.readLine()) != null) {
                            negativeWords.add(word.trim().toLowerCase());
                        }
                    }

                    reader.close();
                }
            }
        }

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] tokens = value.toString().toLowerCase().split("\\W+");

            for (String word : tokens) {
                if (positiveWords.contains(word)) {
                    sentiment.set("positive");
                    context.write(sentiment, one);
                } else if (negativeWords.contains(word)) {
                    sentiment.set("negative");
                    context.write(sentiment, one);
                }
            }
        }
    }

    public static class SentimentReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;

            for (IntWritable val : values) {
                sum += val.get();
            }

            context.write(key, new IntWritable(sum));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Sentiment Analysis");

        job.setJarByClass(SentimentAnalysis.class);
        job.setMapperClass(SentimentMapper.class);
        job.setCombinerClass(SentimentReducer.class);
        job.setReducerClass(SentimentReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Add cache files with correct HDFS paths
        job.addCacheFile(new URI("/user/ahamedshaa/positive-words.txt"));
        job.addCacheFile(new URI("/user/ahamedshaa/negative-words.txt"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
