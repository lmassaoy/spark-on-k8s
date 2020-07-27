#!/bin/bash
k8s_cluster=192.168.64.6:8443
job_name=spark-lyamada
class=com.lyamada.spark.SparkSQLJob
docker_img=spark:latest
jar_path=opt/spark/examples/spark_sql_project/spark-lyamada-1.0-SNAPSHOT.jar

./spark/bin/spark-submit \
--master k8s://https://$k8s_cluster \
--deploy-mode cluster \
--name $job_name \
--class $class \
--conf spark.executor.instances=2 \
--conf spark.executor.memory=512m \
--conf spark.driver.memory=1g \
--conf spark.driver.memoryOverhead=512 \
--conf spark.kubernetes.container.image=$docker_img \
--conf spark.kubernetes.container.image.pullPolicy=Never \
--conf spark.kubernetes.authenticate.driver.serviceAccountName=spark \
local:///$jar_path