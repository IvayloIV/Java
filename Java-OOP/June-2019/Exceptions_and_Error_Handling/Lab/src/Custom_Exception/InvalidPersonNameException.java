package Custom_Exception;

public class InvalidPersonNameException extends Exception {
    public InvalidPersonNameException(String s) {
        super(s);
    }
}
