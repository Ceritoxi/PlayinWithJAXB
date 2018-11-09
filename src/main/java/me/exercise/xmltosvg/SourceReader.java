package me.exercise.xmltosvg;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xmlgenerated.Zips;

public class SourceReader {

    public Zips readXmlSource(String sourceName) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Zips.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        return (Zips) unmarshaller.unmarshal(new File(sourceName));
    }

}
