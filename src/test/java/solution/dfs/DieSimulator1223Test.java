package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DieSimulator1223Test {

    private final DieSimulator_1223 test = new DieSimulator_1223();

    @Test
    public void testHappyCases() {
        assertEquals(34, test.dieSimulator(2, new int[]{1, 1, 2, 2, 2, 3}));
        assertEquals(30, test.dieSimulator(2, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(6, test.dieSimulator(1, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(181, test.dieSimulator(3, new int[]{1, 1, 1, 2, 2, 3}));
    }
}
