package solution.others;

import java.util.Arrays;

/**
 * You have an array of logs. Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * 1. Each word after the identifier will consist only of lowercase letters, or;
 * 2. Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 * Return the final order of the logs.
 *
 * @author BorisMirage
 * Time: 2020/08/01 19:15
 * Created with IntelliJ IDEA
 */

public class ReorderLogFiles_937 {
    /**
     * Sort the array.
     * The comparator has this order:
     * 1. If both log has letter word, simply compare the word, if the word is same, compare the identifier.
     * 2. Otherwise, if both word are digit word, then keep the order.
     * 3. If anyone of the word is letter word, the letter word has higher priority (return -1).
     *
     * @param logs given log list
     * @return sorted array
     */
    public String[] reorderLogFiles(String[] logs) {

        Arrays.sort(logs, (o1, o2) -> {
            String[] log1 = o1.split(" ", 2), log2 = o2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(log1[1].charAt(0)), isDigit2 = Character.isDigit(log2[1].charAt(0));

            if (!isDigit1 && !isDigit2) {       // if both starts with letter, then this has higher priority
                if (log1[1].equals(log2[1])) {
                    return log1[0].compareTo(log2[0]);
                }
                return log1[1].compareTo(log2[1]);
            }

            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;      // if both digit, remain the order in list (return 0)
        });

        return logs;
    }
}
