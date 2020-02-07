package com.testexercise.javajuniorproject.service;

import com.testexercise.javajuniorproject.domain.Currency;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private List<Currency> currencyList = null;
    private Currency currency = null;
    private StringBuilder data = null;

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    private boolean bName = false;
    private boolean bCharCode = false;
    private boolean bNominal = false;
    private boolean bValue = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Valute")) {
            String id = attributes.getValue("ID");

            currency = new Currency();
            currency.setId(id);

            if (currencyList == null)
                currencyList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("CharCode")) {
            bCharCode = true;
        } else if (qName.equalsIgnoreCase("Nominal")) {
            bNominal = true;
        } else if (qName.equalsIgnoreCase("Value")) {
            bValue = true;
        }
        data = new StringBuilder();
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bName) {
            currency.setName(data.toString());
            bName = false;
        } else if (bCharCode) {
            currency.setCharCode(data.toString());
            bCharCode = false;
        } else if (bNominal) {
            currency.setNominal(Integer.parseInt(data.toString()));
            bNominal = false;
        } else if (bValue) {
            currency.setValue(Double.valueOf(data.toString().replace(",",".")));
            bValue = false;
        }

        if (qName.equalsIgnoreCase("Valute")) {
            currencyList.add(currency);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
