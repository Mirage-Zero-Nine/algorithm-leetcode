package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsStrobogrammatic246Test {

    private final IsStrobogrammatic_246 test = new IsStrobogrammatic_246();

    @Test
    public void testHappyCases() {
        assertTrue(test.isStrobogrammatic("69"));
        assertTrue(test.isStrobogrammatic("88"));
        assertTrue(test.isStrobogrammatic("818"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isStrobogrammatic("962"));
        assertFalse(test.isStrobogrammatic("2"));
        assertTrue(test.isStrobogrammatic("0"));
        assertTrue(test.isStrobogrammatic("1"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isStrobogrammatic("1001001"));
        assertFalse(test.isStrobogrammatic("1234567"));
    }
}
