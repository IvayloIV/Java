package layouts;

import contracts.Layout;
import error.Error;

public class SimpleLayout implements Layout {
    private static final String format = "%s - %s - %s";

    @Override
    public String Format(Error e) {
        return String.format(format,
                e.getDateTime(),
                e.getErrorLevel().name(),
                e.getMessage());
    }
}
