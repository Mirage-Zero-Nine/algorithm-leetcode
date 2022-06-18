package solution.graph;

/**
 * In a town, there are N people labelled from 1 to N.
 * There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * 1. The town judge trusts nobody.
 * 2. Everybody (except for the town judge) trusts the town judge.
 * 3. There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * If the town judge exists and can be identified, return the label of the town judge. Otherwise, return -1.
 *
 * @author BorisMirage
 * Time: 2020/02/27 10:06
 * Created with IntelliJ IDEA
 */

public class FindJudge_997 {
    /**
     * 1. Judge has no outdegree.
     * 2. Judge has N - 1 indegree
     *
     * @param N     number of total people
     * @param trust trust map, trust[i] = [a, b] representing that the person a trusts the person b
     * @return return the label of the town judge, or return -1 otherwise
     */
    public int findJudge(int N, int[][] trust) {
        int[] degree = new int[N + 1];

        for (int[] t : trust) {
            degree[t[0]]--;     // judge should have no outdegree
            degree[t[1]]++;     // judge should have N - 1 indegree
        }

        for (int i = 1; i <= N; i++) {
            if (degree[i] == N - 1) {
                return i;
            }
        }

        return -1;
    }
}
