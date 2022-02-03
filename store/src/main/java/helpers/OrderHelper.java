package helpers;

import lombok.SneakyThrows;
import order.Order;
import order.OrderCleaner;
import products.Product;
import store.Store;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderHelper {

    public CopyOnWriteArrayList<Order> purchasedGoods = new CopyOnWriteArrayList<>();
    private static OrderHelper orderHelper;

    private OrderHelper() {
    }

    public static OrderHelper getInstance() {
        if (orderHelper==null) {
            orderHelper = new OrderHelper();
        }
        return orderHelper;
    }

    @SneakyThrows
    public Product selectProductByName(List<Product> productList, String productName) {
        Product selectedProduct = null;
        for (Product product : productList) {
            if (productName.equalsIgnoreCase(product.getName())) {
                selectedProduct = product;
            }
        }
        return selectedProduct;
    }

    public void createOrder(String productName) {
        Order newOrder = new Order(selectProductByName(Store.getInstance().getProductList(), productName));
        new Thread(newOrder).start();
        addOrderToPurchasedGoods(newOrder);
    }

    public void addOrderToPurchasedGoods(Order order) {
        purchasedGoods.add(order);
    }

    public void cleanPurchasedGoods(CopyOnWriteArrayList<Order> purchasedGoods) {
        Timer timer = new Timer();
        timer.schedule(new OrderCleaner(), 120000);
    }
}
