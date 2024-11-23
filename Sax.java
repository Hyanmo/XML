import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MySAXHandler extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Début de document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin de document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("A")) {
            System.out.println("Début élément A");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("A")) {
            System.out.println("Fin élément A");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
        if (!text.isEmpty()) {
            System.out.println("Texte: " + text);
        }
    }

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MySAXHandler handler = new MySAXHandler();
            saxParser.parse("path/to/your/xmlfile.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
