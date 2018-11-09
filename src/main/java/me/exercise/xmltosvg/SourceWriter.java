package me.exercise.xmltosvg;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import svggenerated.Html;

public class SourceWriter {

    public void writeTheHtmlDocument(Html html, String outName) throws JAXBException, PropertyException {
        File file = new File(outName);
        JAXBContext ctx = JAXBContext.newInstance(Html.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(html, file);
    }

}
