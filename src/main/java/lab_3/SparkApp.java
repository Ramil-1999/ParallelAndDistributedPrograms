package lab_3;

import org.apache.spark.SparkConf;
import org.apache.spark.StopMapOutputTracker;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;



public class SparkApp {

    private final Integer airportID = 0;
    private final Integer airportName = 1;

    private final Integer originID = 11;
    private final Integer destID = 14;
    private final Integer delay = 18;



    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: lab3 <table file> <dictionary file> <output file>");
            System.exit(-1);
        }

        //Spark init
        SparkConf conf = new SparkConf().setAppName("SparkApp").set("spark.driver.host","127.0.0.1").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //загрузка данных из HDFS
        JavaRDD<String> flightsFile = sc.textFile(args[0]);
        JavaRDD<String> airportsFile = sc.textFile(args[1]);

        String fHeader = flightsFile.first();
        String aHeader = airportsFile.first();
        JavaRDD<String> flights = flightsFile.filter(row -> !row.equals(fHeader));  // filter out header
        JavaRDD<String> airports = airportsFile.filter(row -> !row.equals(aHeader));

        System.out.println(airports.first());


    }
}