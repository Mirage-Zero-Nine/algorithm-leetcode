package solutions.unionfind;

import java.util.Arrays;
import java.util.stream.IntStream;

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
     * Uses union-find with union by size and path compression to add edges in input order. The roots identify the
     * connected components; an edge whose endpoints already share a root closes the unique cycle. The ordered stream
     * stops at that edge and returns its original row.
     * <p>
     * Time complexity: O(n alpha(n)) amortized over n edges, where alpha is the inverse Ackermann function.
     * Auxiliary space: O(n) for the parent and component-size arrays.
     *
     * @param edges the graph's n edges, with vertices labeled from 1 to n
     * @return the last input edge on the graph's unique cycle
     */
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);

        // The graph has exactly one cycle, so its earlier edges join separate components and the last cycle edge in
        // input order is the first failed union.
        // findFirst short-circuits at that edge, which is also the required last removable edge in the input.
        return Arrays.stream(edges).filter(edge -> {
            int root1 = uf.find(edge[0]), root2 = uf.find(edge[1]);
            if (root1 == root2) {
                return true;
            }
            // Merge only distinct components so every processed tree edge keeps the forest acyclic.
            uf.union(root1, root2);
            return false;
        }).findFirst().orElse(new int[0]);
    }

    /**
     * Tracks disjoint components with a parent array and their sizes. Union by size keeps trees shallow, and
     * path compression makes later finds faster. Component sizes are authoritative only at roots; each instance
     * holds independent state for one graph traversal.
     * <p>
     * Space: O(n).
     */
    private static class UnionFind {
        private final int[] root;
        private final int[] componentSize;

        /**
         * Starts each label, including the unused zero label, in its own size-one component.
         * <p>
         * Time complexity: O(n).
         * Auxiliary space: O(n).
         *
         * @param size largest supported label
         */
        UnionFind(int size) {
            root = IntStream.rangeClosed(0, size).toArray();
            componentSize = IntStream.range(0, size + 1).map(_ -> 1).toArray();
        }

        /**
         * Merges the components containing {@code a} and {@code b}, attaching the smaller tree under the larger one.
         * If their sizes tie, the first root remains the root. Does nothing when both labels are already connected.
         * <p>
         * Time complexity: O(alpha(n)) amortized.
         *
         * @param a a label in the first component
         * @param b a label in the second component
         */
        void union(int a, int b) {
            int root1 = find(a), root2 = find(b);
            if (root1 != root2) {
                // Keep the larger tree as the root; equal sizes deterministically keep root1.
                if (componentSize[root1] < componentSize[root2]) {
                    int swap = root1;
                    root1 = root2;
                    root2 = swap;
                }
                root[root2] = root1;
                // Only root entries are authoritative; sizes stored at non-roots are never consulted.
                componentSize[root1] += componentSize[root2];
            }
        }

        /**
         * Returns the component root and compresses the traversed parent path so future lookups take fewer steps.
         * <p>
         * Time complexity: O(alpha(n)) amortized.
         *
         * @param a label whose component is queried
         * @return the root representing {@code a}'s component
         */
        int find(int a) {
            if (root[a] != a) {
                // Point this vertex at the root so later lookups skip the path traversed here.
                root[a] = find(root[a]);
            }
            return root[a];
        }
    }
}
