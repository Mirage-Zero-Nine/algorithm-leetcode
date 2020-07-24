package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.*;

/**
 * @author BorisMirage
 * Time: 2019/08/12 13:28
 * Created with IntelliJ IDEA
 */

public class VerticalOrder_314 {
    /**
     * DFS.
     * During the DFS traversal, pass the horizon distance and node height to next node.
     * For left child, horizon distance - 1, for right child, horizon distance + 1. Both child will add 1 to height.
     * Use a linked list to store node value. And finally sort list.
     * If nodes have same horizon distance, smaller height first.
     * If they have same height, leave it. The nature order of this list is from left to right.
     *
     * @param root root node
     * @return list of non-empty reports in order of X coordinate
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {

        LinkedList<int[]> list = new LinkedList<>();

        dfs(root, 0, 0, list);

        List<List<Integer>> out = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0] && o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        if (list.size() != 0) {
            int distance = list.getFirst()[0];

            while (!list.isEmpty()) {
                int[] arr = list.getFirst();
                list.removeFirst();
                if (distance != arr[0]) {
                    distance = arr[0];
                    out.add(new ArrayList<>(l));
                    l = new ArrayList<>();
                }
                l.add(arr[2]);
            }
            if (l.size() != 0) {
                out.add(l);
            }
        }

        return out;
    }

    /**
     * DFS. During the DFS, add current node to list.
     *
     * @param root     root node
     * @param distance horizon distance
     * @param height   height of current node
     */
    private void dfs(TreeNode root, int distance, int height, LinkedList<int[]> list) {
        if (root == null) {
            return;
        }

        list.add(new int[]{distance, height, root.val});

        dfs(root.left, distance - 1, height + 1, list);
        dfs(root.right, distance + 1, height + 1, list);
    }
}
