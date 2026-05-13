package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/05 23:54
 * Created with IntelliJ IDEA
 */

public class MyHashSet_705Test {

    private MyHashSet_705 test;

    @BeforeEach
    public void setUp() {
        test = new MyHashSet_705();
    }

    @Test
    public void test() {
        test.add(1);      // set = [1]
        test.add(2);      // set = [1, 2]

        assertTrue(test.contains(1));
        assertFalse(test.contains(Integer.MAX_VALUE));

        test.add(2);      // set = [1, 2]
        assertTrue(test.contains(2));
        test.remove(2);   // set = [1]
        assertFalse(test.contains(2));
    }

    @Test
    public void testEmptySetDoesNotContainKey() {
        assertFalse(test.contains(0));
    }

    @Test
    public void testAddZero() {
        test.add(0);

        assertTrue(test.contains(0));
    }

    @Test
    public void testAddMaximumDocumentedKey() {
        test.add(1_000_000);

        assertTrue(test.contains(1_000_000));
    }

    @Test
    public void testDuplicateAddKeepsValuePresent() {
        test.add(42);
        test.add(42);

        assertTrue(test.contains(42));
    }

    @Test
    public void testRemoveMissingKeyDoesNothing() {
        test.add(7);
        test.remove(8);

        assertTrue(test.contains(7));
        assertFalse(test.contains(8));
    }

    @Test
    public void testRemoveExistingKey() {
        test.add(99);
        test.remove(99);

        assertFalse(test.contains(99));
    }

    @Test
    public void testCollisionBucketKeepsAllKeys() {
        test.add(1);
        test.add(257);
        test.add(513);

        assertTrue(test.contains(1));
        assertTrue(test.contains(257));
        assertTrue(test.contains(513));
    }

    @Test
    public void testRemoveOneCollisionKeyLeavesOthers() {
        test.add(2);
        test.add(258);
        test.add(514);
        test.remove(258);

        assertTrue(test.contains(2));
        assertFalse(test.contains(258));
        assertTrue(test.contains(514));
    }

    @Test
    public void testGiantOperationSequence() {
        for (int i = 0; i < 10_000; i++) {
            test.add(i);
        }
        for (int i = 0; i < 10_000; i += 2) {
            test.remove(i);
        }

        assertFalse(test.contains(0));
        assertTrue(test.contains(1));
        assertFalse(test.contains(9_998));
        assertTrue(test.contains(9_999));
    }
}
