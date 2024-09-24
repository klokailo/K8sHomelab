
# Kai's K8s Homelab
Welcome to my Kubernetes homelab, the basis for experimenting with new technologies in my own private cluster.

The (public) elements of this Homelab include:
- [Jenkins](https://www.jenkins.io/) service for running pipelines
- Private [docker](https://www.docker.com/) registry
- Customizable [ingress](https://docs.nginx.com/nginx-ingress-controller/) throughout the cluster
- Configuration for DEV and PRD environments
- A sample service for testing ingress across our cluster

Experiments running within my cluster that aren't exposed on my public GitHub portfolio include:
- Service mesh injection using [Istio](https://istio.io/) for services
- [Prometheus](https://prometheus.io/) + [Grafana](https://grafana.com/) for exposing metrics (soon to be publicized)
- Custom terraform scripts for deploying to my AWS
- [Cilium](https://cilium.io/) for cloud native network observability, debugging
- MLOps workloads

Soon to come:
- [ArgoCD](https://argoproj.github.io/cd/) deployment for observing the cluster
- More K8s node configurations

## Background
This cluster was initially setup to run on [minikube](https://minikube.sigs.k8s.io/docs/) and [k3s](https://k3s.io/), but now has been generalized to run on Kubeadm for multi-node configurations.

The primary purpose of this configuration is for me to freely run MLOps experiment workloads and other personal projects within my cluster. I have exposed just the barebones configuration for the homelab in this repo.

## Setup
We use [kustomize](https://kustomize.io/) to stack Kubernetes manifests. Recommended tool for testing setup is minikube (or alternatively k3s), using a docker driver. Then, you can use `kubectl apply -k .` to apply my stack of kustomization.

Currently, cluster observability is done through any external tool like [OpenTelemetry](https://opentelemetry.io/) or [K9s](https://k9scli.io/).

This homelab is also configured to use subdomains of kailo.ca, a domain that I own. All private tunnels for this domain start with int.kailo.ca. This way, we can ensure HTTPS/TLS traffic using [LetsEncrypt](https://letsencrypt.org/) certificates.

## Components

### Basic Nginx Ingress
The `ingress` folder has a kustomization stack for creating an ingress admission controller using nginx. Each deployment should have its own set of ingress rules and permissions.

### Devops Tools
#### Jenkins
The jenkins interface is exposed privately at jenkins.int.kailo.ca. Configuration of pipelines is done through Jenkinsfiles in their respective deployments.

#### Docker Registry
A private docker registry for images that is directly accessible within our cluster is exposed privately at docker.int.kailo.ca. Private authentication is required to push to this registry, but images can be pulled from it within this cluster.

### Spring Boot Sample Service
This contains a very basic sample service for us to deploy in the K8s cluster. It handles port 8080 GET requests to `/ping` and responds with `pong`.

This service is written in [Kotlin](https://kotlinlang.org/) using [Spring Boot](https://spring.io/projects/spring-boot) Starter, built with Gradle Kotlin DSL, and dockerized with corretto.

#### Installation
Clone this repository and load with Gradle Kotlin.

We are using:
- Kotlin 1.9.25
- JVM 17 corretto
- Gradle 8.10

#### Build
To build to your local docker registry, run `docker build -t k8s-demo-spring-boot-sample-service .` This project does not currently have its own Jenkins pipeline.

However, this homelab runs a private docker registry within Kubernetes for us to utilize for our pods. To push to this registry, ensure your K8s cluster is running, and then execute 
```
docker -t docker.int.kailo.ca/k8s-demo-spring-boot-sample-service:latest .
```
We can now run this image on our K8s pods.
