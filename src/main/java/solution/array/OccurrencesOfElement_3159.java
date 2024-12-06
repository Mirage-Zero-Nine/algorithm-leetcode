package solution.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * You are given an integer array nums, an integer array queries, and an integer x.
 * For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array.
 * If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
 * Return an integer array answer containing the answers to all queries.
 *
 * @author BorisMirage
 * Time: 2024/12/06 14:14
 * Created with IntelliJ IDEA
 */

public class OccurrencesOfElement_3159 {
    /**
     * Basic looping.
     * Loop nums first, get all indexes of the elements equal to x.
     * Loop queries, if current query is larger than list, add -1 to output.
     * Otherwise, place (query -1)th element in output.
     *
     * @param nums    given array
     * @param queries given queries
     * @param x       target number
     * @return an integer array of all indexes of the queries[i]th occurrence of x in the nums array
     */
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        // corner case
        if (nums == null || queries == null || nums.length == 0 || queries.length == 0) {
            return new int[0];
        }

        List<Integer> position = IntStream.range(0, nums.length)
                .filter(i -> nums[i] == x)
                .boxed()
                .toList();

        return Arrays.stream(queries)
                .map(query -> query > position.size() ? -1 : position.get(query - 1))
                .toArray();
    }
}
