package solutions.design;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;

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

    @Test
    public void testContainsOnNeverAdded() {
        assertFalse(test.contains(999));
        assertFalse(test.contains(500_000));
        assertFalse(test.contains(1));
    }

    @Test
    public void testRemoveNonExistentDoesNotThrow() {
        assertDoesNotThrow(() -> test.remove(12345));
        assertDoesNotThrow(() -> test.remove(0));
    }

    @Test
    public void testReAddAfterRemove() {
        test.add(50);
        assertTrue(test.contains(50));
        test.remove(50);
        assertFalse(test.contains(50));
        test.add(50);
        assertTrue(test.contains(50));
    }

    @Test
    public void testKeyZeroBoundaryAddRemoveReAdd() {
        test.add(0);
        assertTrue(test.contains(0));
        test.remove(0);
        assertFalse(test.contains(0));
        test.add(0);
        assertTrue(test.contains(0));
    }

    @Test
    public void testLargeKeyBoundary() {
        test.add(1_000_000);
        test.add(999_999);
        assertTrue(test.contains(1_000_000));
        assertTrue(test.contains(999_999));
        test.remove(1_000_000);
        assertFalse(test.contains(1_000_000));
        assertTrue(test.contains(999_999));
    }

    @Test
    public void testManyCollisionsSameBucket() {
        // All keys map to bucket 0 with initial capacity 256
        int[] keys = {0, 256, 512, 768, 1024, 1280, 1536, 1792, 2048, 2304};
        for (int k : keys) {
            test.add(k);
        }
        for (int k : keys) {
            assertTrue(test.contains(k), "Should contain " + k);
        }
        test.remove(1024);
        assertFalse(test.contains(1024));
        assertTrue(test.contains(512));
        assertTrue(test.contains(2304));
    }

    @Test
    public void testStress10000OpsRandomCrossCheck() {
        Random rng = new Random(42L);
        HashSet<Integer> expected = new HashSet<>();

        for (int i = 0; i < 10_000; i++) {
            int op = rng.nextInt(3);
            int key = rng.nextInt(1_000_001);
            switch (op) {
                case 0 -> { test.add(key); expected.add(key); }
                case 1 -> { test.remove(key); expected.remove(key); }
                case 2 -> {
                    boolean actual = test.contains(key);
                    boolean exp = expected.contains(key);
                    if (actual != exp) {
                        throw new AssertionError("Mismatch at op " + i + " key=" + key
                                + " expected=" + exp + " actual=" + actual);
                    }
                }
            }
        }
        // Final cross-check on a sample of keys
        for (int key = 0; key < 1000; key++) {
            if (expected.contains(key) != test.contains(key)) {
                throw new AssertionError("Final mismatch at key=" + key);
            }
        }
    }
}
