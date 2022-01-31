import helpers.StoreHelper;
import org.xml.sax.SAXException;
import store.Store;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Store onlineStore = Store.getInstance();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStoreRandomly();
        onlineStore.printAllCategories();
        boolean flag = true;
        while (flag) {
            System.out.println("Enter operation: sort/top5 by price/quit");
            Scanner scanner = new Scanner(System.in);
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
                default:
                    System.out.println("Unidentified operation");
            }
        }
    }
}
