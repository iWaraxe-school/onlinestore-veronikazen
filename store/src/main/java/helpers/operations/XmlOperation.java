package helpers.operations;

import helpers.XmlReader;
import helpers.comparator.ProductComparator;
import org.xml.sax.SAXException;
import products.Product;
import store.Store;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class XmlOperation extends Operation {

    @Override
    public boolean checkOperation(String command) throws ParserConfigurationException, IOException, SAXException {
        if (command.equalsIgnoreCase("sort")){
            printOutput();
        }
        return checkNext(command);
    }

    @Override
    public String operationOutput() throws ParserConfigurationException, IOException, SAXException {
        List<Product> allProductList = Store.getInstance().getProductList();
        Collections.sort(allProductList, new ProductComparator(XmlReader.getInstance().readSortMethods()));
        return allProductList.toString().replaceAll("\\[|\\]|, ", "");
    }

    @Override
    public void printOutput() throws ParserConfigurationException, IOException, SAXException {
        System.out.println(operationOutput());
    }
}
