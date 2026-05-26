package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    /**
     * Iterable roundtrip 1..1000: every n converted to a column title
     * via {@link ConvertToTitle_168} must decode back to n.
     */
    @ParameterizedTest(name = "roundtrip {0}")
    @MethodSource("oneToOneThousand")
    public void testRoundtripOneToOneThousand(int n) {
        ConvertToTitle_168 converter = new ConvertToTitle_168();
        String title = converter.convertToTitle(n);
        assertEquals(n, test.titleToNumber(title),
                "roundtrip failed: " + n + " -> " + title + " -> " + test.titleToNumber(title));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToOneThousand() {
        return IntStream.rangeClosed(1, 1000).mapToObj(i -> arguments(i));
    }
}
