package appenders;

public abstract class Appender implements contracts.Appender {
    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s," +
                "Messages appended: %d",
                this.getClass().getSimpleName(),
                this.getLayout().getClass().getSimpleName(),
                this.getErrorLevel().name(),
                this.getMessageCount());
    }
}
