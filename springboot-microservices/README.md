## What were utilized in this project?


```mermaid
flowchart TB
    A[REACT / CLIENT] --> API
    G[Github]
    SR[Service Registry]
    R[Rabbit MQ]
    API --> microservices
    call[call /burstRefresh] --> RabbitMQ --> microservices
    

    subgraph EC[services]
    API[API GAETWAY]
    CF[Config Server]
    microservices
    end

    subgraph microservices
    E[EMPLOYEE / mysql] ~~~ D[DEPARTMENT / mysql]
    E ~~~ O[ORGANIZATION / mysql]
    end

    microservices --> |GET CONFIG of Each Microservice|CF
    CF --> G

    EC --> a([Register as Eureka Client]) --> SR

    EC --> b([Tracing]) --> Z[Zipkin Server]

```




## Running 
---

Order of Microservices Initialization
1. zipkin & rabbitmq
2. Service Registry
3. Config Server
4. Microservices(employee, department, organization)

config server(github repo): https://github.com/maxkangdev/config-server-git

### Start rabbitmq server(docker)
```
docker run --rm -it -p 5672:5672 rabbitmq:3.12.12
```

### Start zipkin server(docker)
```
docker run --rm -it --name zipkin -p 9411:9411 openzipkin/zipkin
```

