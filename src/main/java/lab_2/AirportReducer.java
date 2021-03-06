package lab_2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(AirportWritableComparable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        String airportName;
        int counter = 0;
        Double sumOfDelays = 0.0;
        Double averageDelay;
        Double minDelay = Double.MAX_VALUE;
        Double maxDelay = Double.MIN_VALUE;
        Iterator<Text> itr = values.iterator();
        airportName = itr.next().toString();

        if(itr.hasNext()) {

            while (itr.hasNext()) {
                counter++;
                String str = itr.next().toString();
                Double value = Double.parseDouble(str);
                if (value < minDelay) minDelay = value;
                if (value > maxDelay) maxDelay = value;
                sumOfDelays += value;
            }

            averageDelay = sumOfDelays / counter;
            String averageDelayFormat = String.format("%.2f", averageDelay);

            context.write(new Text(airportName), new Text(minDelay + ", " + maxDelay + ", " + averageDelayFormat));
        }
    }
}