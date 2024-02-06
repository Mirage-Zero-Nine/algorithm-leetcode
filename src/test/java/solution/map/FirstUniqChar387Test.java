package solution.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2024/02/06 13:37
 * Created with IntelliJ IDEA
 */

public class FirstUniqChar387Test {
    private final FirstUniqChar_387 firstUniqueChar = new FirstUniqChar_387();

    @Test
    void testFirstUniqueChar_normalCase() {
        String str = "statistics";
        int expected = 2;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_noUniqueChars() {
        String str = "aaaa";
        int expected = -1;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_allUniqueChars() {
        String str = "abcd";
        int expected = 0;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_emptyString() {
        String str = "";
        int expected = -1;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_firstCharUnique() {
        String str = "xxyz";
        int expected = 2;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_lastCharUnique() {
        String str = "xyyz";
        int expected = 0;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_middleCharUnique() {
        String str = "aabbccd";
        int expected = 6;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_multipleUniqueChars() {
        String str = "leetcode";
        int expected = 0;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_multipleOccurrencesOfUniqueChar() {
        String str = "loveleetcode";
        int expected = 2;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }
}