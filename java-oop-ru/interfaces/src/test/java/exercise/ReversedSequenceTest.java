package exercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReversedSequenceTest {

    private CharSequence text;

    @BeforeEach
    void setText() {
        text = new ReversedSequence("abcdef");
    }

    @Test
    void length() {
        Assertions.assertThat(text.length()).isEqualTo(6);
    }

    @Test
    void charAt() {
        Assertions.assertThat(text.charAt(1)).isEqualTo('e');
    }

    @Test
    void subSequence() {
        Assertions.assertThat(text.subSequence(1, 4)).isEqualTo("edc");
    }
}
