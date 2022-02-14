package helpers.manager;

import categories.Category;
import helpers.server.Server;
import products.Product;
import store.RandomStorePopulator;
import store.Store;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpManager extends Manager{

    @Override
    public void fillStoreRandomly() {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        Map<Class<? extends Category>, Integer> map = createCategoryMap();
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
    public void manageStore() throws IOException {
        Server server = new Server();
        server.bootstrap();
        //сделать запрос на сервер
        //в кач-ве респонса получаем данные по продукту
    }
}
