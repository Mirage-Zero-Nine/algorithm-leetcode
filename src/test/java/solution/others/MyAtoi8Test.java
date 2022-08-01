package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 19:19
 * Created with IntelliJ IDEA
 */

public class MyAtoi8Test {

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
}