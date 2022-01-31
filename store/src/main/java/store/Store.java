package store;

import categories.Category;
import products.Product;
import java.util.*;

public class Store {

    private static Store store;
    private List<Category> categoryList;

    private Store() {
        categoryList = new ArrayList<>();
    }

    public static Store getInstance() {
        if (store==null) {
            store = new Store();
        }
        return store;
    }

    public <T extends Category> void setCategoryList(T obj) {
        categoryList.add(obj);
    }

    public void printAllCategories() {
        System.out.println(categoryList.toString().replaceAll("(\\[|\\]|, )", ""));
    }

    public List<Product> getProductList() {
        List<Product> listOfAllProducts = new ArrayList<>();
        for (Category category : this.categoryList) {
            listOfAllProducts.addAll(category.getProductList());
        }
        return listOfAllProducts;
    }
}
