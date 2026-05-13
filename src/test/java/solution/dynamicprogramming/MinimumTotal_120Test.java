package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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
}
