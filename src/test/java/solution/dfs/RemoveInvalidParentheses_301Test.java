package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class RemoveInvalidParentheses_301Test {

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

    @Test
    public void testHappyCase2() {
        List<String> result = test.removeInvalidParentheses("()())()");
        assertTrue(result.contains("(())()"));
        assertTrue(result.contains("()()()"));
        assertEquals(2, result.size());
    }

    @Test
    public void testHappyCase3() {
        List<String> result = test.removeInvalidParentheses("(a)())()");
        assertTrue(result.contains("(a())()"));
        assertTrue(result.contains("(a)()()"));
    }

    @Test
    public void testAlreadyValid() {
        List<String> result = test.removeInvalidParentheses("()");
        assertEquals(List.of("()"), result);
    }

    @Test
    public void testNoParentheses() {
        List<String> result = test.removeInvalidParentheses("abc");
        assertEquals(List.of("abc"), result);
    }

    @Test
    public void testAllClose() {
        List<String> result = test.removeInvalidParentheses(")))");
        assertEquals(List.of(""), result);
    }

    @Test
    public void testAllOpen() {
        List<String> result = test.removeInvalidParentheses("(((");
        assertEquals(List.of(""), result);
    }

    @Test
    public void testSingleChar() {
        assertEquals(List.of(""), test.removeInvalidParentheses("("));
        assertEquals(List.of(""), test.removeInvalidParentheses(")"));
    }

    @Test
    public void testNestedValid() {
        List<String> result = test.removeInvalidParentheses("(())");
        assertEquals(List.of("(())"), result);
    }

    @Test
    public void testGiantCase() {
        // Build a string with many valid pairs and a few invalid ones
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("()");
        }
        sb.append("))");
        List<String> result = test.removeInvalidParentheses(sb.toString());
        assertTrue(result.size() > 0);
        for (String s : result) {
            // verify each result is valid
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') count++;
                else if (c == ')') count--;
                assertTrue(count >= 0);
            }
            assertEquals(0, count);
        }
    }
}
