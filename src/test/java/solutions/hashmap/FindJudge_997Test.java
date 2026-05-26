package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindJudge_997Test {

    private final FindJudge_997 test = new FindJudge_997();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findJudge(2, new int[][]{{1, 2}}));
        assertEquals(3, test.findJudge(3, new int[][]{{1, 3}, {2, 3}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 2}, {2, 3}}));
        assertEquals(1, test.findJudge(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.findJudge(4, new int[][]{{1, 4}, {2, 4}, {3, 4}}));
    }

    @Test
    public void testNoJudgeEveryoneTrusts() {
        // Everyone trusts someone - no judge
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }

    @Test
    public void testTwoPeopleNoTrust() {
        // No trust relationships, N=2 means judge needs N-1=1 indegree
        assertEquals(-1, test.findJudge(2, new int[][]{}));
    }

    @Test
    public void testJudgeWithExtraTrust() {
        // Person 3 is trusted by 1 and 2, but 3 also trusts 1 -> not a judge
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
    }

    @Test
    public void testMultipleCandidates() {
        // Two people trusted by everyone else but both trust nobody - impossible with valid input
        // Actually only one can have N-1 indegree with 0 outdegree
        assertEquals(-1, test.findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}}));
    }

    @Test
    public void testFivePersonJudge() {
        assertEquals(5, test.findJudge(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
    }

    @Test
    public void testJudgeIsPersonOne() {
        assertEquals(1, test.findJudge(3, new int[][]{{2, 1}, {3, 1}}));
    }

    @Test
    public void testGiantCase() {
        // N=1000, person 1000 is the judge
        int N = 1000;
        int[][] trust = new int[N - 1][2];
        for (int i = 0; i < N - 1; i++) {
            trust[i] = new int[]{i + 1, N};
        }
        assertEquals(N, test.findJudge(N, trust));
    }
}
