package helpers.manager;

import categories.Category;
import org.reflections.Reflections;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class Manager {

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

    public static Manager createStoreByType(String type) {
        if (type.equalsIgnoreCase("DataBase store")) {
            return new DataBaseManager();
        } else if (type.equalsIgnoreCase("App store")) {
            return new StoreHelper();
        } else if (type.equalsIgnoreCase("Http store")) {
            return new HttpManager();
        }
        else throw new RuntimeException(type + "is unknown store type");
    }

    public abstract void fillStoreRandomly() throws SQLException, ClassNotFoundException;

    public abstract void manageStore() throws ParserConfigurationException, IOException, SAXException, SQLException,
            ClassNotFoundException;

}