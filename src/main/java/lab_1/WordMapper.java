package lab_1;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    public static final String PATTERN_SYMBOLS = "[^A-Za-zА-Яа-я0-9\\s]";
    public static final String PATTERN_SPACES = " +";
    public static final String PATTERN_SPACE = " ";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws
            IOException, InterruptedException {

        String line = value.toString();

        line = line.replaceAll(PATTERN_SYMBOLS, " ").toLowerCase().trim().replaceAll(PATTERN_SPACES, " ");

        String[] words = line.split(PATTERN_SPACE);

        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }

    }
}