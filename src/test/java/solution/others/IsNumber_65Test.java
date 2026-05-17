package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 18:44
 * Created with IntelliJ IDEA
 */

public class IsNumber_65Test {

    private final IsNumber_65 test = new IsNumber_65();

    @Test
    public void straightMethodPositiveTest() {
        assertTrue(test.isNumber("123"));
        assertTrue(test.isNumber(" 123 "));
        assertTrue(test.isNumber("0"));
        assertTrue(test.isNumber("0123"));
        assertTrue(test.isNumber("00"));
        assertTrue(test.isNumber("-10"));
        assertTrue(test.isNumber("-0"));
        assertTrue(test.isNumber("123.5"));
        assertTrue(test.isNumber("123.000000"));
        assertTrue(test.isNumber("-500.777"));
        assertTrue(test.isNumber("0.0000001"));
        assertTrue(test.isNumber("0.00000"));
        assertTrue(test.isNumber("0."));
        assertTrue(test.isNumber("00.5"));
        assertTrue(test.isNumber("123e1"));
        assertTrue(test.isNumber("1.23e10"));
        assertTrue(test.isNumber("0.5e-10"));
        assertTrue(test.isNumber("0.5e04"));
        assertTrue(test.isNumber(".1"));
        assertTrue(test.isNumber("2e0"));
        assertTrue(test.isNumber("+.8"));
        assertTrue(test.isNumber(" 005047e+6"));
    }

    @Test
    public void straightMethodNegativeTest() {
        assertFalse(test.isNumber("1.0e4.5"));
        assertFalse(test.isNumber("e9"));
        assertFalse(test.isNumber("."));
        assertFalse(test.isNumber("12 3"));
        assertFalse(test.isNumber("1a3"));
        assertFalse(test.isNumber(""));
        assertFalse(test.isNumber("     "));
        assertFalse(test.isNumber(null));
    }

    @Test
    public void regexPositiveTest() {
        assertTrue(test.isNumberRegex("123"));
        assertTrue(test.isNumberRegex(" 123 "));
        assertTrue(test.isNumberRegex("0"));
        assertTrue(test.isNumberRegex("0123"));
        assertTrue(test.isNumberRegex("00"));
        assertTrue(test.isNumberRegex("-10"));
        assertTrue(test.isNumberRegex("-0"));
        assertTrue(test.isNumberRegex("123.5"));
        assertTrue(test.isNumberRegex("123.000000"));
        assertTrue(test.isNumberRegex("-500.777"));
        assertTrue(test.isNumberRegex("0.0000001"));
        assertTrue(test.isNumberRegex("0.00000"));
        assertTrue(test.isNumberRegex("0."));
        assertTrue(test.isNumberRegex("00.5"));
        assertTrue(test.isNumberRegex("123e1"));
        assertTrue(test.isNumberRegex("1.23e10"));
        assertTrue(test.isNumberRegex("0.5e-10"));
        assertTrue(test.isNumberRegex("0.5e04"));
        assertTrue(test.isNumberRegex(".1"));
        assertTrue(test.isNumberRegex("2e0"));
        assertTrue(test.isNumberRegex("+.8"));
        assertTrue(test.isNumberRegex(" 005047e+6"));
    }

    @Test
    public void regexNegativeTest() {
        assertFalse(test.isNumberRegex("1.0e4.5"));
        assertFalse(test.isNumberRegex("e9"));
        assertFalse(test.isNumberRegex("."));
        assertFalse(test.isNumberRegex("12 3"));
        assertFalse(test.isNumberRegex("1a3"));
        assertFalse(test.isNumberRegex(""));
        assertFalse(test.isNumberRegex("     "));
        assertFalse(test.isNumberRegex(null));
    }

    @Test
    public void stateMachinePositiveTest() {
        assertTrue(test.isNumberStateMachine("123"));
        assertTrue(test.isNumberStateMachine(" 123 "));
        assertTrue(test.isNumberStateMachine("0"));
        assertTrue(test.isNumberStateMachine("0123"));
        assertTrue(test.isNumberStateMachine("00"));
        assertTrue(test.isNumberStateMachine("-10"));
        assertTrue(test.isNumberStateMachine("-0"));
        assertTrue(test.isNumberStateMachine("123.5"));
        assertTrue(test.isNumberStateMachine("123.000000"));
        assertTrue(test.isNumberStateMachine("-500.777"));
        assertTrue(test.isNumberStateMachine("0.0000001"));
        assertTrue(test.isNumberStateMachine("0.00000"));
        assertTrue(test.isNumberStateMachine("0."));
        assertTrue(test.isNumberStateMachine("00.5"));
        assertTrue(test.isNumberStateMachine("123e1"));
        assertTrue(test.isNumberStateMachine("1.23e10"));
        assertTrue(test.isNumberStateMachine("0.5e-10"));
        assertTrue(test.isNumberStateMachine("0.5e04"));
        assertTrue(test.isNumberStateMachine(".1"));
        assertTrue(test.isNumberStateMachine("2e0"));
        assertTrue(test.isNumberStateMachine("+.8"));
        assertTrue(test.isNumberStateMachine(" 005047e+6"));
    }

