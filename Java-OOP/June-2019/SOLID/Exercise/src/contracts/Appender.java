package contracts;

import error.Error;
import error.ErrorLevel;

import java.io.IOException;

public interface Appender {
    public abstract int getMessageCount();
    public abstract Layout getLayout();
    public abstract ErrorLevel getErrorLevel();
    public abstract void append(Error error) throws IOException;
}
