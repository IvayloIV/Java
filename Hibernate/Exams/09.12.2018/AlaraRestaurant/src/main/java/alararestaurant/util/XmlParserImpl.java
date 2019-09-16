package alararestaurant.util;

import alararestaurant.util.base.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {
    private final String BASE_PATH = "src/main/resources/files/";

    @Override
    public <T> T importXML(String fileName, Class<T> type) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        try (FileReader fr = new FileReader(BASE_PATH + fileName + ".xml");
             BufferedReader br = new BufferedReader(fr)) {
            return (T) unmarshaller.unmarshal(br);
        }
    }

    public <T> void exportXML(String fileName, T item) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try (FileWriter fw = new FileWriter(BASE_PATH + fileName + ".xml");
             BufferedWriter bw = new BufferedWriter(fw)) {
            marshaller.marshal(item, bw);
        }
    }
}
