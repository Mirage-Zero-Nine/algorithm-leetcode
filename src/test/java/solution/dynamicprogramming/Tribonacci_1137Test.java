package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Tribonacci_1137Test {

    private final Tribonacci_1137 test = new Tribonacci_1137();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.tribonacci(4));
        assertEquals(7, test.tribonacci(5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.tribonacci(0));
        assertEquals(1, test.tribonacci(1));
        assertEquals(1, test.tribonacci(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(149, test.tribonacci(10));
    }

    @Test
    public void testN3() {
        assertEquals(2, test.tribonacci(3));
    }

    @Test
    public void testN6() {
        assertEquals(13, test.tribonacci(6));
    }

    @Test
    public void testN7() {
        assertEquals(24, test.tribonacci(7));
    }

    @Test
    public void testN8() {
        assertEquals(44, test.tribonacci(8));
    }

    @Test
    public void testN15() {
        assertEquals(3136, test.tribonacci(15));
    }

    @Test
    public void testN20() {
        assertEquals(66012, test.tribonacci(20));
    }

    @Test
    public void testGiantN37() {
        assertEquals(2082876103, test.tribonacci(37));
    }

    /**
     * Iterable sweep 0..37 (n=37 is the largest input that fits in a
     * signed 32-bit int) cross-checked against an independent O(1)-memory
     * iterative reference.
     */
    @ParameterizedTest(name = "tribonacci({0})")
    @MethodSource("zeroToThirtySeven")
    public void testEveryValueFromZeroToThirtySeven(int n) {
        assertEquals(reference(n), test.tribonacci(n));
    }

    private static int reference(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToThirtySeven() {
        return IntStream.rangeClosed(0, 37).mapToObj(i -> arguments(i));
    }
}
