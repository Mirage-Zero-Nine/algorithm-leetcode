package solution.others;

/**
 * Validate if a given string can be interpreted as a decimal number.
 *
 * @author BorisMirage
 * Time: 2019/07/01 14:13
 * Created with IntelliJ IDEA
 */

public class IsNumber_65 {

    /**
     * Straight forward solution.
     * Trim the string first, then keep 3 boolean to check:
     * 1. Is there a '.' before current position?
     * 2. Is there a 'e' or 'E' before current position?
     * 3. Is there a number before current position?
     * Then check the string to see if it is valid.
     *
     * @param s given string
     * @return is given string a valid number
     */
    public boolean isNumber(String s) {

        /* Corner case */
        if (s == null) {
            return false;
        }

        s = s.trim();
        boolean hasPoint = false;
        boolean hasE = false;
        boolean hasNumber = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') { // case 1: normal digit
                hasNumber = true; // digit in given string
            } else if (s.charAt(i) == '.') { // case 2: a point
                if (hasE || hasPoint) { // '.' should not coming after e, and only one '.' is allowed in a valid number
                    return false;
                }
                hasPoint = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') { // case 3: e
                if (hasE || !hasNumber) { // only one e is allowed in a valid number, and e should come after digit
                    return false;
                }
                hasE = true;
                hasNumber = false; // there should be at least one digit after e
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') { // case 4: -/+
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i) != 'E') {
                    return false; // it could only be at first char in string, or immediate after an 'e'
                }
            } else {
                return false;
            }
        }

        return hasNumber;
    }

    /**
     * Regular expression.
     *
     * @param s given string
     * @return if the given string can be interpreted as a decimal number
     */
    public boolean isNumberRegex(String s) {
        return (s != null) && s.trim().matches("[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?");
    }

    /**
     * Judge if a string represents a valid number can be done by a state machine.
     * The validity is depending on final state of string.
     *
     * @param s given string
     * @return if the given string can be interpreted as a decimal number
     */
    public boolean isNumberStateMachine(String s) {

        /* Corner case */
        if (s == null) {
            return false;
        }

        int state = 0;
        s = s.trim();   // remove all spaces

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '+':
                case '-':
                    if (state == 0) {
                        state = 1;
                    } else if (state == 4) {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    switch (state) {
                        case 0:
                        case 1:
                        case 2:
                            state = 2;
                            break;
                        case 3:
                            state = 3;
                            break;
                        case 4:
                        case 5:
                        case 6:
                            state = 5;
                            break;
                        case 7:
                            state = 8;
                            break;
                        case 8:
                            state = 8;
                            break;
                        default:
                            return false;
                    }
                    break;
                case '.':
                    switch (state) {
                        case 0:
                        case 1:
                            state = 7;
                            break;
                        case 2:
                            state = 3;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 'e':
                    switch (state) {
                        case 2:
                        case 3:
                        case 8:
                            state = 4;
                            break;
                        default:
                            return false;
                    }
                    break;
                default:
                    return false;

            }
        }

        return state == 2 || state == 3 || state == 5 || state == 8;        // only state 2, 3, 5, 8 is valid
    }
}
