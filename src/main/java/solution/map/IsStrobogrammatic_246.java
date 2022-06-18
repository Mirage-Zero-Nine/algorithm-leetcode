package solution.map;

import java.util.HashMap;
import java.util.Map;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * @author BorisMirage
 * Time: 2018/10/12 10:35
 * Created with IntelliJ IDEA
 */

public class IsStrobogrammatic_246 {
    /**
     * Use hash map to store pairs.
     *
     * @param num given num string
     * @return if this given number is strobogrammatic
     */
    public boolean isStrobogrammatic(String num) {

        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) {
                return false;
            }
            if (map.get(num.charAt(l)) != num.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }
}
