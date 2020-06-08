# welcome
Spring Boot mvc + thymleaf with cloud config

# build

run the following to build a docker image

```shell
mvn buildnumber:create docker:build
```

if you want to use another registry just provide `docker.registry` maven parameter

```shell
mvn buildnumber:create docker:build -Ddocker.registry=https://myregistry.lo
```

# deployment
It is supposed to be deployed on kubernetes using the respective `welcome-manifests` repository.
Main k8s resources:
- A config server connected to the respective `welcome-configuration` repository
- A deployment `welcome-app`
- A service (either NodePort or not depending in ingress/istio below)
- Either an istio VirtualService / Gateway to expose the application outside of k8s
- Or an ingress rule to expose the application outside of k8s

The exposure to outside k8s will depend on whether ingress is working on minikube

# runtime

It uses (if found) a config server:
- at `http://welcome-config-server:8888` with 
- application name `welcome-app`
- profile `default` and 
- label `master`.

If not found environment is assumed to be `DEFAULT`.

## Proxy / ReverseProxy

Optionally an apache2 or nginx proxy is required to pass the hostname to k8s so that ingress/istio rules apply.
If none is provided then the service should be exposed via a NodePort outside k8s

If order for the proxy to work we also need an alias on `/etc/hosts` of the hostname with the k8s cluster IP, i.e.
```
192.168.90.101 welcome-app.labros.local
```

