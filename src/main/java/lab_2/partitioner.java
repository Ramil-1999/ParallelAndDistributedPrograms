package lab_2;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class AirportPartitioner extends Partitioner<key, Text>{
    @Override
    public int getPartition(Text text, int numPartitions){

    }
}