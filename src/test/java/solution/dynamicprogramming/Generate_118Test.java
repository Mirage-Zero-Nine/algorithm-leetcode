package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Generate_118Test {

    private final Generate_118 test = new Generate_118();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1)),
            test.generate(5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.generate(0));
        assertEquals(List.of(List.of(1)), test.generate(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.generate(6).size());
    }

    @Test
    public void testTwoRows() {
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.generate(2));
    }

    @Test
    public void testThreeRows() {
        assertEquals(List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1)), test.generate(3));
    }

    @Test
    public void testFourRows() {
        assertEquals(List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1)), test.generate(4));
    }

    @Test
    public void testRowSymmetry() {
        List<List<Integer>> result = test.generate(7);
        List<Integer> row6 = result.get(6);
        // Row should be symmetric
        for (int i = 0; i < row6.size(); i++) {
            assertEquals(row6.get(i), row6.get(row6.size() - 1 - i));
        }
    }

    @Test
    public void testFirstAndLastElementAlwaysOne() {
        List<List<Integer>> result = test.generate(10);
        for (List<Integer> row : result) {
            assertEquals(1, row.get(0));
            assertEquals(1, row.get(row.size() - 1));
        }
    }

    @Test
    public void testRowSizes() {
        List<List<Integer>> result = test.generate(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(i + 1, result.get(i).size());
        }
    }

    @Test
    public void testGiantCase() {
        List<List<Integer>> result = test.generate(20);
        assertEquals(20, result.size());
        // Row 19 (0-indexed) should have 20 elements
        assertEquals(20, result.get(19).size());
        // Check a known value: C(19,1) = 19
        assertEquals(19, result.get(19).get(1));
    }
}
