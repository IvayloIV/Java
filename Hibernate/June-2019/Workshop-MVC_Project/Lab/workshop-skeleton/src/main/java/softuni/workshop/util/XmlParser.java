package softuni.workshop.util;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlParser {
    <T> T importXML(String fileName, Class<T> type) throws JAXBException, IOException;
}
