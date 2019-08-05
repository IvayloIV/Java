package appenders;

import contracts.Layout;
import error.Error;
import error.ErrorLevel;

import java.io.IOException;

public class FileAppender extends Appender {
    private int messageCount;
    private Layout layout;
    private ErrorLevel errorLevel;
    private LogFile logFile;


    public FileAppender(Layout layout, ErrorLevel errorLevel) throws IOException {
        this.layout = layout;
        this.errorLevel = errorLevel;
        this.logFile = new LogFile();
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
    public void append(Error error) throws IOException {
        String text = this.layout.Format(error);
        this.logFile.writeToFile(text);
        this.messageCount++;
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: " + this.logFile.getSize();
    }
}
