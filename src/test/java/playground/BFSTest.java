package playground;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSTest {
    private final BFS bfs = new BFS();

    @Test void nullTreeIsEmpty() { assertEquals(List.of(), bfs.breadthFirstSearch(null)); }
    @Test void singleton() { assertEquals(List.of(List.of(7)), bfs.breadthFirstSearch(new TreeNode(7))); }
    @Test void completeTree() {
        TreeNode r = new TreeNode(1); r.left = new TreeNode(2); r.right = new TreeNode(3);
        r.left.left = new TreeNode(4); r.left.right = new TreeNode(5); r.right.left = new TreeNode(6);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6)), bfs.breadthFirstSearch(r));
    }
    @Test void preservesLeftToRightOrder() {
        TreeNode r = new TreeNode(0); r.left = new TreeNode(-1); r.right = new TreeNode(1);
        r.left.right = new TreeNode(2); r.right.left = new TreeNode(3);
        assertEquals(List.of(List.of(0), List.of(-1, 1), List.of(2, 3)), bfs.breadthFirstSearch(r));
    }
    @Test void leftSpine() { TreeNode r = new TreeNode(1); r.left = new TreeNode(2); r.left.left = new TreeNode(3); assertEquals(List.of(List.of(1), List.of(2), List.of(3)), bfs.breadthFirstSearch(r)); }
    @Test void rightSpine() { TreeNode r = new TreeNode(1); r.right = new TreeNode(2); r.right.right = new TreeNode(3); assertEquals(List.of(List.of(1), List.of(2), List.of(3)), bfs.breadthFirstSearch(r)); }
    @Test void negativeAndDuplicateValues() { TreeNode r = new TreeNode(-1); r.left = new TreeNode(-1); r.right = new TreeNode(0); assertEquals(List.of(List.of(-1), List.of(-1, 0)), bfs.breadthFirstSearch(r)); }
    @Test void sparseCrossParentLevel() { TreeNode r = new TreeNode(1); r.left = new TreeNode(2); r.right = new TreeNode(3); r.left.right = new TreeNode(4); r.right.left = new TreeNode(5); r.right.right = new TreeNode(6); assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6)), bfs.breadthFirstSearch(r)); }
    @Test void repeatedCallsDoNotShareResults() { TreeNode r = new TreeNode(1); List<List<Integer>> first = bfs.breadthFirstSearch(r); first.get(0).add(99); assertEquals(List.of(List.of(1)), bfs.breadthFirstSearch(r)); }
}
