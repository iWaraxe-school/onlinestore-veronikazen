package helpers.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.manager.HttpManager;
import store.Store;
import java.io.IOException;
import java.io.OutputStream;

public class BasicHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();
        new HttpManager().fillStoreRandomly();
        builder.append(Store.getInstance().getCategoryList().toString());
        byte[] response = builder.toString().getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream out = exchange.getResponseBody();
        out.write(response);
        out.close();
    }

}
