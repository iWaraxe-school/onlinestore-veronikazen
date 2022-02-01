package helpers.operations;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class UnknownOperation extends Operation{

    @Override
    public boolean checkOperation(String command) throws ParserConfigurationException, IOException, SAXException {
        if ((!command.equalsIgnoreCase("sort"))& (!command.equalsIgnoreCase("top5 by price"))&
                !(command.equalsIgnoreCase("quit"))) {
            printOutput();
        }
        return checkNext(command);
    }

    @Override
    public String operationOutput() {
        return "Unidentified operation, try else.";
    }

    @Override
    public void printOutput() {
        System.out.println(operationOutput());
    }
}
