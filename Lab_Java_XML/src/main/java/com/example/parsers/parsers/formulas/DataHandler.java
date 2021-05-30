package com.example.parsers.parsers.formulas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {

    private final Repo repo;
    private boolean date = false;
    private boolean x = false;
    private boolean y = false;

    public DataHandler(Repo repo) {
        this.repo = repo;
    }


    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("data")) {
            String date = attributes.getValue("date");
            System.out.println("date " + date);
        } else if (qName.equalsIgnoreCase("x")) {
            x = true;
        } else if (qName.equalsIgnoreCase("y")) {
            y = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("datasheet")) {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Data data = new Data();

        if (date) {
            System.out.println("date " + new String(ch, start, length));
            data.setDate(new String(ch, start, length));
            date = false;
            repo.add(data);

        } else if (x) {
            System.out.println("x " + new String(ch, start, length));
            data.setX(Double.valueOf(new String(ch, start, length)));

            x = false;
            repo.add(data);

        } else if (y) {
            System.out.println("y " + new String(ch, start, length));
            data.setY(Double.valueOf(new String(ch, start, length)));
            y = false;
            repo.add(data);

            Formula.findKoef(repo);
        }
        //Formula.findKoef(repo);

    }

    @Override
    public void warning(SAXParseException ex) {
        System.err.println("Warning: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }

    @Override
    public void error(SAXParseException ex) {
        System.err.println("Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException ex) {
        System.err.println("Fatal error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }

}
