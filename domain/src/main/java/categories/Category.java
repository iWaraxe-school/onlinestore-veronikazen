package categories;

import products.Product;
import java.util.List;

public abstract class Category {

    private String name;
    private List<Product> productList;

    public Category(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "\n" + "Category name:'" + name.replaceAll("categories.", "") + '\'' + "\n" +
                "Product List:" + "\n" +
                productList;
    }
}
