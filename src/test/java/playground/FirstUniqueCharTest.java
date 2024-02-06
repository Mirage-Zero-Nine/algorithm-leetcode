package playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2024/02/06 13:37
 * Created with IntelliJ IDEA
 */

public class FirstUniqueCharTest {

    @Test
    void testFirstUniqueChar_normalCase() {
        String str = "statistics";
        int expected = 3;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_noUniqueChars() {
        String str = "aaaa";
        int expected = -1;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_allUniqueChars() {
        String str = "abcd";
        int expected = 1;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_emptyString() {
        String str = "";
        int expected = -1;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_firstCharUnique() {
        String str = "xxyz";
        int expected = 3;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_lastCharUnique() {
        String str = "xyyz";
        int expected = 1;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstUniqueChar_middleCharUnique() {
        String str = "aabbccd";
        int expected = 7;
        int actual = FirstUniqueChar.firstUniqueChar(str);
        assertEquals(expected, actual);
    }
}