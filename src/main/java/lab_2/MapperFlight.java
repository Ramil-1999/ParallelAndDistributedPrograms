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
            String[] arr = value
                    .toString()
                    .split(",");

            final String airportId = arr[14];
            final String delay = arr[18];

            if (delay.length() > 0) {
                float delayF = Float.parseFloat(delay);
                if (delayF > 0) {
                    context.write(new AirportWritableComparable(Integer.valueOf(airportId), UNO), new Text(delay));
                }
            }

        }
    }
}