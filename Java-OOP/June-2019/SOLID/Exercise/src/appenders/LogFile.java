package appenders;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    private static final String PATH = "./result.txt";
    private int size;
    private static BufferedWriter writer;

    public LogFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(PATH));
    }

    public int getSize() {
        return size;
    }

    public void writeToFile(String text) throws IOException {
        int size = 0;

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (Character.isAlphabetic(letter)) {
                size += letter;
            }
        }

        this.size += size;
        writer.write(text + System.lineSeparator());
    }

    public static void closeWriter() throws IOException {
        writer.close();
    }
}
