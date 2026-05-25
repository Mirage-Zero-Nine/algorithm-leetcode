package solution.stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsValid_20Test {
    private final IsValid_20 v = new IsValid_20();

    @Test public void testEmpty() { assertTrue(v.isValid("")); }
    @Test public void testAllOpen() { assertFalse(v.isValid("(((")); }
    @Test public void testAllClose() { assertFalse(v.isValid(")))") ); }
    @Test public void testValidSingle() { assertTrue(v.isValid("()")); }
    @Test public void testValidMixed() { assertTrue(v.isValid("()[]{}")); }
    @Test public void testValidNested() { assertTrue(v.isValid("{[()]}")); }
    @Test public void testInvalidOrder() { assertFalse(v.isValid("(]")); }
    @Test public void testInvalidOpen() { assertFalse(v.isValid("([)]")); }
    @Test public void testValidLong() { assertTrue(v.isValid("{[({})]}") ); }
    @Test public void testInvalidCloseExtra() { assertFalse(v.isValid("()}}")); }

    // --- New tricky/happy/negative/large cases ---

    @Test
    public void testSingleOpen() {
        assertFalse(v.isValid("("));
    }

    @Test
    public void testSingleClose() {
        assertFalse(v.isValid(")"));
    }

    @Test
    public void testDeeplyNestedValid() {
        // 500 opens + 500 closes = 1000 chars
        String opens = "(".repeat(500);
        String closes = ")".repeat(500);
        assertTrue(v.isValid(opens + closes));
    }

    @Test
    public void testDeeplyNestedMixedValid() {
        // Alternating types deeply nested: ([{([{...}])}])
        StringBuilder sb = new StringBuilder();
        char[] open = {'(', '[', '{'};
        char[] close = {')', ']', '}'};
        for (int i = 0; i < 300; i++) sb.append(open[i % 3]);
        for (int i = 299; i >= 0; i--) sb.append(close[i % 3]);
        assertTrue(v.isValid(sb.toString()));
    }

    @Test
    public void testSameTypeRepeatedNesting() {
        // (((((((((())))))))))
        assertTrue(v.isValid("(((((((((())))))))))"));
    }

    @Test
    public void testMixedNestedAndSequential() {
        // '({[]})()' -> true
        assertTrue(v.isValid("({[]})()"));
    }

    @Test
    public void testMismatchedOverlapping() {
        // '({)}' -> false (overlapping but not properly nested)
        assertFalse(v.isValid("({)}"));
    }

    @Test
    public void testSingleCloseOfEachType() {
        assertFalse(v.isValid("]"));
        assertFalse(v.isValid("}"));
    }

    @ParameterizedTest
    @CsvSource({
            "'()',       true",
            "'[]',       true",
            "'{}',       true",
            "'({[]})',   true",
            "'()[]{}',   true",
            "'({[]})(){{[[(())]]}}{}', true",
            "'(]',       false",
            "'[)',       false",
            "'({)}',     false",
            "'([)]',     false",
            "'(((',      false",
            "')))',      false",
            "'((',       false",
            "'))',       false",
            "']{',       false",
    })
    public void testParameterizedValidation(String input, boolean expected) {
        if (expected) {
            assertTrue(v.isValid(input), "Expected valid: " + input);
        } else {
            assertFalse(v.isValid(input), "Expected invalid: " + input);
        }
    }
}
