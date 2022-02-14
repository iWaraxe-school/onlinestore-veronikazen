import helpers.manager.Manager;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException,
            SQLException, ClassNotFoundException {
        System.out.println("Please, enter store type (DataBase store/App store/Http store):");
        Scanner scanner = new Scanner(System.in);
        String storeType = scanner.nextLine();
        Manager manager = Manager.createStoreByType(storeType);
        manager.manageStore();
    }
}
