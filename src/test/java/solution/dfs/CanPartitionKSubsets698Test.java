package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanPartitionKSubsets698Test {

    private final CanPartitionKSubsets_698 test = new CanPartitionKSubsets_698();

    @Test
    public void testHappyCases() {
        assertTrue(test.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canPartitionKSubsets(null, 2));
        assertFalse(test.canPartitionKSubsets(new int[]{1, 2}, 3));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }
}
