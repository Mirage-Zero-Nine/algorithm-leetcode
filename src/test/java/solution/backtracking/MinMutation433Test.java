package solution.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/13 11:44
 * Created with IntelliJ IDEA
 */

public class MinMutation433Test {

    private final MinMutation_433 test = new MinMutation_433();

    @Test
    public void test() {
        assertEquals(1, test.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        assertEquals(2, test.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
        assertEquals(8, test.minMutation("AAAAAAAA", "CCCCCCCC", new String[]{"AAAAAAAA", "AAAAAAAC", "AAAAAACC", "AAAAACCC", "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCA", "CCCCCCCC"}));
    }

    @Test
    public void testCase4() {
        assertEquals(-1, test.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTC", "AACCGGTA"}));
    }

    @Test
    public void testCase5() {
        assertEquals(0, test.minMutation("AACCGGTT", "AACCGGTT", new String[]{"AACCGGTA"}));
    }

    @Test
    public void testCase6() {
        assertEquals(5, test.minMutation("AAAAACCC", "CCCCCCCC", new String[]{
                "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCCCC"
        }));
    }

    @Test
    public void testCase7() {
        assertEquals(-1, test.minMutation("AACCGGTT", "GGACGGTT", new String[]{
                "AACCGGTA", "AACCGGTT", "AACCGGCA", "GGACGGTA", "GGACGGTT"
        }));
    }

    @Test
    public void testCase8() {
        assertEquals(-1, test.minMutation("AAAAACCC", "CCCCCCCC", new String[]{
                "AAAACCCC", "AACACCCC", "ACCACCCC", "ACCCCCCC", "CCCCCACC"
        }));
    }

    @Test
    public void testCase9() {
        assertEquals(-1, test.minMutation("A", "G", new String[]{})); // No valid mutations in the bank.
    }

    @Test
    public void testCase10() {
        assertEquals(1, test.minMutation("A", "C", new String[]{"C"})); // Single letter mutation.
    }

    @Test
    public void testCase11() {
        assertEquals(-1, test.minMutation("AACCGGTT", "GGTTGGTT", new String[]{
                "AACCGGTT", "AACCGGTC", "AACCGGCC", "AACCGGGC", "GGTTGGTC", "GGTTGGTA", "GGTTGGTT"
        }));
    }
}