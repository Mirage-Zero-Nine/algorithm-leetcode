package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class FactorialTest {
    private final Factorial factorial = new Factorial();
    @Test void computesBaseCases() { assertEquals(1, factorial.factorial(0)); assertEquals(1, factorial.factorial(1)); assertEquals(2, factorial.factorial(2)); }
    @Test void computesValuesThroughTen() {
        int[] expected = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};
        for (int n = 0; n <= 10; n++) assertEquals(expected[n], factorial.factorial(n));
    }
    @Test void rejectsNegativeInputs() { assertThrows(IllegalArgumentException.class, () -> factorial.factorial(-1)); assertThrows(IllegalArgumentException.class, () -> factorial.factorial(Integer.MIN_VALUE)); }
}
