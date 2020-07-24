package Solution.Backtracking;

import java.util.*;

/**
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 * There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.
 * Given the ball, the hole and the maze position, find out how the ball could drop into the hole by moving the shortest distance.
 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included).
 * Output the moving directions by using 'u', 'd', 'l' and 'r'.
 * Since there could be several different shortest ways, you should output the lexicographically smallest way.
 * If the ball cannot reach the hole, output "impossible".
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The ball and the hole coordinates are represented by row and column indexes.
 *
 * @author BorisMirage
 * Time: 2019/11/01 4:56 PM
 * Created with IntelliJ IDEA
 */
public class FindShortestWay_499 {
    /**
     * Backtracking with pruning.
     * During the process of DFS, if current path is longer than shortest path, the end current searching.
     * Use a Point structure to store the path and its corresponding length.
     *
     * @param maze given maze
     * @param ball given start position
     * @param hole given end position
     * @return how the ball could drop into the hole by moving the shortest distance, or "impossible" if no path
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        Point[][] points = new Point[m][n];

        for (int i = 0; i < m * n; i++) {
            points[i / n][i % n] = new Point(i / n, i % n);
        }

        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String[] move = new String[]{"u", "r", "d", "l"};
        PriorityQueue<Point> list = new PriorityQueue<>();

        list.offer(new Point(ball[0], ball[1], 0, ""));
        list.add(new Point(ball[0], ball[1], 0, ""));

        while (!list.isEmpty()) {
            Point p = list.poll();

            if (points[p.x][p.y].compareTo(p) <= 0) {       // pruning
                continue;
            }
            points[p.x][p.y] = p;

            for (int i = 0; i < 4; i++) {
                int xx = p.x, yy = p.y, l = p.length;

                while (xx >= 0 && xx < m && yy >= 0 && yy < n && maze[xx][yy] == 0 && (xx != hole[0] || yy != hole[1])) {
                    xx += directions[i][0];
                    yy += directions[i][1];
                    l++;
                }

                if (xx != hole[0] || yy != hole[1]) {       // check the hole
                    xx -= directions[i][0];
                    yy -= directions[i][1];
                    l--;
                }
                list.offer(new Point(xx, yy, l, p.path + move[i]));
            }
        }

        return (points[hole[0]][hole[1]].length == Integer.MAX_VALUE) ? "impossible" : points[hole[0]][hole[1]].path;
    }

    /**
     * Save cell coord and the distance from start point.
     */
    static class Point implements Comparable<Point> {
        int x, y, length;
        String path;

        /**
         * Constructor of point class by cell coord.
         *
         * @param x x coord
         * @param y y coord
         */
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            length = Integer.MAX_VALUE;
            path = "";
        }

        /**
         * Constructor of point class by cell coord and path from start to current cell.
         *
         * @param x      x coord
         * @param y      y coord
         * @param length length of path
         * @param s      path itself
         */
        Point(int x, int y, int length, String s) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.path = s;
        }

        /**
         * Compare priority of two points.
         * Smaller path has higher priority.
         * If path length is same, return the one with higher alphabet order (a to z).
         *
         * @param p given point
         * @return point with higher priority
         */
        public int compareTo(Point p) {
            return length == p.length ? path.compareTo(p.path) : length - p.length;
        }
    }
}
