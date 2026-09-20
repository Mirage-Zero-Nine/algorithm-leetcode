package solutions.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link DistanceBetweenBusStops_1184}.
 */
public class DistanceBetweenBusStops_1184Test {

    private final DistanceBetweenBusStops_1184 solver = new DistanceBetweenBusStops_1184();

    @Test
    public void testClassicExample() {
        int[] distance = {1, 2, 3, 4};
        assertEquals(1, solver.distanceBetweenBusStops(distance, 0, 1));
    }

    @Test
    public void testClassicExampleReverse() {
        int[] distance = {1, 2, 3, 4};
        assertEquals(1, solver.distanceBetweenBusStops(distance, 1, 0));
    }

    @Test
    public void testSameStartAndEnd() {
        int[] distance = {1, 2, 3, 4};
        assertEquals(0, solver.distanceBetweenBusStops(distance, 0, 0));
    }

    @Test
    public void testAdjacentStops() {
        int[] distance = {1, 2, 3, 4};
        assertEquals(2, solver.distanceBetweenBusStops(distance, 1, 2));
    }

    @Test
    public void testFarStopsForwardShorter() {
        // distance = {1, 10, 1, 1}, start=0, dest=3
        // forward: 1+10+1=12, backward: 1, min=1
        int[] distance = {1, 10, 1, 1};
        assertEquals(1, solver.distanceBetweenBusStops(distance, 0, 3));
    }

    @Test
    public void testFarStopsBackwardShorter() {
        // distance = {10, 1, 1, 1}, start=0, dest=3
        // forward: 10+1+1=12, backward: 1, min=1
        int[] distance = {10, 1, 1, 1};
        assertEquals(1, solver.distanceBetweenBusStops(distance, 0, 3));
    }

    @Test
    public void testTwoStops() {
        // 2 stops: distance between 0->1 is 100, 1->0 is 100
        int[] distance = {100, 100};
        assertEquals(100, solver.distanceBetweenBusStops(distance, 0, 1));
    }

    @Test
    public void testThreeStops() {
        int[] distance = {1, 2, 3};
        assertEquals(3, solver.distanceBetweenBusStops(distance, 0, 2));
    }

    @Test
    public void testWrapAround() {
        int[] distance = {7, 10, 1, 12};
        assertEquals(11, solver.distanceBetweenBusStops(distance, 1, 3));
    }

    @Test
    public void testWrapAroundReverse() {
        int[] distance = {7, 10, 1, 12};
        assertEquals(11, solver.distanceBetweenBusStops(distance, 3, 1));
    }

    @Test
    public void testEqualDistances() {
        int[] distance = {5, 5, 5, 5};
        assertEquals(10, solver.distanceBetweenBusStops(distance, 0, 2));
    }

    @Test
    public void testAllSameStops() {
        int[] distance = {5, 5, 5, 5};
        assertEquals(0, solver.distanceBetweenBusStops(distance, 2, 2));
    }

    @Test
    public void testAdditional1() {
        assertEquals(1, solver.distanceBetweenBusStops(new int[]{1,2,3,4}, 0, 1));
    }

    @Test
    public void testAdditional2() {
        assertEquals(5, solver.distanceBetweenBusStops(new int[]{1,2,3,4}, 1, 3));
    }

    @Test
    public void testAdditional3() {
        assertEquals(4, solver.distanceBetweenBusStops(new int[]{1,2,3,4}, 3, 0));
    }

    @Test
    public void testAdditional4() {
        assertEquals(0, solver.distanceBetweenBusStops(new int[]{5}, 0, 0));
    }

    @Test
    public void testAdditional5() {
        assertEquals(2, solver.distanceBetweenBusStops(new int[]{2,2}, 0, 1));
    }

    @Test
    public void testAdditional6() {
        assertEquals(11, solver.distanceBetweenBusStops(new int[]{10,1,1,10}, 0, 2));
    }

    @Test
    public void testAdditional7() {
        assertEquals(6, solver.distanceBetweenBusStops(new int[]{3,7,4,2,6}, 2, 4));
    }

    @Test
    public void testAdditional8() {
        assertEquals(300, solver.distanceBetweenBusStops(new int[]{100,200,300}, 0, 2));
    }

    @Test
    public void testAdditional9() {
        assertEquals(2, solver.distanceBetweenBusStops(new int[]{1,1,1,1,1}, 1, 4));
    }

    @Test
    public void testAdditional10() {
        assertEquals(18, solver.distanceBetweenBusStops(new int[]{9,8,7,6,5,4}, 5, 2));
    }
}
