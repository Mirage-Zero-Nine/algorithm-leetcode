package solution.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * @author BorisMirage
 * Time: 2019/08/14 11:02
 * Created with IntelliJ IDEA
 */

public class TopKFrequent_692 {
    /**
     * Save words with its frequency, and then use a heap to sort entry.
     * Keep size of heap be at most k. Finally, add heap to output.
     *
     * @param words words array
     * @param k     kth most frequent
     * @return k most frequent elements
     */
    public List<String> topKFrequent(String[] words, int k) {

        List<String> out = new LinkedList<>();
        Map<String, Integer> m = new HashMap<>();

        for (String w : words) {
            m.put(w, m.getOrDefault(w, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>((o1, o2) -> {      // poll smaller one
            if (o1.getValue().equals(o2.getValue())) {
                return (o2.getKey().compareTo(o1.getKey()));        // alphabet sort, larger order first
            }
            return o1.getValue() - o2.getValue();       // smaller frequency should be polled
        });

        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            q.add(entry);
            if (q.size() > k) {
                q.poll();
            }
        }

        while (!q.isEmpty()) {
            out.add(0, q.poll().getKey());
        }

        return out;
    }
}
