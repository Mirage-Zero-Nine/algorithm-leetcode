package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReconstructQueue_406Test {
    private final ReconstructQueue_406 solver = new ReconstructQueue_406();

    @Test public void testBasic() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] expected = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testSingle() {
        int[][] people = {{5, 0}};
        int[][] expected = {{5, 0}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testEmpty() {
        int[][] people = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testSameHeight() {
        int[][] people = {{5, 0}, {5, 1}, {5, 2}};
        int[][] expected = {{5, 0}, {5, 1}, {5, 2}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testAscending() {
        int[][] people = {{6, 0}, {5, 0}, {4, 0}};
        int[][] expected = {{4, 0}, {5, 0}, {6, 0}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }
}
