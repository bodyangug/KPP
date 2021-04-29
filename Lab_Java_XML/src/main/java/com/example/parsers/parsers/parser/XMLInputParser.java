package com.example.parsers.parsers.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLInputParser implements IValidator {
    @Override
    public void parseDocument(String documentPath) throws IOException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(documentPath));
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    System.out.println(parser.getLocalName());
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


    }
}
