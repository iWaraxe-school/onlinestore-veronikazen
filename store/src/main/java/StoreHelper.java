import categories.Category;
import org.reflections.Reflections;
import products.Product;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper{

    private Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    public Map<Class<? extends Category>, Integer> createCategoryMap() {
        Map<Class<? extends Category>, Integer> categoryMap = new HashMap<>();
        Reflections reflections = new Reflections("categories");
        Random random = new Random();
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> subType : subTypes) {
            categoryMap.put(subType, random.nextInt(5) + 1);
        }
        return categoryMap;
    }

    public void fillStoreRandomly() {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        Map<Class<? extends Category>,Integer> map = createCategoryMap();
        for (Class<? extends Category> categoryName: map.keySet()) {
            List<Product> productList = new ArrayList<>();
            for (Integer ignored : map.values()) {
                productList.add(new Product(randomStorePopulator.fillName(categoryName),
                        randomStorePopulator.fillRate(), randomStorePopulator.fillPrice()));
            }
            try {
                Class[] params = {String.class, List.class};
                store.setCategoryList(categoryName.getConstructor(params).newInstance(categoryName.getName(),
                        productList));
            }
            catch (NoSuchMethodException | InstantiationException| IllegalAccessException|
                    InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
