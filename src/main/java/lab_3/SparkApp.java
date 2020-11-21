package lab_3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkApp {
    public static void main(String[] args) throws Exception {
        //Spark init
        SparkConf conf = new SparkConf().setAppName("SparkApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //загрузка данных из HDFS
        JavaRDD<String> distFile = sc.textFile(Hadoop "war-and-peace-1.txt");
    }
}