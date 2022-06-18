package solution.bfs;

import library.NestedInteger;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Weight is defined from bottom up.
 * i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 *
 * @author BorisMirage
 * Time: 2019/05/30 15:24
 * Created with IntelliJ IDEA
 */

public class DepthSumInverse_364 {
    /**
     * BFS, similar to level order traversal.
     * The structure of nested integer is similar to a rootless tree, where the actual root is an "invisible" node.
     * Nested integer can be represented as node of tree, and getList() is calling the child of current node.
     * Meanwhile, the weight of each level is same. Therefore, use BFS to collect nodes in each level.
     * The target is to calculate the sum of each node with different depth, depth is reversed compare to tree depth.
     * To calculate sum of nodes, simply add previous sum one more time after calculate each level's sum.
     * The deeper the tree is, the more time upper nodes sum will be added, and will be exactly same as their depth.
     *
     * @param nestedList given nested list
     * @return sum of all integers in the list weighted by depth
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {

        /* Corner case */
        if (nestedList == null) {
            return 0;
        }

        int sum = 0, previousSum = 0;

        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLayer = new LinkedList<>();     // collect next layer's node

            for (NestedInteger l : nestedList) {
                if (l.isInteger()) {        // if current nested integer has value
                    previousSum += l.getInteger();
                } else {                    // otherwise, collect next layer
                    nextLayer.addAll(l.getList());
                }
            }

            sum += previousSum;
            nestedList = (nextLayer);
        }

        return sum;
    }
}
