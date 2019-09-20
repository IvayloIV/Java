package app.ccb.util;

import app.ccb.util.base.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {
    private static final String PATH_TO_FILES = "src/main/resources/files/xml/";

    @Override
    public <T> T importXML(String fileName, Class<T> type) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        try (FileReader fr = new FileReader(PATH_TO_FILES + fileName + ".xml");
             BufferedReader br = new BufferedReader(fr)) {
            return (T) unmarshaller.unmarshal(br);
        }
    }

    @Override
    public <T> void exportXML(String fileName, T item) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try (FileWriter fw = new FileWriter(PATH_TO_FILES + fileName + ".xml");
             BufferedWriter bw = new BufferedWriter(fw)) {
            marshaller.marshal(item, bw);
        }
    }
}
