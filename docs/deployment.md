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

2. Download the JARs required for this project. These jars include three Open Source JARs which have been modified by Vorstella to enable reporting metrics to Kafka. These are: `reporter-config-base-3.0.3.jar`, `reporter-config3-3.0.3.jar` and `metrics-kafka-0.0.1.jar`. Each of these jars should be downloaded with `curl` from the Maven Central Repository.

  1. reporter-config-base-3.0.3.jar
  ```bash
  curl -o /tmp/reporter-config-base-3.0.3.jar https://oss.sonatype.org/content/repositories/releases/com/vorstella/reporter-config-base/3.0.3/reporter-config-base-3.0.3.jar
  ```
  2. reporter-config3-3.0.3.jar
  ```bash
  curl -o /tmp/reporter-config3-3.0.3.jar https://oss.sonatype.org/content/repositories/releases/com/vorstella/reporter-config3/3.0.3/reporter-config3-3.0.3.jar
  ```
  3. metrics-kafka-0.0.1.jar
  ```bash
  curl -o /tmp/metrics-kafka-0.0.1.jar https://oss.sonatype.org/content/repositories/releases/com/vorstella/metrics-kafka/0.0.1/metrics-kafka-0.0.1.jar
  ```

3. Verify that the MD5 checksum of the JAR files
| jar file | md5 checksum |
|----------|--------------|
| reporter-config-base-3.0.3.jar | 535185501668738dc24e22c8dbc019b0 |
| reporter-config3-3.0.3.jar | 22c50df6741ebcb0900cfa44d1da423a |
| metrics-kafka-0.0.1.jar | af26d28ed10fb43d12de4c9d29a1b8b7 |

4. Remove existing versions of the reporter-config JARs
```bash
rm "${CASSANDRA_LIB}/reporter-config*"
```

5. Move the JAR file to the `lib/` directory.
```bash
mv /tmp/reporter-config-base-3.0.3.jar /tmp/reporter-config3-3.0.3.jar /tmp/metrics-kafka-0.0.1.jar "${CASSANDRA_LIB}"
```

6. Obtain and edit the sample metrics configuration file. 
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

7. Define EXTRA_CLASSPATH and EXTRA_JVM_OPTS for the user running the Cassandra startup script. If the user is `cassandra`, edit the file /home/cassandra/.bash_profile to include these lines:
```bash
export EXTRA_CLASSPATH=<path to Cassandra lib/>/metrics.yaml
export JVM_EXTRA_OPTS='-Dcassandra.metricsReporterConfigFile=metrics.yaml'
```

8. Restart Cassandra
