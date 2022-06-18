package solution.slidingwindow;

/**
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false.
 * He wants to confuse the students by maximizing the number of consecutive questions with the same answer.
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question.
 * In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 *
 * @author BorisMirage
 * Time: 2021/10/09 16:47
 * Created with IntelliJ IDEA
 */

public class MaxConsecutiveAnswers_2024 {
    /**
     * Sliding window for 2 times.
     * First check the longest substring of consecutive F (or T), then check the longest substring of other char.
     * Shrink window when there contains more than k chars that needs to flip.
     *
     * @param answerKey given string
     * @param k         k time of flip
     * @return the maximum number of consecutive 'T's or 'F's in the string after flip at most k times
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(findMax(answerKey, k, 'T'), findMax(answerKey, k, 'F'));
    }

    /**
     * Find length of the longest consecutive substring.
     *
     * @param s          given string
     * @param k          k times of flip
     * @param targetChar consecutive char
     * @return length of the longest consecutive substring
     */
    private int findMax(String s, int k, char targetChar) {
        int n = s.length();
        int max = 0, start = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != targetChar) {
                k--;
            }
            while (k < 0) {
                if (s.charAt(start) != targetChar) {
                    k++;
                }
                start++;
            }
            max = Math.max(i - start + 1, max);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxConsecutiveAnswers_2024 maxConsecutiveAnswers_2024 = new MaxConsecutiveAnswers_2024();
        System.out.println(maxConsecutiveAnswers_2024.maxConsecutiveAnswers("TTFTTFTT", 1));
    }
}
