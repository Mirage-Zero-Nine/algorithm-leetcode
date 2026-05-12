package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsValidSerialization331Test {

    private final IsValidSerialization_331 test = new IsValidSerialization_331();

    @Test
    public void testHappyCases() {
        assertTrue(test.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        assertFalse(test.isValidSerialization("1,#"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isValidSerialization("#"));
        assertFalse(test.isValidSerialization("9,#,#,1"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isValidSerialization("1,2,3,#,#,#,#"));
    }
}
