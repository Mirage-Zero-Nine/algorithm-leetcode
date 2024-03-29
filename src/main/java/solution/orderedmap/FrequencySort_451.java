package solution.orderedmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * @author BorisMirage
 * Time: 2021/09/25 18:37
 * Created with IntelliJ IDEA
 */

public class FrequencySort_451 {
    /**
     * Keep a hash map to store the frequency of each character.
     * Then use a heap to sort the frequency of character.
     * Finally, poll each element out, append with frequency to a string builder.
     *
     * @param s given string
     * @return string sorted in decreasing order based on the frequency of the characters
     */
    public String frequencySort(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return s;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        pq.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> e = pq.poll();
            for (int i = 0; i < e.getValue(); i++) {
                sb.append(e.getKey());
            }
        }

        return sb.toString();
    }

    /**
     * Same idea, but use an array to sort (the largest frequency is the string length, which is fixed).
     *
     * @param s given string
     * @return string sorted in decreasing order based on the frequency of the characters
     */
    public String frequencySortBucketSort(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return s;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int frequency = map.getOrDefault(s.charAt(i), -1) + 1;  // frequency started at 0 to align with bucket index
            map.put(s.charAt(i), frequency);
        }

        List<List<Character>> bucket = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            bucket.add(new ArrayList<>());
        }

        for (Character c : map.keySet()) {
            int frequency = map.get(c);
            bucket.get(frequency).add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = bucket.size() - 1; i >= 0; i--) {
            if (bucket.get(i).size() > 0) {
                for (Character c : bucket.get(i)) {
                    for (int j = 0; j <= i; j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }
}
