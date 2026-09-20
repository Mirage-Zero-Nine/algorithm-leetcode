package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MinHeightShelves_1105Test {

    private final MinHeightShelves_1105 test = new MinHeightShelves_1105();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minHeightShelves(new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}}, 4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minHeightShelves(new int[][]{{1, 1}}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.minHeightShelves(new int[][]{{1, 3}, {2, 4}, {3, 2}}, 6));
    }

    @Test
    public void testHappyAllFitOneShelf() {
        // All books fit on one shelf, height = max height
        assertEquals(5, test.minHeightShelves(new int[][]{{1, 3}, {2, 5}, {1, 4}}, 10));
    }

    @Test
    public void testHappyEachBookOwnShelf() {
        // Shelf width = 1, each book gets its own shelf
        assertEquals(9, test.minHeightShelves(new int[][]{{1, 3}, {1, 4}, {1, 2}}, 1));
    }

    @Test
    public void testHappyTwoShelves() {
        assertEquals(7, test.minHeightShelves(new int[][]{{1, 3}, {1, 4}}, 1));
    }

    @Test
    public void testEdgeSingleBookWideShelf() {
        assertEquals(7, test.minHeightShelves(new int[][]{{3, 7}}, 100));
    }

    @Test
    public void testEdgeTwoBooksExactFit() {
        // Two books exactly fill the shelf width
        assertEquals(5, test.minHeightShelves(new int[][]{{3, 5}, {3, 4}}, 6));
    }

    @Test
    public void testNegativeLikeNarrowShelf() {
        // Each book must go on its own shelf since width == book width
        assertEquals(10, test.minHeightShelves(new int[][]{{2, 3}, {2, 4}, {2, 3}}, 2));
    }

    @Test
    public void testEdgeAllSameBooks() {
        // 5 books of width 2, height 3, shelf width 4 -> 2 per shelf, 3 shelves
        assertEquals(9, test.minHeightShelves(new int[][]{{2, 3}, {2, 3}, {2, 3}, {2, 3}, {2, 3}}, 4));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[][] books = new int[n][2];
        for (int i = 0; i < n; i++) {
            books[i][0] = 1;
            books[i][1] = 1;
        }
        // All books width 1, height 1, shelf width 10 -> 10 shelves of height 1 each = 10
        assertEquals(10, test.minHeightShelves(books, 10));
    }

    @ParameterizedTest
    @CsvSource({"'1:2;1:3',3","'2:4;2:5',5","'1:5;1:1;1:4',5","'3:3;2:2;1:4',4","'2:6;2:6;2:6',6","'1:2;2:5;1:3',5","'3:7;1:2',7","'1:4;1:4;1:4;1:4',4","'2:3;3:5;1:2',5","'1:10;1:1;1:1',10"})
    public void testAdditionalShelfPacking(String encoded, int expected) {
        String[] rows = encoded.split(";"); int[][] books = new int[rows.length][2];
        for (int i = 0; i < rows.length; i++) { String[] values = rows[i].split(":"); books[i][0] = Integer.parseInt(values[0]); books[i][1] = Integer.parseInt(values[1]); }
        assertEquals(expected, test.minHeightShelves(books, 10));
    }
}
