package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AlienOrder_269Test {

    private final AlienOrder_269 test = new AlienOrder_269();

    @Test
    public void testHappyCases() {
        String result = test.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"});
        assertTrue(result.contains("w") && result.contains("e") && result.contains("r"));
        assertEquals("zx", test.alienOrder(new String[]{"z", "x"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.alienOrder(new String[]{"z", "x", "z"}));
        assertEquals("z", test.alienOrder(new String[]{"z"}));
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testLargeCase() {
        String result = test.alienOrder(new String[]{"za", "zb", "ca", "cb"});
        assertTrue(result.indexOf('z') < result.indexOf('c') || result.indexOf('a') < result.indexOf('b'));
    }

    @Test
    public void testEmptyInput() {
        assertEquals("", test.alienOrder(new String[]{}));
    }

    @Test
    public void testSingleCharWords() {
        String result = test.alienOrder(new String[]{"a", "b", "c"});
        assertEquals(3, result.length());
        assertTrue(result.indexOf('a') < result.indexOf('b'));
        assertTrue(result.indexOf('b') < result.indexOf('c'));
    }

    @Test
    public void testTwoIdenticalWords() {
        String result = test.alienOrder(new String[]{"z", "z"});
        assertEquals("z", result);
    }

    @Test
    public void testCycleDetection() {
        assertEquals("", test.alienOrder(new String[]{"a", "b", "a"}));
    }

    @Test
    public void testPrefixValid() {
        // "ab" before "abc" is valid (prefix comes first)
        String result = test.alienOrder(new String[]{"ab", "abc"});
        assertTrue(result.contains("a") && result.contains("b") && result.contains("c"));
    }

    @Test
    public void testPrefixInvalid() {
        // "abc" before "ab" is invalid (longer prefix before shorter)
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testMultipleValidOrders() {
        // Only constraint: t < f from "wrt" vs "wrf"
        String result = test.alienOrder(new String[]{"wrt", "wrf"});
        assertTrue(result.indexOf('t') < result.indexOf('f'));
    }

    @Test
    public void testGiantCase() {
        // Build a long chain: a < b < c < ... < z via 26 single-char words
        String[] words = new String[26];
        for (int i = 0; i < 26; i++) {
            words[i] = String.valueOf((char) ('a' + i));
        }
        String result = test.alienOrder(words);
        assertEquals(26, result.length());
        for (int i = 0; i < 25; i++) {
            assertTrue(result.indexOf((char) ('a' + i)) < result.indexOf((char) ('a' + i + 1)));
        }
    }

    @Test
    public void testDisconnectedChars() {
        // "ac", "bc" => a < b, c has no ordering constraint relative to a/b
        String result = test.alienOrder(new String[]{"ac", "bc"});
        assertTrue(result.indexOf('a') < result.indexOf('b'));
        assertEquals(3, result.length());
    }

    @Test
    public void testSingleWordReturnsWord() {
        // Single word returns the word itself (all letters, any permutation valid)
        assertEquals("abc", test.alienOrder(new String[]{"abc"}));
        assertEquals("z", test.alienOrder(new String[]{"z"}));
        assertEquals("hello", test.alienOrder(new String[]{"hello"}));
    }

    @Test
    public void testLeetCodeExampleValidTopoOrder() {
        // LeetCode example: constraints are w<e, e<r, t<f, r<t
        String result = test.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"});
        assertEquals(5, result.length());
        // Verify all ordering constraints hold
        assertTrue(result.indexOf('w') < result.indexOf('e'));
        assertTrue(result.indexOf('e') < result.indexOf('r'));
        assertTrue(result.indexOf('t') < result.indexOf('f'));
        assertTrue(result.indexOf('r') < result.indexOf('t'));
    }

    @Test
    public void testCycleReturnsEmpty() {
        // Direct cycle: z < x < z
        assertEquals("", test.alienOrder(new String[]{"z", "x", "z"}));
        // Indirect cycle: a<b (from "a","b"), b<c (from "b","c"), c<a (from "c","a")
        assertEquals("", test.alienOrder(new String[]{"a", "b", "c", "a"}));
    }

    @Test
    public void testPrefixViolationReturnsEmpty() {
        // Longer word before its prefix is invalid
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
        assertEquals("", test.alienOrder(new String[]{"xyz", "xy"}));
        assertEquals("", test.alienOrder(new String[]{"abcd", "abc"}));
    }

    @Test
    public void testAllSingleCharWordsChain() {
        // Single char words define a total order
        String result = test.alienOrder(new String[]{"c", "a", "b"});
        assertEquals(3, result.length());
        assertTrue(result.indexOf('c') < result.indexOf('a'));
        assertTrue(result.indexOf('a') < result.indexOf('b'));
    }

    @Test
    public void testDisconnectedLettersAnyOrderValid() {
        // "ab", "cd" => a<c is the only constraint; b,d are disconnected
        String result = test.alienOrder(new String[]{"ab", "cd"});
        assertEquals(4, result.length());
        assertTrue(result.indexOf('a') < result.indexOf('c'));
        // All 4 unique letters present
        Set<Character> chars = new HashSet<>();
        for (char c : result.toCharArray()) chars.add(c);
        assertEquals(4, chars.size());
        assertTrue(chars.contains('a') && chars.contains('b') && chars.contains('c') && chars.contains('d'));
    }

    @Test
    public void testLargeInputSeed42() {
        // Generate 100 random words from letters a-e, verify result properties
        Random rng = new Random(42L);
        String[] words = new String[100];
        for (int i = 0; i < 100; i++) {
            int len = rng.nextInt(4) + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + rng.nextInt(5)));
            }
            words[i] = sb.toString();
        }
        String result = test.alienOrder(words);
        // Result is either empty (cycle/invalid) or contains all unique letters from input
        if (!result.isEmpty()) {
            Set<Character> inputChars = new HashSet<>();
            for (String w : words) for (char c : w.toCharArray()) inputChars.add(c);
            assertEquals(inputChars.size(), result.length());
            for (char c : result.toCharArray()) assertTrue(inputChars.contains(c));
        }
    }

    @Test
    public void testResultContainsAllLettersExactlyOnce() {
        // Property: valid result has each letter from input exactly once
        String[][] inputs = {
                {"wrt", "wrf", "er", "ett", "rftt"},
                {"z", "x"},
                {"ac", "bc"}
        };
        for (String[] words : inputs) {
            String result = test.alienOrder(words);
            Set<Character> inputChars = new HashSet<>();
            for (String w : words) for (char c : w.toCharArray()) inputChars.add(c);
            assertEquals(inputChars.size(), result.length());
            Set<Character> resultChars = new HashSet<>();
            for (char c : result.toCharArray()) resultChars.add(c);
            assertEquals(inputChars, resultChars);
        }
    }

    @Test
    public void testResultIsValidTopoSort() {
        // Property: for every adjacent word pair, the ordering relationship holds in result
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String result = test.alienOrder(words);
        assertTrue(!result.isEmpty());
        for (int i = 0; i < words.length - 1; i++) {
            int idx = 0;
            while (idx < words[i].length() && idx < words[i + 1].length()
                    && words[i].charAt(idx) == words[i + 1].charAt(idx)) {
                idx++;
            }
            if (idx < words[i].length() && idx < words[i + 1].length()) {
                char a = words[i].charAt(idx), b = words[i + 1].charAt(idx);
                assertTrue(result.indexOf(a) < result.indexOf(b),
                        "Expected " + a + " before " + b + " in " + result);
            }
        }
    }

    @Test
    public void testMultipleValidOrderings() {
        // "ab", "ac" => only constraint is b < c; a can be anywhere
        String result = test.alienOrder(new String[]{"ab", "ac"});
        assertEquals(3, result.length());
        assertTrue(result.indexOf('b') < result.indexOf('c'));
        // "a" position is unconstrained relative to b,c — just verify it's present
        assertTrue(result.contains("a"));
    }
}
