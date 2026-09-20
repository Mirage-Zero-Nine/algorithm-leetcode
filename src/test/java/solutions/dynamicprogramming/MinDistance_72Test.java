package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    public void testMemoizedDfsImplementation() {
        assertEquals(3, test.dfsImpl("horse", "ros"));
        assertEquals(5, test.dfsImpl("intention", "execution"));
        assertEquals(0, test.dfsImpl("", ""));
        assertEquals(3, test.dfsImpl("", "abc"));
        assertEquals(1, test.dfsImpl("a", "b"));
    }

    @ParameterizedTest
    @CsvSource({"kitten,sitting,3","flaw,lawn,2","gumbo,gambol,2","book,back,2","abc,yabd,2","a,ab,1","ab,a,1","algorithm,altruistic,6","distance,instance,2","abc,abd,1"})
    public void testAdditionalEditDistanceShapes(String first, String second, int expected) {
        assertEquals(expected, test.minDistance(first, second));
        assertEquals(expected, test.dfsImpl(first, second));
    }
}
