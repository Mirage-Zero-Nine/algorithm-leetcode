package solution.graph;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2023/04/21 14:11
 * Created with IntelliJ IDEA
 */

public class FindMinHeightTrees310Test {

    private final FindMinHeightTrees_310 test = new FindMinHeightTrees_310();

    @Test
    public void test1() {
        Set<Integer> expected = Sets.newHashSet(3);
        assertTrue(expected.containsAll(test.findMinHeightTrees(9, test1)));
    }

    @Test
    public void test2() {
        Set<Integer> expected = Sets.newHashSet(3, 2);
        assertTrue(expected.containsAll(test.findMinHeightTrees(8, test2)));
    }

    @Test
    public void test3() {
        Set<Integer> expected = Sets.newHashSet(3, 4);
        assertTrue(expected.containsAll(test.findMinHeightTrees(6, test3)));
    }

    private final static int[][] test1 = {{0, 1}, {6, 1}, {7, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 8}};
    private final static int[][] test2 = {{0, 1}, {6, 1}, {7, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};
    private final static int[][] test3 = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
}