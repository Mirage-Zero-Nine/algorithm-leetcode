package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GetRow_119Test {

    private final GetRow_119 test = new GetRow_119();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 3, 3, 1), test.getRow(3));
        assertEquals(List.of(1, 4, 6, 4, 1), test.getRow(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(1), test.getRow(0));
        assertEquals(List.of(1, 1), test.getRow(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(1, 5, 10, 10, 5, 1), test.getRow(5));
    }

    @Test
    public void testRow2() {
        assertEquals(List.of(1, 2, 1), test.getRow(2));
    }

    @Test
    public void testRow6() {
        assertEquals(List.of(1, 6, 15, 20, 15, 6, 1), test.getRow(6));
    }

    @Test
    public void testRow7() {
        assertEquals(List.of(1, 7, 21, 35, 35, 21, 7, 1), test.getRow(7));
    }

    @Test
    public void testRow10() {
        assertEquals(List.of(1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1), test.getRow(10));
    }

    @Test
    public void testSymmetry() {
        List<Integer> row = test.getRow(8);
        for (int i = 0; i < row.size() / 2; i++) {
            assertEquals(row.get(i), row.get(row.size() - 1 - i));
        }
    }

    @Test
    public void testFirstAndLastAlwaysOne() {
        for (int i = 0; i <= 15; i++) {
            List<Integer> row = test.getRow(i);
            assertEquals(1, row.get(0));
            assertEquals(1, row.get(row.size() - 1));
        }
    }

    @Test
    public void testGiantRow() {
        List<Integer> row = test.getRow(20);
        assertEquals(21, row.size());
        assertEquals(1, row.get(0));
        assertEquals(184756, row.get(10));
    }

    @Test
    public void testAdditional1() {
        assertEquals(java.util.List.of(1), test.getRow(0));
    }

    @Test
    public void testAdditional2() {
        assertEquals(java.util.List.of(1,1), test.getRow(1));
    }

    @Test
    public void testAdditional3() {
        assertEquals(java.util.List.of(1,2,1), test.getRow(2));
    }

    @Test
    public void testAdditional4() {
        assertEquals(java.util.List.of(1,3,3,1), test.getRow(3));
    }

    @Test
    public void testAdditional5() {
        assertEquals(java.util.List.of(1,4,6,4,1), test.getRow(4));
    }

    @Test
    public void testAdditional6() {
        assertEquals(java.util.List.of(1,5,10,10,5,1), test.getRow(5));
    }

    @Test
    public void testAdditional7() {
        assertEquals(java.util.List.of(1,6,15,20,15,6,1), test.getRow(6));
    }

    @Test
    public void testAdditional8() {
        assertEquals(java.util.List.of(1,7,21,35,35,21,7,1), test.getRow(7));
    }

    @Test
    public void testAdditional9() {
        assertEquals(java.util.List.of(1,8,28,56,70,56,28,8,1), test.getRow(8));
    }

    @Test
    public void testAdditional10() {
        assertEquals(java.util.List.of(1,9,36,84,126,126,84,36,9,1), test.getRow(9));
    }
}
