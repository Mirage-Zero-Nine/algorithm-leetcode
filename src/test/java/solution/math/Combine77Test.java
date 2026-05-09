package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Combine77Test {

    private final Combine_77 test = new Combine_77();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.combine(4, 2);
        assertEquals(6, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.combine(0, 1).size());
        assertEquals(1, test.combine(1, 1).size());
        assertEquals(0, test.combine(3, 4).size());
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.combine(5, 3).size());
        assertEquals(252, test.combine(10, 5).size());
    }
}
