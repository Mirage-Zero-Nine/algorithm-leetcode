package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MajorityChecker1157Test {

    @Test
    public void testHappyCases() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 1, 2, 2, 1, 1});
        assertEquals(1, mc.query(0, 5, 4));
        assertEquals(-1, mc.query(0, 3, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1});
        assertEquals(1, mc.query(0, 0, 1));
        assertEquals(-1, mc.query(0, 0, 2));
    }

    @Test
    public void testLargeCase() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2});
        assertEquals(1, mc.query(0, 4, 3));
        assertEquals(2, mc.query(5, 9, 3));
        assertEquals(-1, mc.query(0, 9, 6));
    }
}
