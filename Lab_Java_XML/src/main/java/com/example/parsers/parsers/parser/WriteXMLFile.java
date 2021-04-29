package com.example.parsers.parsers.parser;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLFile {
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("notebook");
            doc.appendChild(rootElement);
            Element person = doc.createElement("person");
            rootElement.appendChild(person);
            Element name = doc.createElement("name");
            person.appendChild(name);
            Attr attr = doc.createAttribute("first");
            attr.setValue("Иван");
            name.setAttributeNode(attr);
            attr = doc.createAttribute("second");
            attr.setValue("Иванович");
            name.setAttributeNode(attr);
            attr = doc.createAttribute("surname");
            attr.setValue("Иванов");
            name.setAttributeNode(attr);
            Element birthday = doc.createElement("birthday");
            birthday.appendChild(doc.createTextNode("12.12.2001"));
            person.appendChild(birthday);
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,
                    "Windows-1251");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            doc.setXmlStandalone(true);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("data/save/file.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}