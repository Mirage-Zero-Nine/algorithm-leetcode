package Solution.Math;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * In the odd numbered rows, the labelling is left to right, while in the even numbered rows, the labelling is right to left.
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 *
 * @author BorisMirage
 * Time: 2019/07/25 09:24
 * Created with IntelliJ IDEA
 */

public class PathInZigZagTree_1104 {
    /**
     * Math problem. Find out the label's level and then calculate each level's position.
     *
     * @param label given label
     * @return path from root to given label
     */
    public List<Integer> pathInZigZagTree(int label) {
        Stack<Integer> s = new Stack<>();
        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (label == 1) {
            out.add(1);
            return out;
        }

        int last = 1, n = 0;

        while (last < label) {
            last = (int) Math.pow(2, n + 1) - 1;
            s.push(last);       // count max value in each layer
            n++;
        }

        int end = s.pop(), start = s.peek() + 1;        // each level's start and end
        int index = (n % 2 == 0) ? (end - label) : (label - start);     // position of node in last level

        while (s.size() >= 1) {

            /* Even level order is from right to left, while even level is from right to left.
             * After the position of label is found, the position of its previous path is current index/2.
             * If level is odd, then the index is start + index, otherwise it is end - index. */
            int num = (n-- % 2 == 0) ? (end - index) : (start + index);
            out.add(0, num);
            end = s.pop();
            start = (s.size() == 0) ? 0 : s.peek() + 1;
            index = index / 2;
        }

        out.add(0, 1);
        return out;
    }
}
