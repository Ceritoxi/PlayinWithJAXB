package me.exercise.xmltosvg;

import javax.xml.bind.JAXBException;

public class App {

    public static void main(String[] args) throws JAXBException {
        XmlWorker worker = new XmlWorker();
        worker.readXmlSource("zip-full.xml");
        worker.writeHtmlOutput("svg-out.html");
    }

}
