package alararestaurant.domain.dtos.imports.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, LocalDateTime> {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(v);
        }
    }

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            return LocalDateTime.parse(v, dateFormat);
        }
    }

}