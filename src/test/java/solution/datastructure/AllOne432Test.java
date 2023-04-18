package solution.datastructure;

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

public class AllOne432Test {
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
}