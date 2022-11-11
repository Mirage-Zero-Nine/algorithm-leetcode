package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/11 14:44
 * Created with IntelliJ IDEA
 */

public class RemoveVowels1119Test {

    private final RemoveVowels_1119 test = new RemoveVowels_1119();

    @Test
    public void test() {
        assertEquals("ltcdscmmntyfrcdrs", test.removeVowels("leetcodeisacommunityforcoders"));
        assertEquals("bcdfghjklmn", test.removeVowels("abcdefghijklmn"));
    }

    @Test
    public void testEmpty() {
        assertEquals("", test.removeVowels("aeiou"));
        assertEquals("", test.removeVowels(""));
    }
}