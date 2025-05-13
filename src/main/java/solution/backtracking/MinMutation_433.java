package solution.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * - Every adjacent pair of words differs by a single letter.
 * - Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * - sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * @author BorisMirage
 * Time: 2025/05/13 11:36
 * Created with IntelliJ IDEA
 */

public class MinMutation_433 {
    /**
     * Finds the minimum number of mutations needed to transform the start gene string into the end gene string.
     * Each mutation involves changing exactly one character in the string to another valid character from the bank.
     *
     * @param startGene The initial gene string.
     * @param endGene   The target gene string.
     * @param bank      An array of valid gene strings that can be used for mutation.
     * @return The minimum number of mutations required to convert startGene to endGene, or -1 if no solution exists.
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene == null || startGene.isEmpty() || endGene == null || endGene.isEmpty() || startGene.equals(endGene)) {
            return 0;
        }
        if (startGene.length() != endGene.length()) {
            return -1;
        }

        Set<String> bankSet = new HashSet<>(List.of(bank));
        return bfs(startGene, endGene, bankSet);
    }

    /**
     * BFS to find the minimum number of mutations required to transform the start gene
     * into the end gene by traversing through valid mutations present in the bank.
     *
     * @param start The initial gene string.
     * @param end   The target gene string.
     * @param bank  A set of valid gene strings that can be used for mutation.
     * @return The minimum number of mutations needed to transform start into end, or -1 if no solution exists.
     */
    private int bfs(String start, String end, Set<String> bank) {
        char[] gene = new char[]{'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        int count = 0;
        queue.add(start);
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current != null) {
                    if (end.equals(current)) {
                        return count;
                    }

                    char[] array = current.toCharArray();
                    for (int j = 0; j < array.length; j++) {
                        char tmp = array[j];
                        for (char c : gene) {
                            array[j] = c;
                            String newString = new String(array);
                            if (bank.contains(newString) && !visited.contains(newString)) {
                                queue.add(newString);
                                visited.add(newString);
                            }
                        }
                        array[j] = tmp;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
