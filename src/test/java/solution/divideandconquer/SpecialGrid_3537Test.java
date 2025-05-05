package solution.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/05 14:17
 * Created with IntelliJ IDEA
 */

public class SpecialGrid_3537Test {

    private final SpecialGrid_3537 test = new SpecialGrid_3537();

    @Test
    public void testSpecialGridN0() {
        int[][] expected = {
                {0},
        };

        int[][] actual = test.specialGrid(0);
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    @Test
    public void testSpecialGridN1() {
        int[][] expected = {
                {3, 0},
                {2, 1}
        };

        int[][] actual = test.specialGrid(1);
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    @Test
    public void testSpecialGridN2() {
        int[][] expected = {
                {15, 12, 3, 0},
                {14, 13, 2, 1},
                {11, 8, 7, 4},
                {10, 9, 6, 5}
        };

        int[][] actual = test.specialGrid(2);
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    @Test
    public void testSpecialGridN3() {
        int[][] expected = {
                {63, 60, 51, 48, 15, 12, 3, 0},
                {62, 61, 50, 49, 14, 13, 2, 1},
                {59, 56, 55, 52, 11, 8, 7, 4},
                {58, 57, 54, 53, 10, 9, 6, 5},
                {47, 44, 35, 32, 31, 28, 19, 16},
                {46, 45, 34, 33, 30, 29, 18, 17},
                {43, 40, 39, 36, 27, 24, 23, 20},
                {42, 41, 38, 37, 26, 25, 22, 21}
        };

        int[][] actual = test.specialGrid(3);
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    @Test
    public void testSpecialGridN4() {
        int[][] expected = {
                {255, 252, 243, 240, 207, 204, 195, 192, 63, 60, 51, 48, 15, 12, 3, 0},
                {254, 253, 242, 241, 206, 205, 194, 193, 62, 61, 50, 49, 14, 13, 2, 1},
                {251, 248, 247, 244, 203, 200, 199, 196, 59, 56, 55, 52, 11, 8, 7, 4},
                {250, 249, 246, 245, 202, 201, 198, 197, 58, 57, 54, 53, 10, 9, 6, 5},
                {239, 236, 227, 224, 223, 220, 211, 208, 47, 44, 35, 32, 31, 28, 19, 16},
                {238, 237, 226, 225, 222, 221, 210, 209, 46, 45, 34, 33, 30, 29, 18, 17},
                {235, 232, 231, 228, 219, 216, 215, 212, 43, 40, 39, 36, 27, 24, 23, 20},
                {234, 233, 230, 229, 218, 217, 214, 213, 42, 41, 38, 37, 26, 25, 22, 21},
                {191, 188, 179, 176, 143, 140, 131, 128, 127, 124, 115, 112, 79, 76, 67, 64},
                {190, 189, 178, 177, 142, 141, 130, 129, 126, 125, 114, 113, 78, 77, 66, 65},
                {187, 184, 183, 180, 139, 136, 135, 132, 123, 120, 119, 116, 75, 72, 71, 68},
                {186, 185, 182, 181, 138, 137, 134, 133, 122, 121, 118, 117, 74, 73, 70, 69},
                {175, 172, 163, 160, 159, 156, 147, 144, 111, 108, 99, 96, 95, 92, 83, 80},
                {174, 173, 162, 161, 158, 157, 146, 145, 110, 109, 98, 97, 94, 93, 82, 81},
                {171, 168, 167, 164, 155, 152, 151, 148, 107, 104, 103, 100, 91, 88, 87, 84},
                {170, 169, 166, 165, 154, 153, 150, 149, 106, 105, 102, 101, 90, 89, 86, 85}
        };


        int[][] actual = test.specialGrid(4);
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }
}