package Solution.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We are given an elevation map, heights[i] representing the height of the terrain at that index.
 * The width at each index is 1. After V units of water fall at index K, how much water is at each index?
 * Water first drops at index K and rests on top of the highest terrain or water at that index.
 * Then, it flows according to the following rules:
 * If the droplet would eventually fall by moving left, then move left.
 * Otherwise, if the droplet would eventually fall by moving right, then move right.
 * Otherwise, rise at it's current position.
 * Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
 * Also, "level" means the height of the terrain plus any water in that column.
 * We can assume there's infinitely high terrain on the two sides out of bounds of the array.
 * Also, there could not be partial water being spread out evenly on more than 1 grid block.
 * Each unit of water has to be in exactly one block.
 *
 * @author BorisMirage
 * Time: 2019/08/21 11:21
 * Created with IntelliJ IDEA
 */

public class PourWater_755 {
    /**
     * Use two heaps to store position. One store from K - 1 to its left, the other one stores from K + 1 to its right.
     * The priority is, first, height of index, then the position.
     * Position is as close as to K as possible, which means left heap keeps right, right heap keeps to left.
     * Adding water should starts at left part.
     *
     * @param heights given int array representing the height of the terrain at that index
     * @param V       # of water drops
     * @param K       dropping position
     * @return water in each index after V units of water fall at index K
     */
    public int[] pourWater(int[] heights, int V, int K) {

        PriorityQueue<Integer> h1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (heights[i1] - heights[i2] == 0) {
                    return i2 - i1;

                }
                return heights[i1] - heights[i2];
            }
        });

        PriorityQueue<Integer> h2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (heights[i1] - heights[i2] == 0) {
                    return i1 - i2;

                }
                return heights[i1] - heights[i2];
            }
        });

        int left = K - 1, right = K + 1, index;
        for (int i = 0; i < V; i++) {

            while (left >= 0 && heights[left] <= heights[left + 1]) {
                h1.add(left--);     // left part
            }

            while (right < heights.length && heights[right] <= heights[right - 1]) {
                h2.add(right++);        // right part
            }

            if (!h1.isEmpty() && heights[h1.peek()] < heights[K]) {      // left first
                index = h1.poll();
                heights[index]++;
                h1.add(index);
            } else if (!h2.isEmpty() && heights[h2.peek()] < heights[K]) {
                index = h2.poll();
                heights[index]++;
                h2.add(index);
            } else {
                heights[K]++;
            }
        }

        return heights;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PourWater_755().pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 16, 3)));
    }
}
