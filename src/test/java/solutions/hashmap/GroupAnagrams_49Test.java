package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class GroupAnagrams_49Test {

    private final GroupAnagrams_49 test = new GroupAnagrams_49();

    @Test
    public void testHappyCases() {
        assertGroupSetsEqual(
            List.of(
                Set.of("bat"),
                Set.of("tan", "nat"),
                Set.of("eat", "tea", "ate")
            ),
            test.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})
        );

        assertGroupSetsEqual(
            List.of(Set.of("abc", "bca", "cab")),
            test.groupAnagrams(new String[]{"abc", "bca", "cab"})
        );
    }

    @Test
    public void testNegativeAndInvalidCases() {
        assertEquals(List.of(), test.groupAnagrams(new String[]{}));
    }

    @Test
    public void testEdgeCases() {
        assertGroupSetsEqual(
            List.of(Set.of("a"), Set.of("b")),
            test.groupAnagrams(new String[]{"a", "b"})
        );
        assertGroupSetsEqual(
            List.of(Set.of("")),
            test.groupAnagrams(new String[]{"", ""})
        );
    }

    @Test
    public void testLargeCase() {
        String[] input = {
            "listen", "silent", "enlist", "inlets",
            "google", "gogole",
            "evil", "vile", "veil", "live"
        };
        assertGroupSetsEqual(
            List.of(
                Set.of("listen", "silent", "enlist", "inlets"),
                Set.of("google", "gogole"),
                Set.of("evil", "vile", "veil", "live")
            ),
            test.groupAnagrams(input)
        );
    }

    @Test
    public void testSingleElement() {
        List<List<String>> result = test.groupAnagrams(new String[]{"hello"});
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).size());
    }

    @Test
    public void testAllDifferent() {
        List<List<String>> result = test.groupAnagrams(new String[]{"abc", "def", "ghi"});
        assertEquals(3, result.size());
    }

    @Test
    public void testAllSame() {
        List<List<String>> result = test.groupAnagrams(new String[]{"abc", "bca", "cab", "acb"});
        assertEquals(1, result.size());
        assertEquals(4, result.get(0).size());
    }

    @Test
    public void testEmptyStrings() {
        List<List<String>> result = test.groupAnagrams(new String[]{"", "", ""});
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }

    @Test
    public void testSingleCharStrings() {
        List<List<String>> result = test.groupAnagrams(new String[]{"a", "b", "a"});
        assertEquals(2, result.size());
        int totalElements = result.stream().mapToInt(List::size).sum();
        assertEquals(3, totalElements);
    }

    @Test
    public void testMixedLengths() {
        List<List<String>> result = test.groupAnagrams(new String[]{"a", "ab", "ba", "abc"});
        assertEquals(3, result.size());
    }

    @Test
    public void testGiantCase() {
        // 1000 strings, all anagrams of each other
        String[] input = new String[1000];
        char[] base = "abcdefghij".toCharArray();
        for (int i = 0; i < 1000; i++) {
            // rotate
            char[] copy = base.clone();
            char tmp = copy[i % 10];
            copy[i % 10] = copy[(i + 1) % 10];
            copy[(i + 1) % 10] = tmp;
            input[i] = new String(copy);
        }
        List<List<String>> result = test.groupAnagrams(input);
        // All should be in one group since they're all anagrams
        int totalElements = result.stream().mapToInt(List::size).sum();
        assertEquals(1000, totalElements);
    }

    private static void assertGroupSetsEqual(List<Set<String>> expected, List<List<String>> actual) {
        List<Set<String>> normalized = new ArrayList<>();
        for (List<String> group : actual) {
            normalized.add(new HashSet<>(group));
        }
        assertEquals(expected.size(), normalized.size());
        for (Set<String> group : expected) {
            assertTrue(normalized.contains(group));
        }
    }
}
