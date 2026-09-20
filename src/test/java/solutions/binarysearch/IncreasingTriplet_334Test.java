package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests both implementations of LeetCode 334, including strict-order regressions. */
public class IncreasingTriplet_334Test {

    private final IncreasingTriplet_334 solution = new IncreasingTriplet_334();

    @Test
    public void officialExamples() {
        assertBoth(new int[]{1, 2, 3, 4, 5}, true);
        assertBoth(new int[]{5, 4, 3, 2, 1}, false);
        assertBoth(new int[]{2, 1, 5, 0, 4, 6}, true);
    }

    @Test
    public void exactlyThreeElements() {
        assertBoth(new int[]{1, 2, 3}, true);
        assertBoth(new int[]{3, 2, 1}, false);
        assertBoth(new int[]{1, 3, 2}, false);
        assertBoth(new int[]{-3, -2, -1}, true);
    }

    @Test
    public void arraysShorterThanThreeCannotContainTriplet() {
        assertBoth(new int[0], false);
        assertBoth(new int[]{7}, false);
        assertBoth(new int[]{1, 2}, false);
        assertBoth(new int[]{2, 1}, false);
    }

    @Test
    public void descendingAndConstantArraysAreNegative() {
        assertBoth(new int[]{9, 8, 7, 6, 5, 4}, false);
        assertBoth(new int[]{0, 0, 0, 0, 0}, false);
        assertBoth(new int[]{-4, -4, -4, -4}, false);
    }

    @Test
    public void strictIncreaseRejectsDuplicateValues() {
        assertBoth(new int[]{1, 1, 1, 2, 2}, false);
        assertBoth(new int[]{1, 2, 2}, false);
        assertBoth(new int[]{1, 1, 2, 2}, false);
        assertBoth(new int[]{2, 2, 3, 3, 3}, false);
    }

    @Test
    public void duplicatesMaySurroundAValidStrictTriplet() {
        assertBoth(new int[]{1, 1, 2, 2, 3, 3}, true);
        assertBoth(new int[]{5, 5, 1, 1, 2, 2, 3}, true);
        assertBoth(new int[]{-2, -2, -1, -1, 0, 0}, true);
    }

    @Test
    public void subsequenceNeedNotBeContiguous() {
        assertBoth(new int[]{1, 9, 2, 8, 3}, true);
        assertBoth(new int[]{9, 1, 8, 2, 7, 3}, true);
        assertBoth(new int[]{10, 20, 1, 30}, true);
    }

    @Test
    public void orderingTrapsDoNotReuseOutOfOrderValues() {
        assertBoth(new int[]{3, 4, 1, 2}, false);
        assertBoth(new int[]{2, 4, 1, 3}, false);
        assertBoth(new int[]{5, 6, 1, 2, 2}, false);
        assertBoth(new int[]{2, 1, 2, 1, 2}, false);
    }

    @Test
    public void aNewMinimumCanPreserveAnEarlierPair() {
        assertBoth(new int[]{10, 20, 1, 30}, true);
        assertBoth(new int[]{5, 10, 2, 3, 4}, true);
        assertBoth(new int[]{4, 6, 1, 2, 3}, true);
    }

    @Test
    public void validTripletCanAppearOnlyAtTheEnd() {
        assertBoth(new int[]{9, 8, 7, 6, 1, 2, 3}, true);
        assertBoth(new int[]{100, 50, 40, 1, 2, 10}, true);
        assertBoth(new int[]{5, 4, 3, 0, 1, 2}, true);
    }

    @Test
    public void negativeAndMixedSignedValuesUseNumericOrder() {
        assertBoth(new int[]{-5, -4, -3, -2}, true);
        assertBoth(new int[]{-3, 0, 3}, true);
        assertBoth(new int[]{3, 0, -3}, false);
        assertBoth(new int[]{5, 0, -5, 4, 3}, false);
    }

