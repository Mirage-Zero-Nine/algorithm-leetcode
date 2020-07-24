package Solution.SlidingWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a string, s, and a list of words, words, that are all of the SAME LENGTH.
 * Find all starting indices of substring(s) in s
 * These indices are a concatenation of each word in words exactly once and without any intervening characters.
 *
 * @author BorisMirage
 * Time: 2018/06/17 17:55
 * Created with IntelliJ IDEA
 */

public class FindSubstring_30 {
    /**
     * Use two maps to store expected appearance times and appeared times.
     * One map to record the expected times of each word, the other map to record the times words have been seen.
     * Then check for every possible position of i.
     * Once an unexpected word was met or the times of some word is larger than its expected times, stop the check.
     * If check is finished successfully, push i to the result indexes.
     *
     * @param s     input string
     * @param words input word list
     * @return indices of word concatenation start
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (s.length() < 1 || words.length < 1) {
            return out;
        }

        HashMap<String, Integer> wordMap = new HashMap<>();     // save word appearance in words array
        HashMap<String, Integer> stringMap = new HashMap<>();       // save word appearance in string
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);     // avoid corner case such as duplicated words in array or given string
        }

        for (int i = 0; i < s.length() - words[0].length() * words.length + 1; i++) {
            int j = 0;

            while (j < words.length) {      // j is the words index that count # in words[]

                /* Spilt string to a substring that has same length as given word */
                String sub = s.substring(i + j * words[0].length(), i + (j + 1) * words[0].length());

                if (wordMap.containsKey(sub)) {
                    stringMap.put(sub, stringMap.getOrDefault(sub, 0) + 1);
                    if (stringMap.get(sub) > wordMap.get(sub)) {
                        break;      // substring contains more words than given array
                    }
                } else {
                    break;      // current substring does not exist in words array
                }
                j++;        // j + 1: next word in array
            }

            if (j == words.length) {
                out.add(i);     // find all correct words in current substring
            }
        }

        return out;
    }

    public static void main(String[] args) {

        /* Substring with Concatenation of All Words */
        String s = "barfoothefoobarman";
        String[] words = {"bar", "foo"};

        final long startTime = System.currentTimeMillis();  // Timer

        FindSubstring_30 findSubstringTest = new FindSubstring_30();
        System.out.println(findSubstringTest.findSubstring(s, words));

        final long endTime = System.currentTimeMillis();    // Timer
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }
}
