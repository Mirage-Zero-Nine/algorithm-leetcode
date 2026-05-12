package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AreSentencesSimilarTwo737Test {

    private final AreSentencesSimilarTwo_737 test = new AreSentencesSimilarTwo_737();

    @Test
    public void testHappyCases() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"great", "acting", "skills"},
            new String[]{"fine", "drama", "talent"},
            List.of(List.of("great", "good"), List.of("fine", "good"), List.of("acting", "drama"), List.of("skills", "talent"))
        ));
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"great"},
            new String[]{"great"},
            List.of()
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.areSentencesSimilarTwo(
            new String[]{"great"},
            new String[]{"doubleplus", "good"},
            List.of()
        ));
        assertFalse(test.areSentencesSimilarTwo(
            new String[]{"a"},
            new String[]{"b"},
            List.of()
        ));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"a", "b", "c"},
            new String[]{"d", "e", "f"},
            List.of(List.of("a", "d"), List.of("b", "e"), List.of("c", "f"))
        ));
    }
}
