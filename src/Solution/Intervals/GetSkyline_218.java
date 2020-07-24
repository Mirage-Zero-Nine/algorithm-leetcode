package Solution.Intervals;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Given the locations and height of all the buildings, write a program to output the skyline formed by these buildings collectively.
 * Building is represented by a triplet of integers [Li, Ri, Hi].
 * Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 * It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * Assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * @author BorisMirage
 * Time: 2019/07/19 11:07
 * Created with IntelliJ IDEA
 */

public class GetSkyline_218 {
    /**
     * Solution with tree set. Remove in tree set is O(logn).
     * Use two lists. One list stores all points representing building. Sort this list based on point priority.
     * The other list all building's start and height. This list will not be sorted.
     * Then iterate all points in first list. Use a tree set to find the highest building.
     * First, current point is a left edge:
     * If set is empty, then current left edge is beginning of new building and a new key points is found.
     * If set is not empty, check if current edge is highest. If so, then a new key point is found.
     * Otherwise, current point is a right edge:
     * If set is empty, then current point is the last point of current building.
     * Otherwise, check if current edge is highest. At right edge, the lower point is the new key point.
     *
     * @param buildings given buildings list
     * @return skyline formed by these buildings collectively
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return out;
        }

        List<PointWithIndex> leftEdge = new ArrayList<>(), points = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            points.add(new PointWithIndex(b[0], b[2], false, i));
            points.add(new PointWithIndex(b[1], b[2], true, i));
            leftEdge.add(new PointWithIndex(b[0], b[2], false, i));
        }

        Collections.sort(points);        // sort all points

        /*
         * Create a new comparator to find highest building in tree set.
         * If points have different height, then higher point has higher priority.
         * Otherwise, prior point has higher priority. */
        TreeSet<Integer> ts = new TreeSet<>((a, b) -> {
            if (leftEdge.get(a).height != leftEdge.get(b).height) {
                return leftEdge.get(b).height - leftEdge.get(a).height;
            }
            return a - b;
        });

//        Comparator<Integer> comparator = (a, b) ->
//                leftEdge.get(a).height != leftEdge.get(b).height ?
//                        Integer.compare(leftEdge.get(b).height, leftEdge.get(a).height) :
//                        a - b;
//        TreeSet<Integer> ts = new TreeSet<>(comparator);

        for (PointWithIndex p : points) {
            ArrayList<Integer> tmp = new ArrayList<>();
            if (!p.isEnd) {        // left edge

                /*
                 * If set is empty, then current left edge is beginning of new building and a new key points is found.
                 * If set is not empty, check if current edge is highest. If so, then a new key point is found. */
                if (ts.isEmpty() || p.height > leftEdge.get(ts.first()).height) {
                    tmp.add(p.x);
                    tmp.add(p.height);
                    out.add(tmp);
                }
                ts.add(p.index);    // the overrode comparator will compare height first
            } else {                // right edge
                ts.remove(p.index);        // right edge, no more current building, remove the building point from set

                /*
                 * If set is empty, then current point is the last point of current building.
                 * Otherwise, check if current edge is highest.
                 * In this situation, the lower point is the new key point. */
                if (ts.isEmpty() || p.height > leftEdge.get(ts.first()).height) {
                    tmp.add(p.x);
                    tmp.add(ts.isEmpty() ? 0 : leftEdge.get(ts.first()).height);        // last point has 0 height
                    out.add(tmp);
                }
            }
        }

