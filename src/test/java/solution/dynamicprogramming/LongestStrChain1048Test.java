package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestStrChain1048Test {

    private final LongestStrChain_1048 test = new LongestStrChain_1048();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
        assertEquals(5, test.longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.longestStrChain(new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.longestStrChain(new String[]{"bdca", "bda", "ba", "a", "b"}));
    }
}
