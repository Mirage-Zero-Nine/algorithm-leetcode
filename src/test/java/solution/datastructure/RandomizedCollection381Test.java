package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RandomizedCollection381Test {

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
}
