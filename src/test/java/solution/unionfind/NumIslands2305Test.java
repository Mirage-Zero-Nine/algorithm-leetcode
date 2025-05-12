package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2025/05/11 21:21
 * Created with IntelliJ IDEA
 */

public class NumIslands2305Test {
    private NumIslands2_305 test;

    @BeforeEach
    void setUp() {
        test = new NumIslands2_305();
    }

    @Test
    public void testEmptyGrid() {
        int[][] positions = {};
        List<Integer> expected = List.of();
        assertIterableEquals(expected, test.numIslands2(0, 0, positions));
    }

    @Test
    public void testSinglePosition() {
        int[][] positions = {{0, 0}};
        List<Integer> expected = List.of(1);
        assertIterableEquals(expected, test.numIslands2(1, 1, positions));
    }

    @Test
    public void testMultiplePositions() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1);
        assertIterableEquals(expected, test.numIslands2(2, 2, positions));
    }

    @Test
    public void testDisconnectedIslands() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {2, 2}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 2);
        assertIterableEquals(expected, test.numIslands2(3, 3, positions));
    }

    @Test
    public void testLargeGrid() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 2, 3, 4);
        assertIterableEquals(expected, test.numIslands2(5, 5, positions));
    }

    @Test
    public void testComplexScenario() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {3, 3}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 1, 2);
        assertIterableEquals(expected, test.numIslands2(4, 4, positions));
    }

    @Test
    public void testZeroRowsAndColumns() {
        int[][] positions = {{0, 0}};
        List<Integer> expected = Arrays.asList();
        assertIterableEquals(expected, test.numIslands2(0, 0, positions));
    }
}