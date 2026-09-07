package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterReplacement424Test {

    private final CharacterReplacement_424 test = new CharacterReplacement_424();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.characterReplacement("ABAB", 2));
        assertEquals(4, test.characterReplacement("AABABBA", 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.characterReplacement("", 0));
        assertEquals(1, test.characterReplacement("A", 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.characterReplacement("AABABBAABB", 3));
    }

    @Test
    public void testAllSameChars() {
        assertEquals(5, test.characterReplacement("AAAAA", 0));
    }

    @Test
    public void testKGreaterThanLength() {
        assertEquals(3, test.characterReplacement("ABC", 5));
    }

    @Test
    public void testTwoCharsAlternating() {
        assertEquals(3, test.characterReplacement("ABAB", 1));
    }

    @Test
    public void testSingleCharWithK() {
        assertEquals(1, test.characterReplacement("A", 2));
    }

    @Test
    public void testAllDifferent() {
        assertEquals(4, test.characterReplacement("ABCD", 3));
    }

    @Test
    public void testKZeroAllDifferent() {
        assertEquals(1, test.characterReplacement("ABCD", 0));
    }

    @Test
    public void testGiantCase() {
        String s = "A".repeat(5000) + "B".repeat(5000);
        assertEquals(10000, test.characterReplacement(s, 5000));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(24, 'A', 'E').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            int k = random.nextInt(10), expected = 0;
            for (int left = 0; left < s.length(); left++)
                for (int right = left + 1; right <= s.length(); right++)
                    for (char letter = 'A'; letter <= 'D'; letter++) {
                        int changes = 0;
                        for (int i = left; i < right; i++) if (s.charAt(i) != letter) changes++;
                        if (changes <= k) expected = Math.max(expected, right - left);
                    }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new CharacterReplacement_424().characterReplacement(s, k), s + ", k=" + k);
        }
    }
}
