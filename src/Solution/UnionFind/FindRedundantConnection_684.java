package Solution.UnionFind;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
 * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the input.
 *
 * @author BorisMirage
 * Time: 2021/11/29 11:02
 * Created with IntelliJ IDEA
 */

public class FindRedundantConnection_684 {
    /**
     * Union find.
     * Union each vertex of every edge when traversing the edges.
     * If two vertices are having same root, then the cycle found. Return this edge.
     *
     * @param edges array of edges in the graph
     * @return the edge that can be removed so that the resulting graph is a tree of n nodes
     */
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);

        for (int[] e : edges) {
            int r1 = uf.find(e[0]);
            int r2 = uf.find(e[1]);
            if (r1 != r2) {
                uf.union(r1, r2);
            } else {
                return e;
            }
        }

        return new int[0];
    }

    /**
     * Union find class.
     */
    private static class UnionFind {
        private final int[] root;

        /**
         * Initialize union find class.
         *
         * @param n number of vertices in graph
         */
        UnionFind(int n) {
            root = new int[n + 1];
            for (int i = 0; i < root.length; i++) {
                root[i] = i;
            }
        }

        /**
         * Union two vertices.
         *
         * @param n1 first node
         * @param n2 second node
         */
        void union(int n1, int n2) {
            int r1 = find(n1);
            root[n2] = r1;
        }

        /**
         * Find root of current vertex.
         *
         * @param n current vertex
         * @return root of current vertex
         */
        int find(int n) {
            return root[n] == n ? root[n] : find(root[n]);
        }
    }
}
