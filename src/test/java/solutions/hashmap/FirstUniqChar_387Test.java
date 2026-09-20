package solutions.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2024/02/06 13:37
 * Created with IntelliJ IDEA
 */

public class FirstUniqChar_387Test {
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

    @Test
    void testFirstUniqueChar_giantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb.append('a');
        sb.append('z');
        String str = sb.toString();
        int expected = 50000;
        int actual = firstUniqueChar.firstUniqChar(str);
        assertEquals(expected, actual);
        actual = firstUniqueChar.linkedHashMap(str);
        assertEquals(expected, actual);
    }
    @Test void extra01() { assertEquals(0, firstUniqueChar.firstUniqChar("a")); assertEquals(0, firstUniqueChar.linkedHashMap("a")); }
    @Test void extra02() { assertEquals(-1, firstUniqueChar.firstUniqChar("aa")); assertEquals(-1, firstUniqueChar.linkedHashMap("aa")); }
    @Test void extra03() { assertEquals(4, firstUniqueChar.firstUniqChar("aabbc")); assertEquals(4, firstUniqueChar.linkedHashMap("aabbc")); }
    @Test void extra04() { assertEquals(0, firstUniqueChar.firstUniqChar("leetcode")); assertEquals(0, firstUniqueChar.linkedHashMap("leetcode")); }
    @Test void extra05() { assertEquals(2, firstUniqueChar.firstUniqChar("loveleetcode")); assertEquals(2, firstUniqueChar.linkedHashMap("loveleetcode")); }
    @Test void extra06() { assertEquals(4, firstUniqueChar.firstUniqChar("aabbcdd")); assertEquals(4, firstUniqueChar.linkedHashMap("aabbcdd")); }
    @Test void extra07() { assertEquals(1, firstUniqueChar.firstUniqChar("abac")); assertEquals(1, firstUniqueChar.linkedHashMap("abac")); }
    @Test void extra08() { assertEquals(-1, firstUniqueChar.firstUniqChar("zzzz")); assertEquals(-1, firstUniqueChar.linkedHashMap("zzzz")); }
    @Test void extra09() { assertEquals(6, firstUniqueChar.firstUniqChar("aabbccd")); assertEquals(6, firstUniqueChar.linkedHashMap("aabbccd")); }
    @Test void extra10() { assertEquals(1, firstUniqueChar.firstUniqChar("unique")); assertEquals(1, firstUniqueChar.linkedHashMap("unique")); }
}
