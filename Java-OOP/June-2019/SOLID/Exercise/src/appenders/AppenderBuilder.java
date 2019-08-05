package appenders;

import contracts.Appender;
import contracts.Layout;
import error.ErrorLevel;
import layouts.LayoutBuilder;

import java.io.IOException;

public class AppenderBuilder {
    public static Appender create(String... args) throws IOException {
        String type = args[0];
        Layout layout = LayoutBuilder.create(args[1]);
        String error = "INFO";
        if (args.length > 2) {
            error = args[2];
        }

        ErrorLevel errorLevel = ErrorLevel.valueOf(error);

        switch (type) {
            case "ConsoleAppender":
                return new ConsoleAppender(layout, errorLevel);
            case "FileAppender":
                return new FileAppender(layout, errorLevel);
            default:
                throw new IllegalArgumentException("Invalid appender type!");
        }
    }
}
