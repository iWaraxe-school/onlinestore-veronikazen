package helpers;

import categories.Category;
import helpers.comparator.ProductComparator;
import helpers.comparator.SortOrder;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import products.Product;
import store.RandomStorePopulator;
import store.Store;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper{

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
                Store.getInstance().setCategoryList(categoryName.getConstructor(params).newInstance(categoryName.getName(),
                        productList));
            }
            catch (NoSuchMethodException | InstantiationException| IllegalAccessException|
                    InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public String sortProductList() {
        List<Product> allProductList = Store.getInstance().getProductList();
        Collections.sort(allProductList, new ProductComparator(XmlReader.getInstance().readSortMethods()));
        return allProductList.toString().replaceAll("\\[|\\]|, ", "");
    }

    public String getTopViaPriceDesc() {
        List<Product> allProductList = Store.getInstance().getProductList();
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("price", SortOrder.DESC.toString());
        Collections.sort(allProductList, new ProductComparator(sortMap));
        return new ArrayList<>(allProductList.subList(0,5)).toString().replaceAll("\\[|\\]|, ", "");
    }

}
