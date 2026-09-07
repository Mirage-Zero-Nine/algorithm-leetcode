package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AreSentencesSimilarTwo_737Test {

    @Test
    public void testEveryFourWordRelationGraphAgainstTransitiveClosure() {

        int[][] possible = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}};
        for (int mask = 0; mask < 64; mask++) {
            java.util.List<int[]> edges = new java.util.ArrayList<>();
            boolean[][] connected = new boolean[4][4];
            for (int i = 0; i < 4; i++) connected[i][i] = true;
            for (int bit = 0; bit < 6; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    edges.add(possible[bit].clone());
                    connected[possible[bit][0]][possible[bit][1]] = true;
                    connected[possible[bit][1]][possible[bit][0]] = true;
                }
            }
            for (int via = 0; via < 4; via++)
                for (int from = 0; from < 4; from++)
                    for (int to = 0; to < 4; to++)
                        connected[from][to] |= connected[from][via] && connected[via][to];
            int components = 0;
            for (int node = 0; node < 4; node++) {
                boolean firstInComponent = true;
                for (int prior = 0; prior < node; prior++) firstInComponent &= !connected[node][prior];
                if (firstInComponent) components++;
            }

            java.util.List<List<String>> pairs = new java.util.ArrayList<>();
            for (int[] edge : edges) pairs.add(List.of("word" + edge[0], "word" + edge[1]));
            for (int from = 0; from < 4; from++)
                for (int to = 0; to < 4; to++)
                    org.junit.jupiter.api.Assertions.assertEquals(connected[from][to],
                            test.areSentencesSimilarTwo(new String[]{"word" + from}, new String[]{"word" + to}, pairs));
        }
    }

    @Test
    public void testLongSentenceDiffersOnlyAtFinalUnrelatedWord() {
        String[] first = new String[1000], second = new String[1000];
        java.util.Arrays.fill(first, "great");
        java.util.Arrays.fill(second, "fine");
        second[999] = "unknown";
        assertFalse(test.areSentencesSimilarTwo(first, second, List.of(List.of("great", "good"), List.of("good", "fine"))));
    }


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
