package solution.divideandconquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string of numbers and operators
 * Return all possible results from computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 *
 * @author BorisMirage
 * Time: 2019/06/12 10:17
 * Created with IntelliJ IDEA
 */
public class DiffWaysToCompute_241 {
    /**
     * Convert given string to a list that contains numbers and operators in order.
     * Then recursively split list to left part and right part by each operator.
     * Calculate result by left, right, and operator.
     *
     * @param input given string
     * @return all possible results from computing all the different possible ways to group numbers and operators
     */
    public List<Integer> diffWaysToCompute(String input) {

        List<Integer> out = new LinkedList<>();
        if (input == null || input.length() == 0) {
            return out;
        }

        List<String> elements = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {      // pre parse given string to string list

            int j = i;
            while (j < input.length() && Character.isDigit(input.charAt(j))) {
                j++;        // avoid split numbers larger than 9 to individual number, such as split "11" to "1" "1"
            }

            elements.add(input.substring(i, j));        // add numbers
            if (j != input.length()) {
                elements.add(input.substring(j, j + 1));        // add operators
            }
            i = j;      // move to next int
        }

        return recursion(elements, 0, elements.size() - 1);
    }

    /**
     * Recursively split list by operator into left and right part.
     * Calculate the result based on left, right, and operator.
     *
     * @param elements list contains numbers and operators
     * @param start    start position
     * @param end      end position
     * @return all possible results from computing all the different possible ways to group numbers and operators
     */
    private List<Integer> recursion(List<String> elements, int start, int end) {
        List<Integer> out = new ArrayList<>();

        if (start == end) {     // range narrowed into only one number without any operator
            out.add(Integer.valueOf(elements.get(start)));      // add this number to list
            return out;
        }

        for (int i = start + 1; i < end; i += 2) {      // num, operator, num, operator, ..., num

            String operator = elements.get(i);      // split list by operator

            List<Integer> left = recursion(elements, start, i - 1), right = recursion(elements, i + 1, end);

            for (Integer l : left) {
                for (Integer r : right) {
                    int temp = 0;
                    switch (operator) {
                        case "+":
                            temp = l + r;
                            break;
                        case "-":
                            temp = l - r;
                            break;
                        case "*":
                            temp = l * r;
                            break;
//                       default:
//                          throw new IllegalStateException("Unexpected value: " + operator);
                    }
                    out.add(temp);
                }
            }
        }

        return out;
    }
}
