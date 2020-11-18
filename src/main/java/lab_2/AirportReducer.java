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

        /*
        while(itr.hasNext()){
            counter++;
            String str = itr.next().toString();

            Double value = Double.parseDouble(str);
            if (value < minDelay) minDelay = value;
            if (value > maxDelay) maxDelay = value;
            sumOfDelays += value;
        }*/
        Text test;
        test = new Text("test");
        if(itr.hasNext()) {
            test = itr.next();
        }

        averageDelay = sumOfDelays/counter;

        String txt = "";
        if (counter >= 0){
            txt = "index = " + key.getIndex() + "  name = " + airportName + " next= " + test + ": " + minDelay + ", " + maxDelay + ", " + averageDelay;
        }
        if (txt.length() > 0) {
            Text output = new Text(txt);
            context.write(new Text(key.getCode().toString()), output);
        }
    }
}