package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AreSentencesSimilar734Test {

    private final AreSentencesSimilar_734 test = new AreSentencesSimilar_734();

    @Test
    public void testHappyCases() {
        assertTrue(test.areSentencesSimilar(
            new String[]{"great", "acting", "skills"},
            new String[]{"fine", "drama", "talent"},
            List.of(List.of("great", "fine"), List.of("acting", "drama"), List.of("skills", "talent"))
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.areSentencesSimilar(
            new String[]{"great"},
            new String[]{"doubleplus", "good"},
            List.of()
        ));
        assertTrue(test.areSentencesSimilar(new String[]{"great"}, new String[]{"great"}, List.of()));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.areSentencesSimilar(
            new String[]{"a", "b", "c"},
            new String[]{"d", "e", "f"},
            List.of(List.of("a", "d"), List.of("b", "e"))
        ));
    }
}
