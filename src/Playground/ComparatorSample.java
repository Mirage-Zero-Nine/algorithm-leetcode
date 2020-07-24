package Playground;

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

        Arrays.sort(words, new Comparator<String>() {       // sort list by length
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));     // sort based on intervals[i][0]

        int K = 10;
        PriorityQueue<int[]> q = new PriorityQueue<>(K, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
    }
}
