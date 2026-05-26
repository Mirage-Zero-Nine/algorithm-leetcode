package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class IsArmstrong_1134Test {

    private final IsArmstrong_1134 test = new IsArmstrong_1134();

    @Test
    public void testHappyCases() {
        assertTrue(test.isArmstrong(153));
        assertTrue(test.isArmstrong(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isArmstrong(10));
        assertFalse(test.isArmstrong(100));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isArmstrong(1634));
        assertFalse(test.isArmstrong(1000));
    }

    @Test
    public void testSingleDigits() {
        assertTrue(test.isArmstrong(1));
        assertTrue(test.isArmstrong(5));
        assertTrue(test.isArmstrong(7));
    }

    @Test
    public void testTwoDigitNonArmstrong() {
        assertFalse(test.isArmstrong(11));
        assertFalse(test.isArmstrong(99));
    }

    @Test
    public void testThreeDigitArmstrong() {
        assertTrue(test.isArmstrong(370));
        assertTrue(test.isArmstrong(371));
        assertTrue(test.isArmstrong(407));
    }

    @Test
    public void testThreeDigitNonArmstrong() {
        assertFalse(test.isArmstrong(200));
        assertFalse(test.isArmstrong(999));
    }

    @Test
    public void testFourDigitArmstrong() {
        assertTrue(test.isArmstrong(8208));
        assertTrue(test.isArmstrong(9474));
    }

    @Test
    public void testFourDigitNonArmstrong() {
        assertFalse(test.isArmstrong(1234));
        assertFalse(test.isArmstrong(5000));
    }

    @Test
    public void testGiantCase() {
        assertTrue(test.isArmstrong(54748));
        assertFalse(test.isArmstrong(99999));
    }

    /**
     * Iterable sweep 1..1000 against an integer-only reference. The impl
     * under test uses {@link Math#log10} and {@link Math#pow}, both of
     * which can introduce floating-point error; this sweep verifies it
     * still produces correct integer answers in the lower range.
     */
    @ParameterizedTest(name = "isArmstrong({0})")
    @MethodSource("oneToOneThousand")
    public void testEveryValueFromOneToOneThousand(int n, boolean expected) {
        assertEquals(expected, test.isArmstrong(n));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToOneThousand() {
        return IntStream.rangeClosed(1, 1000)
                .mapToObj(i -> arguments(i, isArmstrongReference(i)));
    }

    /**
     * Integer-only reference: count digits, sum each digit raised to that
     * power, compare to N. No floating point. Catches any drift caused by
     * {@code Math.pow} rounding in the impl.
     */
    private static boolean isArmstrongReference(int n) {
        if (n <= 0) return false;
        int digits = 0;
        for (int t = n; t > 0; t /= 10) digits++;
        long sum = 0;
        for (int t = n; t > 0; t /= 10) {
            int d = t % 10;
            long p = 1;
            for (int i = 0; i < digits; i++) p *= d;
            sum += p;
            if (sum > n) return false;
        }
        return sum == n;
    }

    /**
     * The complete list of known Armstrong (narcissistic) numbers up to 7
     * digits. The impl must accept every one.
     */
    @ParameterizedTest(name = "Armstrong: {0}")
    @ValueSource(ints = {
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            153, 370, 371, 407,
            1634, 8208, 9474,
            54748, 92727, 93084,
            548834,
            1741725, 4210818, 9800817, 9926315
    })
    public void testKnownArmstrongNumbers(int n) {
        assertTrue(test.isArmstrong(n), n + " is a known Armstrong number");
    }
}
