package com.example.parsers.parsers.error;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.err.println("Warning: " + exception);
        System.err.println("Line = " + exception.getLineNumber() + " column = " + exception.getColumnNumber());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.err.println("Error: " + exception);
        System.err.println("Line = " + exception.getLineNumber() + " column = " + exception.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("Fatal error: " + exception);
        System.err.println("Line = " + exception.getLineNumber() + " column = " + exception.getColumnNumber());

    }
}
