package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveDuplicates_1047Test {
    private final RemoveDuplicates_1047 solver = new RemoveDuplicates_1047();

    @Test public void testExample() {
        assertEquals("ca", solver.removeDuplicates("abbaca"));
    }

    @Test public void testAllRemoved() {
        assertEquals("", solver.removeDuplicates("aabb"));
    }

    @Test public void testNoDuplicates() {
        assertEquals("abc", solver.removeDuplicates("abc"));
    }

    @Test public void testChainReaction() {
        // "azxxzy" -> remove xx -> "azzy" -> remove zz -> "ay"
        assertEquals("ay", solver.removeDuplicates("azxxzy"));
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.removeDuplicates("a"));
    }
}