        return out;
    }

    /**
     * Point of building with height and mark if current point is build end.
     * Also, the point has index field, which represent the order of building this point belongs to.
     */
    private static class PointWithIndex implements Comparable<PointWithIndex> {
        int x, height, index;
        boolean isEnd;

        /**
         * Constructor of point with index.
         *
         * @param x      point x coord
         * @param height height of current point
         * @param isEnd  is current point the end of building
         * @param index  position of building
         */
        public PointWithIndex(int x, int height, boolean isEnd, int index) {
            this.x = x;
            this.height = height;
            this.isEnd = isEnd;
            this.index = index;
        }

        /**
         * Priority:
         * 1. If point has different position, smaller one has higher priority.
         * 2. If both points are left edges, find higher one.
         * 3. If both points are right edge, find lower one.
         * 4. Otherwise, one left edge and one right edge, find left one.
         *
         * @param that the other point with index
         * @return priority
         */
        @Override
        public int compareTo(PointWithIndex that) {
            if (this.x != that.x) {
                return this.x - that.x;     // smaller index has higher priority
            } else {
                if (!this.isEnd && !that.isEnd) {       // both left edges: find higher one
                    return that.height - this.height;
                } else if (this.isEnd && that.isEnd) {      // both right edges: find lower one
                    return this.height - that.height;
                } else {        // one left edge and one right edge: find left edge
                    return this.isEnd ? 1 : -1;
                }
            }
        }
    }

    /**
     * Solution with max heap. The idea is almost same.
     * However, remove element in heap is O(n), which is slower than remove element in tree set.
     *
     * @param buildings given buildings list
     * @return skyline formed by these buildings collectively
     */
    public List<List<Integer>> maxHeap(int[][] buildings) {
        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return out;
        }

        List<Point> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new Point(b[0], b[2], false));      // add start point and its height
            points.add(new Point(b[1], b[2], true));     // add end point and its height
        }

        Collections.sort(points);
        Queue<Integer> maxHeap = new PriorityQueue<>(buildings.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (Point ep : points) {
            if (!ep.isEnd) {
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(ep.x);
                    tmp.add(ep.height);
                    out.add(tmp);
//                    out.add(new ArrayList<Integer>() {{
//                        add(ep.x);
//                        add(ep.height);
//                    }});
                }
                maxHeap.offer(ep.height);       // current height
            } else {
                maxHeap.remove(ep.height);
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(ep.x);
                    tmp.add(maxHeap.isEmpty() ? 0 : maxHeap.peek());
                    out.add(tmp);
//                    out.add(new ArrayList<Integer>() {{
//                        add(ep.x);
//                        add(maxHeap.isEmpty() ? 0 : maxHeap.peek());
//                    }});
                }
            }
        }

        return out;
    }

    /**
     * Point of building with height and mark if current point is build end.
     */
    private static class Point implements Comparable<Point> {
        int x, height;
        boolean isEnd;

        /**
         * Constructor of point.
         *
         * @param x      point x coord
         * @param height height of current point
         * @param isEnd  is current point the end of building
         */
        public Point(int x, int height, boolean isEnd) {
            this.x = x;
            this.height = height;
            this.isEnd = isEnd;
        }

        /**
         * Priority:
         * 1. If point has different position, smaller one has higher priority.
         * 2. If both points are left edges, find higher one.
         * 3. If both points are right edge, find lower one.
         * 4. Otherwise, one left edge and one right edge, find left one.
         *
         * @param that the other point with index
         * @return priority
         */
        @Override
        public int compareTo(Point that) {
            if (this.x != that.x) {                     // smaller index has higher priority
                return this.x - that.x;
            } else {
                if (!this.isEnd && !that.isEnd) {       // both left edges: find higher one
                    return that.height - this.height;
                } else if (this.isEnd && that.isEnd) {      // both right edges: find lower one
                    return this.height - that.height;
                } else {        // one left edge and one right edge: find left edge
                    return this.isEnd ? 1 : -1;
                }
            }
        }
    }

    /**
     * Use a heap and tree map to sort the given coord. The idea is similar.
     * However, it uses negative number to mark left edge's height. In this way to reduce boolean mark.
     *
     * @param buildings given buildings list
     * @return skyline formed by these buildings collectively
     */
    public List<List<Integer>> heap(int[][] buildings) {
        List<List<Integer>> out = new LinkedList<>();
        List<int[]> height = new LinkedList<>();

        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});     // incomplete height marked as negative
            height.add(new int[]{b[1], b[2]});
        }

        height.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];     // if position is same, sort by height
                }
                return a[0] - b[0];     // sort the given array by position
            }
        });

        TreeMap<Integer, Integer> tm = new TreeMap<>();     // key: height; value: how many at this height
        int previousHighest = 0;
        tm.put(0, 1);

        for (int[] b : height) {

            if (b[1] < 0) {
                tm.put(-b[1], tm.getOrDefault(-b[1], 0) + 1);
            } else {
                if (tm.get(b[1]) > 1) {
                    tm.put(b[1], tm.get(b[1]) - 1);
                } else {
                    tm.remove(b[1]);
                }
            }
            int currentHighest = tm.lastKey();
            if (currentHighest != previousHighest) {        // if there is a new height
                previousHighest = currentHighest;
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(b[0]);
                temp.add(currentHighest);
                out.add(temp);

//                out.add(new ArrayList<Integer>() {{
//                    add(b[0]);
//                    add(currentHighest);
//                }});
            }
        }

        return out;
    }
}
