package to.noc.sslping;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.io.OutputStream;


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
            sslsocket.setTcpNoDelay(true); // we only send 1 byte, don't buffer

            InputStream in = sslsocket.getInputStream();
            OutputStream out = sslsocket.getOutputStream();

            // Write a test byte to get a reaction :)
            out.write(1);

            while (in.available() > 0) {
                System.out.print(in.read());
            }
            System.out.println("Successfully connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
