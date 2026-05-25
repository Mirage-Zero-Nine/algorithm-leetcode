package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/04 23:09
 * Created with IntelliJ IDEA
 */

public class MyHashMap_706Test {

    private MyHashMap_706 test;

    @BeforeEach
    public void setUp() {
        test = new MyHashMap_706();
    }

    @Test
    public void test() {
        test.put(1, 1);
        test.put(2, 2);
        assertEquals(1, test.get(1));
        assertEquals(-1, test.get(111));
        assertEquals(-1, test.get(31));
        assertEquals(2, test.get(2));
        assertEquals(-1, test.get(Integer.MAX_VALUE));
        assertEquals(-1, test.get(Integer.MIN_VALUE));
        assertEquals(2, test.get(2));
        test.remove(2);
        assertEquals(-1, test.get(2));
    }

    @Test
    public void testEmptyMapReturnsNegativeOne() {
        assertEquals(-1, test.get(0));
    }

    @Test
    public void testPutZeroKey() {
        test.put(0, 100);

        assertEquals(100, test.get(0));
    }

    @Test
    public void testPutMaximumDocumentedKey() {
        test.put(1_000_000, 123);

        assertEquals(123, test.get(1_000_000));
    }

    @Test
    public void testUpdateExistingKey() {
        test.put(5, 10);
        test.put(5, 20);

        assertEquals(20, test.get(5));
    }

    @Test
    public void testRemoveMissingKeyDoesNotRemoveOtherMappings() {
        test.put(7, 70);
        test.remove(8);

        assertEquals(70, test.get(7));
        assertEquals(-1, test.get(8));
    }

    @Test
    public void testRemoveExistingKey() {
        test.put(9, 90);
        test.remove(9);

        assertEquals(-1, test.get(9));
    }

    @Test
    public void testCollisionBucketKeepsAllMappings() {
        test.put(1, 10);
        test.put(257, 20);
        test.put(513, 30);

        assertEquals(10, test.get(1));
        assertEquals(20, test.get(257));
        assertEquals(30, test.get(513));
    }

    @Test
    public void testRemoveOneCollisionKeyLeavesOthers() {
        test.put(2, 10);
        test.put(258, 20);
        test.put(514, 30);
        test.remove(258);

        assertEquals(10, test.get(2));
        assertEquals(-1, test.get(258));
        assertEquals(30, test.get(514));
    }

    @Test
    public void testGiantOperationSequenceAcrossRehash() {
        for (int i = 0; i < 300; i++) {
            test.put(i, i * 10);
        }
        for (int i = 0; i < 300; i += 3) {
            test.remove(i);
        }

        assertEquals(-1, test.get(0));
        assertEquals(10, test.get(1));
        assertEquals(20, test.get(2));
        assertEquals(-1, test.get(297));
        assertEquals(2_990, test.get(299));
    }

    @Test
    public void testUpdateValueToZero() {
        test.put(42, 100);
        assertEquals(100, test.get(42));
        test.put(42, 0);
        assertEquals(0, test.get(42));
    }

    @Test
    public void testRemoveNonExistentKeyNoException() {
        assertDoesNotThrow(() -> test.remove(999));
        assertDoesNotThrow(() -> test.remove(0));
    }

    @Test
    public void testRemoveThenReinsert() {
        test.put(10, 100);
        test.remove(10);
        assertEquals(-1, test.get(10));
        test.put(10, 200);
        assertEquals(200, test.get(10));
    }

    @Test
    public void testManyCollisionsSameBucket() {
        // Keys 0, 256, 512, 768, 1024 all map to bucket 0 (key % 256)
        int[] keys = {0, 256, 512, 768, 1024};
        for (int i = 0; i < keys.length; i++) {
            test.put(keys[i], i * 11);
        }
        for (int i = 0; i < keys.length; i++) {
            assertEquals(i * 11, test.get(keys[i]));
        }
        test.remove(512);
        assertEquals(-1, test.get(512));
        assertEquals(0, test.get(0));
        assertEquals(44, test.get(1024));
    }

    @Test
    public void testLargeKeysNearMaxValue() {
        int k1 = 1_000_000;
        int k2 = 999_999;
        test.put(k1, 1);
        test.put(k2, 2);
        assertEquals(1, test.get(k1));
        assertEquals(2, test.get(k2));
        test.remove(k1);
        assertEquals(-1, test.get(k1));
        assertEquals(2, test.get(k2));
    }

    @Test
    public void testGetOnNeverPutKeyAfterOtherOps() {
        test.put(1, 10);
        test.put(2, 20);
        test.remove(1);
        assertEquals(-1, test.get(3));
        assertEquals(-1, test.get(1));
        assertEquals(-1, test.get(1000));
    }

    @Test
    public void testPutMultipleUpdatesThenGet() {
        test.put(7, 1);
        test.put(7, 2);
        test.put(7, 3);
        test.put(7, 4);
        assertEquals(4, test.get(7));
    }

    @Test
    public void testStress10000OpsRandomSeed42() {
        Random rng = new Random(42L);
        HashMap<Integer, Integer> reference = new HashMap<>();

        for (int i = 0; i < 10000; i++) {
            int op = rng.nextInt(3);
            int key = rng.nextInt(10000);
            if (op == 0) {
                int val = rng.nextInt(10000);
                test.put(key, val);
                reference.put(key, val);
            } else if (op == 1) {
                int expected = reference.getOrDefault(key, -1);
                assertEquals(expected, test.get(key), "get(" + key + ") at op " + i);
            } else {
                test.remove(key);
                reference.remove(key);
            }
        }
        // Final verification of all remaining keys
        for (var entry : reference.entrySet()) {
            assertEquals(entry.getValue().intValue(), test.get(entry.getKey()),
                    "final check key=" + entry.getKey());
        }
    }
}
