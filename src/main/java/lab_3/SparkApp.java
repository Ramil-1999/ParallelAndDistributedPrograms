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
        JavaRDD<String> airports = sc.textFile("hadoop/2.csv");
        JavaRDD<String> flights = sc.textFile("hadoop/1.csv");

        
    }
}