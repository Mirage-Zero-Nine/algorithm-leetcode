package solution.array;

/**
 * You are playing the Bulls and Cows game with your friend: You give a number and ask your friend to guess it.
 * When guessing, return how many digits are exactly same, and how many digits are right but in wrong position.
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * Write a function to return a hint by the secret number and guess, use A to indicate the bulls and B for cows.
 * Please note that both secret number and friend's guess may contain duplicate digits.
 *
 * @author BorisMirage
 * Time: 2020/04/04 10:55
 * Created with IntelliJ IDEA
 */

public class GetHint_299 {
    /**
     * Use an array to count the digits may match but locate in the wrong position.
     * 1. Exactly same number: add one to the counter, do nothing to array.
     * 2. Otherwise, add one in array at value of secret, and subtract one in array at value of guess.
     * Later on, if next value in secret is smaller than 0, then there exists a previous value same to guess.
     * Also, if next value in guess is larger than 0, then there exists a previous value same to secret.
     *
     * @param secret given secret
     * @param guess  guess of secret
     * @return how many digits are exactly same and how many digits match the secret but locate in the wrong position
     */
    public String getHint(String secret, String guess) {
        int a = 0, b = 0, n = secret.length();
        int[] tmp = new int[10];

        for (int i = 0; i < n; i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {
                a++;
            } else {
                if (tmp[s] < 0) {
                    b++;
                }
                if (tmp[g] > 0) {
                    b++;
                }
                tmp[s]++;
                tmp[g]--;
            }
        }

        return a + "A" + b + "B";
    }
}
