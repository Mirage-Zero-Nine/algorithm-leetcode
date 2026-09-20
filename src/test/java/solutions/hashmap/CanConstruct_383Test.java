package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/19 23:36
 * Created with IntelliJ IDEA
 */

public class CanConstruct_383Test {

    private final CanConstruct_383 test = new CanConstruct_383();

    @Test
    public void test() {
        assertTrue(test.canConstruct("aa", "aab"));
    }

    @Test
    public void testFalse() {
        assertFalse(test.canConstruct("a", "b"));
        assertFalse(test.canConstruct("aa", "ab"));
    }

    @Test
    public void testHappyCases() {
        assertTrue(test.canConstruct("", ""));
        assertTrue(test.canConstruct("abc", "cbad"));
        assertTrue(test.canConstruct("zzz", "zzzz"));
        assertTrue(test.canConstruct("leetcode", "codeleet"));
    }

    @Test
    public void testEdgeAndNegativeCases() {
        assertFalse(test.canConstruct("abc", "ab"));
        assertFalse(test.canConstruct("abcd", "abc"));
        assertFalse(test.canConstruct("zzz", "zz"));
        assertTrue(test.canConstruct("x", "x"));
    }

    @Test
    public void testGiantCase() {
        String ransomNote = "a".repeat(500) + "b".repeat(300) + "c".repeat(200);
        String magazine = "c".repeat(300) + "b".repeat(500) + "a".repeat(600);
        assertTrue(test.canConstruct(ransomNote, magazine));
    }

    @Test
    public void testSingleCharMatch() {
        assertTrue(test.canConstruct("a", "a"));
    }

    @Test
    public void testSingleCharNoMatch() {
        assertFalse(test.canConstruct("a", "b"));
    }

    @Test
    public void testMagazineShorterThanNote() {
        assertFalse(test.canConstruct("aabb", "ab"));
    }

    @Test
    public void testAllSameChars() {
        assertTrue(test.canConstruct("aaaa", "aaaa"));
        assertFalse(test.canConstruct("aaaaa", "aaaa"));
    }

    @Test
    public void testGiantNegativeCase() {
        String ransomNote = "z".repeat(1000);
        String magazine = "z".repeat(999) + "a".repeat(1000);
        assertFalse(test.canConstruct(ransomNote, magazine));
    }

    @Test void extra01() { assertTrue(test.canConstruct("a", "a")); }
    @Test void extra02() { assertFalse(test.canConstruct("b", "a")); }
    @Test void extra03() { assertTrue(test.canConstruct("abc", "cba")); }
    @Test void extra04() { assertFalse(test.canConstruct("abcd", "abc")); }
    @Test void extra05() { assertTrue(test.canConstruct("zz", "zzz")); }
    @Test void extra06() { assertTrue(test.canConstruct("aab", "aba")); }
    @Test void extra07() { assertTrue(test.canConstruct("xyz", "xzyw")); }
    @Test void extra08() { assertFalse(test.canConstruct("hello", "helo")); }
    @Test void extra09() { assertTrue(test.canConstruct("", "anything")); }
    @Test void extra10() { assertTrue(test.canConstruct("long", "loong")); }
}
