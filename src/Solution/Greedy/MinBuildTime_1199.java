package Solution.Greedy;

import java.util.PriorityQueue;

/**
 * You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built.
 * A block can only be built by exactly one worker.
 * A worker can either split into two workers (number of workers increases by one) or build a block then go home.
 * Both decisions cost some time.
 * The time cost of spliting one worker into two workers is given as an integer split.
 * Note that if two workers split at the same time, they split in parallel so the cost would be split.
 * Output the minimum time needed to build all blocks.
 * Initially, there is only one worker.
 *
 * @author BorisMirage
 * Time: 2019/09/21 13:28
 * Created with IntelliJ IDEA
 */

public class MinBuildTime_1199 {
    /**
     * Huffman's Algorithm implemented by heap.
     * The way to find the min time is to generate a Huffman tree, so that the total cost can be minimized.
     *
     * @param blocks given array representing a list of blocks
     * @param split  time consumption of a work split
     * @return minimum time needed to build all blocks
     */
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : blocks) {
            pq.add(i);
        }

        while (pq.size() > 1) {         // one worker is enough for one block
            pq.poll();                  // first element is smallest element, remove it has no impact to final result
            int tmp = pq.poll();
            pq.add(tmp + split);        // complete current block, and split a new worker
        }

        return pq.poll();
    }
}
