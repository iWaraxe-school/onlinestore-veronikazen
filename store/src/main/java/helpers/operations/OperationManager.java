package helpers.operations;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class OperationManager {

    public Operation setConnection() throws ParserConfigurationException, IOException,
            SAXException {
        Operation exactOperation = new XmlOperation();
        exactOperation.linkWith(new TopByPriceDescOperation()).linkWith(new UnknownOperation()).
                linkWith(new QuitOperation());
        return exactOperation;
    }

    public void selectOperation() throws ParserConfigurationException, IOException, SAXException,
            NoSuchMethodException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter operation: sort/top5 by price/quit");
            String command = scanner.nextLine();
            setConnection().checkOperation(command);
            flag = Operation.state(command);
        }
    }
}
