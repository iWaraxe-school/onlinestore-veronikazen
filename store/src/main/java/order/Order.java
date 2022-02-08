package order;

import lombok.SneakyThrows;
import products.Product;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Order implements Runnable{

    private Product purchasedProduct;

    public Order(Product selectedProduct) {
        this.purchasedProduct = selectedProduct;
    }

    @SneakyThrows
    @Override
    public void run() {
        TimeUnit.SECONDS.sleep(new Random().nextInt(30));
    }

    @Override
    public String toString() {
        return "ORDER" +  "\n" +
                "Selected product:" + purchasedProduct;
    }

}
