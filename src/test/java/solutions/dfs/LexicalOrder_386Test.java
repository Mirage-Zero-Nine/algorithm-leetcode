package solutions.dfs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link LexicalOrder_386}.
 *
 * <p>The reference implementation used by these tests sorts the numbers by
 * their decimal text. It is intentionally separate from the production
 * algorithm so that the tests can catch traversal-order mistakes.</p>
 */
public class LexicalOrder_386Test {

    private final LexicalOrder_386 solver = new LexicalOrder_386();

    @Test
    public void testLeetCodeExamples() {
        List<Integer> expectedFor13 =
                List.of(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9);
        assertIterableEquals(expectedFor13, solver.lexicalOrder(13));
        assertIterableEquals(expectedFor13, solver.lexicalOrderDfs(13));

        List<Integer> expectedFor2 = List.of(1, 2);
        assertIterableEquals(expectedFor2, solver.lexicalOrder(2));
        assertIterableEquals(expectedFor2, solver.lexicalOrderDfs(2));
    }

    @Test
    public void testZeroReturnsEmptyList() {
        assertSolutionMatchesReference(0);
        List<Integer> actual = solver.lexicalOrder(0);
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testSingleNumber() {
        assertSolutionMatchesReference(1);
    }

    @ParameterizedTest(name = "n={0}")
    @ValueSource(ints = {
            2, 3, 8, 9, 10, 11, 12, 13, 19, 20, 21, 29, 30, 31,
            39, 40, 49, 50, 89, 90, 98, 99, 100, 101, 109, 110, 111,
            119, 120, 199, 200, 209, 210, 219, 220, 999, 1000, 1001,
            1010, 1099, 1100, 1999, 2000, 9999, 10000, 10001, 10009,
            10010, 10099, 10100, 10999, 11000, 19999, 20000, 49999, 50000
    })
    public void testBoundaryAndTypicalInputs(int n) {
        assertSolutionMatchesReference(n);
    }

    @Test
    public void testAllSmallInputsAgainstReference() {
        for (int n = 1; n <= 250; n++) {
            assertSolutionMatchesReference(n);
        }
    }

    @Test
    public void testDeterministicRandomInputsAgainstReference() {
        Random random = new Random(386L);

        for (int i = 0; i < 100; i++) {
            int n = random.nextInt(50_000) + 1;
            assertSolutionMatchesReference(n);
        }
    }

    @Test
    public void testResultContainsEveryNumberExactlyOnce() {
        int n = 50_000;
        List<Integer> actual = solver.lexicalOrder(n);
        List<Integer> dfsActual = solver.lexicalOrderDfs(n);

        assertIterableEquals(actual, dfsActual);
        assertEquals(n, actual.size());
        assertEquals(n, new HashSet<>(actual).size());
        assertTrue(actual.stream().allMatch(value -> value >= 1 && value <= n));
    }

    @Test
    public void testLargeResultIsLexicographicallyOrdered() {
        List<Integer> actual = solver.lexicalOrder(50_000);
        assertIterableEquals(actual, solver.lexicalOrderDfs(50_000));

        for (int i = 1; i < actual.size(); i++) {
            String previous = String.valueOf(actual.get(i - 1));
            String current = String.valueOf(actual.get(i));
            assertTrue(
                    previous.compareTo(current) < 0,
                    "Not lexicographically ordered at index " + i);
        }
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        assertSolutionMatchesReference(13);
        assertSolutionMatchesReference(2);
        assertSolutionMatchesReference(1);
    }

    private void assertSolutionMatchesReference(int n) {
        List<Integer> expected = referenceLexicalOrder(n);

        assertIterableEquals(expected, solver.lexicalOrder(n), "Arithmetic solution failed for n=" + n);
        assertIterableEquals(expected, solver.lexicalOrderDfs(n), "DFS solution failed for n=" + n);
    }

    /**
     * Independent, intentionally slower oracle for test data.
     */
    private List<Integer> referenceLexicalOrder(int n) {
        return IntStream.rangeClosed(1, n)
                .boxed()
                .sorted(Comparator.comparing(String::valueOf))
                .toList();
    }
}
