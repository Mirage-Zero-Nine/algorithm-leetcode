package Solution.OrderedMap;

import java.util.*;

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

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
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
    @SuppressWarnings("unchecked")
    public String frequencySortBucketSort(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return s;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        List<Character>[] bucket = new List[s.length() + 1];
        for (Character c : map.keySet()) {
            int frequency = map.get(c);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(c);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                List<Character> current = bucket[i];
                for (Character c : current) {
                    for (int j = 0; j < map.get(c); j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        FrequencySort_451 frequencySort_451 = new FrequencySort_451();
        System.out.println(frequencySort_451.frequencySort("tree"));
    }
}
