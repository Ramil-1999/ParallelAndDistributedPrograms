package lab_2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class MapperAirport extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    private static final Integer ZERO = 0;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String[] values = value.toString().replaceAll("\"", "").split(",");
            context.write(new AirportWritableComparable(Integer.valueOf(values[0]), ZERO), new Text(values[1]));
        }
    }
}