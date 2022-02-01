package helpers.operations;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class Operation {

    private Operation nextOperation;

    public Operation linkWith(Operation nextOperation) {
        this.nextOperation = nextOperation;
        return nextOperation;
    }

    public abstract boolean checkOperation(String command) throws ParserConfigurationException, IOException,
            SAXException;

    protected boolean checkNext(String command) throws ParserConfigurationException, IOException, SAXException {
        if (nextOperation == null) {
            return true;
        }
        return nextOperation.checkOperation(command);
    }

    public abstract String operationOutput() throws ParserConfigurationException, IOException, SAXException;

    public abstract void printOutput() throws ParserConfigurationException, IOException, SAXException;

    public static boolean state(String command) {
        return !command.equalsIgnoreCase("quit");
    }
}
