# KNOWN ISSUES

## Vulnerabilities Deemed Inconsequential
The dependency-check-maven plugin reports vulnerabilities in dependency libraries. Those vulnerabilities which were found are reported here. Review of these issues has determined that these will not increase the risk of exploit because of the intended deployment context. Details will be included with each vulnerability below.

### netty-3.2.2.Final.jar
- CVE-2015-2156
When used to implement an HTTP server, netty may allow remote hackers to bypass httpOnly flag on cookies, exposing cookies to XSS scripts. No HTTP server is created by this module as part of the operation of metrics-kafka.
- CVE-2014-3488
The SSL handler in netty can cause an infinite loop and DoS when listening for connections. The netty library is not used to listen for SSL connections in metrics-kafka.

### zookeeper-3.4.5.jar
- CVE-2014-0085
Zookeeper logs admin password as cleartext. Zookeeper is used as a server-side component by a Kafka deployment. metrics-kafka does not deploy a Zookeeper server.
- CVE-2016-5017
The Zookeeper CLI can be used to cause a buffer overflow. The Zookeeper CLI is used to manage the Zookeeper server on the node hosting the server. metrics-kafka does not deploy a Zookeeper server. 
- CVE-2017-5637
The Zookeeper CLI commands “wchp" and "wchc” cause can cause intolerable CPU spikes and can be used for DoS. The Zookeeper CLI is used to manage the Zookeeper server on the node hosting the server. metrics-kafka does not deploy a Zookeeper server.  

### jackson-databind-2.4.2.jar
- CVE-2017-17485 and CVE-2018-5968
Object unmarshalling code in jackson-databind can be exploited to coerce arbitrary code execution. No object unmarshalling occurs in metrics-kafka.

### logback-core-1.0.13.jar
- CVE-2017-5929
The logback SocketServer and ServerSocketReceiver components can be used to deserialize arbitrary payloads by an authenticated user. These components are not used in the metrics-kafka implementation.
