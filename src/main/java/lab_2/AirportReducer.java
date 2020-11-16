package lab_2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(AirportWritableComparable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        Text airportName;
        int counter = 0;
        Double sumOfDelays = 0.0;
        Double averageDelay;
        Double minDelay = Double.MAX_VALUE;
        Double maxDelay = Double.MIN_VALUE;
        Iterator<Text> itr = values.iterator();
        airportName = itr.next();

        while(itr.hasNext()){
            counter++;
            Double value = Double.parseDouble(itr.toString());
            if (value < minDelay) minDelay = value;
            if (value > maxDelay) maxDelay = value;
            sumOfDelays += value;
        }

        averageDelay = sumOfDelays/counter;


        if (counter > 0){
            String sss = minDelay + ", " + maxDelay + ", " + averageDelay;
            Text output = new Text(sss);
        }
        context.write(airportName, output);
    }
}