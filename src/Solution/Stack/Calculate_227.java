package Solution.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 *
 * @author BorisMirage
 * Time: 2019/06/26 14:31
 * Created with IntelliJ IDEA
 */

public class Calculate_227 {
    /**
     * Use integers to store previous operand, current operand, and use character to store operator.
     * If the operator is + or -, then add previous number to sum, and set current number to positive or negative.
     * If the operator is * or /, then multiply or divide two operands, and set previous number to this result.
     * The reason is that * and / has higher priority, and can not involve previous result to multiply or divide.
     * Finally, add previous number to final result.
     *
     * @param s expression string
     * @return calculation result of s
     */
    public int calculate(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        long previousNumber = 0;        //
        int sum = 0, currentNumber;
        char previousOperator = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ' ') {     // trim string to remove redundant space
                if (Character.isDigit(c)) {
                    currentNumber = c - '0';
                    while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                        currentNumber = currentNumber * 10 + (s.charAt(i + 1) - '0');
                        i++;
                    }

                    switch (previousOperator) {
                        case '+':
                            sum += previousNumber;          // add previous number
                            previousNumber = currentNumber; // set current number to positive
                            break;
                        case '-':
                            sum += previousNumber;              // add previous number
                            previousNumber = -currentNumber;    // set previous number to negative
                            break;
                        case '*':       // multiplication has higher priority, can not involve previous sum
                            previousNumber = previousNumber * currentNumber;        // current multiplication result
                            break;
                        case '/':       // division is same as multiplication
                            previousNumber = previousNumber / currentNumber;        // current division result
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid character!");
                    }
                } else {
                    previousOperator = c;        // if current digit is not operand, then it is an operator
                }
            }
        }

        sum += previousNumber;      // final result

        return sum;
    }

    public static void main(String[] args) {
        Calculate_227 test = new Calculate_227();
        System.out.println(test.calculate("1*3+2+3+3*2"));
    }
}
