package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestCommonSupersequence1092Test {

    private final ShortestCommonSupersequence_1092 test = new ShortestCommonSupersequence_1092();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.shortestCommonSupersequence("abac", "cab").length());
        assertEquals(3, test.shortestCommonSupersequence("abc", "abc").length());
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.shortestCommonSupersequence("a", "a").length());
        assertEquals(2, test.shortestCommonSupersequence("a", "b").length());
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.shortestCommonSupersequence("abcde", "ace").length());
    }
}
