package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RandomizedSet_380Test {

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

    @Test
    public void testInsertDuplicate() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertTrue(rs.insert(5));
        assertFalse(rs.insert(5));
        assertFalse(rs.insert(5));
    }

    @Test
    public void testRemoveNonExistent() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertFalse(rs.remove(100));
        rs.insert(1);
        assertFalse(rs.remove(2));
    }

    @Test
    public void testGetRandomSingleElement() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        rs.insert(99);
        assertEquals(99, rs.getRandom());
    }

    @Test
    public void testInsertRemoveInsert() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertTrue(rs.insert(1));
        assertTrue(rs.remove(1));
        assertTrue(rs.insert(1));
        assertEquals(1, rs.getRandom());
    }

    @Test
    public void testGetRandomCoversAll() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        rs.insert(1); rs.insert(2); rs.insert(3);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) seen.add(rs.getRandom());
        assertTrue(seen.contains(1));
        assertTrue(seen.contains(2));
        assertTrue(seen.contains(3));
    }

    @Test
    public void testNegativeValues() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        assertTrue(rs.insert(-1));
        assertTrue(rs.insert(-2));
        assertFalse(rs.insert(-1));
        int r = rs.getRandom();
        assertTrue(r == -1 || r == -2);
    }

    @Test
    public void testGiantCase() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        for (int i = 0; i < 5000; i++) assertTrue(rs.insert(i));
        for (int i = 0; i < 5000; i++) assertFalse(rs.insert(i));
        for (int i = 0; i < 100; i++) {
            int r = rs.getRandom();
            assertTrue(r >= 0 && r < 5000);
        }
        for (int i = 0; i < 5000; i++) assertTrue(rs.remove(i));
        assertFalse(rs.remove(0));
    }

    @Test
    public void testRemoveMiddleElement() {
        RandomizedSet_380 rs = new RandomizedSet_380();
        rs.insert(1); rs.insert(2); rs.insert(3);
        assertTrue(rs.remove(2));
        for (int i = 0; i < 20; i++) {
            int r = rs.getRandom();
            assertTrue(r == 1 || r == 3);
        }
    }
}
