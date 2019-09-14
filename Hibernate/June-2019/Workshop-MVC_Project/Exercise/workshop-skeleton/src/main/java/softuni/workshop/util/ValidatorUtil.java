package softuni.workshop.util;

import java.util.List;

public interface ValidatorUtil {
    <T> List<String> getViolationsMessage(T item);
}
