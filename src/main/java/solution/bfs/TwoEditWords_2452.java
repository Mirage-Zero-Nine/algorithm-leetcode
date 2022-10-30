package solution.bfs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given two string arrays, queries and dictionary.
 * All words in each array comprise lowercase English letters and have the same length.
 * In one edit you can take a word from queries, and change any letter in it to any other letter.
 * Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
 * Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits.
 * Return the words in the same order they appear in queries.
 *
 * @author BorisMirage
 * Time: 2022/10/30 11:18
 * Created with IntelliJ IDEA
 */

public class TwoEditWords_2452 {
    /**
     * Similar to word ladder. However, approach is not as complex as word ladder.
     * If a word in query has less than 3 differences in any word in dictionary, then it's a valid output.
     * Iterate all words in queries, find all words match the condition above.
     *
     * @param queries    array of query words
     * @param dictionary array of words in dictionary
     * @return a list of all words from queries match with some word from dictionary after a maximum of two edits
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        return Arrays.stream(queries)
                .filter(query -> Arrays.stream(dictionary)
                        .anyMatch(word -> IntStream.range(0, word.length())
                                // filter all words in queries that matches any word in dictionary with less than 3 different chars
                                .filter(i -> word.charAt(i) != query.charAt(i)).count() < 3))
                .collect(Collectors.toList());
    }
}
