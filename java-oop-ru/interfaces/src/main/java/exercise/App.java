package exercise;

import java.util.List;

// BEGIN
public final class App {

    public static void main(String[] args) {

    }

    public static List<String> buildAppartmentsList(List<Home> homes, int limit) {
        return homes.stream().sorted(Home::compareTo).limit(limit).map(Home::toString).toList();
    }
}
// END
