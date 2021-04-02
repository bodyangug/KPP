package com.example.parsers.parsers.parser;

import com.example.parsers.parsers.error.MyErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

public class DomParser implements IValidator {

    @Override
    public void parseDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        ErrorHandler handler = new MyErrorHandler();
        builder.setErrorHandler(handler);

        Document document = builder.parse(new FileInputStream(path)); //loaded into memory

        Element element = document.getDocumentElement();
        printElements(element.getChildNodes());
    }

    private void printElements(NodeList nodeList) {

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                System.out.println(((Element) nodeList.item(i)).getTagName());

                if (nodeList.item(i).hasChildNodes()) {
                    printElements(nodeList.item(i).getChildNodes());
                }
            }
        }
    }

}
