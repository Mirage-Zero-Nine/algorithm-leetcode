package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MinimumTotal120Test {

    private final MinimumTotal_120 test = new MinimumTotal_120();

    @Test
    public void testHappyCases() {
        assertEquals(11, test.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minimumTotal(null));
        assertEquals(-10, test.minimumTotal(List.of(List.of(-10))));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.minimumTotal(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6))));
    }
}
