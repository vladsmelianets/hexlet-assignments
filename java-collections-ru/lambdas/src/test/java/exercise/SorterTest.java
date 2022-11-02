package exercise;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class SorterTest {

    @Test
    void Should_ReturnListOfOnlyMalesNamesSortedByTheirAge_WhenGivenListContainsAnyMales() {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> expected = List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev");

        assertThat(Sorter.takeOldestMans(users)).isEqualTo(expected);
    }

    @Test
    void Should_ReturnEmptyList_WhenGivenListNotContainsAnyMales() {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        assertThat(Sorter.takeOldestMans(users)).isEqualTo(emptyList());
    }

    @Test
    void Should_ReturnEmptyList_WhenGivenEmptyList() {
        assertThat(Sorter.takeOldestMans(emptyList())).isEqualTo(emptyList());
    }
}
// END


