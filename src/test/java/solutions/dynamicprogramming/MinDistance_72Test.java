package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinDistance_72Test {

    private final MinDistance_72 test = new MinDistance_72();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.minDistance("horse", "ros"));
        assertEquals(5, test.minDistance("intention", "execution"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minDistance("abc", "abc"));
        assertEquals(3, test.minDistance("abc", ""));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.minDistance("zoologicoarchaeologist", "zoogeologist"));
    }

    @Test
    public void testBothEmpty() {
        assertEquals(0, test.minDistance("", ""));
    }

    @Test
    public void testEmptyToWord() {
        assertEquals(5, test.minDistance("", "hello"));
    }

    @Test
    public void testNullInputs() {
        assertEquals(0, test.minDistance(null, "abc"));
        assertEquals(0, test.minDistance("abc", null));
    }

    @Test
    public void testSingleCharDifferent() {
        assertEquals(1, test.minDistance("a", "b"));
    }

    @Test
    public void testSingleCharSame() {
        assertEquals(0, test.minDistance("a", "a"));
    }

    @Test
    public void testInsertionOnly() {
        assertEquals(3, test.minDistance("abc", "abcdef"));
    }

    @Test
    public void testGiantCase() {
        assertEquals(6, test.minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
    }
}
