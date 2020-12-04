package lab_3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;


public class SparkApp {

    private static final String COMMA_PATT = ",";

    private static final int airportID = 0;
    private static final int airportName = 1;

    private static final int originID = 11;
    private static final int destID = 14;
    private static final int delay = 18;
    private static final int isCancelled = 19;



    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
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


        JavaPairRDD<Tuple2<String,String>, FlightData> flightsKeyValue = flights
                .mapToPair(row -> {
                    String[] arr = row.split(COMMA_PATT);
                    return new Tuple2<>(new Tuple2<>(arr[originID], arr[destID]), new FlightData(arr[delay], arr[isCancelled])); })
                .groupByKey()
                .mapValues(row ->  new FlightData().calculations(row));

        Map<String, String> airportMap = airports
                .map(row -> row.split(COMMA_PATT))
                .mapToPair(row -> new Tuple2<>(row[airportID], row[airportName])).collectAsMap();


        final Broadcast<Map<String, String>> airportBroadcasted = sc.broadcast(airportMap);


        JavaRDD output = flightsKeyValue.map(row -> {
            String result = " FROM: " + airportBroadcasted.value().get(row._1._1) + ", TO: " + airportBroadcasted.value().get(row._1()._2() + ", " + row._2.toString());
            return result;
        });


        output.saveAsTextFile(args[2]);
    }
}