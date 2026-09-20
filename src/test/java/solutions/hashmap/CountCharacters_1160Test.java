package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountCharacters_1160Test {

    private final CountCharacters_1160 test = new CountCharacters_1160();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        assertEquals(10, test.countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countCharacters(new String[]{}, "abc"));
        assertEquals(0, test.countCharacters(new String[]{"abc"}, "xy"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.countCharacters(new String[]{"abc", "def", "ghi"}, "abcdefghi"));
    }

    @Test
    public void testSingleCharWords() {
        assertEquals(3, test.countCharacters(new String[]{"a", "b", "c"}, "abc"));
    }

    @Test
    public void testDuplicateCharsNeeded() {
        // "aa" needs two 'a's but chars only has one
        assertEquals(0, test.countCharacters(new String[]{"aa"}, "a"));
    }

    @Test
    public void testDuplicateCharsAvailable() {
        assertEquals(2, test.countCharacters(new String[]{"aa"}, "aa"));
    }

    @Test
    public void testAllWordsGood() {
        assertEquals(6, test.countCharacters(new String[]{"ab", "cd", "ef"}, "abcdef"));
    }

    @Test
    public void testNoWordsGood() {
        assertEquals(0, test.countCharacters(new String[]{"xyz", "uvw"}, "abc"));
    }

    @Test
    public void testEmptyChars() {
        assertEquals(0, test.countCharacters(new String[]{"a", "b"}, ""));
    }

    @Test
    public void testGiantCase() {
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) {
            words[i] = "a";
        }
        // Each word "a" can be formed from chars "a"
        assertEquals(1000, test.countCharacters(words, "a"));
    }
    @Test void extra01() { assertEquals(0, test.countCharacters(new String[]{"z"}, "a")); }
    @Test void extra02() { assertEquals(4, test.countCharacters(new String[]{"a", "aa", "b"}, "aab")); }
    @Test void extra03() { assertEquals(4, test.countCharacters(new String[]{"ab", "ba"}, "ab")); }
    @Test void extra04() { assertEquals(0, test.countCharacters(new String[]{"abc"}, "ab")); }
    @Test void extra05() { assertEquals(6, test.countCharacters(new String[]{"abc", "cba"}, "abcabc")); }
    @Test void extra06() { assertEquals(2, test.countCharacters(new String[]{"a", "b", "c"}, "ab")); }
    @Test void extra07() { assertEquals(0, test.countCharacters(new String[]{"aaab"}, "aab")); }
    @Test void extra08() { assertEquals(3, test.countCharacters(new String[]{"zz", "z"}, "zzz")); }
    @Test void extra09() { assertEquals(0, test.countCharacters(new String[]{}, "")); }
    @Test void extra10() { assertEquals(5, test.countCharacters(new String[]{"hello"}, "hello")); }
}
