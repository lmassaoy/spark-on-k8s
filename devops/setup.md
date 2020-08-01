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
To make this work, what we do is create an spark docker image inserting our customs .jars to be executed. Explore the Dockerfile here to understand how we inject the custom .jars

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
# GCP/spark-on-k8s-operator
### Setup the directory
The first thing I'd like to recommend to you is to download the oficial repo from GCP
There are plenty of good things to explore and understand how to use this feature properly
```
$ cd gcp-spark-on-k8s-operator/
$ git clone https://github.com/GoogleCloudPlatform/spark-on-k8s-operator.git
```

### Installing the sparkoperator in your cluster
Now you'll need to execute the command below (make sure you already have Helm in your enviroment)
```
$ helm install incubator/sparkoperator --generate-name --namespace default --set sparkJobNamespace=default --set enableWebhook=true --set webhookPort=443
```

### Creating role bind
You can execute the command below:
```
$ kubectl create clusterrolebinding spark-operator-admin --clusterrole=cluster-admin --user=default:spark
```

### Building image for Spark with .jars
To make this work, what we do is create an spark docker image inserting our customs .jars to be executed. Explore the Dockerfile here to understand how we inject the custom .jars

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
You just need to create your .yaml files to start deploying your jobs using the SparkApplication category.
Example:
```
$ kubectl apply -f spark-applications-yaml/spark-lyamada.yaml
```
