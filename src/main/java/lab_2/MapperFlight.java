package lab_2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class MapperAirport extends Mapper<LongWritable, Text, TextPair, Text> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
       
    }
}
