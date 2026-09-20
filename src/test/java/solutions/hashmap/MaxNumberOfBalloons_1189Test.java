package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxNumberOfBalloons_1189Test {

    private final MaxNumberOfBalloons_1189 test = new MaxNumberOfBalloons_1189();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.maxNumberOfBalloons("nlaebolko"));
        assertEquals(2, test.maxNumberOfBalloons("loonbalxballpoon"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxNumberOfBalloons("leetcode"));
        assertEquals(0, test.maxNumberOfBalloons("bal"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxNumberOfBalloons("balloonballoonballoon"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.maxNumberOfBalloons(""));
    }

    @Test
    public void testExactlyBalloon() {
        assertEquals(1, test.maxNumberOfBalloons("balloon"));
    }

    @Test
    public void testMissingOneChar() {
        assertEquals(0, test.maxNumberOfBalloons("alloon")); // missing 'b'
        assertEquals(0, test.maxNumberOfBalloons("blloon")); // missing 'a'
        assertEquals(0, test.maxNumberOfBalloons("baoon"));  // missing 'l' (need 2)
    }

    @Test
    public void testExtraCharsDoNotHelp() {
        assertEquals(1, test.maxNumberOfBalloons("balloonxyz"));
    }

    @Test
    public void testDoubleLAndO() {
        // 'l' and 'o' need to appear twice per balloon
        assertEquals(1, test.maxNumberOfBalloons("bbaalloonn"));
    }

    @Test
    public void testLimitedByL() {
        // enough of everything except 'l' (only 2 l's -> 1 balloon)
        assertEquals(1, test.maxNumberOfBalloons("bbaallooooonnnn"));
    }

    @Test
    public void testLimitedByO() {
        // enough of everything except 'o' (only 2 o's -> 1 balloon)
        assertEquals(1, test.maxNumberOfBalloons("bbaalllloonn"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append("balloon");
        assertEquals(1000, test.maxNumberOfBalloons(sb.toString()));
    }
    @Test void extra01() { assertEquals(1, test.maxNumberOfBalloons("balloon")); }
    @Test void extra02() { assertEquals(0, test.maxNumberOfBalloons("balon")); }
    @Test void extra03() { assertEquals(2, test.maxNumberOfBalloons("balloonballoon")); }
    @Test void extra04() { assertEquals(0, test.maxNumberOfBalloons("bbbbbaaaaa")); }
    @Test void extra05() { assertEquals(1, test.maxNumberOfBalloons("xxballoonyy")); }
    @Test void extra06() { assertEquals(3, test.maxNumberOfBalloons("balloonballoonballoonx")); }
    @Test void extra07() { assertEquals(1, test.maxNumberOfBalloons("bbaallooon")); }
    @Test void extra08() { assertEquals(1, test.maxNumberOfBalloons("nlaebolko")); }
    @Test void extra09() { assertEquals(0, test.maxNumberOfBalloons("")); }
    @Test void extra10() { assertEquals(4, test.maxNumberOfBalloons("balloon".repeat(4))); }
}
