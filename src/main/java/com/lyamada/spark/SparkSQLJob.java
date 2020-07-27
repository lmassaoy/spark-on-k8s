package com.lyamada.spark;

import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Iterator;

public class SparkSQLJob
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World! :)" );

        SparkSession session = SparkSession.builder().appName("spark-lyamada-test").getOrCreate();

        Dataset<Row> df = session
                            .read()
                            .parquet("/opt/spark/examples/spark_sql_project/Datasets/user_reviews.parquet");

        df.createOrReplaceTempView("reviews");

        session.sql("SELECT score, count(score) from reviews group by score order by score").show();

        df.foreachPartition(new ForeachPartitionFunction<Row>() {
            public void call(Iterator<Row> iterator) throws Exception {
                while (iterator.hasNext()){
                    Row row = iterator.next();
                    System.out.println("Score: " + row.getString(3) + ", Review: " + row.getString(5));
                }
            }
        });

        System.out.println("Waiting two minutes until finish the job :)");
        Thread.sleep(180000);

        session.stop();
        session.close();
    }
}
