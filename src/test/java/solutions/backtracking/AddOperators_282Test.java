package solutions.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddOperators_282Test {
    private final AddOperators_282 solution = new AddOperators_282();

    @Test
    void testBasic() {
        assertExpressions("123", 6, Set.of("1+2+3", "1*2*3"));
    }

    @Test
    void testMultiplication() {
        assertExpressions("232", 8, Set.of("2*3+2", "2+3*2"));
    }

    @Test
    void testNoSolution() {
        assertExpressions("3456237490", 9191, Set.of());
    }

    @Test
    void testSingleDigit() {
        assertExpressions("3", 3, Set.of("3"));
    }

    @Test
    void testZero() {
        assertExpressions("00", 0, Set.of("0+0", "0-0", "0*0"));
    }

    @Test
    void testEmptyString() {
        List<String> result = solution.addOperators("", 0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleDigitNoMatch() {
        assertExpressions("5", 3, Set.of());
    }

    @Test
    void testLeadingZero() {
        assertExpressions("105", 5, Set.of("1*0+5", "10-5"));
    }

    @Test
    void testTwoDigits() {
        assertExpressions("12", 3, Set.of("1+2"));
    }

    @Test
    void testLargeTarget() {
        assertExpressions("123456", 123456, Set.of("123456"));
    }

    @Test
    void testGiantInput() {
        assertExpressions("3456237490", 9191, Set.of());
    }

    @Test
    void testMaximumPositiveTarget() {
        assertExpressions("2147483647", Integer.MAX_VALUE, Set.of("2147483647"));
    }

    @Test
    void testAllSameDigits() {
        assertExpressions("111", 3, Set.of("1+1+1"));
    }

    @Test
    void testNegativeTarget() {
        assertExpressions("12", -1, Set.of("1-2"));
    }

    @Test
    void testMultiplicationPrecedence() {
        assertExpressions("123", 7, Set.of("1+2*3"));
    }

    @Test
    void testConcatenationAndMultiplication() {
        assertExpressions("1234", 24, Set.of("1*2*3*4", "12+3*4"));
    }

    @Test
    void testAllOperatorsCanBeUsed() {
        assertExpressions("1234", 10, Set.of("1+2+3+4", "1*2*3+4"));
    }

    @Test
    void testLeadingZeroCannotFormMultiDigitOperand() {
        assertExpressions("105", 10, Set.of());
    }

    @Test
    void testZeroIntermediateValues() {
        assertExpressions("100", 0, Set.of("1*0+0", "1*0-0", "1*0*0", "10*0"));
    }

    @Test
    void testZeroIntermediateValuesWithTargetOne() {
        assertExpressions("100", 1,
                Set.of("1+0+0", "1+0-0", "1+0*0", "1-0+0", "1-0-0", "1-0*0"));
    }

    @Test
    void testLeadingZeroInputStillAllowsZeroOperand() {
        assertExpressions("01", 1, Set.of("0+1"));
    }

    @Test
    void testTwoZeroDigitsCannotReachOne() {
        assertExpressions("00", 1, Set.of());
    }

    @Test
    void testSubtractionToZero() {
        assertExpressions("11", 0, Set.of("1-1"));
    }

    @Test
    void testNoMatchDespiteValidDigits() {
        assertExpressions("123", 100, Set.of());
    }

    @Test
    void testNoMatchWithRepeatedDigits() {
        assertExpressions("999", 81, Set.of());
    }

    @Test
    void testNegativeResultWithThreeDigits() {
        assertExpressions("123", -4, Set.of("1-2-3"));
    }

    @Test
    void testNegativeMultiplicationTerm() {
        assertExpressions("123", -5, Set.of("1-2*3"));
    }

    @Test
    void testMultiplicationAfterSubtraction() {
        assertExpressions("232", -4, Set.of("2-3*2"));
    }

    @Test
    void testIntegerMinimumTargetHasNoExpression() {
        assertExpressions("2147483648", Integer.MIN_VALUE, Set.of());
    }

    @Test
    void testExhaustiveSmallTargetsAgainstIndependentParser() {
        for (int target = -10; target <= 30; target++) {
            Set<String> expected = bruteForceExpressions("1234", target);
            assertExpressions("1234", target, expected);
        }
    }

    private void assertExpressions(String num, int target, Set<String> expected) {
        List<String> actual = solution.addOperators(num, target);
        assertEquals(expected, new HashSet<>(actual));
        assertEquals(expected.size(), actual.size(), "expressions should not be duplicated");
    }

    /** Generates every legal expression, then evaluates it with a separate precedence parser. */
    private Set<String> bruteForceExpressions(String num, int target) {
        Set<String> result = new HashSet<>();
        enumerate(num, 0, "", target, result);
        return result;
    }

    private void enumerate(String num, int start, String expression, int target, Set<String> result) {
        for (int end = start; end < num.length(); end++) {
            if (end > start && num.charAt(start) == '0') {
                break;
            }
            String operand = num.substring(start, end + 1);
            if (expression.isEmpty()) {
                if (end + 1 == num.length()) {
                    if (evaluate(operand) == target) {
                        result.add(operand);
                    }
                } else {
                    enumerate(num, end + 1, operand, target, result);
                }
            } else {
                for (char operator : new char[]{'+', '-', '*'}) {
                    String candidate = expression + operator + operand;
                    if (end + 1 == num.length()) {
                        if (evaluate(candidate) == target) {
                            result.add(candidate);
                        }
                    } else {
                        enumerate(num, end + 1, candidate, target, result);
                    }
                }
            }
        }
    }

    private long evaluate(String expression) {
        List<Long> values = new java.util.ArrayList<>();
        List<Character> operators = new java.util.ArrayList<>();
        int start = 0;
        for (int i = 0; i <= expression.length(); i++) {
            if (i == expression.length() || !Character.isDigit(expression.charAt(i))) {
                values.add(Long.parseLong(expression.substring(start, i)));
                if (i < expression.length()) {
                    operators.add(expression.charAt(i));
                }
                start = i + 1;
            }
        }
        List<Long> additive = new java.util.ArrayList<>();
        long term = values.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            long next = values.get(i + 1);
            if (operator == '*') {
                term *= next;
            } else {
                additive.add(term);
                term = operator == '+' ? next : -next;
            }
        }
        additive.add(term);
        return additive.stream().mapToLong(Long::longValue).sum();
    }
}
