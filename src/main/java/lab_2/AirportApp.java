package lab_2;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;


public class AirportApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: lab2");
            System.exit(-1);
        }
        Job job = Job.getInstance();
        job.setJarByClass(AirportApp.class);
        job.setJobName("AirportApp");

        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, MapperAirport.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, MapperFlight.class);

        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
