package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Partition_131Test {
    private final Partition_131 solution = new Partition_131();

    @Test
    void testBasic() {
        List<List<String>> result = solution.partition("aab");
        assertEquals(2, result.size());
    }

    @Test
    void testSingleChar() {
        List<List<String>> result = solution.partition("a");
        assertEquals(1, result.size());
    }

    @Test
    void testAllSame() {
        List<List<String>> result = solution.partition("aaa");
        assertEquals(4, result.size());
    }

    @Test
    void testNoPalindrome() {
        List<List<String>> result = solution.partition("abc");
        assertEquals(1, result.size());
    }

    @Test
    void testLonger() {
        List<List<String>> result = solution.partition("aabb");
        assertTrue(result.size() >= 4);
    }
}
