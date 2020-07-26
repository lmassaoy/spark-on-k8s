# setup Spark (it may take several minutes)
$ git clone https://github.com/apache/spark.git
$ cd spark
$ dev/make-distribution.sh -Pkubernetes

# setup and starting minikube
$ minikube start --memory 6144 --cpus 4

# building image for spark
You can execute the file "img_builder.sh" or execute the commands below: 
$ eval $(minikube docker-env)
$ docker build . -t spark:latest

# creating service account and role bind
You can execute the file "k8s_svc_accounts.sh" or execute the commands below: 
$ kubectl create serviceaccount spark
$ kubectl create clusterrolebinding spark-role --clusterrole=edit  --serviceaccount=default:spark --namespace=default

# now you're good to go!
Now you can execute the spark-submit over your k8s cluster (minikube)