    @Test
    public void integerBoundariesAndStrictness() {
        assertBoth(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, true);
        assertBoth(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MAX_VALUE}, true);
        assertBoth(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, false);
        assertBoth(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, false);
        assertBoth(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, false);
    }

    @Test
    public void alternatingPeaksAndValleysNeedThreeOrderedValues() {
        assertBoth(new int[]{2, 1, 4, 3, 5}, true);
        assertBoth(new int[]{4, 1, 3, 2, 5}, true);
        assertBoth(new int[]{2, 1, 2, 1, 2, 1}, false);
        assertBoth(new int[]{3, 1, 2, 0, 2}, false);
    }

    @Test
    public void bothMethodsHandleFreshInputsIndependently() {
        int[] input = {2, 1, 5, 0, 4, 6};
        int[] snapshot = input.clone();
        assertEquals(true, solution.increasingTriplet(input));
        assertArrayEquals(snapshot, input);
        assertEquals(true, solution.traverse(input));
        assertArrayEquals(snapshot, input);
    }

    @Test
    public void repeatedCallsDoNotLeakStateBetweenArrays() {
        assertEquals(false, solution.increasingTriplet(new int[]{3, 2, 1}));
        assertEquals(true, solution.increasingTriplet(new int[]{1, 2, 3}));
        assertEquals(false, solution.increasingTriplet(new int[]{2, 1, 2}));

        assertEquals(false, solution.traverse(new int[]{3, 2, 1}));
        assertEquals(true, solution.traverse(new int[]{1, 2, 3}));
        assertEquals(false, solution.traverse(new int[]{2, 1, 2}));
    }

    @Test
    public void exhaustiveSixElementArraysMatchIndependentCubicOracle() {
        for (int encoded = 0; encoded < 4 * 4 * 4 * 4 * 4 * 4; encoded++) {
            int[] values = new int[6];
            int remaining = encoded;
            for (int i = 0; i < values.length; i++) {
                values[i] = remaining % 4 - 1;
                remaining /= 4;
            }
            assertBoth(values, hasIncreasingTriplet(values));
        }
    }

    @Test
    public void seededSmallArraysMatchIndependentCubicOracle() {
        Random random = new Random(334L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int[] values = new int[3 + random.nextInt(10)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(21) - 10;
            }
            assertBoth(values, hasIncreasingTriplet(values));
        }
    }

    @Test
    public void maximumLengthDescendingArrayIsNegative() {
        int[] values = new int[500_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = values.length - i;
        }
        assertBoth(values, false);
    }

    @Test
    public void maximumLengthArrayWithDelayedTripletIsPositive() {
        int[] values = new int[500_000];
        for (int i = 0; i < values.length - 3; i++) {
            values[i] = values.length - i;
        }
        values[values.length - 3] = Integer.MIN_VALUE;
        values[values.length - 2] = 0;
        values[values.length - 1] = Integer.MAX_VALUE;
        assertBoth(values, true);
    }

    @Test
    public void exactThreeValueBoundariesAreCheckedAtBothEnds() {
        assertBoth(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2}, true);
        assertBoth(new int[]{Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, true);
        assertBoth(new int[]{Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 1, Integer.MIN_VALUE}, false);
    }

    @Test
    public void delayedTripletMustRespectIndexOrder() {
        assertBoth(new int[]{4, 3, 2, 1, 0, 5}, false);
        assertBoth(new int[]{4, 3, 2, 5, 1, 6}, true);
        assertBoth(new int[]{6, 5, 4, 3, 2, 1}, false);
    }

    @Test
    public void allCandidatesCanBeReplacedBeforeSuccess() {
        assertBoth(new int[]{100, 1, 2, 0, 3}, true);
        assertBoth(new int[]{8, 9, 1, 7, 2, 6, 3}, true);
        assertBoth(new int[]{8, 9, 1, 7, 6, 5, 0}, false);
    }

    private void assertBoth(int[] values, boolean expected) {
        int[] firstInput = values.clone();
        int[] secondInput = values.clone();
        assertEquals(expected, solution.increasingTriplet(firstInput),
                "binary-search tails approach for " + java.util.Arrays.toString(values));
        assertEquals(expected, solution.traverse(secondInput),
                "one-pass traversal approach for " + java.util.Arrays.toString(values));
        assertArrayEquals(values, firstInput, "increasingTriplet must not mutate its input");
        assertArrayEquals(values, secondInput, "traverse must not mutate its input");
    }

    private boolean hasIncreasingTriplet(int[] values) {
        for (int i = 0; i < values.length - 2; i++) {
            for (int j = i + 1; j < values.length - 1; j++) {
                for (int k = j + 1; k < values.length; k++) {
                    if (values[i] < values[j] && values[j] < values[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
