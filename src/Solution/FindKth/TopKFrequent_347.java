package Solution.FindKth;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * Note:
 * 1. k is always valid, 1 ≤ k ≤ number of unique elements.
 * 2. Time complexity must be better than O(n log n), where n is the array's size.
 *
 * @author BorisMirage
 * Time: 2019/06/24 16:05
 * Created with IntelliJ IDEA
 */

public class TopKFrequent_347 {
    /**
     * Use min heap to find top k frequent elements. Note that the heap size should be kept to less than k.
     * Time complexity: O(nlogk).
     *
     * @param nums given array
     * @param k    k most frequent elements
     * @return k most frequent elements
     */
    public List<Integer> heap(int[] nums, int k) {
        List<Integer> out = new LinkedList<>();
        HashMap<Integer, Integer> m = new HashMap<>();

        for (int n : nums) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            pq.add(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        for (int i = 0; i < k; i++) {
            out.add(pq.poll().getKey());
        }

        return out;
    }

    /**
     * Bucket sorting. The bucket index is the frequency of each element.
     * Number of bucket should be the length of array to avoid out of boundary exception.
     * After adding all elements with their frequency into hash map, iterate the map and add all frequency to list.
     * Iterate the bucket list, find k elements that are most frequent.
     *
     * @param nums given array
     * @param k    k most frequent elements
     * @return k most frequent elements
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> m = new HashMap<>();
        List<Integer>[] bucket = new List[nums.length + 1];     // index is the frequency, value is elements with this frequency
        List<Integer> out = new ArrayList<>();

        for (int n : nums) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        for (int key : m.keySet()) {
            int frequency = m.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (int i = 0; i < bucket[pos].size() && out.size() < k; i++) {
                    out.add(bucket[pos].get(i));
                }
            }
        }

        return out;
    }

    /**
     * Direct approach.
     * Use a hash map to count the element frequency. Sort the map by value after the traverse.
     *
     * @param nums given array
     * @param k    k most frequent elements
     * @return k most frequent elements
     */
    public List<Integer> twoHashMap(int[] nums, int k) {
        List<Integer> out = new LinkedList<>();

        HashMap<Integer, Integer> m = new HashMap<>();     // save the key - frequency pair

        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) - 1);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(m.entrySet());
        list.sort(Map.Entry.comparingByValue());       // sort map based on value instead of key

        for (int i = 0; i < k; i++) {
            out.add(list.get(i).getKey());
        }

        return out;
    }

    public static void main(String[] args) {
        TopKFrequent_347 test = new TopKFrequent_347();

        // output should be [3,4,6,2,5]
        System.out.println(test.twoHashMap(new int[]{1, 3, 5, 5, 6, 3, 4, 4, 6, 4, 2, 2, 3, 4, 5, 6, 7, 8, 9, 0, 6, 4, 3, 3, 1, 2}, 5));
    }
}
