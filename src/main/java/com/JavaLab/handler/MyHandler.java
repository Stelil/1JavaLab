package com.JavaLab.handler;

import java.util.ArrayList;
import java.util.List;

import com.JavaLab.Human;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

    private List<Human> empList = null;
    private Human emp = null;
    private StringBuilder data = null;

    public List<Human> getEmpList() {
        return empList;
    }

    boolean bBrd = false;
    boolean bName = false;
    boolean bGender = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            emp = new Human();
            if (empList == null)
                empList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("Brd")) {
            bBrd = true;
        } else if (qName.equalsIgnoreCase("Sex")) {
            bGender = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bBrd) {
            LocalDate inputDate;
            inputDate = LocalDate.parse(data.toString(), DateTimeFormat.forPattern("yyyy-mm-dd"));
            emp.setBrd(inputDate);
            bBrd = false;
        } else if (bName) {
            emp.setName(data.toString());
            bName = false;
        } else if (bGender) {
            emp.setSex(data.toString());
            bGender = false;
        }

        if (qName.equalsIgnoreCase("student")) {
            empList.add(emp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
