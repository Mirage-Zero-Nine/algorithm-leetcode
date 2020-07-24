package Solution.Others;

/**
 * Given a string S, check if the letters can be rearranged so that two adjacent characters are not the same.
 * If possible, output any possible result. If not possible, return the empty string.
 *
 * @author BorisMirage
 * Time: 2020/05/03 19:39
 * Created with IntelliJ IDEA
 */

public class ReorganizeString_767 {
    /**
     * Count the appearance of each char in given string.
     * If the most appeared char occurred more than half of the length of string, then it is impossible to rearrange.
     * Then put the most frequent char into output result. Leave a space to avoid identical adjacent char.
     * Then put the rest of char. Start after the first char.
     *
     * @param S given string
     * @return whether the letters can be rearranged so that two adjacent characters are not the same
     */
    public String reorganizeString(String S) {
        int[] map = new int[26];
        int max = 0, maxIndex = 0;

        for (int i = 0; i < S.length(); i++) {      // find the most appeared char and count the number of appearance
            int current = S.charAt(i) - 'a';
            map[current]++;
            if (map[current] > max) {
                max = map[current];     // new max appearance
                maxIndex = current;     // count of max appearance
            }
        }

        if (max > (S.length() + 1) / 2) {       // if the most frequent char appears more than the half, return false
            return "";
        }

        char[] tmp = new char[S.length()];
        int index = 0;

        while (map[maxIndex] > 0) {      // place chars appears the most
            tmp[index] = (char) (maxIndex + 'a');
            index += 2;
            map[maxIndex]--;
        }

        for (int i = 0; i < map.length; i++) {     // put rest of chars
            while (map[i] > 0) {
                if (index >= tmp.length) {      // reset put position if move out of bound
                    index = 1;
                }
                tmp[index] = (char) (i + 'a');
                index += 2;
                map[i]--;
            }
        }

        return String.valueOf(tmp);
    }
}
