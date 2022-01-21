import categories.Category;
import java.util.*;

public class Store {

    private List<Category> categoryList;

    public Store() {
        categoryList = new ArrayList<>();
    }

    public <T extends Category> void setCategoryList(T obj) {
        categoryList.add(obj);
    }

    public void printAllCategories() {
        System.out.println(categoryList.toString().replaceAll("(\\[|\\]|, )", ""));
    }
}
