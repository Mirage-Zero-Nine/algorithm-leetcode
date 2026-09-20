package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rotate_189Test {

    private final Rotate_189 test = new Rotate_189();

    @Test
    public void testHappyCases() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        test.rotate(arr, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        int[] arr = {1};
        test.rotate(arr, 5);
        assertArrayEquals(new int[]{1}, arr);

        int[] arr2 = {1, 2};
        test.rotate(arr2, 2);
        assertArrayEquals(new int[]{1, 2}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        test.rotate(arr, 4);
        assertArrayEquals(new int[]{7, 8, 9, 10, 1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    public void testRotateByZero() {
        int[] arr = {1, 2, 3};
        test.rotate(arr, 0);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    public void testRotateByLength() {
        int[] arr = {1, 2, 3, 4};
        test.rotate(arr, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr);
    }

    @Test
    public void testRotateByOne() {
        int[] arr = {1, 2, 3, 4, 5};
        test.rotate(arr, 1);
        assertArrayEquals(new int[]{5, 1, 2, 3, 4}, arr);
    }

    @Test
    public void testRotateGreaterThanLength() {
        int[] arr = {1, 2, 3};
        test.rotate(arr, 5);
        assertArrayEquals(new int[]{2, 3, 1}, arr);
    }

    @Test
    public void testTwoElementsRotateOne() {
        int[] arr = {1, 2};
        test.rotate(arr, 1);
        assertArrayEquals(new int[]{2, 1}, arr);
    }

    @Test
    public void testAllSameElements() {
        int[] arr = {7, 7, 7, 7};
        test.rotate(arr, 2);
        assertArrayEquals(new int[]{7, 7, 7, 7}, arr);
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i;
        test.rotate(arr, 300);
        // After rotating right by 300, element at index 0 should be 700
        assertEquals(700, arr[0]);
        assertEquals(999, arr[299]);
        assertEquals(0, arr[300]);
    }

    @Test
    public void testAdditional1() {
        int[] a = new int[]{1}; test.rotate(a, 0); assertArrayEquals(new int[]{1}, a);
    }

    @Test
    public void testAdditional2() {
        int[] a = new int[]{1,2}; test.rotate(a, 1); assertArrayEquals(new int[]{2,1}, a);
    }

    @Test
    public void testAdditional3() {
        int[] a = new int[]{1,2,3}; test.rotate(a, 1); assertArrayEquals(new int[]{3,1,2}, a);
    }

    @Test
    public void testAdditional4() {
        int[] a = new int[]{1,2,3}; test.rotate(a, 2); assertArrayEquals(new int[]{2,3,1}, a);
    }

    @Test
    public void testAdditional5() {
        int[] a = new int[]{1,2,3,4}; test.rotate(a, 4); assertArrayEquals(new int[]{1,2,3,4}, a);
    }

    @Test
    public void testAdditional6() {
        int[] a = new int[]{1,2,3,4}; test.rotate(a, 5); assertArrayEquals(new int[]{4,1,2,3}, a);
    }

    @Test
    public void testAdditional7() {
        int[] a = new int[]{-1,-2,-3}; test.rotate(a, 1); assertArrayEquals(new int[]{-3,-1,-2}, a);
    }

    @Test
    public void testAdditional8() {
        int[] a = new int[]{0,0,1,0}; test.rotate(a, 2); assertArrayEquals(new int[]{1,0,0,0}, a);
    }

    @Test
    public void testAdditional9() {
        int[] a = new int[]{1,2,3,4,5}; test.rotate(a, 3); assertArrayEquals(new int[]{3,4,5,1,2}, a);
    }

    @Test
    public void testAdditional10() {
        int[] a = new int[]{1,2,3,4,5}; test.rotate(a, 6); assertArrayEquals(new int[]{5,1,2,3,4}, a);
    }
}
