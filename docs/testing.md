# Testing

## Included End-to-End Test Disabled By Default
Testing via Maven has been turned off in this project by default. The single test implemented by the [original metrics-kafka project](https://github.com/hengyunabc/metrics-kafka) does not exit. It implements an infinite loop, sending mock data to the KafkaReporter periodically so an engineer can inspect the results. This behavior is valuable, but allowing the test to run by default means Maven builds cannot be automated or expected to complete. Instead testing is off. It can be manually invoked by setting `skipTests` to `false` as follows:

```bash
mvn -DskipTests=false package
```

## Kafka Server
A Kafka server is needed to complete the test. Two easy options are:

1. Install a minimal Kafka server using the Kafka Project [quickstart instructions](http://kafka.apache.org/082/documentation.html#quickstart)

2. Deploy the [spotify/kafka](https://hub.docker.com/r/spotify/kafka/) Docker container. This container is nice because it includes Zookeeper in the single-image install.
```bash
docker get spotify/kafka
docker run \
  -d --rm \
  -p 2181:2181 \
  -p 9092:9092 \
  --env ADVERTISED_HOST=<your host IP> \
  --env ADVERTISED_PORT=9092 \
  spotify/kafka
```
