package helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XmlReader {

    private static XmlReader xmlReader;

    public static XmlReader getInstance() {
        if (xmlReader==null) {
            xmlReader = new XmlReader();
        }
        return xmlReader;
    }

    public Map<String, String> readSortMethods() throws ParserConfigurationException, IOException, SAXException {
        String filePath = "store/src/main/resources/config.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document =  documentBuilder.parse(new File(filePath));
        Node node = document.getElementsByTagName("sort").item(0);
        NodeList sortCases = node.getChildNodes();
        Element element;
        Map<String, String> sortMap = new LinkedHashMap<>();
        for (int i = 0; i < sortCases.getLength(); i++) {
            if (sortCases.item(i).getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) sortCases.item(i);
                sortMap.put(element.getTagName().toLowerCase(Locale.ROOT), element.getTextContent().
                        toUpperCase(Locale.ROOT));
            }
        }
        return sortMap;
    }
}
