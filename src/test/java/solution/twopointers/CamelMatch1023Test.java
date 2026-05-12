package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CamelMatch1023Test {

    private final CamelMatch_1023 test = new CamelMatch_1023();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(true, false, true, true),
            test.camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer"}, "FB"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.camelMatch(new String[]{}, "FB"));
        assertEquals(List.of(true), test.camelMatch(new String[]{"FooBar"}, "FooBar"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(true, false),
            test.camelMatch(new String[]{"FooBar", "FooBarTest"}, "FooBar"));
    }
}
