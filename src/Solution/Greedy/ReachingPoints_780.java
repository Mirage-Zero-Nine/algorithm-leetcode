package Solution.Greedy;

/**
 * A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 * Given a starting point (sx, sy) and a target point (tx, ty).
 * Return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty).
 * Otherwise, return False.
 *
 * @author BorisMirage
 * Time: 2020/03/17 10:05
 * Created with IntelliJ IDEA
 */

public class ReachingPoints_780 {
    /**
     * Track back from target point to start point.
     * The end point will finally be reached by adding kx or ky.
     * Which is, (tx, ty) = (ax + by, cx + dy), where a, b, c, d are coefficient.
     * Trace back from target point by repeating find point from previous move.
     * The move is (x, y + kx) or (x + ky, y). Use module instead of subtraction to avoid TLE.
     * Repeat this process until x or y is smaller than start point.
     * There are two cases:
     * 1. sx == tx, then at this time, ty = sy + k * sx. Hence, (ty - sy) % sx should be 0.
     * 2. sy == ty, same situation, tx = sx + k * sy, and (tx - sx) % sy == 0.
     * If sx != tx && sy != ty, then target point can not be reached.
     *
     * @param sx start point x coord
     * @param sy start point y coord
     * @param tx target point x coord
     * @param ty target point y coord
     * @return true iff a sequence of moves exists to transform the point (sx, sy) to (tx, ty), false otherwise
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {      // larger value % smaller value
                ty %= tx;
            } else {
                tx %= ty;
            }
        }

        /*
         * Two cases:
         * 1. sx == tx, then at this time, ty = sy + k * sx. Hence, (ty - sy) % sx should be 0.
         * 2. sy == ty, same situation, tx = sx + k * sy, and (tx - sx) % sy == 0.
         * If sx != tx && sy != ty, then target point can not be reached. */
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 || sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
