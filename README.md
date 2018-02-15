# metrics-kafka
This code is derived from the [Dropwizard Metrics reporter for kafka](https://github.com/dropwizard/metrics). Use of this code is governed by the Apache License V2 included in this repository at [./LICENSE.txt](./LICENSE.txt). This module uses the [Cassandra Pluggable Metrics Reporting](https://www.datastax.com/dev/blog/pluggable-metrics-reporting-in-cassandra-2-0-2) capability to periodically generate metrics records in a Kafka topic.

## Maven configuration

```xml
<dependency>
    <groupId>com.vorstella</groupId>
    <artifactId>metrics-kafka</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Vulnerability Checking

The [dependency-check-maven plugin](https://jeremylong.github.io/DependencyCheck/dependency-check-maven/index.html) has been added to this project to enable checking dependencies against known vulnerabilities cataloged by the [NIST National Vulnerability Database](https://nvd.nist.gov/).

To perform a check, execute this maven goal:

```bash
mvn dependency-check:check
```

## Content

- [CHANGELOG](./CHANGELOG.md)

## License

Apache License V2
