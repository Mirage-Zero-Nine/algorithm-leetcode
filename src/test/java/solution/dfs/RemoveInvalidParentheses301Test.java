package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class RemoveInvalidParentheses301Test {

    private final RemoveInvalidParentheses_301 test = new RemoveInvalidParentheses_301();

    @Test
    public void testHappyCases() {
        List<String> result = test.removeInvalidParentheses("()())()");
        assertTrue(result.contains("(())()") || result.contains("()()()"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(""), test.removeInvalidParentheses(")("));
        assertEquals(List.of(""), test.removeInvalidParentheses(""));
    }

    @Test
    public void testLargeCase() {
        List<String> result = test.removeInvalidParentheses("(a)())()");
        assertTrue(result.size() > 0);
    }
}
