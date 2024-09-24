# Spring Boot Sample Service
This contains a very basic sample service for us to deploy in the K8s cluster. It handles port 8080 GET requests to `/ping` and responds with `pong`.

This service is written in Kotlin using Spring Boot Starter, built with Gradle Kotlin DSL, and dockerized with corretto.

## Installation
Clone this repository and load with Gradle Kotlin.

We are using:
- Kotlin 1.9.25
- JVM 17 corretto
- Gradle 8.10

## Build
To build to your local docker registry, run `docker build -t k8s-demo-spring-boot-sample-service .`

However, this homelab runs a private docker registry within Kubernetes for us to utilize for our pods. To push to this registry, ensure your K8s cluster is running, and then execute 

```
docker -t docker.int.kailo.ca/k8s-demo-spring-boot-sample-service:latest .
```
We can now run this image on our K8s pods.