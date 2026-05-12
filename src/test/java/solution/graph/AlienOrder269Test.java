package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlienOrder269Test {

    private final AlienOrder_269 test = new AlienOrder_269();

    @Test
    public void testHappyCases() {
        String result = test.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"});
        assertTrue(result.contains("w") && result.contains("e") && result.contains("r"));
        assertEquals("zx", test.alienOrder(new String[]{"z", "x"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.alienOrder(new String[]{"z", "x", "z"}));
        assertEquals("z", test.alienOrder(new String[]{"z"}));
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testLargeCase() {
        String result = test.alienOrder(new String[]{"za", "zb", "ca", "cb"});
        assertTrue(result.indexOf('z') < result.indexOf('c') || result.indexOf('a') < result.indexOf('b'));
    }
}
