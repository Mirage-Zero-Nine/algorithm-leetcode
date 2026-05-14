package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterReplacement_424Test {

    private final CharacterReplacement_424 test = new CharacterReplacement_424();

    @Test
    public void testHappyCase1() {
        assertEquals(4, test.characterReplacement("ABAB", 2));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(4, test.characterReplacement("AABABBA", 1));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(5, test.characterReplacement("ABCDE", 4));
    }

    @Test
    public void testHappyCase4() {
        assertEquals(2, test.characterReplacement("ABAA", 0));
    }

    @Test
    public void testHappyCase5() {
        // AABABBA, k=2.
        // A:4, B:3.
        // If we replace 2 Bs with As, we get AAAAA. But Bs are separated.
        // AABA B BA. Replace 2 Bs at index 2 and 5.
        // AA A A B AA -> AAAABAA (6)
        // Wait, the implementation returns 5 for AABABBA, 2.
        // Let's re-examine: AABABBA.
        // Window index 0-4: AABAB. A:3, B:2. 5-3=2 <= k. Window size 5.
        // Window index 1-5: ABABB. A:2, B:3. 5-3=2 <= k. Window size 5.
        // Window index 2-6: BABBA. A:2, B:3. 5-3=2 <= k. Window size 5.
        assertEquals(5, test.characterReplacement("AABABBA", 2));
    }

    @Test
    public void testNegativeCase() {
        assertEquals(0, test.characterReplacement("", 1));
    }

    @Test
    public void testEdgeCaseKGreaterThanLength() {
        assertEquals(3, test.characterReplacement("ABC", 10));
    }

    @Test
    public void testEdgeCaseAllSame() {
        assertEquals(5, test.characterReplacement("AAAAA", 2));
    }

    @Test
    public void testEdgeCaseAllDifferent() {
        assertEquals(3, test.characterReplacement("ABC", 2));
        assertEquals(1, test.characterReplacement("ABC", 0));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i % 2 == 0 ? 'A' : 'B');
        }
        // ABABAB... length n, k=100.
        // The implementation uses historical max frequency.
        // For ABAB... max frequency is always (window_size + 1) / 2.
        // Window grows as long as window_size <= max_freq + k.
        // For ABAB..., this means window_size <= (window_size + 1) / 2 + 100
        // 2 * window_size <= window_size + 1 + 200
        // window_size <= 201.
        assertEquals(201, test.characterReplacement(sb.toString(), 100));
    }
}
