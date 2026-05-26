package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RandomizedCollection_381Test {

    @Test
    public void testHappyCases() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertTrue(rc.insert(1));
        assertFalse(rc.insert(1));
        assertTrue(rc.insert(2));
        assertTrue(rc.remove(1));
        int random = rc.getRandom();
        assertTrue(random == 1 || random == 2);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertFalse(rc.remove(1));
        rc.insert(1);
        assertTrue(rc.remove(1));
        assertFalse(rc.remove(1));
    }

    @Test
    public void testLargeCase() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int i = 1; i <= 5; i++) rc.insert(i);
        for (int i = 1; i <= 5; i++) assertTrue(rc.remove(i));
        assertFalse(rc.remove(1));
    }

    @Test
    public void testInsertDuplicates() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertTrue(rc.insert(5));
        assertFalse(rc.insert(5));
        assertFalse(rc.insert(5));
        assertTrue(rc.remove(5));
        assertTrue(rc.remove(5));
        assertTrue(rc.remove(5));
        assertFalse(rc.remove(5));
    }

    @Test
    public void testGetRandomSingleElement() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(42);
        assertEquals(42, rc.getRandom());
    }

    @Test
    public void testRemoveLastElement() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.insert(3);
        assertTrue(rc.remove(3));
        // getRandom should only return 1 or 2
        for (int i = 0; i < 20; i++) {
            int r = rc.getRandom();
            assertTrue(r == 1 || r == 2);
        }
    }

    @Test
    public void testInsertAfterRemove() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.remove(1);
        assertTrue(rc.insert(1)); // 1 was fully removed, so insert returns true
        int r = rc.getRandom();
        assertTrue(r == 1 || r == 2);
    }

    @Test
    public void testMultipleDuplicatesRemoval() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(1);
        rc.insert(2);
        rc.insert(2);
        assertTrue(rc.remove(1));
        assertFalse(rc.insert(1)); // still has one 1
        assertTrue(rc.remove(2));
        assertTrue(rc.remove(2));
        assertFalse(rc.remove(2));
    }

    @Test
    public void testGetRandomCoversAllValues() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.insert(3);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) seen.add(rc.getRandom());
        assertTrue(seen.contains(1));
        assertTrue(seen.contains(2));
        assertTrue(seen.contains(3));
    }

    @Test
    public void testGiantCase() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int i = 0; i < 5000; i++) rc.insert(i % 100);
        for (int i = 0; i < 5000; i++) {
            int r = rc.getRandom();
            assertTrue(r >= 0 && r < 100);
        }
        for (int i = 0; i < 5000; i++) assertTrue(rc.remove(i % 100));
        assertFalse(rc.remove(0));
    }

    @Test
    public void testRemoveNonExistent() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        assertFalse(rc.remove(2));
        assertFalse(rc.remove(0));
        assertTrue(rc.remove(1));
    }
}
