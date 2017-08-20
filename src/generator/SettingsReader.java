package generator;


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
import java.util.ArrayList;
import java.util.List;

public class SettingsReader {

    private static final String FILENAME = "settings.xml";

    private int width;
    private int height;
    private String number;
    private String date;
    private String fio;
    private int numberVal;
    private int dateVal;
    private int fioVal;

    List<String> list = new ArrayList<>();

    public void fileReader(){

        final File xmlFile = new File(FILENAME);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            try {
                Document doc = db.parse(FILENAME);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("page");

                for (int i = 0; i < nodeList.getLength(); i++) {

                    Node node = nodeList.item(i);
                    if (Node.ELEMENT_NODE == node.getNodeType()) {
                        Element element = (Element)node;
                        width = Integer.parseInt(element.getElementsByTagName("width")
                                .item(0).getTextContent());
                        height = Integer.parseInt(element.getElementsByTagName("height")
                                .item(0).getTextContent());
                    }

                }

                nodeList = doc.getElementsByTagName("columns");
                NodeList children;
                for (int i = 0; i < nodeList.getLength(); i++){
                    children = nodeList.item(i).getChildNodes();
                    for (int j = 0; j < children.getLength(); j++) {
                        Node childNode = children.item(j);
                        if (Node.ELEMENT_NODE == childNode.getNodeType()) {
                            Element element = (Element)childNode;
                            list.add(element.getElementsByTagName("title")
                                    .item(0)
                                    .getTextContent());
                            list.add(element.getElementsByTagName("width")
                                    .item(0)
                                    .getTextContent());
                        }
                    }
                }

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        number = list.get(0);
        date = list.get(2);
        fio = list.get(4);
        numberVal = Integer.parseInt(list.get(1));
        dateVal = Integer.parseInt(list.get(3));
        fioVal = Integer.parseInt(list.get(5));

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberVal() {
        return numberVal;
    }

    public int getDateVal() {
        return dateVal;
    }

    public int getFioVal() {
        return fioVal;
    }

    @Override
    public String toString() {
        return "SettingsReader{" +
                "width=" + width +
                ", height=" + height +
                ", number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", fio='" + fio + '\'' +
                ", numberVal=" + numberVal +
                ", dateVal=" + dateVal +
                ", fioVal=" + fioVal +
                '}';
    }
}
