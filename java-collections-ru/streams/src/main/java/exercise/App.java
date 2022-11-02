package exercise;

import java.util.List;

// BEGIN
public final class App {

    private static final List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
                .map(email -> email.split("@")[1])
                .filter(freeDomains::contains)
                .count();
    }
}

// END
