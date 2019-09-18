package hiberspring.util.base;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlParser {
    <T> T importXML(String fileName, Class<T> type) throws JAXBException, IOException;

    <T> void exportXML(String fileName, T item) throws JAXBException, IOException;
}
