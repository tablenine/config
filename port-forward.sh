#!/bin.sh
kubectl port-forward svc/rabbit-cluster-rabbitmq 5672:5672 --namespace rabbit-test &
kubectl port-forward svc/rabbit-cluster-rabbitmq 15672:15672 --namespace rabbit-test &
kubectl port-forward svc/rabbit-cluster-rabbitmq 25672:25672 --namespace rabbit-test &
kubectl port-forward svc/rabbit-cluster-rabbitmq 4369:4369 --namespace rabbit-test &
kubectl port-forward svc/rabbit-cluster-rabbitmq 5552:5552 --namespace rabbit-test &
