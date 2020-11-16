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
        Float sumOfDelays, averageDelay;
        Float minDelay, maxDelay;
        Iterator<Text> itr = values.iterator();
        airportName = itr.next();
        

        while(itr.hasNext()){
            Float value = Float.parseFloat(itr.toString());
            if (value < minDelay) minDelay = value;
            if (value > maxDelay) maxDelay = value;

        }

        context.write(airportName, );
    }
}