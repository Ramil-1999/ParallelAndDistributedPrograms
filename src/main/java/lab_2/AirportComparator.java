package lab_2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportComparator extends WritableComparator{

    protected AirportComparator(){
        super(AirportWritableComparable.class, true);
    }

    @Override
    public int compare(WritableComparable first, WritableComparable second){
        return ((AirportWritableComparable)first).compareTo((AirportWritableComparable)second);
    }
}