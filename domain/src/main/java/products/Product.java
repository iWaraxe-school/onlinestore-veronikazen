package products;

public class Product {

    public String name;
    public int rate;
    public double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                "; rate=" + rate +
                "; price=" + price + "\n";
    }
}
