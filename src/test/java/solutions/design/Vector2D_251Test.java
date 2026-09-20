package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Vector2D_251Test {

    @Test
    public void testHappyCases() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2, 3}, {4}, {5}});
        assertEquals(1, v.next());
        assertEquals(2, v.next());
        assertEquals(3, v.next());
        assertTrue(v.hasNext());
        assertEquals(4, v.next());
        assertEquals(5, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}});
        assertFalse(v.hasNext());
        Vector2D_251 v2 = new Vector2D_251(new int[][]{{1}});
        assertTrue(v2.hasNext());
        assertEquals(1, v2.next());
    }

    @Test
    public void testLargeCase() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int i = 1; i <= 9; i++) assertEquals(i, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testEmptyArray() {
        Vector2D_251 v = new Vector2D_251(new int[][]{});
        assertFalse(v.hasNext());
    }

    @Test
    public void testMultipleEmptyRows() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {1}, {}});
        assertTrue(v.hasNext());
        assertEquals(1, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testSingleElement() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{42}});
        assertTrue(v.hasNext());
        assertEquals(42, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testAllEmptyRows() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {}});
        assertFalse(v.hasNext());
    }

    @Test
    public void testHasNextMultipleCalls() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2}});
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(1, v.next());
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(2, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testNegativeValues() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{-1, -2}, {-3}});
        assertEquals(-1, v.next());
        assertEquals(-2, v.next());
        assertEquals(-3, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testEmptyRowsAtStart() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {5, 6}});
        assertTrue(v.hasNext());
        assertEquals(5, v.next());
        assertEquals(6, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testGiantCase() {
        int[][] data = new int[100][100];
        int val = 0;
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                data[i][j] = val++;
        Vector2D_251 v = new Vector2D_251(data);
        for (int i = 0; i < 10000; i++) {
            assertTrue(v.hasNext());
            assertEquals(i, v.next());
        }
        assertFalse(v.hasNext());
    }

    @Test
    public void testOfficialExampleOperationOrder() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2}, {3}, {4}});
        assertEquals(1, v.next());
        assertEquals(2, v.next());
        assertEquals(3, v.next());
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(4, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testMiddleEmptyRowsPreserveOrder() {
        assertIteratorMatchesOracle(new int[][]{{1}, {}, {2, 3}, {}, {4}, {5, 6}});
    }

    @Test
    public void testTrailingEmptyRows() {
        assertIteratorMatchesOracle(new int[][]{{8, 9}, {}, {}, {}});
    }

    @Test
    public void testDuplicateValuesRemainDistinct() {
        assertIteratorMatchesOracle(new int[][]{{7, 7}, {7}, {}, {7, 7, 7}});
    }

    @Test
    public void testSignedAndBoundaryValues() {
        assertIteratorMatchesOracle(new int[][]{{-500, -1, 0}, {500}, {-500, 500}});
    }

    @Test
    public void testManyLeadingEmptyRows() {
        int[][] vector = new int[25][];
        for (int i = 0; i < vector.length - 1; i++) {
            vector[i] = new int[0];
        }
        vector[24] = new int[]{-4, 0, 4};
        assertIteratorMatchesOracle(vector);
    }

    @Test
    public void testManyInterspersedEmptyRows() {
        int[][] vector = new int[31][];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = i % 3 == 0 ? new int[]{i, -i} : new int[0];
        }
        assertIteratorMatchesOracle(vector);
    }

    @Test
    public void testHasNextDoesNotAdvanceWithinRow() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{10, 20}, {}, {30}});
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(10, v.next());
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(20, v.next());
        assertEquals(30, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testRepeatedHasNextAfterExhaustionIsStable() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1}, {}, {2}});
        assertEquals(1, v.next());
        assertEquals(2, v.next());
        assertFalse(v.hasNext());
        assertFalse(v.hasNext());
        assertFalse(v.hasNext());
    }

    @Test
    public void testIndependentIteratorsOverSameInput() {
        int[][] input = new int[][]{{1, 2}, {}, {3, 4}};
        Vector2D_251 first = new Vector2D_251(input);
        Vector2D_251 second = new Vector2D_251(input);

        assertEquals(1, first.next());
        assertEquals(1, second.next());
        assertEquals(2, first.next());
        assertEquals(2, second.next());
        assertEquals(3, second.next());
        assertEquals(3, first.next());
        assertEquals(4, first.next());
        assertFalse(first.hasNext());
        assertEquals(4, second.next());
        assertFalse(second.hasNext());
    }

    @Test
    public void testConsumingOneIteratorDoesNotChangeAnother() {
        int[][] input = new int[][]{{-2, -1}, {0}, {1, 2}};
        Vector2D_251 consumed = new Vector2D_251(input);
        Vector2D_251 untouched = new Vector2D_251(input);

        assertEquals(-2, consumed.next());
        assertEquals(-1, consumed.next());
        assertEquals(0, consumed.next());
        assertTrue(untouched.hasNext());
        assertEquals(-2, untouched.next());
        assertEquals(-1, untouched.next());
        assertEquals(0, untouched.next());
        assertEquals(1, untouched.next());
        assertEquals(2, untouched.next());
        assertFalse(untouched.hasNext());
    }

    @Test
    public void testDeterministicRaggedVectorMatchesIndependentOracle() {
        int[][] vector = new int[][]{
            {}, {-5, 5, 5}, {0}, {}, {-500}, {500, -500}, {}, {42, 42, 42}, {}
        };
        assertIteratorMatchesOracle(vector);
    }

    @Test
    public void testRandomizedRaggedVectorsAgainstIndependentOracle() {
        Random random = new Random(251L);
        for (int caseNumber = 0; caseNumber < 60; caseNumber++) {
            int rows = random.nextInt(21);
            int[][] vector = new int[rows][];
            for (int row = 0; row < rows; row++) {
                int width = random.nextInt(11);
                vector[row] = new int[width];
                for (int column = 0; column < width; column++) {
                    vector[row][column] = random.nextInt(1001) - 500;
                }
            }
            assertIteratorMatchesOracle(vector);
        }
    }

    @Test
    public void testMaximumRowCount() {
        int[][] vector = new int[200][];
        for (int row = 0; row < vector.length; row++) {
            vector[row] = new int[]{row - 100};
        }
        assertIteratorMatchesOracle(vector);
    }

    @Test
    public void testMaximumRowWidth() {
        int[][] vector = new int[1][500];
        for (int column = 0; column < vector[0].length; column++) {
            vector[0][column] = column - 250;
        }
        assertIteratorMatchesOracle(vector);
    }

    @Test
    public void testMaximumRowsAndElements() {
        int[][] vector = new int[200][500];
        int value = -500;
        for (int row = 0; row < vector.length; row++) {
            for (int column = 0; column < vector[row].length; column++) {
                vector[row][column] = value;
                value = value == 500 ? -500 : value + 1;
            }
        }

        Vector2D_251 iterator = new Vector2D_251(vector);
        assertTrue(iterator.hasNext());
        for (int[] row : vector) {
            for (int expected : row) {
                assertEquals(expected, iterator.next());
            }
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSourceRowsRemainUnchangedAfterTraversal() {
        int[][] vector = new int[][]{{3, 1}, {}, {-2, 8, 0}};
        int[][] snapshot = new int[][]{{3, 1}, {}, {-2, 8, 0}};
        assertIteratorMatchesOracle(vector);
        for (int row = 0; row < vector.length; row++) {
            assertEquals(snapshot[row].length, vector[row].length);
            for (int column = 0; column < vector[row].length; column++) {
                assertEquals(snapshot[row][column], vector[row][column]);
            }
        }
    }

    private static void assertIteratorMatchesOracle(int[][] vector) {
        List<Integer> expected = flattenOracle(vector);
        Vector2D_251 iterator = new Vector2D_251(vector);
        for (int value : expected) {
            assertTrue(iterator.hasNext());
            assertEquals(value, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    private static List<Integer> flattenOracle(int[][] vector) {
        List<Integer> flattened = new ArrayList<>();
        for (int[] row : vector) {
            for (int value : row) {
                flattened.add(value);
            }
        }
        return flattened;
    }
}
