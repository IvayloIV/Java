package softuni.workshop.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilImpl implements FileUtil {
    private static final String INPUT_BASE_PATH = "src/main/resources/files/xmls/";

    public String readContent(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();

        FileReader fileReader = new FileReader(INPUT_BASE_PATH + fileName + ".xml");
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
