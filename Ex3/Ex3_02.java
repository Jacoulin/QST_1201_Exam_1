package com.jacoulin.date_2016_12_01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Jacoulin on 2016/11/30.
 */
public class Ex3_02 {
    public static class DefMap extends Mapper<LongWritable, Text, Text, Text> {
        Text ip = new Text();
        Text time = new Text();
        Set<String> set = new HashSet<String>();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            FileSystem fs = FileSystem.get(conf);
            Path customIPsPath = new Path(conf.get("ip_1"));
            InputStream is = fs.open(customIPsPath);
            Scanner scanner = new Scanner(is);
            while(scanner.hasNext()){
                set.add(scanner.nextLine());
            }
            String[] line = value.toString().split("\\t");
            if(set.contains(line[0])){
                ip.set(line[0]);
                time.set(line[1]);
                context.write(ip, time);
            }
        }
    }

    public static class DefReduce extends Reducer<Text, Text, Text, Text>{
        int count = 0;
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Text val : values){
                count++;
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(new Text("两个文件重复的IP数："), new Text(String.valueOf(count)));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("ip1", args[2]);
        Job job = Job.getInstance(conf, "Ex3_02");
        job.setJarByClass(Ex3_02.class);

        job.setMapperClass(DefMap.class);
        job.setReducerClass(DefReduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
