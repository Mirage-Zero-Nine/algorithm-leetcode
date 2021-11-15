package Solution.Heap;

import java.util.PriorityQueue;

/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map.
 * Compute the volume of water it is able to trap after raining.
 * Note:
 * Both m and n are less than 110.
 * The height of each unit cell is greater than 0 and is less than 20,000.
 *
 * @author BorisMirage
 * Time: 2019/05/30 16:38
 * Created with IntelliJ IDEA
 */

public class TrapRainWater_407 {
    /**
     * Collect all cells on border and add them to a Priority Queue (heap), with the priority of cell's height.
     * The shortest cell is the first one (highest priority), since higher cell may contains more water.
     * Based on heap, each time poll the shortest cell (should be unvisited) and search neighbors.
     * If the neighbors is shorter than current cell, then it can contain water. Otherwise sum remains same.
     * Note that as long as the neighbor cell is unvisited, it should be added to Priority Queue.
     * In this adding process, the priority is the higher height between neighbor and current cell.
     *
     * @param heightMap given height map
     * @return volume of water it is able to trap
     */
    public int trapRainWater(int[][] heightMap) {

        /* Corner case */
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>(1);     // use heap instead of queue

        int r = heightMap.length;
        int c = heightMap[0].length;
        boolean[][] visited = new boolean[r][c];

        /* Add all cells on border to queue */
        for (int i = 0; i < r; i++) {
            visited[i][0] = true;
            visited[i][c - 1] = true;
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, c - 1, heightMap[i][c - 1]));
        }
        for (int i = 0; i < c; i++) {
            visited[0][i] = true;
            visited[r - 1][i] = true;
            pq.offer(new Cell(0, i, heightMap[0][i]));
            pq.offer(new Cell(r - 1, i, heightMap[r - 1][i]));
        }

        int[][] xy = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int max = 0;

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();       // obtain shortest cell in queue

            for (int[] coord : xy) {        // BFS to find all neighbor
                int newRow = cell.row + coord[0];
                int newCol = cell.col + coord[1];
                if (newRow > 0 && newRow < r && newCol > 0 && newCol < c && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    max += Math.max(0, cell.height - heightMap[newRow][newCol]);
                    pq.offer(new Cell(newRow, newCol, Math.max(heightMap[newRow][newCol], cell.height)));
                }
            }
        }
        return max;
    }

    /**
     * Cell class with override comparator.
     */
    static class Cell implements Comparable<Cell> {
        int row, col, height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        public int compareTo(Cell o) {
            return this.height - o.height;      // sort by height, shorter cell has higher priority
        }
    }
}

