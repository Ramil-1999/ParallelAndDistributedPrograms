package lab_2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class MapperFlight extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    private static  final  Integer UNO = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String values[] = value.toString().replaceAll("\"", "").split(",");
            if (values[18].length() > 0) {
                Float delay = Float.parseFloat(values[18]);
                if (delay > 0) {
                    context.write(new AirportWritableComparable(Integer.valueOf(values[14]), UNO), new Text(values[18]));
                }
            }
        }
    }
}