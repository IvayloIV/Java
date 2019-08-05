package layouts;

import contracts.Layout;
import error.Error;

public class XmlLayout implements Layout {
    private static final String format = "<log>%n" +
            "<date>%s</date>%n" +
            "<level>%s</level>%n" +
            "<message>%s</message>%n"+
            "</log>";

    @Override
    public String Format(Error e) {
        return String.format(format,
                e.getDateTime(),
                e.getErrorLevel().name(),
                e.getMessage());
    }
}
