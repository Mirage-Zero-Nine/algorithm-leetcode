package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StreamChecker1032Test {

    @Test
    public void testHappyCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"cd", "f", "kl"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertTrue(sc.query('d'));
        assertFalse(sc.query('e'));
        assertTrue(sc.query('f'));
    }

    @Test
    public void testEdgeCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"a"});
        assertTrue(sc.query('a'));
        assertFalse(sc.query('b'));
    }

    @Test
    public void testLargeCase() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"abc", "de"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertTrue(sc.query('c'));
        assertFalse(sc.query('d'));
        assertTrue(sc.query('e'));
    }
}
