package app.ccb.util;


import app.ccb.util.base.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    public String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();

        FileReader fileReader = new FileReader("src/main/resources/files/" + fileName);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
