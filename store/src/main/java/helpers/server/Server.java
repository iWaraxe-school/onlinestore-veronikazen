package helpers.server;

import com.sun.net.httpserver.HttpServer;
import helpers.server.handlers.BasicHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private HttpServer server;

    public void bootstrap() throws IOException {
        server = HttpServer.create(new InetSocketAddress("localhost", 8088), 0);
        server.createContext("/online-store", new BasicHandler()).setAuthenticator(new BasicAuthenticator());
        server.start();
    }
}