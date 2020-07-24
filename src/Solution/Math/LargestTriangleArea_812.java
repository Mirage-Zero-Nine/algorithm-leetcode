package Solution.Math;

/**
 * You have a list of points in the plane.
 * Return the area of the largest triangle that can be formed by any 3 of the points.
 *
 * @author BorisMirage
 * Time: 2019/09/10 14:41
 * Created with IntelliJ IDEA
 */

public class LargestTriangleArea_812 {
    /**
     * Brute force.
     * Area: 0.5 * Math.abs(a[0] * b[1] + b[0] * c[1] + c[0] * a[1] - b[0] * a[1] - c[0] * b[1] - a[0] * c[1])
     * @param p given points list
     * @return area of the largest triangle that can be formed by any 3 of the points
     */
    public double largestTriangleArea(int[][] p) {
        double area = 0;
        for (int[] a : p) {
            for (int[] b : p) {
                for (int[] c : p) {
                    area = Math.max(area, 0.5 * Math.abs(a[0] * b[1] + b[0] * c[1] + c[0] * a[1] - b[0] * a[1] - c[0] * b[1] - a[0] * c[1]));
                }
            }
        }
        return area;
    }
}
