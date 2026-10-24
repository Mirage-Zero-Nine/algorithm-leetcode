package solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * @author BorisMirage
 * Time: 2019/06/26 18:02
 * Created with IntelliJ IDEA
 */

public class DecodeString_394 {
    /**
     * Scans the encoding once while keeping decoded text in one builder. Each opening bracket
     * saves the builder position; the matching repeat count and position are popped together at
     * the closing bracket. The first copy of the group is already in the builder, so only the
     * remaining copies are appended. A count of one needs no copy, including in nested groups.
     *
     * <p>A valid problem input has encoded length {@code n} from 1 through 30, repeat counts from 1
     * through 300, and decoded length at most 100,000. A {@code null} or empty input is returned
     * unchanged, and empty bracket bodies are treated as empty strings. Other inputs must follow
     * the valid encoding grammar with lowercase English letters and ASCII repeat digits. The input
     * is not modified.
     *
     * <p>Time complexity: O(n + L), where {@code n} is the encoded input length and {@code L} is
     * the decoded output length. Scanning costs O(n). Each nonempty group snapshot is matched by at
     * least one appended copy, and all appended copies together account for at most O(L) work.
     *
     * <p>Auxiliary space complexity: O(L + d), where {@code d} is the maximum bracket nesting
     * depth. The working builder and one temporary group snapshot use O(L) space, and the two
     * stacks store O(d) counts and positions; the returned string uses O(L) output space.
     *
     * @param encodedString encoded input to decode
     * @return the decoded string, or {@code null} when the input is {@code null}
     */
    public String decodeString(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) {
            return encodedString;
        }

        // These local stacks use ArrayDeque to avoid Stack's unnecessary synchronization.
        Deque<Integer> repeatCounts = new ArrayDeque<>();
        Deque<Integer> groupStartIndices = new ArrayDeque<>();
        StringBuilder decodedString = new StringBuilder();
        int inputIndex = 0;

        while (inputIndex < encodedString.length()) {
            char currentChar = encodedString.charAt(inputIndex);
            if (Character.isDigit(currentChar)) {
                int number = 0;
                while (Character.isDigit(encodedString.charAt(inputIndex))) {
                    number = number * 10 + (encodedString.charAt(inputIndex++) - '0');
                }
                repeatCounts.push(number);
            } else if (currentChar == '[') {
                groupStartIndices.push(decodedString.length());
                inputIndex++;
            } else if (currentChar == ']') {
                // LIFO order pairs these values with the group opened by the matching '['.
                int repeatCount = repeatCounts.pop();
                int groupStartIndex = groupStartIndices.pop();
                int groupLength = decodedString.length() - groupStartIndex;
                // The fully decoded first copy is already in the builder; skip count-one and empty groups.
                if (repeatCount > 1 && groupLength > 0) {
                    // An immutable snapshot keeps appended copies from changing the source body.
                    String groupContents = decodedString.substring(groupStartIndex);
                    decodedString.repeat(String.valueOf(groupContents), repeatCount - 1);
                }
                inputIndex++;
            } else {
                decodedString.append(currentChar);
                inputIndex++;
            }
        }

        return decodedString.toString();
    }
}
