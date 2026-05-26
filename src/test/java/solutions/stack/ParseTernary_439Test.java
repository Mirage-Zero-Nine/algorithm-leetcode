package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParseTernary_439Test {
    private final ParseTernary_439 solver = new ParseTernary_439();

    @Test public void testNested() {
        // T ? (T?1:2) : (F?3:4) -> T selects T?1:2 = 1
        assertEquals("1", solver.parseTernary("T?T?1:2:F?3:4"));
    }

    @Test public void testNestedFalse() {
        // F ? (T?1:2) : (F?4:5) -> F selects F?4:5 = 5
        assertEquals("5", solver.parseTernary("F?T?1:2:F?4:5"));
    }

    @Test public void testDeepNest() {
        // T ? (T?F:5) : 3 -> T selects T?F:5 = F
        assertEquals("F", solver.parseTernary("T?T?F:5:3"));
    }

    @Test public void testFalseOuter() {
        // F ? 1 : (T?4:5) -> F selects T?4:5 = 4
        assertEquals("4", solver.parseTernary("F?1:T?4:5"));
    }

    @Test public void testShortExpression() {
        assertEquals("", solver.parseTernary("T?2:3"));
    }

    @Test public void testSimpleTrue() {
        // T?T?1:2:3 -> T selects T?1:2 = 1
        assertEquals("1", solver.parseTernary("T?T?1:2:3"));
    }

    @Test public void testSimpleFalse() {
        // F?1:F?3:4 -> F selects F?3:4 = 4
        assertEquals("4", solver.parseTernary("F?1:F?3:4"));
    }

    @Test public void testAllTrue() {
        // T?T?T?1:2:3:4 -> T selects T?T?1:2:3 -> T selects T?1:2 -> 1
        assertEquals("1", solver.parseTernary("T?T?T?1:2:3:4"));
    }

    @Test public void testAllFalse() {
        // F?1:F?2:F?3:4 -> F selects F?2:F?3:4 -> F selects F?3:4 -> 4
        assertEquals("4", solver.parseTernary("F?1:F?2:F?3:4"));
    }

    @Test public void testGiant() {
        // Build T?T?T?...?1:0:0:...:0 (deep nesting)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append("T?");
        sb.append("1");
        for (int i = 0; i < 500; i++) sb.append(":0");
        assertEquals("1", solver.parseTernary(sb.toString()));
    }
}
