package chushki.util;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(filePath)
                        )
                )
        );

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line)
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
