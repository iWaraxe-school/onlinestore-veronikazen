package helpers.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.manager.HttpManager;
import store.Store;
import java.io.IOException;
import java.io.OutputStream;

public class BasicHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] response = createHeader().toString().getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream out = exchange.getResponseBody();
        out.write(response);
        out.close();
    }

    public StringBuilder createHeader() {
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>Product list:</h1>");
        new HttpManager().fillStoreRandomly();
        builder.append(formatContext());
        return builder;
    }

    public String formatContext() {
        return Store.getInstance().getCategoryList().toString().
                replaceAll("],", "<br>").
                replaceAll("(\\[|\\]|, )", "").
                replaceAll("Product List", "<br>Product List").
                replaceAll("name=", "<br>name=");
    }
}
