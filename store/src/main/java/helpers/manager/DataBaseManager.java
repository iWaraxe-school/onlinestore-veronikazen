package helpers.manager;

import categories.Category;
import store.RandomStorePopulator;
import java.sql.*;
import java.util.Map;

public class DataBaseManager extends Manager{

    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:database";
    public static final String CATEGORY_TABLE_FIELDS_DESCRIPTION = "CategoryName varchar(50)";
    public static final String PRODUCT_TABLE_FIELDS_DESCRIPTION = "CategoryName varchar(50), ProductName varchar(50), " +
            "Rate int, Price double";
    public static Connection connection;
    public static Statement statement;

    public static Connection setConnection() throws ClassNotFoundException, SQLException {
        Class.forName (JDBC_DRIVER);
        return connection = DriverManager.getConnection (DB_URL);
    }

    public void createTable(String tableName, String fields) throws SQLException, ClassNotFoundException {
        statement = setConnection().createStatement();
        String insertField = String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, fields);
        statement.executeUpdate(insertField);
    }

    public void insertFieldIntoTable(String tableName, String values) throws SQLException,
            ClassNotFoundException {
        statement = setConnection().createStatement();
        String insertField = String.format("INSERT INTO %s VALUES (%s);", tableName, values);
        statement.executeUpdate(insertField);
    }

    @Override
    public void fillStoreRandomly() throws SQLException, ClassNotFoundException {
        createTable("CATEGORY_LIST", CATEGORY_TABLE_FIELDS_DESCRIPTION);
        createTable("PRODUCT_LIST", PRODUCT_TABLE_FIELDS_DESCRIPTION);
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        Map<Class<? extends Category>, Integer> map = createCategoryMap();
        for (Class<? extends Category> categoryName : map.keySet()) {
            insertFieldIntoTable("CATEGORY_LIST", "'" + categoryName + "'");
            for (Integer ignored : map.values()) {
                insertFieldIntoTable("PRODUCT_LIST", "'" + categoryName + "', '" +
                        randomStorePopulator.fillName(categoryName) + "', " + randomStorePopulator.fillRate() + ", " +
                        randomStorePopulator.fillPrice());
            }
        }
    }

    @Override
    public void manageStore() throws SQLException, ClassNotFoundException {
        fillStoreRandomly();
    }

}
