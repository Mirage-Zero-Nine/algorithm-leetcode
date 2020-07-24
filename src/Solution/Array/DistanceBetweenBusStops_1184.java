package Solution.Array;

/**
 * A bus has n stops numbered from 0 to n - 1 that form a circle.
 * distance[i] is the distance between stops i and (i + 1) % n.
 * The bus goes along both directions i.e. clockwise and counterclockwise.
 * Return the shortest distance between the given start and destination stops.
 *
 * @author BorisMirage
 * Time: 2020/05/01 13:08
 * Created with IntelliJ IDEA
 */

public class DistanceBetweenBusStops_1184 {
    /**
     * There is only one connection between neighboring stops, which means it is a circular array.
     * Only two ways to reach destination from start: forward or backward.
     * Calculate the sum of array, and the cost from start to the destination.
     * The forward cost is from start to the end, the backward cost is the sum minus forward cost. Find the minor cost.
     * Note that if start is located at the later of destination, swap them first.
     *
     * @param distance    given array
     * @param start       start position
     * @param destination end position
     * @return shortest distance between the given start and destination stops
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {      // swap start and destination if start is larger than destination
            int tmp = destination;
            destination = start;
            start = tmp;
        }

        int forward = 0, total = 0;

        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                forward += distance[i];
            }

            total += distance[i];
        }

        return Math.min(forward, total - forward);
    }
}
