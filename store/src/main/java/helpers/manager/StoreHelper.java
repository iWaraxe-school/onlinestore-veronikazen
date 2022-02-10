package helpers.manager;

import categories.Category;
import helpers.OrderHelper;
import helpers.TimerCleanupTask;
import helpers.XmlReader;
import helpers.comparator.ProductComparator;
import helpers.comparator.SortOrder;
import org.xml.sax.SAXException;
import products.Product;
import store.RandomStorePopulator;
import store.Store;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper extends Manager{

    @Override
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

    @Override
    public void manageStore() throws ParserConfigurationException, IOException, SAXException {
        fillStoreRandomly();
        StoreHelper storeHelper = new StoreHelper();
        Store.getInstance().printAllCategories();

        TimerCleanupTask.getInstance().cleanPurchasedGoods();

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter operation: sort/top5 by price/create order/quit");
            String operation = scanner.nextLine();
            switch (operation) {
                case "sort":
                    System.out.println(storeHelper.sortProductList());
                    break;
                case "top5 by price":
                    System.out.println(storeHelper.getTopViaPriceDesc());
                    break;
                case "quit":
                    System.out.println("Successful quit");
                    flag = false;
                    break;
                case "create order":
                    System.out.println("Enter name of product to order:");
                    String productName = scanner.nextLine();
                    OrderHelper.getInstance().createOrder(productName);
                    break;
                default:
                    System.out.println("Unidentified operation");
            }
        }
    }

    public String sortProductList() throws ParserConfigurationException, IOException, SAXException {
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
