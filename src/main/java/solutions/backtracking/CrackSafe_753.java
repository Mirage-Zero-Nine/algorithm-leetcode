package solutions.backtracking;

/**
 * There is a box protected by a password.
 * The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 *
 * @author BorisMirage
 * Time: 2019/09/14 15:01
 * Created with IntelliJ IDEA
 */

public class CrackSafe_753 {
    /**
     * A minimum-length answer is a De Bruijn sequence: view every length {@code n - 1}
     * string as a vertex and every length {@code n} string as a directed edge.  An
     * Euler tour visits each password exactly once.  The tour is constructed
     * iteratively so the largest valid graph cannot overflow the Java call stack.
     * <p>
     * Constructs an Euler tour of the De Bruijn graph using an explicit stack.
     * Each stack frame records the digit used to enter its vertex; recording
     * that digit is necessary when {@code n == 1}, because all edges then have
     * the same vertex as both endpoints.  Vertices are exhausted in digit
     * order, and the post-order edge labels are reversed to obtain the tour.
     * <p>
     * The tour contains exactly {@code k^n} edges, so prepending {@code n - 1}
     * zeroes gives the required minimum-length string.  The explicit stack
     * avoids recursion depth proportional to {@code k^n}.
     * <p>
     * Time complexity is {@code O(k^n)} and auxiliary space complexity is
     * {@code O(k^n)}.
     *
     * @param n password sequence of n digits
     * @param k password combination from 0 to k - 1
     * @return any password of minimum length that is guaranteed to open the box at some point of entering it
     */
    public String crackSafe(int n, int k) {
        int edgeCount = 1;
        for (int i = 0; i < n; i++) {
            edgeCount *= k;
        }
        int vertexCount = edgeCount / k;
        int[] nextDigit = new int[vertexCount];
        int[] vertexStack = new int[edgeCount + 1];
        int[] incomingDigitStack = new int[edgeCount + 1];
        incomingDigitStack[0] = -1; // The initial vertex has no incoming edge.

        int top = 0;
        StringBuilder reverseTour = new StringBuilder(edgeCount);
        while (top >= 0) {
            int vertex = vertexStack[top];
            if (nextDigit[vertex] < k) {
                int digit = nextDigit[vertex]++;
                vertexStack[++top] = (vertex * k + digit) % vertexCount;
                incomingDigitStack[top] = digit;
            } else {
                int incomingDigit = incomingDigitStack[top--];
                if (incomingDigit >= 0) {
                    reverseTour.append((char) ('0' + incomingDigit));
                }
            }
        }

        StringBuilder result = new StringBuilder(edgeCount + n - 1);
        result.append("0".repeat(n - 1));
        result.append(reverseTour.reverse());
        return result.toString();
    }
}
