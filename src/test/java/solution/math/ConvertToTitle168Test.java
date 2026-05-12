package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConvertToTitle168Test {

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
}
