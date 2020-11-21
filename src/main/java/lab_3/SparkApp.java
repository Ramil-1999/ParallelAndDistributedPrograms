package lab_3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkApp {
    public static void main(String[] args) throws Exception {
        //Spark init
        SparkConf conf = new SparkConf().setAppName("SparkApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //загрузка данных


    }
}