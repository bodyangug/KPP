package com.example.parsers.parsers.parser;

import com.example.parsers.parsers.formulas.DataHandler;
import com.example.parsers.parsers.formulas.Repo;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SaxParser implements IValidator {

    @Override
    public void parseDocument(String documentPath) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser saxParser = factory.newSAXParser();
        Repo repo = new Repo();
        DefaultHandler handler = new DataHandler(repo);
        InputStream xmlInput = new FileInputStream(documentPath);
        saxParser.parse(xmlInput, handler);

        System.out.println(repo);
    }

}