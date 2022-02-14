package helpers.server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public void bootstrap() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8088), 0);
        server.createContext("/online-store", new BasicHandler()).setAuthenticator(new BasicAuthenticator());
        server.start();
    }
}