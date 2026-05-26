package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2023/04/20 00:18
 * Created with IntelliJ IDEA
 */

public class AllOne_432Test {
    private AllOne_432 test;

    @BeforeEach
    public void setUp() {
        test = new AllOne_432();
    }

    @Test
    public void testInvalid() {
        test.dec("sdsds");
        assertEquals("", test.getMinKey());
        assertEquals("", test.getMaxKey());
    }

    @Test
    public void test() {
        test.inc("a");
        assertEquals("a", test.getMinKey());
        assertEquals("a", test.getMaxKey());

        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());

        test.dec("b");
        test.dec("b");
        assertEquals("a", test.getMinKey());
        test.dec("a");
        assertEquals("c", test.getMaxKey());
        assertEquals("c", test.getMinKey());
    }

    @Test
    public void testAllOne() {


        // Test inc() and getMaxKey() on empty list
        test.inc("hello");
        assertEquals("hello", test.getMaxKey());
        assertEquals("hello", test.getMinKey());

        // Test inc() and getMaxKey() on non-empty list
        test.inc("world");
        Set<String> set = Sets.newHashSet("hello", "world");
        assertTrue(set.contains(test.getMaxKey()));

        // Test inc() with existing key and getMaxKey()
        test.inc("hello");
        assertEquals("hello", test.getMaxKey());
        assertEquals("world", test.getMinKey());

        // Test dec() with existing key and getMinKey()
        test.dec("hello");
        assertTrue(set.contains(test.getMinKey()));

        // Test dec() and getMinKey() with key count of 1
        test.dec("hello");
        assertEquals("world", test.getMaxKey());
        assertEquals("world", test.getMinKey());

        // Test inc() with new key and getMinKey()
        test.inc("goodbye");
        set = Sets.newHashSet("world", "goodbye");
        assertTrue(set.contains(test.getMaxKey()));
        assertTrue(set.contains(test.getMinKey()));


        // Test dec() with non-existent key
        test.dec("missing_key");
        assertTrue(set.contains(test.getMaxKey()));
        assertTrue(set.contains(test.getMinKey()));
    }

    @Test
    public void testSingleKeyIncrementAndDecrementToEmpty() {
        test.inc("x");
        test.inc("x");
        assertEquals("x", test.getMaxKey());
        assertEquals("x", test.getMinKey());
        test.dec("x");
        test.dec("x");
        assertEquals("", test.getMaxKey());
        assertEquals("", test.getMinKey());
    }

    @Test
    public void testTieForMaxAndMin() {
        test.inc("a");
        test.inc("b");
        Set<String> tie = Sets.newHashSet("a", "b");
        assertTrue(tie.contains(test.getMaxKey()));
        assertTrue(tie.contains(test.getMinKey()));
    }

    @Test
    public void testMinAndMaxMoveAcrossCounts() {
        test.inc("a");
        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());
        test.dec("c");
        test.dec("c");
        Set<String> maxTie = Sets.newHashSet("b", "c");
        assertTrue(maxTie.contains(test.getMaxKey()));
    }

    @Test
    public void testRemovingMiddleFrequencyNode() {
        test.inc("a");
        test.inc("a");
        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        test.dec("c");
        assertEquals("a", test.getMinKey());
        Set<String> maxTie = Sets.newHashSet("a", "b", "c");
        assertTrue(maxTie.contains(test.getMaxKey()));
    }

    @Test
    public void testDecrementNonExistingAfterRemovals() {
        test.inc("a");
        test.dec("a");
        test.dec("a");
        assertEquals("", test.getMaxKey());
        assertEquals("", test.getMinKey());
    }

    @Test
    public void testLargeOperationSequence() {
        for (int i = 0; i < 100; i++) {
            test.inc("k1");
        }
        for (int i = 0; i < 80; i++) {
            test.inc("k2");
        }
        for (int i = 0; i < 50; i++) {
            test.inc("k3");
        }
        assertEquals("k1", test.getMaxKey());
        assertEquals("k3", test.getMinKey());
        for (int i = 0; i < 50; i++) {
            test.dec("k3");
        }
        assertEquals("k1", test.getMaxKey());
        assertEquals("k2", test.getMinKey());
    }

    @Test
    public void testInterleavedUpdatesMaintainCorrectExtremes() {
        test.inc("p");
        test.inc("q");
        test.inc("q");
        test.inc("r");
        test.inc("r");
        test.inc("r");
        assertEquals("r", test.getMaxKey());
        assertEquals("p", test.getMinKey());
        test.dec("r");
        test.dec("r");
        Set<String> mins = Sets.newHashSet("p", "r");
        assertTrue(mins.contains(test.getMinKey()));
    }
}
