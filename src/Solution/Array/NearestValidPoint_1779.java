package Solution.Array;

/**
 * You are given two integers, x and y represent current location on a grid: (x, y).
 * You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi).
 * A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.
 * Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location.
 * If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.
 * The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).
 *
 * @author BorisMirage
 * Time: 2021/10/13 12:06
 * Created with IntelliJ IDEA
 */

public class NearestValidPoint_1779 {
    /**
     * Traverse the array. If a point is valid, check the Manhattan distance.
     * Replace the index if it's the minimum distance.
     *
     * @param x      coord x
     * @param y      coord y
     * @param points array of points
     * @return the index (0-indexed) of the valid point with the smallest Manhattan distance from current location
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDistance = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] == x || point[1] == y) {
                int distance = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if (distance < minDistance) {
                    minDistance = distance;
                    minIndex = i;
                }
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        System.out.println(new NearestValidPoint_1779().nearestValidPoint(3, 4, new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}));
    }
}
