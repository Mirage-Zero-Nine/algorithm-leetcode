package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class Deserialize385Test {

    private final Deserialize_385 test = new Deserialize_385();

    @Test
    public void testHappyCases() {
        NestedInteger result = test.deserialize("324");
        assertEquals(324, result.getInteger());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deserialize(""));
        NestedInteger result = test.deserialize("[123,[456,[789]]]");
        assertEquals(123, result.getList().get(0).getInteger());
    }

    @Test
    public void testLargeCase() {
        NestedInteger result = test.deserialize("[1,2,3]");
        assertEquals(3, result.getList().size());
    }
}
