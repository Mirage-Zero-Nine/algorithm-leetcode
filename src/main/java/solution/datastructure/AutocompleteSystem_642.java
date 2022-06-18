package solution.datastructure;

import java.util.*;

/**
 * Design a search autocomplete system for a search engine.
 * Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type, return the top 3 historical hot sentences with same prefix of sentence typed.
 * Here are the specific rules:
 * 1. The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * 2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
 * 3. If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * 4. If less than 3 hot sentences exist, then just return as many as you can.
 * 5. When the input is a special character, it means the sentence ends, and in this case, return an empty list.
 * 6. When '#' is typed, previous sentence should be saved as a historical sentence in system.
 *
 * @author BorisMirage
 * Time: 2019/09/17 21:43
 * Created with IntelliJ IDEA
 */

public class AutocompleteSystem_642 {
    private List<Character> l = new LinkedList<>();     // history list
    private TrieNode root;      // root node of trie
    private TrieNode tmp;       // current trie node for next search
    private int p = 0;          // pointer point at current char for searching

    /**
     * Use a trie to save all chars and its relating sentence/frequency.
     *
     * @param sentences given sentences
     * @param times     weight of each sentences
     */
    public AutocompleteSystem_642(String[] sentences, int[] times) {
        root = new TrieNode();
        tmp = root;
        this.root = build(sentences, times);
    }

    /**
     * Based on given input char, return the result list.
     * Each TrieNode contains the string that all its sub trie node containing via a hash map.
     * When a new char comes in, search the trie to find the
     * If input is '#', update trie as new history.
     *
     * @param c given char
     * @return result list, if input is '#' or not exist, return empty list
     */
    public List<String> input(char c) {
        List<String> out = new LinkedList<>();

        if (c == '#') {
            StringBuilder sb = new StringBuilder();
            for (Character ch : l) {
                sb.append(ch);
            }
            root = build(new String[]{sb.toString()}, new int[]{1});        // if input is '#', update trie
            l = new LinkedList<>();
            tmp = root;
            p = 0;

            return new LinkedList<>();
        }

        l.add(c);

        /*
         * If input char is not in trie, stop moving pointer forward.
         * The history list will not reset until accept a '#'.
         * Therefore, if a input char is not found in trie, then the later input char will always return empty list. */
        if (!tmp.child.containsKey(l.get(p))) {
            return new LinkedList<>();
        }
        tmp = tmp.child.get(l.get(p++));

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o2.getValue().equals(o1.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return (o2.getValue()).compareTo(o1.getValue());
        });
        pq.addAll(tmp.sentence.entrySet());     // use a heap to add all entries in each trie node, then sort them

        for (int i = 0; !pq.isEmpty() && i < 3; i++) {      // find top 3 most visited
            out.add(pq.poll().getKey());
        }

        return out;
    }

    /**
     * Build trie based on given sentences and count.
     * Also, this build method can update frequency if the sentence can be found in trie.
     *
     * @param sentences given sentences list
     * @param times     given sentences count
     * @return root of trie node
     */
    private TrieNode build(String[] sentences, int[] times) {

        for (int i = 0; i < sentences.length; i++) {
            String s = sentences[i];
            TrieNode tmp = root;

            for (int j = 0; j < s.length(); j++) {
                if (!tmp.child.containsKey(s.charAt(j))) {
                    tmp.child.put(s.charAt(j), new TrieNode());
                }

                tmp = tmp.child.get(s.charAt(j));
                tmp.sentence.put(s, tmp.sentence.getOrDefault(s, 0) + times[i]);
            }
        }

        return root;
    }

    /**
     * Constructor of trie node.
     */
    static class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();       // save child node of current node
        HashMap<String, Integer> sentence = new HashMap<>();        // save each node's relating sentence and count
    }

    public static void main(String[] args) {
        String[] s = new String[]{"i love you", "island", "iroman", "i love leetcode"};
        int[] time = {5, 3, 2, 2};
        AutocompleteSystem_642 test = new AutocompleteSystem_642(s, time);
        System.out.println(test.input('i'));
        System.out.println(test.input(' '));
        System.out.println(test.input('a'));
        System.out.println(test.input('#'));
        System.out.println(test.input('i'));
        System.out.println(test.input(' '));
        System.out.println(test.input('a'));
        System.out.println(test.input('#'));
        System.out.println(test.input('i'));
        System.out.println(test.input(' '));
        System.out.println(test.input('a'));
        System.out.println(test.input('#'));

        // ["i love you","island","i love leetcode"],
        // ["i love you","i love leetcode"],
        // [],
        // [],
        // ["i love you","island","i love leetcode"],
        // ["i love you","i love leetcode","i a"],
        // ["i a"],
        // [],
        // ["i love you","island","i a"],
        // ["i love you","i a","i love leetcode"],
        // ["i a"],
        // []
    }
}