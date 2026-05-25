package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testNumRowsTen() {
        List<List<Integer>> result = test.generate(10);
        assertEquals(10, result.size());
        // Row 9: 1 9 36 84 126 126 84 36 9 1
        assertEquals(List.of(1, 9, 36, 84, 126, 126, 84, 36, 9, 1), result.get(9));
    }

    @Test
    public void testNumRowsThirtyDeep() {
        List<List<Integer>> result = test.generate(30);
        assertEquals(30, result.size());
        assertEquals(30, result.get(29).size());
        // C(29,1) = 29
        assertEquals(29, result.get(29).get(1));
        // C(29,2) = 406
        assertEquals(406, result.get(29).get(2));
    }

    @Test
    public void testPropertyRowSizeEqualsIPlusOne() {
        List<List<Integer>> result = test.generate(25);
        for (int i = 0; i < 25; i++) {
            assertEquals(i + 1, result.get(i).size(), "Row " + i + " should have " + (i + 1) + " elements");
        }
    }

    @Test
    public void testPropertyFirstAndLastAreOne() {
        List<List<Integer>> result = test.generate(30);
        for (int i = 0; i < 30; i++) {
            assertEquals(1, result.get(i).get(0));
            assertEquals(1, result.get(i).get(i));
        }
    }

    @Test
    public void testPropertyAdjacentSumRelation() {
        List<List<Integer>> result = test.generate(15);
        for (int i = 2; i < 15; i++) {
            for (int j = 1; j < i; j++) {
                assertEquals(
                    result.get(i - 1).get(j - 1) + result.get(i - 1).get(j),
                    result.get(i).get(j),
                    "row[" + i + "][" + j + "] should equal row[" + (i - 1) + "][" + (j - 1) + "] + row[" + (i - 1) + "][" + j + "]"
                );
            }
        }
    }

    @Test
    public void testPropertyRowSumEqualsPowerOfTwo() {
        List<List<Integer>> result = test.generate(20);
        for (int i = 0; i < 20; i++) {
            int sum = result.get(i).stream().mapToInt(Integer::intValue).sum();
            assertEquals(1 << i, sum, "Sum of row " + i + " should be 2^" + i);
        }
    }

    @Test
    public void testNumRowsFiveExplicit() {
        List<List<Integer>> expected = List.of(
            List.of(1),
            List.of(1, 1),
            List.of(1, 2, 1),
            List.of(1, 3, 3, 1),
            List.of(1, 4, 6, 4, 1)
        );
        assertEquals(expected, test.generate(5));
    }

    @Test
    public void testNumRowsZeroReturnsEmpty() {
        assertTrue(test.generate(0).isEmpty());
    }

    @Test
    public void testNumRowsOneReturnsSingleRow() {
        assertEquals(List.of(List.of(1)), test.generate(1));
    }

    @Test
    public void testNumRowsTwoExplicit() {
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.generate(2));
    }
}
