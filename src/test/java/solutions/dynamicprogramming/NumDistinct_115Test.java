package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NumDistinct_115Test {

    private final NumDistinct_115 test = new NumDistinct_115();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.numDistinct("rabbbit", "rabbit"));
        assertEquals(5, test.numDistinct("babgbag", "bag"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDistinct("abc", "abcd"));
        assertEquals(1, test.numDistinct("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.numDistinct("aabdbabd", "abd"));
    }

    @Test
    public void testEmptyTarget() {
        assertEquals(1, test.numDistinct("abc", ""));
    }

    @Test
    public void testBothEmpty() {
        assertEquals(1, test.numDistinct("", ""));
    }

    @Test
    public void testEmptySource() {
        assertEquals(0, test.numDistinct("", "a"));
    }

    @Test
    public void testSingleCharMatch() {
        assertEquals(3, test.numDistinct("aaa", "a"));
    }

    @Test
    public void testSingleCharNoMatch() {
        assertEquals(0, test.numDistinct("bbb", "a"));
    }

    @Test
    public void testIdenticalStrings() {
        assertEquals(1, test.numDistinct("hello", "hello"));
    }

    @Test
    public void testGiantCase() {
        // s = "aaa...a" (20 chars), t = "aa" -> C(20,2) = 190
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) sb.append('a');
        assertEquals(190, test.numDistinct(sb.toString(), "aa"));
    }

    @ParameterizedTest(name = "distinct subsequences {0} -> {1}")
    @CsvSource({"aaa,aa,3", "abcd,bd,1", "abab,ab,3", "banana,ban,3", "rabbbit,rab,3", "babgbag,bg,5", "abc,ac,1", "abcd,ef,0", "aaaa,a,4", "aab,aab,1"})
    public void testAdditionalSubsequenceShapes(String source, String target, int expected) {
        assertEquals(expected, test.numDistinct(source, target));
    }
}
