package error;

public class Error {
    private String dateTime;
    private String message;
    private ErrorLevel errorLevel;

    public Error(String dateTime, String message, ErrorLevel errorLevel) {
        this.dateTime = dateTime;
        this.message = message;
        this.errorLevel = errorLevel;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }
}
