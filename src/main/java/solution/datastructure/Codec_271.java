package solution.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Note:
 * 1. The string may contain any possible characters out of 256 valid ascii characters.
 * 2. Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * 3. Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 *
 * @author BorisMirage
 * Time: 2019/07/06 20:16
 * Created with IntelliJ IDEA
 */

public class Codec_271 {
    /**
     * Encodes a list of strings to a single string.
     * The method to encoding is by add a "/LENGTH_OF_STRING/".
     * First slash is the start mark of length, and second slash is the end of string length.
     *
     * @param strs given string
     * @return encoded string
     */
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append("/").append(str.length()).append("/").append(str);       // encoded to "/LENGTH/STRING"
        }
        return encoded.toString();
    }

    /**
     * Decodes a single string to a list of strings.
     * Find slash and length (represent length of upcoming string) in encoded string and cut corresponding substring.
     *
     * @param s encoded string
     * @return decoded list
     */
    public List<String> decode(String s) {
        int i = 0;
        List<String> out = new ArrayList<>();

        while (i++ < s.length()) {
            int length = 0;

            while (i < s.length() && Character.isDigit(s.charAt(i))) {      // avoid out of bound
                length = length * 10 + s.charAt(i++) - '0';     // count length of next substring
            }

            out.add(s.substring(i + 1, i + length + 1));        // cut substring
            i += length + 1;
        }
        return out;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("0");
        list.add("electricity");
        list.add("hello");
        list.add("word");
        Codec_271 test = new Codec_271();
        System.out.println(test.decode(test.encode(list)));
    }
}