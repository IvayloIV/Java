import appenders.AppenderBuilder;
import appenders.LogFile;
import contracts.Appender;
import error.Error;
import error.ErrorLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Appender> appenders = new ArrayList<>();
        int n = Integer.parseInt(bufferedReader.readLine());

        createAppenders(bufferedReader, appenders, n);
        Logger logger = new Logger(appenders);
        logErrors(bufferedReader, logger);

        LogFile.closeWriter();
        printAppenders(appenders);
    }

    private static void logErrors(BufferedReader bufferedReader, Logger logger) throws IOException {
        String input;
        while (!(input = bufferedReader.readLine()).equals("END")) {
            String[] tokens = input.split("\\|");
            ErrorLevel errorLevel = ErrorLevel.valueOf(tokens[0]);
            String dateTime = tokens[1];
            String message = tokens[2];

            Error error = new Error(dateTime, message, errorLevel);
            logger.Log(error);
        }
    }

    private static void createAppenders(BufferedReader bufferedReader, List<Appender> appenders, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] appenderTokens = bufferedReader.readLine().split("\\s+");
            Appender appender = AppenderBuilder.create(appenderTokens);
            appenders.add(appender);
        }
    }

    private static void printAppenders(List<Appender> appenders) {
        System.out.println("Logger info");
        for (Appender appender : appenders) {
            System.out.println(appender);
        }
    }
}
