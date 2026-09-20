package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the binary-lifting implementation of LeetCode 1483. */
public class TreeAncestor_1483Test {

    @Test
    public void officialExampleCoversParentGrandparentAndMissingAncestor() {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertEquals(1, tree.getKthAncestor(3, 1));
        assertEquals(0, tree.getKthAncestor(5, 2));
        assertEquals(-1, tree.getKthAncestor(6, 3));
    }

    @Test
    public void rootAndZeroStep() {
        int[] parent = {-1, 0, 0, 1, 1};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertEquals(0, tree.getKthAncestor(0, 0));
        assertEquals(-1, tree.getKthAncestor(0, 1));
        assertEquals(-1, tree.getKthAncestor(0, parent.length));
        assertEquals(3, tree.getKthAncestor(3, 0));
    }

    @Test
    public void singletonTree() {
        int[] parent = {-1};
        TreeAncestor_1483 tree = new TreeAncestor_1483(1, parent);

        assertEquals(0, tree.getKthAncestor(0, 0));
        assertEquals(-1, tree.getKthAncestor(0, 1));
    }

    @Test
    public void chainReturnsEveryValidDistance() {
        int n = 12;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        for (int node = 0; node < n; node++) {
            for (int k = 0; k <= n; k++) {
                assertEquals(walk(parent, node, k), tree.getKthAncestor(node, k),
                        "node=" + node + ", k=" + k);
            }
        }
    }

