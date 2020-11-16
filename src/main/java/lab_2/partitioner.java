package lab_2;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class AirportPartitioner extends Partitioner<AirportWritableComparable, Text>{
    @Override
    public int getPartition(AirportWritableComparable airportWritableComparable,Text text, int num){
        return airportWritableComparable.hashCode() % num;
    }
}