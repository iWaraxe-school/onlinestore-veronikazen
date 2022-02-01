package helpers.operations;

import helpers.comparator.ProductComparator;
import helpers.comparator.SortOrder;
import org.xml.sax.SAXException;
import products.Product;
import store.Store;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class TopByPriceDescOperation extends Operation {

    @Override
    public boolean checkOperation(String command) throws ParserConfigurationException, IOException, SAXException {
        if (command.equalsIgnoreCase("top5 by price")){
            printOutput();
        }
        return checkNext(command);
    }

    @Override
    public String operationOutput(){
        List<Product> allProductList = Store.getInstance().getProductList();
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("price", SortOrder.DESC.toString());
        Collections.sort(allProductList, new ProductComparator(sortMap));
        return new ArrayList<>(allProductList.subList(0,5)).toString().replaceAll("\\[|\\]|, ", "");
    }

    @Override
    public void printOutput() {
        System.out.println(operationOutput());
    }
}
