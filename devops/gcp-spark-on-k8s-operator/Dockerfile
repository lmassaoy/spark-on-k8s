ARG SPARK_IMAGE=gcr.io/spark-operator/spark:v3.0.0
FROM ${SPARK_IMAGE}

# insert my custom jars
COPY custom_jars/ /opt/spark/examples/

ENTRYPOINT ["/opt/entrypoint.sh"]