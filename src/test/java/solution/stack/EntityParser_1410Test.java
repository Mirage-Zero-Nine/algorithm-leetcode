package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityParser_1410Test {
    private final EntityParser_1410 solver = new EntityParser_1410();

    @Test public void testGtAndAmpAndLt() {
        assertEquals("x > y && x < y is always false", solver.entityParser("x &gt; y &amp;&amp; x &lt; y is always false"));
    }

    @Test public void testInvalidEntity() {
        assertEquals("& is an HTML entity but &ambassador; is not.", solver.entityParser("&amp; is an HTML entity but &ambassador; is not."));
    }

    @Test public void testQuot() {
        assertEquals("and I quote: \"...\"", solver.entityParser("and I quote: &quot;...&quot;"));
    }

    @Test public void testNoEntity() {
        assertEquals("Stay home! Practice on Leetcode :)", solver.entityParser("Stay home! Practice on Leetcode :)"));
    }

    @Test public void testFrasl() {
        assertEquals("leetcode.com/problemset/all", solver.entityParser("leetcode.com&frasl;problemset&frasl;all"));
    }

    @Test public void testNestedEntity() {
        assertEquals("&gt;", solver.entityParser("&amp;gt;"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.entityParser(""));
    }
}
