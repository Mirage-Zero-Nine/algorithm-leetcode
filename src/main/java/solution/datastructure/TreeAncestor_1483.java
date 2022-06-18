package solution.datastructure;

/**
 * You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i.
 * The root of the tree is node 0.
 * Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node.
 * If there is no such ancestor, return -1.
 * The k-th ancestor of a tree node is the k-th node in the path from that node to the root.
 *
 * @author BorisMirage
 * Time: 2020/06/13 20:36
 * Created with IntelliJ IDEA
 */

public class TreeAncestor_1483 {
    int[][] ancestor;           // save 2^n th ancestor of each node
    int maxStepLength;          // max step length

    /**
     * Binary lifting.
     * Create a 2D table that stores 2^n th ancestor of each node.
     * When searching for kth ancestor, find the largest 2^n that is smaller than k.
     * Then narrow the length of step until the kth ancestor is found.
     *
     * @param n      number of nodes
     * @param parent first ancestor of each node
     */
    public TreeAncestor_1483(int n, int[] parent) {

        maxStepLength = (int) (Math.log(n) / Math.log(2)) + 1;     // max possible steps to find ancestor
        ancestor = new int[maxStepLength][n];
        ancestor[0] = parent;

        for (int i = 1; i < maxStepLength; i++) {        // first step is the input array
            for (int j = 0; j < n; j++) {
                int previous = ancestor[i - 1][j];      // find previous node
                ancestor[i][j] = previous == -1 ? -1 : ancestor[i - 1][previous];
            }
        }
    }

    /**
     * Get the kth ancestor.
     * Find the step k' == 2^n, and k' <= k. Then narrow the k' until k' == k.
     * Since the first ancestor of each node is stored, it assures no matter what k is, the kth ancestor can be found.
     *
     * @param node current node
     * @param k    kth ancestor
     * @return kth ancestor of given node
     */
    public int getKthAncestor(int node, int k) {
        int maxPow = this.maxStepLength;            // try step length starts from the largest one
        while (k > 0 && node > -1) {
            if (k >= 1 << maxPow) {                 // if this step is acceptable
                node = ancestor[maxPow][node];      // jump to this ancestor
                k -= 1 << maxPow;
            } else {                                // otherwise, shrink the step length
                maxPow -= 1;
            }
        }

        return node;
    }
}
