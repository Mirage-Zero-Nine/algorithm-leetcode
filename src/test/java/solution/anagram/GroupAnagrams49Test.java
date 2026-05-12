package solution.anagram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class GroupAnagrams49Test {

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