    @Test
    public void chainExercisesEveryPowerOfTwoJump() {
        int n = 50_000;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        int[] powers = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
            2048, 4096, 8192, 16_384, 32_768};
        for (int k : powers) {
            assertEquals(n - 1 - k, tree.getKthAncestor(n - 1, k), "k=" + k);
        }
    }

    @Test
    public void chainExercisesCombinationsOfBinaryBits() {
        int n = 50_000;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);
        int[] distances = {3, 5, 7, 15, 31, 63, 127, 255, 1023, 4095,
            16_383, 32_767, 32_768, 49_999, 50_000};

        for (int k : distances) {
            assertEquals(k < n ? n - 1 - k : -1, tree.getKthAncestor(n - 1, k), "k=" + k);
        }
    }

    @Test
    public void chainReturnsMinusOneAboveEachNodeDepth() {
        int[] parent = chainParents(10);
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertEquals(-1, tree.getKthAncestor(3, 4));
        assertEquals(-1, tree.getKthAncestor(3, 10));
        assertEquals(-1, tree.getKthAncestor(9, 10));
        assertEquals(0, tree.getKthAncestor(9, 9));
    }

    @Test
    public void starReturnsRootThenMinusOne() {
        int n = 11;
        int[] parent = new int[n];
        parent[0] = -1;
        Arrays.fill(parent, 1, n, 0);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        for (int node = 1; node < n; node++) {
            assertEquals(node, tree.getKthAncestor(node, 0), "node=" + node);
            assertEquals(0, tree.getKthAncestor(node, 1), "node=" + node);
            assertEquals(-1, tree.getKthAncestor(node, 2), "node=" + node);
            assertEquals(-1, tree.getKthAncestor(node, n), "node=" + node);
        }
    }

    @Test
    public void balancedTreeCoversDifferentBranchPaths() {
        int n = 31;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int node = 1; node < n; node++) {
            parent[node] = (node - 1) / 2;
        }
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        assertEquals(0, tree.getKthAncestor(30, 4));
        assertEquals(14, tree.getKthAncestor(30, 1));
        assertEquals(6, tree.getKthAncestor(30, 2));
        assertEquals(2, tree.getKthAncestor(30, 3));
        assertEquals(0, tree.getKthAncestor(17, 4));
        assertEquals(-1, tree.getKthAncestor(17, 5));
    }

    @Test
    public void irregularParentArrayUsesIndependentWalkOracle() {
        int[] parent = {-1, 0, 0, 2, 1, 3, 3, 0, 7, 4, 9, 2, 11, 12, 6, 14};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);
        int[][] queries = {
            {15, 1}, {15, 2}, {15, 3}, {15, 4}, {15, 5},
            {10, 1}, {10, 2}, {10, 3}, {8, 1}, {8, 2},
            {13, 1}, {13, 2}, {13, 3}, {6, 2}, {0, 1}, {4, 0}
        };

        assertQueries(tree, parent, queries);
    }

    @Test
    public void exhaustiveSmallTreeQueriesMatchParentWalk() {
        int[] parent = {-1, 0, 0, 1, 0, 2, 2, 4};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        for (int node = 0; node < parent.length; node++) {
            for (int k = 0; k <= parent.length; k++) {
                assertEquals(walk(parent, node, k), tree.getKthAncestor(node, k),
                        "node=" + node + ", k=" + k);
            }
        }
    }

    @Test
    public void seededRandomTreesMatchIndependentOracle() {
        Random random = new Random(1_483_2026L);
        for (int treeNumber = 0; treeNumber < 8; treeNumber++) {
            int n = 2 + random.nextInt(120);
            int[] parent = new int[n];
            parent[0] = -1;
            for (int node = 1; node < n; node++) {
                parent[node] = random.nextInt(node);
            }
            TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

            for (int query = 0; query < 250; query++) {
                int node = random.nextInt(n);
                int k = random.nextInt(n + 1);
                assertEquals(walk(parent, node, k), tree.getKthAncestor(node, k),
                        "tree=" + treeNumber + ", query=" + query + ", node=" + node + ", k=" + k);
            }
        }
    }

    @Test
    public void maximumNodeCountChainCoversBoundaryTableRows() {
        int n = 50_000;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        assertEquals(0, tree.getKthAncestor(n - 1, n - 1));
        assertEquals(1, tree.getKthAncestor(n - 1, n - 2));
        assertEquals(-1, tree.getKthAncestor(n - 1, n));
        assertEquals(16_231, tree.getKthAncestor(49_999, 33_768));
        assertEquals(17_232, tree.getKthAncestor(49_999, 32_767));
    }

    @Test
    public void exactPowerOfTwoNodeCountSupportsHighestValidDistance() {
        int n = 32_768;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        assertEquals(0, tree.getKthAncestor(n - 1, n - 1));
        assertEquals(-1, tree.getKthAncestor(n - 1, n));
        assertEquals(0, tree.getKthAncestor(n - 1, 32_767));
        assertEquals(1, tree.getKthAncestor(n - 1, 32_766));
    }

    @Test
    public void justAbovePowerOfTwoNodeCountSupportsBoundaryDistance() {
        int n = 32_769;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        assertEquals(0, tree.getKthAncestor(n - 1, n - 1));
        assertEquals(-1, tree.getKthAncestor(n - 1, n));
        assertEquals(1, tree.getKthAncestor(n - 1, 32_767));
        assertEquals(0, tree.getKthAncestor(n - 1, 32_768));
    }

    @Test
    public void maximumQueryCountOnMaximumChainIsStable() {
        int n = 50_000;
        int[] parent = chainParents(n);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);
        Random random = new Random(1483);

        for (int query = 0; query < 50_000; query++) {
            int node = random.nextInt(n);
            int k = 1 + random.nextInt(n);
            int expected = k <= node ? node - k : -1;
            assertEquals(expected, tree.getKthAncestor(node, k),
                    "query=" + query + ", node=" + node + ", k=" + k);
        }
    }

    @Test
    public void repeatedQueriesDoNotChangeThePreparedTable() {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 5};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertEquals(0, tree.getKthAncestor(8, 3));
        assertEquals(-1, tree.getKthAncestor(8, 8));
        assertEquals(5, tree.getKthAncestor(8, 1));
        assertEquals(2, tree.getKthAncestor(8, 2));
        assertEquals(0, tree.getKthAncestor(8, 3));
        assertEquals(8, tree.getKthAncestor(8, 0));
    }

    @Test
    public void separateInstancesKeepTheirOwnTrees() {
        int[] firstParent = {-1, 0, 1, 2, 3};
        int[] secondParent = {-1, 0, 0, 0, 0};
        TreeAncestor_1483 first = new TreeAncestor_1483(firstParent.length, firstParent);
        TreeAncestor_1483 second = new TreeAncestor_1483(secondParent.length, secondParent);

        assertEquals(0, first.getKthAncestor(4, 4));
        assertEquals(2, first.getKthAncestor(4, 2));
        assertEquals(0, second.getKthAncestor(4, 1));
        assertEquals(-1, second.getKthAncestor(4, 2));
        assertEquals(-1, first.getKthAncestor(4, 5));
    }

    @Test
    public void constructorLeavesCallerParentArrayUnchanged() {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 6};
        int[] before = parent.clone();
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertArrayEquals(before, parent);
        assertEquals(0, tree.getKthAncestor(7, 3));
        assertArrayEquals(before, parent);
    }

    @Test
    public void branchesWithDifferentDepthsReturnAtTheirExactBoundary() {
        int[] parent = {-1, 0, 1, 0, 3, 4, 0, 6, 7, 8};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);

        assertEquals(0, tree.getKthAncestor(2, 2));
        assertEquals(-1, tree.getKthAncestor(2, 3));
        assertEquals(0, tree.getKthAncestor(9, 4));
        assertEquals(-1, tree.getKthAncestor(9, 5));
        assertEquals(0, tree.getKthAncestor(6, 1));
        assertEquals(-1, tree.getKthAncestor(6, 2));
    }

    @Test
    public void arbitraryQueriesAcrossEveryNodeUseIndependentOracle() {
        int[] parent = {-1, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 6, 7, 9, 10, 12, 14, 15, 17};
        TreeAncestor_1483 tree = new TreeAncestor_1483(parent.length, parent);
        int[][] queries = new int[parent.length * 3][2];
        int index = 0;
        for (int node = 0; node < parent.length; node++) {
            queries[index++] = new int[]{node, 0};
            queries[index++] = new int[]{node, 1};
            queries[index++] = new int[]{node, parent.length};
        }

        assertQueries(tree, parent, queries);
    }

    @Test
    public void wideStarAtMaximumNodeCountHandlesManyShallowQueries() {
        int n = 50_000;
        int[] parent = new int[n];
        parent[0] = -1;
        Arrays.fill(parent, 1, n, 0);
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

        for (int node = 1; node < n; node++) {
            assertEquals(0, tree.getKthAncestor(node, 1));
            assertEquals(-1, tree.getKthAncestor(node, 2));
        }
    }

    @Test
    public void mixedBitQueriesOnNonChainTreeMatchParentWalk() {
        int n = 64;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int node = 1; node < n; node++) {
            parent[node] = node % 3 == 0 ? node / 3 : (node - 1) / 2;
        }
        TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);
        int[] distances = {0, 1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 31, 32, 63, 64};

        for (int node = 0; node < n; node += 3) {
            for (int k : distances) {
                assertEquals(walk(parent, node, k), tree.getKthAncestor(node, k),
                        "node=" + node + ", k=" + k);
            }
        }
    }

    @Test
    public void repeatedFreshConstructionDoesNotReusePreviousInstanceState() {
        for (int iteration = 0; iteration < 20; iteration++) {
            int n = 2 + iteration;
            int[] parent = chainParents(n);
            TreeAncestor_1483 tree = new TreeAncestor_1483(n, parent);

            assertEquals(0, tree.getKthAncestor(n - 1, n - 1));
            assertEquals(-1, tree.getKthAncestor(n - 1, n));
        }
    }

    private static int[] chainParents(int n) {
        int[] parent = new int[n];
        parent[0] = -1;
        for (int node = 1; node < n; node++) {
            parent[node] = node - 1;
        }
        return parent;
    }

    private static int walk(int[] parent, int node, int k) {
        while (k > 0 && node != -1) {
            node = parent[node];
            k--;
        }
        return node;
    }

    private static void assertQueries(TreeAncestor_1483 tree, int[] parent, int[][] queries) {
        for (int[] query : queries) {
            int node = query[0];
            int k = query[1];
            assertEquals(walk(parent, node, k), tree.getKthAncestor(node, k),
                    "node=" + node + ", k=" + k);
        }
    }
}
