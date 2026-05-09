package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrackSafe_753Test {
    private final CrackSafe_753 solution = new CrackSafe_753();

    private void assertValidDeBruijnSequence(String result, int n, int k) {
        assertEquals((int) Math.pow(k, n) + n - 1, result.length());

        Set<String> seen = new HashSet<>();
        for (int i = 0; i <= result.length() - n; i++) {
            String window = result.substring(i, i + n);
            assertTrue(window.chars().allMatch(ch -> ch >= '0' && ch < '0' + k));
            seen.add(window);
        }
        assertEquals((int) Math.pow(k, n), seen.size());
    }

    @Test
    void testBasic() {
        String result = solution.crackSafe(1, 2);
        assertValidDeBruijnSequence(result, 1, 2);
    }

    @Test
    void testTwoTwo() {
        String result = solution.crackSafe(2, 2);
        assertValidDeBruijnSequence(result, 2, 2);
    }

    @Test
    void testOneOne() {
        String result = solution.crackSafe(1, 1);
        assertValidDeBruijnSequence(result, 1, 1);
    }

    @Test
    void testTwoThree() {
        String result = solution.crackSafe(2, 3);
        assertValidDeBruijnSequence(result, 2, 3);
    }

    @Test
    void testThreeTwo() {
        String result = solution.crackSafe(3, 2);
        assertValidDeBruijnSequence(result, 3, 2);
    }
}
