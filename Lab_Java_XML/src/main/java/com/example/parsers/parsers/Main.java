package com.example.parsers.parsers;

import com.example.parsers.parsers.parser.DomParser;
import com.example.parsers.parsers.parser.IValidator;
import com.example.parsers.parsers.parser.SaxParser;
import com.example.parsers.parsers.parser.XMLInputParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final IValidator validator = new SaxParser();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        String document = null;

        System.out.println("1 - DTD validation\n2 - XSD validation");
        Scanner scn = new Scanner(System.in);
        int choise = scn.nextInt();
        switch (choise) {
            case 1:
                document = "data/dataForDTD.xml";
                break;
            case 2:
                document = "data/dataForXSD.xml";
                break;
            default:
                System.out.println("Request was bad .. Bye :)");
                break;
        }

        if (choise == 2 || choise == 1) {
            validator.parseDocument(document);

        }
    }

}


