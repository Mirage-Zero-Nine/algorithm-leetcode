package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CountSteppingNumbers1215Test {

    private final CountSteppingNumbers_1215 test = new CountSteppingNumbers_1215();

    @Test
    public void testHappyCases() {
        List<Integer> result = test.countSteppingNumbers(0, 21);
        assertEquals(13, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        List<Integer> result = test.countSteppingNumbers(0, 0);
        assertTrue(result.contains(0));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.countSteppingNumbers(100, 200);
        assertTrue(result.contains(101));
        assertTrue(result.contains(121));
    }
}
