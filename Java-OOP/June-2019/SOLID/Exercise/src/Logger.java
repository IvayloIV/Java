import contracts.Appender;
import error.Error;

import java.io.IOException;
import java.util.List;

public class Logger {
    private List<Appender> appenders;

    public Logger(List<Appender> appenders) {
        this.appenders = appenders;
    }

    public void Log(Error error) throws IOException {
        for (Appender appender : appenders) {
            if (appender.getErrorLevel().compareTo(error.getErrorLevel()) <= 0) {
                appender.append(error);
            }
        }
    }
}
