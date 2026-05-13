package solution.intervals;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveInterval_1272Test {
    private final RemoveInterval_1272 solver = new RemoveInterval_1272();

    @Test public void testBasic() {
        int[][] intervals = {{0, 2}, {3, 4}, {5, 7}};
        int[] toBeRemoved = {1, 6};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(0, 1), List.of(6, 7)), res);
    }

    @Test public void testPartialRemove() {
        int[][] intervals = {{0, 5}};
        int[] toBeRemoved = {2, 3};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(0, 2), List.of(3, 5)), res);
    }

    @Test public void testFullRemove() {
        int[][] intervals = {{0, 5}};
        int[] toBeRemoved = {-1, 10};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertTrue(res.isEmpty());
    }

    @Test public void testNoOverlap() {
        int[][] intervals = {{0, 2}, {3, 4}};
        int[] toBeRemoved = {5, 10};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(0, 2), List.of(3, 4)), res);
    }

    @Test public void testNullInput() {
        List<List<Integer>> res = solver.removeInterval(null, new int[]{1, 2});
        assertTrue(res.isEmpty());
    }

    @Test public void testRemoveAtStart() {
        int[][] intervals = {{0, 5}, {7, 10}};
        int[] toBeRemoved = {0, 3};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(3, 5), List.of(7, 10)), res);
    }

    @Test public void testRemoveAtEnd() {
        int[][] intervals = {{0, 5}, {7, 10}};
        int[] toBeRemoved = {8, 12};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(0, 5), List.of(7, 8)), res);
    }

    @Test public void testRemoveExactInterval() {
        int[][] intervals = {{0, 2}, {3, 5}, {7, 10}};
        int[] toBeRemoved = {3, 5};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(0, 2), List.of(7, 10)), res);
    }

    @Test public void testRemoveBeforeAll() {
        int[][] intervals = {{5, 10}, {15, 20}};
        int[] toBeRemoved = {0, 3};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertEquals(List.of(List.of(5, 10), List.of(15, 20)), res);
    }

    @Test public void testEmptyIntervals() {
        int[][] intervals = {};
        int[] toBeRemoved = {1, 5};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        assertTrue(res.isEmpty());
    }

    @Test public void testGiantCase() {
        int size = 5000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 4, i * 4 + 2};
        }
        // Remove interval that covers middle portion
        int[] toBeRemoved = {2000, 10000};
        List<List<Integer>> res = solver.removeInterval(intervals, toBeRemoved);
        // Should have intervals before 2000 and after 10000, plus partial overlaps
        assertTrue(res.size() > 0);
    }
}
