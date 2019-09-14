package softuni.workshop.util;

import java.io.IOException;

public interface GsonUtil {
    <T> void saveJSONtoFile(String fileName, T[] items) throws IOException;
}
