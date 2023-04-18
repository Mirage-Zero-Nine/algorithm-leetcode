package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

        List<int[]> list = new ArrayList<>();

        dfs(root, 0, 0, list);

        List<List<Integer>> out = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        list.sort((o1, o2) -> {
            if (o1[0] == o2[0] && o1[1] != o2[1]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        if (list.size() != 0) {
            int distance = list.get(0)[0];

            while (!list.isEmpty()) {
                int[] arr = list.get(0);
                list.remove(0);
                if (distance != arr[0]) {
                    distance = arr[0];
                    out.add(new ArrayList<>(temp));
                    temp = new ArrayList<>();
                }
                temp.add(arr[2]);
            }
            if (temp.size() != 0) {
                out.add(temp);
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
    private void dfs(TreeNode root, int distance, int height, List<int[]> list) {
        if (root == null) {
            return;
        }

        list.add(new int[]{distance, height, root.val});

        dfs(root.left, distance - 1, height + 1, list);
        dfs(root.right, distance + 1, height + 1, list);
    }
}
