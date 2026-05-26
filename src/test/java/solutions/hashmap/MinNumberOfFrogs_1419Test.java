package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinNumberOfFrogs_1419Test {

    private final MinNumberOfFrogs_1419 test = new MinNumberOfFrogs_1419();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minNumberOfFrogs("croak"));
        assertEquals(-1, test.minNumberOfFrogs("crcoakak"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.minNumberOfFrogs("croakcroak"));
        assertEquals(-1, test.minNumberOfFrogs("cr"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.minNumberOfFrogs("croakcroakcroak"));
    }

    @Test
    public void testTwoSimultaneous() {
        assertEquals(2, test.minNumberOfFrogs("ccroakroak"));
    }

    @Test
    public void testInvalidOrder() {
        assertEquals(-1, test.minNumberOfFrogs("roakc"));
    }

    @Test
    public void testInvalidIncomplete() {
        assertEquals(-1, test.minNumberOfFrogs("croa"));
    }

    @Test
    public void testInvalidExtraChar() {
        // 'c' without completing makes it invalid
        assertEquals(-1, test.minNumberOfFrogs("croakc"));
    }

    @Test
    public void testThreeSimultaneous() {
        assertEquals(3, test.minNumberOfFrogs("cccroakroakroak"));
    }

    @Test
    public void testNegativeEmptyString() {
        // empty string: newFrog==0, c==r==o==a==k==0, returns 0
        assertEquals(0, test.minNumberOfFrogs(""));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("croak");
        }
        assertEquals(1, test.minNumberOfFrogs(sb.toString()));
    }
}
