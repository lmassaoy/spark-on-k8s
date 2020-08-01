#!/bin/bash
echo "building new image for spark:latest"
eval $(minikube docker-env)
docker build . -t spark:latest
echo "image built"