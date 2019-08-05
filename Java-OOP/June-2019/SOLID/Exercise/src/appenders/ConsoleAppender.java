package appenders;

import contracts.Layout;
import error.Error;
import error.ErrorLevel;

public class ConsoleAppender extends Appender {
    private int messageCount;
    private Layout layout;
    private ErrorLevel errorLevel;

    public ConsoleAppender(Layout layout, ErrorLevel errorLevel) {
        this.layout = layout;
        this.errorLevel = errorLevel;
    }

    @Override
    public int getMessageCount() {
        return this.messageCount;
    }

    @Override
    public Layout getLayout() {
        return this.layout;
    }

    @Override
    public ErrorLevel getErrorLevel() {
        return this.errorLevel;
    }

    @Override
    public void append(Error error) {
        String text = this.layout.Format(error);
        System.out.println(text);
        this.messageCount++;
    }
}
