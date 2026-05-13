package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TitleToNumber_171Test {

    private final TitleToNumber_171 test = new TitleToNumber_171();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.titleToNumber("A"));
        assertEquals(28, test.titleToNumber("AB"));
        assertEquals(701, test.titleToNumber("ZY"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(26, test.titleToNumber("Z"));
        assertEquals(27, test.titleToNumber("AA"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(18278, test.titleToNumber("ZZZ"));
    }

    @Test
    public void testSingleLetters() {
        assertEquals(2, test.titleToNumber("B"));
        assertEquals(3, test.titleToNumber("C"));
        assertEquals(25, test.titleToNumber("Y"));
    }

    @Test
    public void testTwoLetterColumns() {
        assertEquals(52, test.titleToNumber("AZ"));
        assertEquals(53, test.titleToNumber("BA"));
        assertEquals(702, test.titleToNumber("ZZ"));
    }

    @Test
    public void testThreeLetterColumns() {
        assertEquals(703, test.titleToNumber("AAA"));
        assertEquals(704, test.titleToNumber("AAB"));
    }

    @Test
    public void testBoundaryOneToTwo() {
        assertEquals(26, test.titleToNumber("Z"));
        assertEquals(27, test.titleToNumber("AA"));
    }

    @Test
    public void testBoundaryTwoToThree() {
        assertEquals(702, test.titleToNumber("ZZ"));
        assertEquals(703, test.titleToNumber("AAA"));
    }

    @Test
    public void testNegativeCaseEmptyString() {
        assertEquals(0, test.titleToNumber(""));
    }

    @Test
    public void testFourLetterColumn() {
        assertEquals(475254, test.titleToNumber("ZZZZ"));
    }

    @Test
    public void testGiantConsistencyWithConvertToTitle() {
        ConvertToTitle_168 converter = new ConvertToTitle_168();
        int[] values = {1, 26, 27, 52, 100, 702, 703, 18278, 475254};
        for (int v : values) {
            assertEquals(v, test.titleToNumber(converter.convertToTitle(v)));
        }
    }
}
