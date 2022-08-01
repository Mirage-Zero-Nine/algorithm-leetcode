package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 18:44
 * Created with IntelliJ IDEA
 */

public class IsNumber65Test {

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
}