package solution.stack;

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
}
