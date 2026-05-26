package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AreSentencesSimilar_734Test {

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

    @Test
    public void testEmptySentences() {
        assertTrue(test.areSentencesSimilar(new String[]{}, new String[]{}, List.of()));
    }

    @Test
    public void testSymmetry() {
        // If "great" ~ "fine", then "fine" ~ "great"
        assertTrue(test.areSentencesSimilar(
            new String[]{"fine"},
            new String[]{"great"},
            List.of(List.of("great", "fine"))
        ));
    }

    @Test
    public void testNotTransitive() {
        // great~fine, fine~good does NOT mean great~good
        assertFalse(test.areSentencesSimilar(
            new String[]{"great"},
            new String[]{"good"},
            List.of(List.of("great", "fine"), List.of("fine", "good"))
        ));
    }

    @Test
    public void testSelfSimilar() {
        assertTrue(test.areSentencesSimilar(
            new String[]{"a", "b", "c"},
            new String[]{"a", "b", "c"},
            List.of()
        ));
    }

    @Test
    public void testNoMatchingPair() {
        assertFalse(test.areSentencesSimilar(
            new String[]{"hello"},
            new String[]{"world"},
            List.of(List.of("foo", "bar"))
        ));
    }

    @Test
    public void testPartialMatch() {
        // First word matches, second doesn't
        assertFalse(test.areSentencesSimilar(
            new String[]{"a", "b"},
            new String[]{"c", "d"},
            List.of(List.of("a", "c"))
        ));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        String[] words1 = new String[n];
        String[] words2 = new String[n];
        List<List<String>> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words1[i] = "word" + i;
            words2[i] = "syn" + i;
            pairs.add(List.of("word" + i, "syn" + i));
        }
        assertTrue(test.areSentencesSimilar(words1, words2, pairs));
    }
}
