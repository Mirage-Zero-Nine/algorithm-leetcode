package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for {@link MyPow_50}.
 *
 * <p>The independent oracle is {@link StrictMath#pow(double, double)}. A
 * relative/ULP-scaled tolerance is used for finite nonzero results because
 * exponentiation by squaring and the platform library may round intermediate
 * products differently. Signed zero, infinities, and NaN are checked
 * separately when exercising supported floating-point edge behavior.</p>
 */
public class MyPow_50Test {

    private final MyPow_50 test = new MyPow_50();

    @Test
    public void testOfficialExampleLargePositiveExponent() {
        assertPow(2.0, 10);
    }

    @Test
    public void testOfficialExampleDecimalBase() {
        assertPow(2.1, 3);
    }

    @Test
    public void testOfficialExampleNegativeExponent() {
        assertPow(2.0, -2);
    }

    @Test
    public void testZeroExponentForRepresentativeNonzeroBases() {
        assertPow(2.5, 0);
        assertPow(-2.5, 0);
        assertPow(0.0001, 0);
        assertPow(99.999, 0);
    }

    @Test
    public void testExponentOneReturnsBase() {
        assertPow(-99.999, 1);
        assertPow(-0.125, 1);
        assertPow(0.125, 1);
        assertPow(99.999, 1);
    }

    @Test
    public void testExponentMinusOneReturnsReciprocal() {
        assertPow(-4.0, -1);
        assertPow(-0.25, -1);
        assertPow(0.25, -1);
        assertPow(4.0, -1);
    }

    @Test
    public void testZeroBasePositiveExponent() {
        assertPow(0.0, 1);
        assertPow(0.0, 2);
        assertPow(0.0, 37);
        assertPow(0.0, Integer.MAX_VALUE);
    }

    @Test
    public void testNegativeZeroPositiveExponentPreservesParity() {
        assertPow(-0.0, 1);
        assertPow(-0.0, 3);
        assertPow(-0.0, 2);
        assertPow(-0.0, Integer.MAX_VALUE);
    }

    @Test
    public void testUnitBaseAtExponentBounds() {
        assertPow(1.0, Integer.MIN_VALUE);
        assertPow(1.0, Integer.MAX_VALUE);
        assertPow(1.0, -1);
        assertPow(1.0, 0);
    }

    @Test
    public void testNegativeUnitBaseAtExponentBounds() {
        assertPow(-1.0, Integer.MIN_VALUE);
        assertPow(-1.0, Integer.MAX_VALUE);
        assertPow(-1.0, Integer.MIN_VALUE + 1);
        assertPow(-1.0, Integer.MAX_VALUE - 1);
    }

    @Test
    public void testNegativeBaseOddPositivePowers() {
        assertPow(-2.0, 1);
        assertPow(-2.0, 3);
        assertPow(-2.0, 5);
        assertPow(-3.5, 7);
    }

    @Test
    public void testNegativeBaseEvenPositivePowers() {
        assertPow(-2.0, 2);
        assertPow(-2.0, 4);
        assertPow(-3.5, 6);
        assertPow(-9.0, 8);
    }

    @Test
    public void testNegativeBaseOddNegativePowers() {
        assertPow(-2.0, -3);
        assertPow(-2.0, -5);
        assertPow(-3.5, -7);
        assertPow(-0.5, -3);
    }

    @Test
    public void testNegativeBaseEvenNegativePowers() {
        assertPow(-2.0, -2);
        assertPow(-2.0, -4);
        assertPow(-3.5, -6);
        assertPow(-0.5, -4);
    }

    @Test
    public void testFractionalBasePositivePowers() {
        assertPow(0.5, 3);
        assertPow(0.25, 4);
        assertPow(0.8, 9);
        assertPow(-0.75, 8);
    }

    @Test
    public void testFractionalBaseNegativePowers() {
        assertPow(0.5, -2);
        assertPow(0.25, -4);
        assertPow(0.8, -9);
        assertPow(-0.5, -7);
    }

    @Test
    public void testDecimalBasePrecision() {
        assertPow(1.0001, 10);
        assertPow(1.0001, -10);
        assertPow(2.123456789, 7);
        assertPow(-1.23456789, 8);
    }

    @Test
    public void testNearUnitBaseWithLargeExponent() {
        assertPow(0.9999, 10000);
        assertPow(0.9999, -10000);
        assertPow(1.00001, 50000);
        assertPow(1.00001, -50000);
    }

    @Test
    public void testMaximumMagnitudeBasesWithinProblemResultBound() {
        assertPow(99.999, 1);
        assertPow(-99.999, 1);
        assertPow(99.999, 2);
        assertPow(-99.999, 2);
    }

    @Test
    public void testMinimumExponentPositiveBaseUnderflowsSafely() {
        assertPow(2.0, Integer.MIN_VALUE);
        assertPow(10.0, Integer.MIN_VALUE);
        assertPow(99.0, Integer.MIN_VALUE);
    }

    @Test
    public void testMinimumExponentNegativeBaseUnderflowsWithEvenParity() {
        assertPow(-2.0, Integer.MIN_VALUE);
        assertPow(-10.0, Integer.MIN_VALUE);
        assertPow(-99.0, Integer.MIN_VALUE);
    }

    @Test
    public void testMaximumExponentFractionalBaseUnderflowsSafely() {
        assertPow(0.5, Integer.MAX_VALUE);
        assertPow(0.25, Integer.MAX_VALUE);
        assertPow(-0.5, Integer.MAX_VALUE);
    }

    @Test
    public void testMaximumExponentNegativeUnitBaseHasOddParity() {
        assertPow(-1.0, Integer.MAX_VALUE);
    }

    @Test
    public void testExactlyRepresentablePowersNearFloatingPointRange() {
        assertEquals(Math.scalb(1.0, -1022), test.myPow(2.0, -1022), 0.0);
        assertEquals(Math.scalb(1.0, 1023), test.myPow(2.0, 1023), 0.0);
    }

    @Test
    public void testSmallIntegerExponentTableAgainstStrictMathOracle() {
        double[] bases = {-2.5, -1.25, -0.5, 0.25, 0.75, 1.5, 2.5};
        for (double base : bases) {
            for (int exponent = -20; exponent <= 20; exponent++) {
                assertPow(base, exponent);
            }
        }
    }

    @Test
    public void testAdditionalMixedSignsAgainstStrictMathOracle() {
        double[] bases = {-9.75, -3.125, -0.0625, 0.0625, 3.125, 9.75};
        int[] exponents = {-31, -16, -7, -2, 2, 7, 16, 31};
        for (double base : bases) {
            for (int exponent : exponents) {
                assertPow(base, exponent);
            }
        }
    }

    @Test
    public void testRepeatedCallsDoNotShareExponentiationState() {
        assertPow(2.0, 10);
        assertPow(3.0, 3);
        assertPow(0.5, -4);
        assertPow(-2.0, 5);
        assertPow(2.0, 10);
    }

    @Test
    public void testCallsRemainIndependentAfterExtremeExponent() {
        assertPow(2.0, Integer.MIN_VALUE);
        assertPow(0.5, 2);
        assertPow(-1.0, Integer.MAX_VALUE);
        assertPow(7.0, 0);
        assertPow(2.0, -10);
    }

    @Test
    public void testFiniteSpecialValuesMatchStrictMathOracle() {
        assertPow(Double.MIN_VALUE, 1);
        assertPow(Double.MIN_VALUE, 2);
        assertPow(Double.MAX_VALUE, 1);
        assertPow(-Double.MAX_VALUE, 1);
    }

    @Test
    public void testInfinityAndNaNFollowStrictMathOracle() {
        assertPow(Double.POSITIVE_INFINITY, 3);
        assertPow(Double.NEGATIVE_INFINITY, 3);
        assertPow(Double.POSITIVE_INFINITY, -3);
        assertPow(Double.NaN, 3);
        assertPow(Double.NaN, 0);
    }

    @Test
    public void testOracleHelperChecksSmallNonzeroResultsPrecisely() {
        double expected = StrictMath.pow(2.0, -1022);
        double actual = test.myPow(2.0, -1022);
        assertEquals(expected, actual, Math.ulp(expected) * 16);
        assertTrue(actual > 0.0);
    }

    private void assertPow(double base, int exponent) {
        double expected = StrictMath.pow(base, exponent);
        double actual = test.myPow(base, exponent);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(actual), () -> "expected NaN for " + base + "^" + exponent);
        } else if (Double.isInfinite(expected)) {
            assertEquals(expected, actual, () -> "wrong infinity for " + base + "^" + exponent);
        } else if (expected == 0.0) {
            assertEquals(Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(actual),
                    () -> "wrong signed zero for " + base + "^" + exponent);
        } else {
            double tolerance = Math.max(Math.abs(expected) * 1e-12, Math.ulp(expected) * 16);
            assertEquals(expected, actual, tolerance,
                    () -> "wrong value for " + base + "^" + exponent);
        }
    }
}
