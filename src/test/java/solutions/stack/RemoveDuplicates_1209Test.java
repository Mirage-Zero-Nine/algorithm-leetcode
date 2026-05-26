package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveDuplicates_1209Test {
    private final RemoveDuplicates_1209 solver = new RemoveDuplicates_1209();

    @Test public void testExample1() {
        assertEquals("abcd", solver.removeDuplicates("abcd", 2));
    }

    @Test public void testExample2() {
        assertEquals("aa", solver.removeDuplicates("deeedbbcccbdaa", 3));
    }

    @Test public void testExample3() {
        assertEquals("ps", solver.removeDuplicates("pbbcggttciiippooaais", 2));
    }

    @Test public void testAllRemoved() {
        assertEquals("", solver.removeDuplicates("aaa", 3));
    }

    @Test public void testNoRemoval() {
        assertEquals("ab", solver.removeDuplicates("ab", 3));
    }

    // Additional happy cases
    @Test public void testChainRemoval() {
        assertEquals("a", solver.removeDuplicates("aabba", 2));
    }

    @Test public void testK2Simple() {
        assertEquals("", solver.removeDuplicates("abba", 2));
    }

    // Negative case: single char, can't remove
    @Test public void testSingleChar() {
        assertEquals("a", solver.removeDuplicates("a", 2));
    }

    // Edge cases
    @Test public void testEmptyString() {
        assertEquals("", solver.removeDuplicates("", 2));
    }

    @Test public void testKLargerThanString() {
        assertEquals("aaa", solver.removeDuplicates("aaa", 5));
    }

    // Giant test case
    @Test public void testGiant() {
        String s = "a".repeat(10000);
        String result = solver.removeDuplicates(s, 3);
        assertEquals("a", result); // 10000 mod 3 = 1
    }
}
