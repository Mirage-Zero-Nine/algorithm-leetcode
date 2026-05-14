package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 19:19
 * Created with IntelliJ IDEA
 */

public class MyAtoi_8Test {

    private final MyAtoi_8 test = new MyAtoi_8();

    @Test
    public void test() {
        assertEquals(0, test.myAtoi("   - 321"));
        assertEquals(-12, test.myAtoi("  -0012a42"));
        assertEquals(0, test.myAtoi("   +0 123"));
        assertEquals(10, test.myAtoi("    010"));
        assertEquals(2147483647, test.myAtoi("9223372036854775808"));       // larger than long
    }

    @Test
    public void testInvalidInput() {
        assertEquals(0, test.myAtoi(null));
        assertEquals(0, test.myAtoi(""));
    }

    @Test
    public void testHappyCases() {
        assertEquals(42, test.myAtoi("42"));
        assertEquals(-42, test.myAtoi("   -42"));
        assertEquals(4193, test.myAtoi("4193 with words"));
        assertEquals(7, test.myAtoi("+7"));
    }

    @Test
    public void testEdgeAndNegativeCases() {
        assertEquals(0, test.myAtoi("words and 987"));
        assertEquals(0, test.myAtoi("   "));
        assertEquals(0, test.myAtoi("+-12"));
        assertEquals(-2147483648, test.myAtoi("-91283472332"));
        assertEquals(2147483647, test.myAtoi("2147483648"));
    }

    @Test
    public void testGiantCase() {
        String giant = " ".repeat(50) + "1234567890".repeat(20);
        assertEquals(2147483647, test.myAtoi(giant));
    }

    @Test
    public void testLeadingZeros() {
        assertEquals(0, test.myAtoi("000000"));
        assertEquals(42, test.myAtoi("0000042"));
        assertEquals(-42, test.myAtoi("-0000042"));
    }

    @Test
    public void testExactBoundaries() {
        assertEquals(2147483647, test.myAtoi("2147483647"));
        assertEquals(-2147483648, test.myAtoi("-2147483648"));
    }

    @Test
    public void testOnlySign() {
        assertEquals(0, test.myAtoi("+"));
        assertEquals(0, test.myAtoi("-"));
        assertEquals(0, test.myAtoi("  +  "));
    }

    @Test
    public void testMixedContent() {
        assertEquals(0, test.myAtoi(".1"));
        assertEquals(3, test.myAtoi("3.14159"));
        assertEquals(1, test.myAtoi("+1e10"));
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals(0, test.myAtoi("!@#$%"));
        assertEquals(0, test.myAtoi("\t123"));
        assertEquals(123, test.myAtoi("  123abc456"));
    }
}
