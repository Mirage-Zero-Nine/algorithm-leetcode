package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RandomizedSet380Test {

    @Test
    public void testHappyCases() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertTrue(rs.insert(1));
        assertFalse(rs.remove(2));
        assertTrue(rs.insert(2));
        int random = rs.getRandom();
        assertTrue(random == 1 || random == 2);
        assertTrue(rs.remove(1));
        assertFalse(rs.insert(2));
        assertTrue(rs.getRandom() == 2);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertFalse(rs.remove(1));
        assertTrue(rs.insert(1));
        assertFalse(rs.insert(1));
    }

    @Test
    public void testLargeCase() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        for (int i = 1; i <= 10; i++) assertTrue(rs.insert(i));
        for (int i = 1; i <= 10; i++) assertTrue(rs.remove(i));
        assertFalse(rs.remove(1));
    }
}
