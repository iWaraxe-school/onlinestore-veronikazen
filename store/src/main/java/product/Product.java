package product;

public class Product {

    private String name;
    private int rate;
    private double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n" + "name='" + name + '\'' +
                ", rate=" + rate +
                ", price=" + price;
    }
}
