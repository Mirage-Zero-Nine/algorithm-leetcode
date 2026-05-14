package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AreSentencesSimilarTwo_737Test {

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

    @Test
    public void testTransitiveChain() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"a"},
            new String[]{"d"},
            List.of(List.of("a", "b"), List.of("b", "c"), List.of("c", "d"))
        ));
    }

    @Test
    public void testSameWordNoPairs() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"hello", "world"},
            new String[]{"hello", "world"},
            List.of()
        ));
    }

    @Test
    public void testEmptySentences() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{},
            new String[]{},
            List.of()
        ));
    }

    @Test
    public void testNotSimilarOneWordDiffers() {
        assertFalse(test.areSentencesSimilarTwo(
            new String[]{"a", "b"},
            new String[]{"a", "c"},
            List.of(List.of("a", "x"))
        ));
    }

    @Test
    public void testSymmetricSimilarity() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"big"},
            new String[]{"large"},
            List.of(List.of("large", "big"))
        ));
    }

    @Test
    public void testSelfSimilar() {
        assertTrue(test.areSentencesSimilarTwo(
            new String[]{"x"},
            new String[]{"x"},
            List.of(List.of("x", "y"))
        ));
    }

    @Test
    public void testGiantTransitiveChain() {
        int size = 500;
        String[] words1 = new String[]{"word0"};
        String[] words2 = new String[]{"word" + (size - 1)};
        List<List<String>> pairs = new java.util.ArrayList<>();
        for (int i = 0; i < size - 1; i++) {
            pairs.add(List.of("word" + i, "word" + (i + 1)));
        }
        assertTrue(test.areSentencesSimilarTwo(words1, words2, pairs));
    }
}
