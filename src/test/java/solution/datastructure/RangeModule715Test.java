package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RangeModule715Test {

    @Test
    public void testHappyCases() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        assertFalse(rm.queryRange(13, 15));
        assertTrue(rm.queryRange(16, 17));
        assertTrue(rm.queryRange(10, 14));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        RangeModule_715 rm = new RangeModule_715();
        assertFalse(rm.queryRange(1, 10));
        rm.addRange(1, 10);
        assertTrue(rm.queryRange(1, 10));
        rm.removeRange(1, 10);
        assertFalse(rm.queryRange(1, 10));
    }

    @Test
    public void testLargeCase() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 100);
        assertTrue(rm.queryRange(1, 100));
        rm.removeRange(50, 60);
        assertFalse(rm.queryRange(45, 65));
        assertTrue(rm.queryRange(1, 50));
    }
}
