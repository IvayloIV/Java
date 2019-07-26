package Тelephony;
import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String url : this.urls) {
            if (isValidUrl(url)){
                stringBuilder.append(String.format("Browsing: %s!", url));
            } else {
                stringBuilder.append("Invalid URL!");
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String number : this.numbers) {
            if (isValidNumber(number)){
                stringBuilder.append("Calling... ").append(number);
            } else {
                stringBuilder.append("Invalid number!");
            }
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    private boolean isValidNumber(String number) {
        char[] numberToChars = number.toCharArray();
        for (char c : numberToChars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidUrl(String url) {
        char[] urlToChars = url.toCharArray();
        for (char c : urlToChars) {
            if (Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
