package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests zero-sum subtree removal with an independent iterative post-order oracle. */
public class DeleteTreeNodes_1273Test {

    @Test
    public void officialExampleOne() {
        assertResult(2, 7, new int[] {-1, 0, 0, 1, 2, 2, 2},
                new int[] {1, -2, 4, 0, -2, -1, -1});
    }

    @Test
    public void officialExampleTwo() {
        assertResult(6, 7, new int[] {-1, 0, 0, 1, 2, 2, 2},
                new int[] {1, -2, 4, 0, -2, -1, -2});
    }

    @Test
    public void officialExampleThreeHasNoZeroSumSubtree() {
        assertResult(5, 5, new int[] {-1, 0, 1, 0, 0},
                new int[] {-672, 441, 18, 728, 378});
    }

    @Test
    public void officialExampleFourHasNoZeroSumSubtree() {
        assertResult(5, 5, new int[] {-1, 0, 0, 1, 1},
                new int[] {-686, -842, 616, -739, -746});
    }

    @Test
    public void emptyImplementationGuardReturnsZero() {
        assertEquals(0, new DeleteTreeNodes_1273().deleteTreeNodes(0, null, null));
    }

    @Test
    public void nullParentOrValueImplementationGuardReturnsZero() {
        assertEquals(0, new DeleteTreeNodes_1273().deleteTreeNodes(1, null, new int[] {1}));
        assertEquals(0, new DeleteTreeNodes_1273().deleteTreeNodes(1, new int[] {-1}, null));
    }

    @Test
    public void singletonPositiveValueRemains() {
        assertResult(1, 1, new int[] {-1}, new int[] {100_000});
    }

    @Test
    public void singletonZeroValueIsDeleted() {
        assertResult(0, 1, new int[] {-1}, new int[] {0});
    }

    @Test
    public void nonzeroRootWithZeroValuedLeafDeletesOnlyLeaf() {
        assertResult(2, 3, new int[] {-1, 0, 0}, new int[] {7, -3, 0});
    }

    @Test
    public void entireTreeZeroSumDeletesRootAndAllDescendants() {
        assertResult(0, 4, new int[] {-1, 0, 1, 1}, new int[] {5, -5, 8, -8});
    }

    @Test
    public void internalZeroSumSubtreeDeletesItsWholeBranch() {
        assertResult(2, 5, new int[] {-1, 0, 0, 1, 1}, new int[] {10, 5, 3, -2, -3});
    }

    @Test
    public void nestedZeroSumSubtreesAreRemovedTogether() {
        // Node 2 and its child form a zero subtree; node 1 also totals zero after it is removed.
        assertResult(2, 6, new int[] {-1, 0, 1, 2, 1, 0},
                new int[] {9, -9, 4, -4, 9, 1});
    }

    @Test
    public void independentZeroSumBranchesDoNotDeleteNonzeroRoot() {
        assertResult(1, 5, new int[] {-1, 0, 0, 1, 2}, new int[] {11, 2, -2, -2, 2});
    }

    @Test
    public void zeroValuedInternalNodeWithNonzeroSubtreeRemains() {
        assertResult(3, 3, new int[] {-1, 0, 1}, new int[] {5, 0, 2});
    }

    @Test
    public void negativeOnlyTreeHasNoZeroSumSubtree() {
        assertResult(4, 4, new int[] {-1, 0, 0, 2}, new int[] {-1, -2, -3, -4});
    }

    @Test
    public void positiveOnlyTreeHasNoZeroSumSubtree() {
        assertResult(6, 6, new int[] {-1, 0, 0, 1, 1, 3}, new int[] {1, 2, 3, 4, 5, 6});
    }

    @Test
    public void signedBoundaryValuesAreSummedWithoutChangingTheContract() {
        assertResult(4, 4, new int[] {-1, 0, 0, 2},
                new int[] {100_000, -100_000, -99_999, 100_000});
    }

    @Test
    public void parentIndexOrderingSupportsADeepAndBranchingTree() {
        assertResult(5, 7, new int[] {-1, 0, 0, 2, 1, 4, 2},
                new int[] {20, 1, 2, -2, 3, -3, 4});
    }

    @Test
    public void valueInputMutationDoesNotContaminateASecondFreshInvocation() {
        int[] parent = {-1, 0, 0, 1};
        int[] values = {5, -2, 3, 3};
        DeleteTreeNodes_1273 solver = new DeleteTreeNodes_1273();

        assertEquals(4, solver.deleteTreeNodes(4, parent, values));
        assertEquals(4, solver.deleteTreeNodes(4, parent, new int[] {5, -2, 3, 3}));
    }

    @Test
    public void parentInputIsNotMutated() {
        int[] parent = {-1, 0, 0, 1, 1};
        int[] before = parent.clone();
        new DeleteTreeNodes_1273().deleteTreeNodes(5, parent, new int[] {8, -3, 2, 1, 4});

        assertArrayEquals(before, parent);
    }

    @Test
    public void repeatedCallsWithDifferentTreesRemainIndependent() {
        DeleteTreeNodes_1273 solver = new DeleteTreeNodes_1273();

        assertResultWithSolver(solver, 0, 2, new int[] {-1, 0}, new int[] {1, -1});
        assertResultWithSolver(solver, 3, 3, new int[] {-1, 0, 0}, new int[] {1, 2, 3});
        assertResultWithSolver(solver, 0, 1, new int[] {-1}, new int[] {0});
    }

