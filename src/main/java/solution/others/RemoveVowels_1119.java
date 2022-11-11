package solution.others;

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

        s.chars().forEach(
                c -> {
                    if ((char) c != 'a' && (char) c != 'e' && (char) c != 'i' && (char) c != 'o' && (char) c != 'u') {
                        sb.append((char) c);
                    }
                }
        );

        return sb.toString();
    }
}
