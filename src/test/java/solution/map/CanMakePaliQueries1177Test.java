package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CanMakePaliQueries1177Test {

    private final CanMakePaliQueries_1177 test = new CanMakePaliQueries_1177();

    @Test
    public void testHappyCases() {
        List<Boolean> result = test.canMakePaliQueries("abcda", new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}});
        assertEquals(List.of(true, false, false, true, true), result);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        List<Boolean> result = test.canMakePaliQueries("a", new int[][]{{0, 0, 0}});
        assertEquals(List.of(true), result);
        List<Boolean> result2 = test.canMakePaliQueries("ab", new int[][]{{0, 1, 0}});
        assertEquals(List.of(false), result2);
    }

    @Test
    public void testLargeCase() {
        List<Boolean> result = test.canMakePaliQueries("aaabbbccc", new int[][]{{0, 8, 4}, {0, 8, 0}});
        assertEquals(List.of(true, false), result);
    }
}
