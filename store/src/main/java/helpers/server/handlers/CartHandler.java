package helpers.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.OrderHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class CartHandler  implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of product to order:");
        String productName = scanner.nextLine();
        OrderHelper.getInstance().createOrder(productName);
        byte[] response = String.valueOf(OrderHelper.getInstance().purchasedGoods).getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream out = exchange.getResponseBody();
        out.write(response);
        out.close();
    }

}