package to.noc.sslping;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 *  Simple tool to test an SSL handshake to a remote server that does not assume that
 *  the remote end is an HTTP(S) server.
 */
public class SSLPing {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar SSLPing.jar <host> <port>");
            System.exit(1);
        }
        try {

            String hostname = args[0];
            int port = Integer.parseInt(args[1]);

            System.out.println("About to connect to '" + hostname + "' on port " + port);

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);

            // Hostname verification is not done by default in Java with raw SSL connections.
            // The next 3 lines enable it.
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            sslsocket.setSSLParameters(sslParams);

            sslsocket.startHandshake();

            System.out.println("Successfully connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
