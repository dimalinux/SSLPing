# SSLPing

Java SSL client to test for valid SSL handshake.  I wanted a client in Java, so it verifies using
the signing authorities trusted by your Java instance.  I also wanted a client that does not assume
that the remote server is an HTTPS server (i.e. it can be running any protocol
over TLS/SSL).


##  Build the project
```
$ ./gradlew clean build
```

## Example Use

### Run against a known good certificate
```
$ java -jar build/libs/SSLPing.jar www.google.com 443
About to connect to 'www.google.com' on port 443
Successfully connected
```

### Run against a known bad certificate
```
$ java -jar build/libs/SSLPing.jar testssl-expire.disig.sk 443
javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException:
    PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
        unable to find valid certification path to requested target
	at sun.security.ssl.Alerts.getSSLException(Alerts.java:192)
...
```

### Run against a server port that's not using SSL
```
$ java -jar build/libs/SSLPing.jar www.yahoo.com 80
About to connect to 'www.yahoo.com' on port 80
javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?
```