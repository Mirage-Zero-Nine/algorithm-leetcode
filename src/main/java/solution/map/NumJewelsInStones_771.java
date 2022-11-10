package solution.map;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 * The letters in J are guaranteed distinct, and all characters in J and S are letters.
 * Letters are case-sensitive, so "a" is considered a different type of stone from "A".
 *
 * @author BorisMirage
 * Time: 2019/07/16 21:57
 * Created with IntelliJ IDEA
 */

public class NumJewelsInStones_771 {
    /**
     * Use a hash set to save elements in J.
     *
     * @param jewels given jewels string
     * @param stones given stones string
     * @return how many chars in J are both in S
     */
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = jewels.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        return (int) stones.chars()
                .filter(c -> set.contains((char) c))
                .count();
    }
}
