package playground;

import java.util.*;

/**
 * Find any path that contains in word ladder II.
 *
 * @author BorisMirage
 * Time: 2019/11/05 16:16
 * Created with IntelliJ IDEA
 */

public class WordLadderIII {
    /**
     * Modify from word ladder II, the only difference is that in hash map, there is no need to store all paths.
     * Direct over write previous path if find a new one.
     *
     * @param beginWord begin word
     * @param endWord   end word
     * @param wordList  all words can be reached
     * @return any of path that can be converted from begin word to end word
     */
    public List<String> wordLadderIII(String beginWord, String endWord, List<String> wordList) {

        /* Corner case */
        if (beginWord == null || endWord == null || wordList == null) {
            return null;
        }
        if (beginWord.equals(endWord)) {
            return Collections.singletonList(beginWord);
        }

        Set<String> set = new HashSet<>(wordList);      // convert input list to hash set
        Queue<String> q = new LinkedList<>();           // BFS queue
        q.add(beginWord);
        HashMap<String, List<String>> m = new HashMap<>();      // key: word, value: path to current word
        List<String> initPath = new LinkedList<>();             // all path starts at begin word
        m.put(beginWord, initPath);
        m.get(beginWord).add(beginWord);
        boolean found = false;                          // if found end word, break BFS
        int size;

        while (!q.isEmpty() && !set.isEmpty() && !found) {
            size = q.size();
            HashSet<String> currentLayer = new HashSet<>();

            for (int n = 0; n < size; n++) {
                String tmp = q.poll();
                List<String> path = m.get(tmp);

                for (int i = 0; tmp != null && i < tmp.length(); i++) {
                    char[] arr = tmp.toCharArray();
                    for (int j = 0; j < 26; j++) {
                        arr[i] = (char) (j + 'a');
                        String w = new String(arr);
                        if (set.contains(w) && !w.equals(tmp) && path != null) {
                            q.add(w);
                            currentLayer.add(w);
                            found |= endWord.equals(w);
                            List<String> currentPath = new LinkedList<>(path);
                            currentPath.add(w);
                            m.put(w, currentPath);
                        }
                    }
                }

                m.remove(tmp);
            }
            set.removeAll(currentLayer);        // avoid duplication
        }

        return m.getOrDefault(endWord, new LinkedList<>());
    }

    public static void main(String[] args) {
        WordLadderIII test = new WordLadderIII();
        String b = "hit";
        String e = "cog";
        List<String> l = Arrays.asList("hot", "dog", "lot", "log", "cog", "hop", "tot", "hog");
        System.out.println(test.wordLadderIII(b, e, l));

        b = "a";
        e = "d";
        l = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        System.out.println(test.wordLadderIII(b, e, l));

        b = "red";
        e = "tax";
        l = Arrays.asList("ted", "tex", "red", "tax", "tad", "hop", "rex", "pee");
        System.out.println(test.wordLadderIII(b, e, l));

        b = "ted";
        e = "tax";
        l = Arrays.asList("ted", "tex", "red", "abc", "tad", "hop", "rex", "pee");
        System.out.println(test.wordLadderIII(b, e, l));

        b = "a";
        e = "a";
        l = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        System.out.println(test.wordLadderIII(b, e, l));
    }
}
