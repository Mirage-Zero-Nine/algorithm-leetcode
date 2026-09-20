package solutions.design;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        // Exactly 10,000 calls: 6,000 adds, 3,000 removes, and 1,000 contains.
        for (int i = 0; i < 6_000; i++) {
            test.add(i);
        }
        for (int i = 0; i < 6_000; i += 2) {
            test.remove(i);
        }

        for (int i = 0; i < 1_000; i++) {
            assertEquals(i % 2 != 0, test.contains(i), "Mismatch for key " + i);
        }
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
    public void testMinimumAndMaximumKeysRemainIndependentFromNeighbors() {
        test.add(0);
        test.add(1_000_000);

        assertTrue(test.contains(0));
        assertTrue(test.contains(1_000_000));
        assertFalse(test.contains(1));
        assertFalse(test.contains(999_999));

        test.remove(0);
        assertFalse(test.contains(0));
        assertTrue(test.contains(1_000_000));

        test.add(0);
        test.remove(1_000_000);
        assertTrue(test.contains(0));
        assertFalse(test.contains(1_000_000));
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

        // Reserve the final 1,000 calls for deterministic contains checks so this
        // workload stays within the problem's total-call limit.
        for (int i = 0; i < 9_000; i++) {
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
        // The final 1,000 calls complete the exact 10,000-call workload.
        for (int key = 0; key < 1000; key++) {
            assertEquals(expected.contains(key), test.contains(key), "Final mismatch at key=" + key);
        }
    }

    @Test
    public void testRehashPreservesKeysAtInitialLoadFactorThreshold() {
        for (int key = 0; key <= 192; key++) {
            test.add(key);
        }

        for (int key = 0; key <= 192; key++) {
            assertTrue(test.contains(key), "Key should survive rehash: " + key);
        }
        assertFalse(test.contains(193));
    }

    @Test
    public void testRehashPreservesCollidingKeys() {
        int[] keys = {0, 256, 512, 768, 1_024, 1_280, 1_536, 1_792};
        for (int key : keys) {
            test.add(key);
        }
        for (int key = 1; key <= 192; key++) {
            test.add(key);
        }

        for (int key : keys) {
            assertTrue(test.contains(key), "Colliding key should survive rehash: " + key);
        }
    }

    @Test
    public void testRemoveAndReAddCollidingKeysAfterRehash() {
        for (int key = 0; key <= 192; key++) {
            test.add(key);
        }
        test.add(1_024);
        test.add(2_048);

        test.remove(1_024);
        assertFalse(test.contains(1_024));
        assertTrue(test.contains(2_048));

        test.add(1_024);
        assertTrue(test.contains(1_024));
        assertTrue(test.contains(2_048));
    }

    @Test
    public void testMultipleRehashesPreserveAllKeys() {
        HashSet<Integer> expected = new HashSet<>();
        for (int key = 0; key < 2_048; key++) {
            test.add(key);
            expected.add(key);
        }

        for (int key = 0; key < 2_048; key++) {
            assertEquals(expected.contains(key), test.contains(key), "Mismatch for key " + key);
        }
        assertFalse(test.contains(2_048));
    }

    @Test
    public void testIndependentInstancesDoNotShareKeys() {
        MyHashSet_705 first = new MyHashSet_705();
        MyHashSet_705 second = new MyHashSet_705();

        first.add(0);
        first.add(256);
        second.add(1_000_000);

        assertTrue(first.contains(0));
        assertTrue(first.contains(256));
        assertFalse(first.contains(1_000_000));
        assertTrue(second.contains(1_000_000));
        assertFalse(second.contains(0));

        first.remove(256);
        assertFalse(first.contains(256));
        assertTrue(second.contains(1_000_000));
    }

    @Test
    public void testDuplicateAddsRemainIdempotentAcrossRehash() {
        for (int key = 0; key <= 192; key++) {
            test.add(key);
        }
        for (int i = 0; i < 100; i++) {
            test.add(0);
            test.add(192);
        }

        test.remove(0);
        assertFalse(test.contains(0));
        assertTrue(test.contains(192));
    }

    @Test
    public void testProvidedOperationSequencePrefixAgainstReferenceSet() {
        String[] operations = {
                "add", "add", "add", "add", "remove", "add", "add", "add", "add", "add",
                "add", "remove", "remove", "add", "add", "add", "add", "add", "contains", "contains",
                "add", "add", "add", "add", "remove", "add", "remove", "contains", "contains", "contains",
                "add", "remove", "add", "add", "remove", "add", "add", "remove", "add", "add",
                "add", "remove", "add", "add", "add", "add", "add", "add", "add", "add",
                "add", "add", "add", "add", "remove", "add", "add", "contains", "add", "add",
                "contains", "add", "add", "add", "add", "add", "add", "add", "add", "add",
                "add", "add", "remove", "contains", "add", "add", "contains", "add", "add", "remove",
                "contains", "add", "contains", "add", "contains", "remove", "remove", "remove", "add", "remove"
        };
        int[] keys = {
                594, 175, 820, 872, 319, 933, 543, 667, 417, 300,
                818, 707, 822, 742, 369, 520, 713, 567, 417, 784,
                468, 108, 339, 675, 409, 177, 615, 417, 594, 216,
                392, 104, 242, 894, 251, 893, 92, 770, 350, 420,
                493, 893, 293, 322, 389, 795, 482, 253, 848, 320,
                211, 939, 378, 326, 31, 216, 286, 282, 390, 867,
                201, 590, 85, 475, 68, 733, 259, 70, 293, 349,
                111, 276, 962, 637, 930, 870, 893, 328, 25, 99,
                997, 319, 729, 998, 885, 323, 344, 430, 910, 517
        };

        replayAndAssertAgainstReferenceSet(operations, keys);
    }

    @Test
    public void testGiantMixedOperationsAgainstReferenceSet() {
        Random rng = new Random(705L);
        HashSet<Integer> expected = new HashSet<>();

        for (int step = 0; step < 10_000; step++) {
            int key;
            // Include both bucket collisions and the documented boundaries.
            switch (step % 7) {
                case 0 -> key = (step / 7) % 3_907 * 256;
                case 1 -> key = 0;
                case 2 -> key = 1_000_000;
                default -> key = rng.nextInt(1_000_001);
            }

            switch (rng.nextInt(3)) {
                case 0 -> {
                    test.add(key);
                    expected.add(key);
                }
                case 1 -> {
                    test.remove(key);
                    expected.remove(key);
                }
                case 2 -> assertEquals(expected.contains(key), test.contains(key),
                        "Mismatch at step " + step + " for key " + key);
                default -> throw new AssertionError("unreachable");
            }
        }
    }

    private void replayAndAssertAgainstReferenceSet(String[] operations, int[] keys) {
        assertEquals(operations.length, keys.length, "Each operation must have one key");
        HashSet<Integer> expected = new HashSet<>();

        for (int i = 0; i < operations.length; i++) {
            int key = keys[i];
            switch (operations[i]) {
                case "add" -> {
                    test.add(key);
                    expected.add(key);
                }
                case "remove" -> {
                    test.remove(key);
                    expected.remove(key);
                }
                case "contains" -> assertEquals(expected.contains(key), test.contains(key),
                        "Mismatch at operation " + i + " for key " + key);
                default -> throw new IllegalArgumentException("Unsupported operation: " + operations[i]);
            }
        }
    }
}
