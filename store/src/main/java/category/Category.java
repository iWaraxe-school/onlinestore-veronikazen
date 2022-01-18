package category;

import product.Product;
import java.util.List;

public abstract class Category {

    private String name;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Category name:'" + name + '\'' + "\n" +
                "Product List:" + productList.toString().replaceAll("(\\[|\\])", "") + "\n";
    }
}
