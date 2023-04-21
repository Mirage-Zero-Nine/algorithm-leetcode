package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * Running a vertical line from X = -infinity to X = +infinity.
 * Whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 * @author BorisMirage
 * Time: 2019/08/12 10:30
 * Created with IntelliJ IDEA
 */

public class VerticalTraversal_987 {
    /**
     * DFS.
     * During the DFS traversal, pass the horizon distance and node height to next node.
     * For left child, horizon distance - 1, for right child, horizon distance + 1. Both child will add 1 to height.
     * Use a heap to sort nodes based on horizon distance and height.
     * If nodes have same horizon distance, smaller height first. If they have same height, smaller value first.
     *
     * @param root root node
     * @return list of non-empty reports in order of X coordinate
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        // save node value based on horizon distance and node height
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        dfs(root, 0, 0, pq);

        List<List<Integer>> out = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        if (pq.size() != 0) {
            int distance = pq.peek()[0];
            while (!pq.isEmpty()) {
                int[] arr = pq.poll();

                if (distance != arr[0]) {
                    distance = arr[0];
                    out.add(new ArrayList<>(list));
                    list = new ArrayList<>();
                }
                list.add(arr[2]);
            }
            if (list.size() != 0) {
                out.add(list);
            }
        }
        return out;
    }

    /**
     * DFS. During the DFS, add current node to heap.
     *
     * @param root     root node
     * @param distance horizon distance
     * @param height   height of current node
     */
    private void dfs(TreeNode root, int distance, int height, Queue<int[]> pq) {

        /* Corner case */
        if (root == null) {
            return;
        }

        pq.add(new int[]{distance, height, root.val});

        dfs(root.left, distance - 1, height + 1, pq);
        dfs(root.right, distance + 1, height + 1, pq);
    }
}
