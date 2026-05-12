package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemovePalindromeSub1332Test {

    private final RemovePalindromeSub_1332 test = new RemovePalindromeSub_1332();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.removePalindromeSub("ababa"));
        assertEquals(2, test.removePalindromeSub("abb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.removePalindromeSub(""));
        assertEquals(1, test.removePalindromeSub("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.removePalindromeSub("baabb"));
        assertEquals(1, test.removePalindromeSub("abba"));
    }
}
