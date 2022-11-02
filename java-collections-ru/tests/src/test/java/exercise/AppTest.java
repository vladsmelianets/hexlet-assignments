package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void should_ReturnListOfSizeN_then_nLessThanGivenListSize() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2));

        Assertions.assertIterableEquals(expected, App.take(numbers, 2));
    }

    @Test
    void should_ReturnOriginalList_then_nMoreThanGivenListSize() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        Assertions.assertIterableEquals(expected, App.take(numbers, 10));
    }

    @Test
    void should_ReturnEmptyList_then_givenEmptyList() {
        Assertions.assertIterableEquals(Collections.emptyList(), App.take(Collections.emptyList(), 10));
    }

    @Test
    void should_ReturnEmptyList_then_nEqualsZero() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        Assertions.assertIterableEquals(Collections.emptyList(), App.take(numbers, 0));
    }




}
