package solution.stack;

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
}
