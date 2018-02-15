# Deployment

** Requires Cassandra 2.0.2 or greater **
** Requires Java Runtime 1.8.0 or greater **

This module is designed to work with [Pluggable metrics in Cassandra](https://www.datastax.com/dev/blog/pluggable-metrics-reporting-in-cassandra-2-0-2), available after the 2.0.2 release. The JAR is compiled for the 1.8 JRE by default. The [original project](https://github.com/hengyunabc/metrics-kafka) was designed for JRE 1.6. It may be possible to downgrade to that version of the JRE but your mileage may vary.

## Instructions

1. Set variables for your Cassandra `conf/` and `lib/` directories. You can verify the location of directories by checking their contents. `conf/` should contain the file `cassandra.yaml`. `lib/` should contain the file `apache-cassandra-<version>.jar`.
```bash
export CASSANDRA_CONF=<your path here>
export CASSANDRA_LIB=<your path here>
```

2. Download the `metrics-kafka-0.0.2.jar` from the Maven Central Repository here: [https://oss.sonatype.org/content/repositories/releases/com/vorstella/metrics-kafka/0.0.2/metrics-kafka-0.0.2.jar](https://oss.sonatype.org/content/repositories/releases/com/vorstella/metrics-kafka/0.0.2/metrics-kafka-0.0.2.jar)
```bash
curl -o /tmp/metrics-kafka-0.0.2.jar https://oss.sonatype.org/content/repositories/releases/com/vorstella/metrics-kafka/0.0.2/metrics-kafka-0.0.2.jar
```

3. Verify that the MD5 checksum of the metrics-kafka JAR is `ce88a2743e26d47dedb53d729bd7bf8e`
```bash
md5sum /tmp/metrics-kafka-0.0.2.jar
```

4. Move the metrics-kafka JAR file to the `lib/` directory.
```bash
mv /tmp/metrics-kafka-0.0.2.jar "${CASSANDRA_LIB}"
```

5. Obtain and edit the sample metrics configuration file. 
```bash
curl -o /tmp/metrics.yaml https://github.com/vorstella/metrics-kafka/blob/master/metrics.yaml.sample
$EDITOR /tmp/metrics.yaml
```
  1. Edit the regex patterns under the "white" and "black" `predicate:` entries to adjust which metrics are whitelisted and blacklisted, respectively. 
  2. Set the host and port for the Kafka broker
  ```bash
       hosts:
       - host: 'kafka.mydomain.com'
         port: 9092
  ```

6. Move metrics.yaml to the `conf/` directory.
```bash
mv /tmp/metrcs.yaml "${CASSANDRA_CONF}"
```

7. Edit "${CASSANDRA_CONF}"/jvm.options to indicate the location of the metrics configuration file.
- Before:
```bash
 #-Dcassandra.load_ring_state=true|false
 
 # Enable pluggable metrics reporter. See Pluggable metrics reporting in Cassandra 2.0.2.
 #-Dcassandra.metricsReporterConfigFile=file
 
 # Set the port on which the CQL native transport listens for clients. (Default: 9042)
 #-Dcassandra.native_transport_port=port
```
- After:
```bash
 #-Dcassandra.load_ring_state=true|false
 
 # Enable pluggable metrics reporter. See Pluggable metrics reporting in Cassandra 2.0.2.
-Dcassandra.metricsReporterConfigFile=metrics.yaml
 
 # Set the port on which the CQL native transport listens for clients. (Default: 9042)
 #-Dcassandra.native_transport_port=port
```

8. Restart Cassandra
