#!/bin/bash
echo "creating service account and rolebind"
kubectl create serviceaccount spark
kubectl create clusterrolebinding spark-role --clusterrole=edit  --serviceaccount=default:spark --namespace=default