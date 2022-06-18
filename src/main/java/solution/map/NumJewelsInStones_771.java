package solution.map;

import java.util.HashSet;

/**
 * Given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 * The letters in J are guaranteed distinct, and all characters in J and S are letters.
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * @author BorisMirage
 * Time: 2019/07/16 21:57
 * Created with IntelliJ IDEA
 */

public class NumJewelsInStones_771 {
    /**
     * Use a hash set to save elements in J.
     *
     * @param J given jewels string
     * @param S given stones string
     * @return how many chars in J are both in S
     */
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> s = new HashSet<>();

        for (int i = 0; i < J.length(); i++) {
            s.add(J.charAt(i));
        }

        int total = 0;

        for (int i = 0; i < S.length(); i++) {
            if (s.contains(S.charAt(i))) {
                total++;
            }
        }

        return total;
    }
}
