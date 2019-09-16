package alararestaurant.util;

import alararestaurant.util.base.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    private static final String INPUT_BASE_PATH = "src/main/resources/files/";

    public String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();

        FileReader fileReader = new FileReader(INPUT_BASE_PATH + fileName);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
