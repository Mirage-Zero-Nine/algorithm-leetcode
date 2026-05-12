package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumOfPairs2023Test {

    private final NumOfPairs_2023 test = new NumOfPairs_2023();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numOfPairs(new String[]{"777", "7", "77", "77"}, "7777"));
        assertEquals(2, test.numOfPairs(new String[]{"123", "4", "12", "34"}, "1234"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numOfPairs(new String[]{"1", "2"}, "12"));
        assertEquals(0, test.numOfPairs(new String[]{}, "abc"));
        assertEquals(0, test.numOfPairs(new String[]{"abc", "def"}, "xyz"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.numOfPairs(new String[]{"ab", "ab", "ab", "ab"}, "abab"));
    }
}
