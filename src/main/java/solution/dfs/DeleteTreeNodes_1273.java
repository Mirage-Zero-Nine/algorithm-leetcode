package solution.dfs;

import java.util.HashSet;

/**
 * A tree rooted at node 0 is given as follows:
 * 1. The number of nodes is nodes;
 * 2. The value of the i-th node is value[i];
 * 3. The parent of the i-th node is parent[i].
 * 4. Remove every subtree whose sum of values of nodes is zero.
 * After doing so, return the number of nodes remaining in the tree.
 * Constraints:
 * 1. 1 <= nodes <= 10^4
 * 2. -10^5 <= value[i] <= 10^5
 * 3. parent.length == nodes
 * 4. parent[0] == -1 which indicates that 0 is the root.
 *
 * @author BorisMirage
 * Time: 2019/12/03 14:05
 * Created with IntelliJ IDEA
 */

public class DeleteTreeNodes_1273 {
    /**
     * There is one hidden condition: parent node index is always smaller than child node index.
     * Therefore, it can be directly solved by traverse elements in given array twice.
     * First time, from right to left, calculate subtree sum that from bottom of tree to the top of tree.
     * Second time, from left to right, calculate all nodes that are included in subtree with 0 sum.
     *
     * @param nodes  number of nodes
     * @param parent parent of each node, parent < child
     * @param value  each node's value
     * @return the number of nodes remaining in the tree
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {

        /* Corner case */
        if (nodes == 0 || parent == null || value == null) {
            return 0;
        }

        for (int i = nodes - 1; i >= 0; i--) {
            if (parent[i] != -1) {      // -1 is root node
                value[parent[i]] += value[i];
            }
        }

        if (value[0] == 0) {
            return 0;       // in case entire tree's sum is 0
        }

        HashSet<Integer> s = new HashSet<>();
        int count = 0;
        for (int i = 0; i < nodes; i++) {
            if (value[i] == 0 || s.contains(parent[i])) {
                s.add(i);
                count++;
            }
        }

        return nodes - count;
    }
}
