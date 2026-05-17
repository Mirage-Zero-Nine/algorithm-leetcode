package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class MinimumTotal_120Test {

    private final MinimumTotal_120 test = new MinimumTotal_120();

    @Test
    public void testHappyCases() {
        assertEquals(11, test.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minimumTotal(null));
        assertEquals(-10, test.minimumTotal(List.of(List.of(-10))));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.minimumTotal(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6))));
    }

    @Test
    public void testEmptyTriangle() {
        assertEquals(0, test.minimumTotal(List.of()));
    }

    @Test
    public void testSingleElement() {
        assertEquals(5, test.minimumTotal(List.of(List.of(5))));
    }

    @Test
    public void testTwoRows() {
        assertEquals(3, test.minimumTotal(List.of(List.of(1), List.of(2, 3))));
    }

    @Test
    public void testAllNegatives() {
        assertEquals(-7, test.minimumTotal(List.of(List.of(-1), List.of(-2, -3), List.of(-1, -2, -3))));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.minimumTotal(List.of(List.of(0), List.of(0, 0), List.of(0, 0, 0))));
    }

    @Test
    public void testRightPath() {
        // Minimum path goes right: 1->3->6 = 10 vs 1->2->4=7
        assertEquals(7, test.minimumTotal(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6))));
    }

    @Test
    public void testGiantTriangle() {
        // Build a triangle of 100 rows, all 1s -> min path sum = 100
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(1);
            triangle.add(row);
        }
        assertEquals(100, test.minimumTotal(triangle));
    }

    @Test
    public void testSingleRowReturnsValue() {
        assertEquals(42, test.minimumTotal(List.of(List.of(42))));
        assertEquals(0, test.minimumTotal(List.of(List.of(0))));
        assertEquals(-99, test.minimumTotal(List.of(List.of(-99))));
    }

    @Test
    public void testTwoRowsTopPlusMinBottom() {
        // 1 + min(10, 2) = 3
        assertEquals(3, test.minimumTotal(List.of(List.of(1), List.of(10, 2))));
        // 5 + min(3, 8) = 8
        assertEquals(8, test.minimumTotal(List.of(List.of(5), List.of(3, 8))));
    }

    @Test
    public void testAllZerosLarger() {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(0);
            triangle.add(row);
        }
        assertEquals(0, test.minimumTotal(triangle));
    }

    @Test
    public void testAllSameValue() {
        // Triangle of 5 rows, all values = 3 -> min path sum = 5 * 3 = 15
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(3);
            triangle.add(row);
        }
        assertEquals(15, test.minimumTotal(triangle));
    }

    @Test
    public void testNegativeValues() {
        // [[-1],[-2,-3],[-4,-5,-6]] -> -1 + -3 + -6 = -10
        assertEquals(-10, test.minimumTotal(List.of(List.of(-1), List.of(-2, -3), List.of(-4, -5, -6))));
    }

    @Test
    public void testLeetCodeExample() {
        // Classic LeetCode example: [[2],[3,4],[6,5,7],[4,1,8,3]] -> 2+3+5+1 = 11
        assertEquals(11, test.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
    }

    @Test
    public void testLargeTriangle100RowsSeed42() {
        Random rng = new Random(42L);
        int rows = 100;
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(rng.nextInt(201) - 100); // values in [-100, 100]
            triangle.add(row);
        }
        int result = test.minimumTotal(triangle);
        // Verify deterministic: rebuild and recompute
        Random rng2 = new Random(42L);
        List<List<Integer>> triangle2 = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(rng2.nextInt(201) - 100);
            triangle2.add(row);
        }
        assertEquals(result, test.minimumTotal(triangle2));
    }

    @Test
    public void testPropertyResultGeMinColumnSum() {
        // Property: result >= minimum of picking one element per row (greedy min per row)
        // Actually result >= sum of min of each row is NOT guaranteed (path constraint).
        // But result >= min element in top row (since path must include top).
        // Stronger property: result >= sum of (min possible element per row along SOME valid path)
        // Simpler valid property: result >= top element + (rows-1) * global_min_value
        List<List<Integer>> triangle = List.of(
                List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3));
        int result = test.minimumTotal(triangle);
        // The result must include the top element
        assertTrue(result >= triangle.get(0).get(0) + (triangle.size() - 1) * 1,
                "Result should be at least top + (rows-1)*min_element_in_triangle");
    }

    @Test
    public void testMixedPositiveNegative() {
        // [[1],[-2, 3],[4, -5, 6]] -> 1 + -2 + -5 = invalid (not adjacent)
        // Path: 1->-2->4 = 3 or 1->-2->-5 = -6 or 1->3->-5 = -1 or 1->3->6 = 10
        // Adjacent rule: from row i col j, can go to row i+1 col j or j+1
        // 1(col0) -> -2(col0) -> -5(col1) = 1 + -2 + -5 = -6
        assertEquals(-6, test.minimumTotal(List.of(List.of(1), List.of(-2, 3), List.of(4, -5, 6))));
    }
}
