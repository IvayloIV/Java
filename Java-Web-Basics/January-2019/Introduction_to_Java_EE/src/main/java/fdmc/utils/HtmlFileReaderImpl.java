package fdmc.utils;

import java.io.*;

public class HtmlFileReaderImpl implements HtmlFileReader {
    @Override
    public String readHtmlFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(filePath)
                        )
                )
        );

        StringBuilder sb = new StringBuilder();
        String fileLine;
        while ((fileLine = reader.readLine()) != null) {
            sb.append(fileLine)
                .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
