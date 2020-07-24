package Solution.Others;

/**
 * Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 *
 * @author BorisMirage
 * Time: 2020/05/01 18:30
 * Created with IntelliJ IDEA
 */

public class RemoveVowels_1119 {
    /**
     * Simply traverse the string and keep a string builder to append all chars that are not vowels.
     *
     * @param s given string
     * @return string without vowels
     */
    public String removeVowels(String s) {
        StringBuilder sb = new StringBuilder();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
