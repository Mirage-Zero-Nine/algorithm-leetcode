package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ConvertToTitle_168Test {

    private final ConvertToTitle_168 test = new ConvertToTitle_168();

    @Test
    public void testHappyCases() {
        assertEquals("A", test.convertToTitle(1));
        assertEquals("Z", test.convertToTitle(26));
        assertEquals("AA", test.convertToTitle(27));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("", test.convertToTitle(0));
        assertEquals("AZ", test.convertToTitle(52));
    }

    @Test
    public void testLargeCase() {
        assertEquals("ZZZ", test.convertToTitle(18278));
    }

    @Test
    public void testSingleLetters() {
        assertEquals("B", test.convertToTitle(2));
        assertEquals("C", test.convertToTitle(3));
        assertEquals("Y", test.convertToTitle(25));
    }

    @Test
    public void testTwoLetters() {
        assertEquals("AB", test.convertToTitle(28));
        assertEquals("BA", test.convertToTitle(53));
        assertEquals("ZY", test.convertToTitle(701));
    }

    @Test
    public void testThreeLetters() {
        assertEquals("AAA", test.convertToTitle(703));
        assertEquals("AAB", test.convertToTitle(704));
    }

    @Test
    public void testColumnAZ() {
        assertEquals("AZ", test.convertToTitle(52));
    }

    @Test
    public void testNegativeCase() {
        // 0 is the only non-positive handled, returns empty
        assertEquals("", test.convertToTitle(0));
    }

    @Test
    public void testBoundaryBetweenOneAndTwoLetters() {
        assertEquals("Z", test.convertToTitle(26));
        assertEquals("AA", test.convertToTitle(27));
    }

    @Test
    public void testBoundaryBetweenTwoAndThreeLetters() {
        assertEquals("ZZ", test.convertToTitle(702));
        assertEquals("AAA", test.convertToTitle(703));
    }

    @Test
    public void testGiantCase() {
        // 26^4 + 26^3 + 26^2 + 26 = 475254 -> ZZZZ
        assertEquals("ZZZZ", test.convertToTitle(475254));
    }

    /**
     * Iterable roundtrip 1..1000: every column title produced for n must
     * decode back to n via {@link TitleToNumber_171}. Catches encode/
     * decode asymmetry.
     */
    @ParameterizedTest(name = "roundtrip {0}")
    @MethodSource("oneToOneThousand")
    public void testRoundtripOneToOneThousand(int n) {
        TitleToNumber_171 decoder = new TitleToNumber_171();
        String title = test.convertToTitle(n);
        assertEquals(n, decoder.titleToNumber(title),
                "roundtrip failed: " + n + " -> " + title);
    }

    /**
     * Cross-check 1..1000 against an independent recursive reference.
     */
    @ParameterizedTest(name = "ref {0}")
    @MethodSource("oneToOneThousand")
    public void testAgainstRecursiveReference(int n) {
        assertEquals(referenceConvert(n), test.convertToTitle(n));
    }

    private static String referenceConvert(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) ('A' + (n % 26)));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToOneThousand() {
        return IntStream.rangeClosed(1, 1000).mapToObj(i -> arguments(i));
    }
}
