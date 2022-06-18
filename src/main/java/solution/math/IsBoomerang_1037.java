package solution.math;

/**
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.
 * Given a list of three points in the plane, return whether these points are a boomerang.
 *
 * @author BorisMirage
 * Time: 2019/09/10 14:30
 * Created with IntelliJ IDEA
 */

public class IsBoomerang_1037 {
    /**
     * Calculate the slope of AB and AC.
     * K_AB = (p[0][0] - p[1][0]) / (p[0][1] - p[1][1])
     * K_AC = (p[0][0] - p[2][0]) / (p[0][1] - p[2][1])
     * Check if K_AB != K_AC, instead of calculate a fraction that may be invalid.
     * Hence, check if (p[0][0] - p[1][0]) * (p[0][1] - p[2][1]) != (p[0][0] - p[2][0]) * (p[0][1] - p[1][1]).
     *
     * @param p given points list
     * @return if these points are not in a line
     */
    public boolean isBoomerang(int[][] p) {
        return (p[0][0] - p[1][0]) * (p[0][1] - p[2][1]) != (p[0][0] - p[2][0]) * (p[0][1] - p[1][1]);
    }
}
