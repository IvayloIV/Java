package softuni.workshop.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XmlParserImpl implements XmlParser {
    private final String BASE_PATH = "src/main/resources/";
    private final String INPUT_PATH = BASE_PATH + "files/xmls/";

    @Override
    public <T> T importXML(String fileName, Class<T> type) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        try (FileReader fr = new FileReader(INPUT_PATH + fileName + ".xml");
             BufferedReader br = new BufferedReader(fr)) {
            return (T) unmarshaller.unmarshal(br);
        }
    }
}
