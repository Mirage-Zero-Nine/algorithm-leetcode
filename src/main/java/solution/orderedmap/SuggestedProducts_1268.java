package solution.orderedmap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given an array of strings products and a string searchWord.
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with the searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 * @author BorisMirage
 * Time: 2020/02/20 19:01
 * Created with IntelliJ IDEA
 */

public class SuggestedProducts_1268 {
    /**
     * In a sorted string array, if A[i] is a prefix of A[j], then A[i] is the prefix of A[i + 1], A[i + 2], ..., A[j].
     * Sort the array first, then put each word and its sorted index into a tree map.
     * Iterate each character from begin to the end of search key word.
     * Find the lower bound and upper bound of the key word prefix.
     * The next 3 words after lower bound is the result of current partial keyword.
     * Find upper bound to avoid overflow when split sublist.
     * Note that the partial word can be ceiling or flooring from tree map.
     *
     * @param products   given products list
     * @param searchWord search key word
     * @return list of lists of the suggested products after each character of searchWord is typed
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        TreeMap<String, Integer> m = new TreeMap<>();

        List<String> list = Arrays.asList(products);        // convert array to list for more convenient split
        List<List<String>> out = new LinkedList<>();

        for (int i = 0; i < products.length; i++) {
            m.put(products[i], i);      // put string and its index, use tree map for ceiling and flooring
        }

        StringBuilder key = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            key.append(c);
            String ceilingWord = m.ceilingKey(key.toString());          // find lower bound of key
            String flooringWord = m.floorKey(key + "|");     // '|' > 'z', find upper bound of key
            if (ceilingWord == null || flooringWord == null) {          // if any of bound does not exist, no word exist
                break;
            }

            /*
             * At least 3 words, and if only considered the lower bound, it may be overflow.
             * Therefore, add the upper bound and find the minor one between the upper bound and lower bound.
             * If lower bound will cause overflow, then the upper bound will be minor value and replace lower bound. */
            out.add(list.subList(m.get(ceilingWord), Math.min(m.get(ceilingWord) + 3, m.get(flooringWord) + 1)));
        }

        while (out.size() < searchWord.length()) {      // output's lengths should be same to searchWord
            out.add(new LinkedList<>());
        }

        return out;
    }
}
