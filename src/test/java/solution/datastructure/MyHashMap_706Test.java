package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
