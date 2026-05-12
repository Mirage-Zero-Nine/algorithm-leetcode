package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxNumberOfBalloons1189Test {

    private final MaxNumberOfBalloons_1189 test = new MaxNumberOfBalloons_1189();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.maxNumberOfBalloons("nlaebolko"));
        assertEquals(2, test.maxNumberOfBalloons("loonbalxballpoon"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxNumberOfBalloons("leetcode"));
        assertEquals(0, test.maxNumberOfBalloons("bal"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxNumberOfBalloons("balloonballoonballoon"));
    }
}
