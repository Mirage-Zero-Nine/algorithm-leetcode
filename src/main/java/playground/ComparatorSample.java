package playground;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author BorisMirage
 * Time: 2019/08/10 18:12
 * Created with IntelliJ IDEA
 */

public class ComparatorSample {
    public ComparatorSample() {
        String[] words = new String[10];
        int[][] intervals = new int[10][10];

        // sort list by length
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));     // sort based on intervals[i][0]

        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.deepToString(intervals));

        int K = 10;
        PriorityQueue<int[]> q = new PriorityQueue<>(K, Comparator.comparingInt(a -> a[0]));

        q.add(new int[]{1, 2});
        System.out.println(q);
    }
}
