package helpers;

import categories.Category;
import org.reflections.Reflections;
import org.xml.sax.SAXException;
import products.Product;
import store.RandomStorePopulator;
import store.Store;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

    public String sortProductList() throws ParserConfigurationException, IOException, SAXException {
        List<Product> allProductList = store.getProductList();
        Collections.sort(allProductList, new ProductComparator(XmlReader.getInstance().readSortMethods()));
        return allProductList.toString().replaceAll("\\[|\\]|, ", "");
    }

    public String getTopViaPriceDesc() {
        List<Product> allProductList = store.getProductList();
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("price", SortOrder.DESC.toString());
        Collections.sort(allProductList, new ProductComparator(sortMap));
        return new ArrayList<>(allProductList.subList(0,5)).toString().replaceAll("\\[|\\]|, ", "");
    }
}
