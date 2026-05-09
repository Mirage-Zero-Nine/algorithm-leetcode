package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxRepOpt11156Test {

    private final MaxRepOpt1_1156 test = new MaxRepOpt1_1156();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxRepOpt1_1156("aaabaaa"));
        assertEquals(4, test.maxRepOpt1_1156("aaabbaaa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.maxRepOpt1_1156("a"));
        assertEquals(1, test.maxRepOpt1_1156("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.maxRepOpt1_1156("aababaab"));
    }
}
