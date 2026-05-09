package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AllPossibleFBT894Test {

    private final AllPossibleFBT_894 test = new AllPossibleFBT_894();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.allPossibleFBT(7).size());
        assertEquals(1, test.allPossibleFBT(1).size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.allPossibleFBT(2).size());
        assertEquals(0, test.allPossibleFBT(4).size());
    }

    @Test
    public void testLargeCase() {
        assertEquals(14, test.allPossibleFBT(9).size());
    }
}
