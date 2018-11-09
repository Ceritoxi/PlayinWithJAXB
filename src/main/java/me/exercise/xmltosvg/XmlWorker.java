package me.exercise.xmltosvg;

import java.math.BigInteger;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import svggenerated.Html;
import svggenerated.Html.Body;
import svggenerated.Html.Body.Svg;
import svggenerated.Html.Body.Svg.Circle;
import xmlgenerated.Zips;
import xmlgenerated.Zips.Record;

public class XmlWorker {

    private static final int RECORD_SCATTER_MAGNITUDE = 10;
    private static final BigInteger DEFAULT_WIDTH = new BigInteger("1700");
    private static final BigInteger DEFAULT_HEIGHT = new BigInteger("800");
    private Zips source;

    public void readXmlSource(String sourceName) throws JAXBException {
        SourceReader sourceReader = new SourceReader();
        source = sourceReader.readXmlSource(sourceName);
    }

    public void writeHtmlOutput(String outName) throws JAXBException {
        checkSource();
        Html html = createBasicHtmlLayout();
        fillTheHtmlFromSource(html);
        writeTheHtmlDocument(html, outName);
    }

    private void fillTheHtmlFromSource(Html html) {
        StateColorer colorer = new StateColorer();
        for (Record record : source.getRecord()) {
            if (necessaryElementsExistInRecord(record)) {
                setSvgElementsFromRecord(html, colorer, record);
            }
        }
    }

    private void checkSource() {
        if (source == null) {
            throw new IllegalStateException("Source is not yet read");
        }
    }

    private void setSvgElementsFromRecord(Html html, StateColorer colorer, Record record) {
        Circle circle = new Circle();
        circle.setCx((double) ((record.getLongitude() * RECORD_SCATTER_MAGNITUDE) + DEFAULT_WIDTH.intValue()));
        circle.setCy((double) (DEFAULT_HEIGHT.intValue() - (record.getLatitude() * RECORD_SCATTER_MAGNITUDE)));
        circle.setFill(colorer.getColorOfState(record.getState()));
        circle.setR((byte) 1);
        addSvgElementToHtml(html, circle);
    }

    private void addSvgElementToHtml(Html html, Circle circle) {
        html.getBody().getSvg().getCircle().add(circle);
    }

    private boolean necessaryElementsExistInRecord(Record record) {
        return record.getLongitude() != null && record.getLatitude() != null && record.getState() != null;
    }

    private void writeTheHtmlDocument(Html html, String outName) throws JAXBException, PropertyException {
        SourceWriter sourceWriter = new SourceWriter();
        sourceWriter.writeTheHtmlDocument(html, outName);
    }

    private Html createBasicHtmlLayout() {
        Svg svg = new Svg();
        svg.setHeight(DEFAULT_HEIGHT);
        svg.setWidth(DEFAULT_WIDTH);
        Body body = new Body();
        body.setSvg(svg);
        Html html = new Html();
        html.setBody(body);
        return html;
    }

}
