package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountHomogenous1759Test {

    private final CountHomogenous_1759 test = new CountHomogenous_1759();

    @Test
    public void testHappyCases() {
        assertEquals(13, test.countHomogenous("abbcccaa"));
        assertEquals(2, test.countHomogenous("xy"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.countHomogenous("a"));
        assertEquals(6, test.countHomogenous("aaa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.countHomogenous("aaaaaaaaaa"));
    }
}
