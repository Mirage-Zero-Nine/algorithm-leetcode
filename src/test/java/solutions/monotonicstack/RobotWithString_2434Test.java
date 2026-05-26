package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotWithString_2434Test {
    private final RobotWithString_2434 solver = new RobotWithString_2434();

    @Test public void testExample1() {
        assertEquals("azz", solver.robotWithString("zza"));
    }

    @Test public void testExample2() {
        assertEquals("abc", solver.robotWithString("bac"));
    }

    @Test public void testAlreadySorted() {
        assertEquals("abc", solver.robotWithString("abc"));
    }

    @Test public void testReversed() {
        assertEquals("abc", solver.robotWithString("cba"));
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.robotWithString("a"));
    }

    @Test public void testBdda() {
        assertEquals("addb", solver.robotWithString("bdda"));
    }

    @Test public void testAllSame() {
        assertEquals("aaaa", solver.robotWithString("aaaa"));
    }

    @Test public void testTwoChars() {
        assertEquals("ab", solver.robotWithString("ba"));
    }

    @Test public void testBca() {
        assertEquals("acb", solver.robotWithString("bca"));
    }

    @Test public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('a');
        String result = solver.robotWithString(sb.toString());
        assertEquals(sb.toString(), result);
    }
}
