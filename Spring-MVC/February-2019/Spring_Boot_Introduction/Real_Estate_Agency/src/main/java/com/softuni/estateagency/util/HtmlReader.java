package com.softuni.estateagency.util;

import java.io.*;
import java.nio.file.Paths;

public class HtmlReader {

    public String reader(String fileName) throws IOException {
        BufferedReader bufferedReader = this.createBufferedReader(fileName);

        String newLine;
        StringBuilder builder = new StringBuilder();
        while ((newLine = bufferedReader.readLine()) != null) {
            builder.append(newLine)
                .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    private BufferedReader createBufferedReader(String fileName) throws FileNotFoundException {
        String path = Paths.get("").toAbsolutePath().toString();
        String filePath = String.format("%s\\src\\main\\resources\\templates\\%s.html", path, fileName);
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
    }
}
