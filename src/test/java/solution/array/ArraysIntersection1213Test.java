package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:45
 * Created with IntelliJ IDEA
 */

public class ArraysIntersection1213Test {

    private final ArraysIntersection_1213 test = new ArraysIntersection_1213();

    @Test
    public void testArraysIntersection() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {2, 3, 4, 5};
        int[] arr3 = {3, 4, 5, 6};

        List<Integer> result = test.arraysIntersection(arr1, arr2, arr3);
        List<Integer> expected = Arrays.asList(3, 4);

        assertEquals(expected, result);
    }

    @Test
    public void testArraysIntersectionEmpty() {
        int[] arr1 = {};
        int[] arr2 = {};
        int[] arr3 = {};

        List<Integer> result = test.arraysIntersection(arr1, arr2, arr3);
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    public void testArraysIntersectionNoCommonElements() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] arr3 = {7, 8, 9};

        List<Integer> result = test.arraysIntersection(arr1, arr2, arr3);
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    public void testArraysIntersectionDuplicateElements() {
        int[] arr1 = {1, 2, 2, 3};
        int[] arr2 = {2, 3, 3, 4};
        int[] arr3 = {3, 4, 4, 5};

        List<Integer> result = test.arraysIntersection(arr1, arr2, arr3);
        List<Integer> expected = Collections.singletonList(3);

        assertEquals(expected, result);
    }

    @Test
    public void testArraysIntersectionAllCommonElements() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {2, 3, 4};
        int[] arr3 = {3, 4, 5};

        List<Integer> result = test.arraysIntersection(arr1, arr2, arr3);
        List<Integer> expected = Collections.singletonList(3);

        assertEquals(expected, result);
    }
}