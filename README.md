# spark-on-k8s
[![Screen-Shot-2020-07-27-at-05-03-34.png](https://i.postimg.cc/DyXB5kWY/Screen-Shot-2020-07-27-at-05-03-34.png)]()

In this repo, I expect to show you a couple of different ways to work with Spark out of a Hadoop Cluster.
Kubernetes clusters are becoming more and more common in all sizes of companies, and use their power to process Spark is attractive.
With this in mind, I'd like to invite you to join me on a journey of learning in a seek of more wide options to do Big Data.

You're about to face 3 ways of running Spark over containers:
- [K8s Cluster](http://spark.apache.org/docs/latest/running-on-kubernetes.html) (as your Spark Master)
- [K8s Spark Operator](https://github.com/GoogleCloudPlatform/spark-on-k8s-operator/blob/master/docs/quick-start-guide.md) (as a native module for Kubernetes)
- [Docker Jupyter Pyspark](https://hub.docker.com/r/jupyter/pyspark-notebook/) (a really nice way to do plenty of adhocs and local experiences).

I deeply hope you to have fun with this experience and get yourself more confident to step outside of your traditional Hadoop cluster :)


## How to set everything up
Click [HERE](https://github.com/lmassaoy/spark-on-k8s/blob/master/devops/setup.md) to follow the step-by-step :)

## So far...
| Mode | Status |
| ------ | ------ |
| K8s: Spark-Submit | OK |
| GCP/spark-on-k8s-operator | OK (currently in Beta) |
| Docker: Jupyter PySpark | OK |

## Architecture
[![Screen-Shot-2020-07-27-at-04-43-52.png](https://i.postimg.cc/13VLcQvX/Screen-Shot-2020-07-27-at-04-43-52.png)]()