    @Test
    public void stateMachineNegativeTest() {
        assertFalse(test.isNumberStateMachine("1.0e4.5"));
        assertFalse(test.isNumberStateMachine("e9"));
        assertFalse(test.isNumberStateMachine("."));
        assertFalse(test.isNumberStateMachine("12 3"));
        assertFalse(test.isNumberStateMachine("1a3"));
        assertFalse(test.isNumberStateMachine(""));
        assertFalse(test.isNumberStateMachine("     "));
        assertFalse(test.isNumberStateMachine(null));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertTrue(test.isNumber("1E5"));
        assertTrue(test.isNumber("+1"));
        assertTrue(test.isNumber("-1"));
        assertFalse(test.isNumber("--6"));
        assertFalse(test.isNumber("-+3"));
        assertFalse(test.isNumber("6e6.5"));
    }

    @Test
    public void testGiantInputString() {
        String giant = "1" + "0".repeat(1000);
        assertTrue(test.isNumber(giant));
        assertTrue(test.isNumberRegex(giant));
        assertTrue(test.isNumberStateMachine(giant));
    }

    @Test
    public void testSignEdgeCases() {
        assertFalse(test.isNumber("+"));
        assertFalse(test.isNumber("-"));
        assertTrue(test.isNumber("+0"));
        assertTrue(test.isNumber("-0"));
        assertFalse(test.isNumber("e"));
        assertFalse(test.isNumber("1e"));
    }

    @Test
    public void testDecimalEdgeCases() {
        assertTrue(test.isNumber("3."));
        assertTrue(test.isNumber(".5"));
        assertFalse(test.isNumber(".."));
        assertFalse(test.isNumber("1..1"));
        assertFalse(test.isNumber(".e1"));
        assertTrue(test.isNumber("1.e1"));
    }

    @Test
    public void testExponentWithUpperCaseE() {
        assertTrue(test.isNumber("-90E3"));
        assertTrue(test.isNumber("2E10"));
        assertTrue(test.isNumber("53.5e93"));
        assertTrue(test.isNumber("46.e3"));
    }

    @Test
    public void testNoExponentValue() {
        assertFalse(test.isNumber("1e"));
        assertFalse(test.isNumber("1E"));
        assertFalse(test.isNumber("3.e"));
    }

    @Test
    public void testNoMantissa() {
        assertFalse(test.isNumber("e3"));
        assertFalse(test.isNumber("E3"));
        assertFalse(test.isNumber("+e3"));
        assertFalse(test.isNumber("-e3"));
    }

    @Test
    public void testNonIntegerExponent() {
        assertFalse(test.isNumber("99e2.5"));
        assertFalse(test.isNumber("1e1.5"));
        assertFalse(test.isNumber("2e.5"));
    }

    @Test
    public void testAlphabeticInput() {
        assertFalse(test.isNumber("abc"));
        assertFalse(test.isNumber("1 a"));
        assertFalse(test.isNumber("95a54e53"));
    }

    @Test
    public void testDoubleSignsInvalid() {
        assertFalse(test.isNumber("--6"));
        assertFalse(test.isNumber("-+3"));
        assertFalse(test.isNumber("++1"));
        assertFalse(test.isNumber("+-2"));
    }

    @Test
    public void testSignOnlyAndSignDot() {
        assertFalse(test.isNumber("+"));
        assertFalse(test.isNumber("-"));
        assertFalse(test.isNumber("+."));
        assertFalse(test.isNumber("-."));
        assertTrue(test.isNumber("+.8"));
        assertTrue(test.isNumber("-.8"));
    }

    @Test
    public void testWhitespaceHandling() {
        // Implementation trims whitespace, so leading/trailing spaces are valid
        assertTrue(test.isNumber(" 0.1 "));
        assertTrue(test.isNumber(" 1 "));
        assertTrue(test.isNumber("0"));
        assertTrue(test.isNumber("2e10"));
    }
}