    @Test
    public void exhaustiveOrderedSmallTreesMatchIndependentOracle() {
        int[] parent = new int[6];
        parent[0] = -1;
        int[] values = new int[6];
        int[] checked = {0};
        for (int rootValue = -1; rootValue <= 1; rootValue++) {
            values[0] = rootValue;
            enumerateSmallCases(1, parent, values, checked);
        }

        // n=6, every parent[i] < i and every value in {-1, 0, 1}.
        assertEquals(87_480, checked[0]);
    }

    @Test
    public void seededRandomTreesMatchIndependentPostOrderOracle() {
        Random random = new Random(1273L);

        for (int trial = 0; trial < 250; trial++) {
            int nodes = 1 + random.nextInt(45);
            int[] parent = new int[nodes];
            int[] values = new int[nodes];
            parent[0] = -1;
            for (int node = 1; node < nodes; node++) {
                parent[node] = random.nextInt(node);
            }
            for (int node = 0; node < nodes; node++) {
                values[node] = random.nextInt(7) - 3;
            }

            assertEquals(expectedRemaining(nodes, parent, values),
                    new DeleteTreeNodes_1273().deleteTreeNodes(nodes, parent, values.clone()),
                    "trial=" + trial + ", parent=" + Arrays.toString(parent)
                            + ", values=" + Arrays.toString(values));
        }
    }

    @Test
    public void maximumNodeCountStarWithZeroLeavesRemovesAllLeaves() {
        int nodes = 10_000;
        int[] parent = new int[nodes];
        int[] values = new int[nodes];
        parent[0] = -1;
        values[0] = 1;
        Arrays.fill(parent, 1, nodes, 0);

        assertResult(1, nodes, parent, values);
    }

    @Test
    public void maximumNodeCountChainStaysWithinValueBounds() {
        int nodes = 10_000;
        int[] parent = new int[nodes];
        int[] values = new int[nodes];
        parent[0] = -1;
        Arrays.fill(values, 100_000);
        for (int node = 1; node < nodes; node++) {
            parent[node] = node - 1;
        }

        assertResult(nodes, nodes, parent, values);
    }

    @Test
    public void maximumDepthChainWithNegativeLeafHasNoZeroSubtree() {
        int nodes = 10_000;
        int[] parent = new int[nodes];
        int[] values = new int[nodes];
        parent[0] = -1;
        Arrays.fill(values, 1);
        values[nodes - 1] = -100_000;
        for (int node = 1; node < nodes; node++) {
            parent[node] = node - 1;
        }

        assertResult(nodes, nodes, parent, values);
    }

    private static void assertResult(int expected, int nodes, int[] parent, int[] values) {
        assertEquals(expected, expectedRemaining(nodes, parent, values),
                "fixture expected value must agree with the independent oracle");
        assertEquals(expected, new DeleteTreeNodes_1273().deleteTreeNodes(nodes, parent.clone(), values.clone()));
    }

    private static void assertResultWithSolver(DeleteTreeNodes_1273 solver, int expected, int nodes,
            int[] parent, int[] values) {
        assertEquals(expected, expectedRemaining(nodes, parent, values));
        assertEquals(expected, solver.deleteTreeNodes(nodes, parent.clone(), values.clone()));
    }

    /** Enumerates all ordered-parent trees through six nodes and all ternary value assignments. */
    private static void enumerateSmallCases(int node, int[] parent, int[] values, int[] checked) {
        if (node > 5) {
            checked[0]++;
            int nodes = 6;
            assertEquals(expectedRemaining(nodes, parent, values),
                    new DeleteTreeNodes_1273().deleteTreeNodes(nodes, parent.clone(), values.clone()));
            return;
        }
        for (int parentIndex = 0; parentIndex < node; parentIndex++) {
            parent[node] = parentIndex;
            for (int value = -1; value <= 1; value++) {
                values[node] = value;
                enumerateSmallCases(node + 1, parent, values, checked);
            }
        }
    }

    /** Returns the independent oracle result for a valid rooted parent array. */
    private static int expectedRemaining(int nodes, int[] parent, int[] values) {
        if (nodes == 0 || parent == null || values == null) {
            return 0;
        }

        @SuppressWarnings("unchecked")
        List<Integer>[] children = new List[nodes];
        for (int node = 0; node < nodes; node++) {
            children[node] = new ArrayList<>();
        }
        for (int node = 1; node < nodes; node++) {
            children[parent[node]].add(node);
        }

        List<Integer> order = new ArrayList<>(nodes);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            order.add(node);
            for (int child : children[node]) {
                stack.push(child);
            }
        }

        long[] sums = new long[nodes];
        int[] counts = new int[nodes];
        for (int index = order.size() - 1; index >= 0; index--) {
            int node = order.get(index);
            sums[node] = values[node];
            counts[node] = 1;
            for (int child : children[node]) {
                sums[node] += sums[child];
                if (sums[child] != 0) {
                    counts[node] += counts[child];
                }
            }
            if (sums[node] == 0) {
                counts[node] = 0;
            }
        }
        return counts[0];
    }
}
