package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MostVisitedPattern_1152Test {

    private final MostVisitedPattern_1152 test = new MostVisitedPattern_1152();

    @Test
    public void testHappyCases() {
        assertEquals(List.of("home", "about", "career"),
            test.mostVisitedPattern(
                new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"}
            ));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1"},
                new int[]{1, 2, 3},
                new String[]{"a", "b", "c"}
            ));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u2", "u2", "u2"},
                new int[]{1, 2, 3, 4, 5, 6},
                new String[]{"a", "b", "c", "a", "b", "c"}
            ));
    }

    @Test
    public void testLexicographicTieBreaking() {
        // Two patterns with same count, pick lexicographically smaller
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u2", "u2", "u2"},
                new int[]{1, 2, 3, 4, 5, 6},
                new String[]{"a", "b", "c", "x", "y", "z"}
            ));
    }

    @Test
    public void testUnsortedTimestamps() {
        // Timestamps not in order - should sort by time
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1"},
                new int[]{3, 1, 2},
                new String[]{"c", "a", "b"}
            ));
    }

    @Test
    public void testSingleUserMultipleVisits() {
        // One user visits 4 sites, multiple 3-sequences possible
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u1"},
                new int[]{1, 2, 3, 4},
                new String[]{"a", "b", "c", "d"}
            ));
    }

    @Test
    public void testDuplicateWebsitesInSequence() {
        assertEquals(List.of("a", "a", "a"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1"},
                new int[]{1, 2, 3},
                new String[]{"a", "a", "a"}
            ));
    }

    @Test
    public void testMultipleUsersWithDifferentPatterns() {
        // u1: a,b,c; u2: a,b,c; u3: x,y,z -> "a,b,c" wins with count 2
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u2", "u2", "u2", "u3", "u3", "u3"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                new String[]{"a", "b", "c", "a", "b", "c", "x", "y", "z"}
            ));
    }

    @Test
    public void testUserWithFewerThanThreeVisits() {
        // u2 has only 2 visits, should be ignored
        assertEquals(List.of("a", "b", "c"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u2", "u2"},
                new int[]{1, 2, 3, 4, 5},
                new String[]{"a", "b", "c", "x", "y"}
            ));
    }

    @Test
    public void testDuplicateSequenceFromSameUser() {
        // Same user generates same 3-sequence multiple times, should count only once per user
        assertEquals(List.of("a", "a", "a"),
            test.mostVisitedPattern(
                new String[]{"u1", "u1", "u1", "u1", "u1"},
                new int[]{1, 2, 3, 4, 5},
                new String[]{"a", "a", "a", "a", "a"}
            ));
    }

    @Test
    public void testGiantCase() {
        // 3 users each visiting 10 sites with same pattern
        int n = 30;
        String[] usernames = new String[n];
        int[] timestamps = new int[n];
        String[] websites = new String[n];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                int idx = i * 10 + j;
                usernames[idx] = "user" + i;
                timestamps[idx] = idx;
                websites[idx] = "site" + j;
            }
        }
        // All 3 users share many common 3-sequences; "site0,site1,site2" should be among them
        List<String> result = test.mostVisitedPattern(usernames, timestamps, websites);
        assertEquals(3, result.size());
    }
}
