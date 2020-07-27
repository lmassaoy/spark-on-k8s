# Cluster Kubernetes
### Setup Spark
You will need to start downloading the spark from GitHub, generating the 'spark' directory
```
$ git clone https://github.com/apache/spark.git
```
Access the directory and build using Maven (it may take several minutes, so grab a cup of coffee and stay with me)
```
$ cd spark
$ dev/make-distribution.sh -Pkubernetes
```

### Starting minikube (with enough resources)
```
$ minikube start --memory 6144 --cpus 4
```

### Creating service account and role bind
We'll need these components to execute our spark jobs over pods
You can execute the file "k8s_svc_accounts.sh" or execute the commands below:
```
$ kubectl create serviceaccount spark
$ kubectl create clusterrolebinding spark-role --clusterrole=edit  --serviceaccount=default:spark --namespace=default
```

### Building image for Spark with .jars
To make this work, what we do is create an spark docker image insertind our customs .jars to be executed. Explore the Dockerfile here to understand how we inject the custom .jars

You can execute the file "img_builder.sh" or execute the commands below:
```
$ eval $(minikube docker-env)
$ docker build . -t spark:latest
```
Obs.: to return to your original docker deamon you just need to close the current terminal or run the commands below:
```
$ unset DOCKER_HOST
$ unset DOCKER_TLS_VERIFY
$ unset DOCKER_CERT_PATH
```

### Now you're good to go!
Now you can execute the spark-submit over your k8s cluster (minikube)
Feel free to explore the example "devops/k8s_spark/spark_ignition/sql_project.sh" :)

# Jupyter PySpark
### Pull the docker image
No secrets, huh? - you may notice this image is a little bit heavy (around 4,5GB), but it's worthy!
```
$ docker pull jupyter/pyspark-notebook
```

### Build using docker-compose
This is pretty simple!
Drive with your terminal until the directory "devops/jupyter_pyspark" and fire the docker-compose up.
```
$ docker-compose up -d
```
The ports you'll be interested:
- localhost:10000 - it's the 8888 of the container, which is exposed for Jupyter
- localhost:4040 (and :4041 and :4042 for more spark contexts) - start-history-server

### How to access the Jupyter and have fun!
You'll need the URL+Token to access properly. So get this from the container's log
```
$ docker logs <containder_id>
```
Copy and paste in your browser :)

To enter bash and navigate in the folders you can execute (if you will):
```
$ docker exec -i -t <containder_id> /bin/bash
```
