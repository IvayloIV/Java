package alararestaurant.util.base;

import java.io.IOException;

public interface GsonUtil {
    <T> void saveJSONtoFile(String fileName, T[] items) throws IOException;
}
