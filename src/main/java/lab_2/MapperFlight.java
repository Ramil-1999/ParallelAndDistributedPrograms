package lab_2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class MapperFlight extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String values[] = value.toString().split(", ");
        context.write(new AirportWritableComparable(Integer.valueOf(values[14]), values[18]), new Text(String.valueOf(1)));
    }
}
