package com.example.parsers.parsers.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IValidator {
    void parseDocument(String documentPath) throws ParserConfigurationException, SAXException, IOException;
}
