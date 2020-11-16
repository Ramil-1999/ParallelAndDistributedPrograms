package lab_2;

import org.apache.hadoop.mapreduce.Partitioner;

public abstract class Partitioner<KEY, VALUE> {
    public abstract int getPartition(KEY key, VALUE value, int numPartitions);
}