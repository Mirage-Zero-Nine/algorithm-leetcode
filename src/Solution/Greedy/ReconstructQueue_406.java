package Solution.Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k).
 * h is the height of the person.
 * k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 * Note:
 * The number of people is less than 1,100.
 *
 * @author BorisMirage
 * Time: 2019/09/02 14:19
 * Created with IntelliJ IDEA
 */

public class ReconstructQueue_406 {
    /**
     * Greedy.
     * Sort the given array by height from high to low. If height is same, smaller people[1] first.
     * Then insert into a linked list. Each time, insert index == people[1].
     * The reason is based on sorting. Array is sorted by height.
     * Hence, if two people have same height, higher person will be inserted into output first.
     * Then the shorter one will be inserted into output, and the higher person will be moved one forward.
     * In this way to assure the arrangement of ascending array.
     *
     * @param people given array, people[0]: height of each person, people[1]: # of people in front whose height >= h
     * @return reconstructed queue
     */
    public int[][] reconstructQueue(int[][] people) {
        int p = people.length;

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];     // if heights are same, less higher people first
                }

                return b[0] - a[0];         // higher people first
            }
        });

        List<int[]> tmp = new LinkedList<>();

        for (int[] a : people) {
            tmp.add(a[1], a);               // insert based on how many higher people front
        }

        return tmp.toArray(new int[p][2]);
    }
}
