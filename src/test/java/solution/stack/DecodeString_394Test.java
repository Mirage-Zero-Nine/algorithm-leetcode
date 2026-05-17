package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecodeString_394Test {
    private final DecodeString_394 d = new DecodeString_394();

    @Test public void testSimple() { assertEquals("accaccacc", d.decodeString("3[a2[c]]")); }
    @Test public void testNoRepeat() { assertEquals("abc", d.decodeString("abc")); }
    @Test public void testSingleRepeat() { assertEquals("aaa", d.decodeString("3[a]")); }
    @Test public void testNull() { assertNull(d.decodeString(null)); }
    @Test public void testEmpty() { assertEquals("", d.decodeString("")); }
    @Test public void testMultipleRepeat() { assertEquals("ababab", d.decodeString("3[ab]")); }
    @Test public void testNestedDeep() { assertEquals("aaaaaa", d.decodeString("2[3[a]]")); }
    @Test public void testAdjacentBrackets() { assertEquals("aaabbb", d.decodeString("3[a]3[b]")); }
    @Test public void testMixedTextAndBrackets() { assertEquals("xaaay", d.decodeString("x3[a]y")); }
    @Test public void testDoubleDigitRepeat() { assertEquals("a".repeat(12), d.decodeString("12[a]")); }
    @Test public void testGiant() {
        // 100[ab] = "ab" repeated 100 times
        assertEquals("ab".repeat(100), d.decodeString("100[ab]"));
    }

    @Test public void testAdjacentWithTrailingLetters() {
        // 2[abc]3[cd]ef -> abcabccdcdcdef
        assertEquals("abcabccdcdcdef", d.decodeString("2[abc]3[cd]ef"));
    }

    @Test public void testTenRepeat() {
        // 10[a] -> aaaaaaaaaa
        assertEquals("a".repeat(10), d.decodeString("10[a]"));
    }

    @Test public void testMultiDigitNested() {
        // 12[a3[b]] -> (abbb) repeated 12 times
        assertEquals("abbb".repeat(12), d.decodeString("12[a3[b]]"));
    }

    @Test public void testDeepNesting5Levels() {
        // 2[a2[b2[c2[d2[e]]]]] - 5 levels deep
        // innermost: 2[e] = ee
        // 2[dee] = deedee
        // 2[cdeedee] = cdeedeecdeedee
        // 2[bcdeedeecdeedee] = bcdeedeecdeede ebcdeedeecdeedee
        // 2[a...] = ...
        String l5 = "ee";
        String l4 = ("d" + l5).repeat(2);
        String l3 = ("c" + l4).repeat(2);
        String l2 = ("b" + l3).repeat(2);
        String l1 = ("a" + l2).repeat(2);
        assertEquals(l1, d.decodeString("2[a2[b2[c2[d2[e]]]]]"));
    }

    @Test public void testSingleRepetition() {
        // 1[a] -> a
        assertEquals("a", d.decodeString("1[a]"));
    }

    @Test public void testSingleRepetitionLonger() {
        // 1[abc] -> abc
        assertEquals("abc", d.decodeString("1[abc]"));
    }

    @Test public void testOnlyLettersLong() {
        // No brackets at all, long string
        assertEquals("abcdefghij", d.decodeString("abcdefghij"));
    }

    @Test public void testResultLengthProperty() {
        // 3[a2[c]] -> accaccacc, length = 3 * (1 + 2*1) = 9
        String result = d.decodeString("3[a2[c]]");
        assertEquals(9, result.length());
    }

    @Test public void testLargeMultiDigit() {
        // 50[ab] -> "ab" * 50, length 100
        String result = d.decodeString("50[ab]");
        assertEquals(100, result.length());
        assertEquals("ab".repeat(50), result);
    }
}
