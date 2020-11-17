package lab_2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class MapperAirport extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    private static final String ZERO = "0";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String values[] = value.toString().split(",");
        context.write(new AirportWritableComparable(Integer.valueOf(values[0]), ZERO), new Text(values[1]));
    }
}
