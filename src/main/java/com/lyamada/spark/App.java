package com.lyamada.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World! :)" );

        SparkSession session = SparkSession.builder().appName("spark-lyamada-test").getOrCreate();

        JavaSparkContext sparkContext = new JavaSparkContext(session.sparkContext());

        List<Integer> integers = new ArrayList<>();
        for (int counter = 1; counter <= 15; counter++) {
            integers.add(counter);
        }

        JavaRDD<Integer> javaRDD = sparkContext.parallelize(integers,2);
        javaRDD.foreach((VoidFunction<Integer>) integer -> {
            System.out.println("Java RDD: "+integer);
            Thread.sleep(2000);
        });

        System.out.println("SaulGoodman ;) - 1m to turn off");

        Thread.sleep(60000);

        sparkContext.stop();
        sparkContext.close();
    }
}
