package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Contract and regression tests for {@link ShuffleArray_384}.
 *
 * <p>Randomness is checked through deterministic permutation, freshness, and state-isolation
 * properties. A statistical distribution assertion is intentionally avoided because a finite
 * sample cannot reliably prove equal likelihood and would make the suite flaky.</p>
 */
public class ShuffleArray_384Test {

    @Test
    public void officialWorkflowRestoresOriginalBetweenShuffles() {
        int[] original = {1, 2, 3};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        assertPermutationOf(original, solution.shuffle());
        assertArrayEquals(original, solution.reset());
        assertPermutationOf(original, solution.shuffle());
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void singletonAlwaysRemainsTheOnlyPermutation() {
        ShuffleArray_384 solution = new ShuffleArray_384(new int[]{42});

        for (int call = 0; call < 20; call++) {
            assertArrayEquals(new int[]{42}, solution.shuffle());
        }
        assertArrayEquals(new int[]{42}, solution.reset());
    }

    @Test
    public void emptyArrayIsSupportedByImplementation() {
        int[] original = {};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        int[] shuffled = solution.shuffle();
        assertArrayEquals(original, shuffled);
        assertNotSame(original, shuffled);
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void nullArrayUsesDocumentedImplementationGuard() {
        ShuffleArray_384 solution = new ShuffleArray_384(null);

        assertNull(solution.shuffle());
        assertNull(solution.reset());
    }

    @Test
    public void twoElementsProduceOnlyValidPermutations() {
        int[] original = {1, 2};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 20; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
    }

    @Test
    public void threeElementsPreserveEveryValue() {
        int[] original = {7, 3, 9};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 30; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
    }

    @Test
    public void duplicateValuesRemainWithTheirOriginalMultiplicity() {
        int[] original = {2, 1, 2, 1, 2, 1};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 30; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void allEqualValuesAreUnchanged() {
        int[] original = {8, 8, 8, 8, 8};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 20; call++) {
            assertArrayEquals(original, solution.shuffle());
        }
    }

    @Test
    public void negativeAndZeroValuesArePreserved() {
        int[] original = {-10, -1, 0, 1, 10};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        assertPermutationOf(original, solution.shuffle());
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void javaIntegerBoundariesArePreserved() {
        int[] original = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 20; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void shuffleDoesNotMutateCallerArray() {
        int[] original = {5, 4, 3, 2, 1};
        int[] snapshot = original.clone();
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        solution.shuffle();
        solution.shuffle();

        assertArrayEquals(snapshot, original);
        assertArrayEquals(snapshot, solution.reset());
    }

    @Test
    public void everyShuffleReturnsFreshStorage() {
        int[] original = {1, 2, 3, 4};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        int[] first = solution.shuffle();
        int[] second = solution.shuffle();

        assertNotSame(original, first);
        assertNotSame(first, second);
        assertPermutationOf(original, first);
        assertPermutationOf(original, second);
    }

    @Test
    public void mutatingShuffleResultDoesNotChangeResetState() {
        int[] original = {11, 22, 33, 44};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        int[] shuffled = solution.shuffle();
        shuffled[0] = Integer.MIN_VALUE;
        shuffled[1] = Integer.MAX_VALUE;

        assertArrayEquals(original, solution.reset());
        assertPermutationOf(original, solution.shuffle());
    }

    @Test
    public void resetAlwaysReturnsOriginalAfterManyShuffles() {
        int[] original = {9, 8, 7, 6, 5, 4, 3};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 100; call++) {
            assertPermutationOf(original, solution.shuffle());
            assertArrayEquals(original, solution.reset());
        }
    }

    @Test
    public void repeatedResetCallsRemainOriginal() {
        int[] original = {4, 1, 4, 2};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        assertArrayEquals(original, solution.reset());
        assertArrayEquals(original, solution.reset());
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void maximumLeetCodeInputSizeRemainsAValidPermutation() {
        int[] original = new int[50];
        for (int i = 0; i < original.length; i++) {
            original[i] = 1000 - i * 37;
        }
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 20; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void manyCallsPreservePermutationAndOriginalState() {
        int[] original = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 1_000; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void separateInstancesDoNotShareShuffleState() {
        int[] firstOriginal = {1, 2, 3};
        int[] secondOriginal = {10, 20, 30, 40};
        ShuffleArray_384 first = new ShuffleArray_384(firstOriginal);
        ShuffleArray_384 second = new ShuffleArray_384(secondOriginal);

        for (int call = 0; call < 20; call++) {
            assertPermutationOf(firstOriginal, first.shuffle());
            assertPermutationOf(secondOriginal, second.shuffle());
        }
        assertArrayEquals(firstOriginal, first.reset());
        assertArrayEquals(secondOriginal, second.reset());
    }

    @Test
    public void equalContentsInSeparateInstancesRemainIndependent() {
        int[] firstInput = {6, 5, 4, 3};
        int[] secondInput = {6, 5, 4, 3};
        ShuffleArray_384 first = new ShuffleArray_384(firstInput);
        ShuffleArray_384 second = new ShuffleArray_384(secondInput);

        int[] firstShuffle = first.shuffle();
        firstShuffle[0] = 99;

        assertArrayEquals(new int[]{6, 5, 4, 3}, first.reset());
        assertArrayEquals(new int[]{6, 5, 4, 3}, second.reset());
        assertPermutationOf(secondInput, second.shuffle());
    }

    @Test
    public void shuffleRemainsUsableAfterReturnedArrayIsMutated() {
        int[] original = {3, 1, 4, 1, 5, 9};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        int[] first = solution.shuffle();
        Arrays.fill(first, Integer.MIN_VALUE);
        int[] second = solution.shuffle();

        assertPermutationOf(original, second);
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void differentInputOrderIsRetainedByReset() {
        int[] original = {5, 1, 4, 2, 3};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        solution.shuffle();

        assertArrayEquals(new int[]{5, 1, 4, 2, 3}, solution.reset());
    }

    @Test
    public void everySmallPermutationCanBeUsedAsAnInput() {
        int[][] inputs = {
            {1, 2, 3},
            {1, 3, 2},
            {2, 1, 3},
            {2, 3, 1},
            {3, 1, 2},
            {3, 2, 1}
        };

        for (int[] input : inputs) {
            ShuffleArray_384 solution = new ShuffleArray_384(input);
            assertPermutationOf(input, solution.shuffle());
            assertArrayEquals(input, solution.reset());
        }
    }

    @Test
    public void repeatedValuesAtBothEndsRemainDistinctByMultiplicity() {
        int[] original = {Integer.MIN_VALUE, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 30; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
    }

    @Test
    public void zeroAndNegativeValuesRemainUnchangedAtInput() {
        int[] original = {0, -1, -2, -3, -4, -5};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 25; call++) {
            assertPermutationOf(original, solution.shuffle());
        }
        assertArrayEquals(new int[]{0, -1, -2, -3, -4, -5}, original);
    }

    @Test
    public void resetWorksBeforeAnyShuffle() {
        int[] original = {13, 17, 19};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        assertArrayEquals(original, solution.reset());
        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void resetAfterOnlyMutatingReturnedShuffleStillRestoresOriginal() {
        int[] original = {21, 34, 55, 89};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        int[] shuffled = solution.shuffle();
        shuffled[shuffled.length - 1] = 123456789;

        assertArrayEquals(original, solution.reset());
    }

    @Test
    public void shuffledArraysHaveSameLengthAsInput() {
        int[] original = {-4, -3, -2, -1, 0, 1, 2, 3, 4};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 50; call++) {
            assertEquals(original.length, solution.shuffle().length);
        }
    }

    @Test
    public void duplicateOnlyInputHasOneObservablePermutation() {
        int[] original = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        ShuffleArray_384 solution = new ShuffleArray_384(original);

        for (int call = 0; call < 50; call++) {
            assertArrayEquals(original, solution.shuffle());
        }
    }

    private static void assertPermutationOf(int[] expectedValues, int[] actual) {
        assertEquals(expectedValues.length, actual.length);
        int[] expectedSorted = expectedValues.clone();
        int[] actualSorted = actual.clone();
        Arrays.sort(expectedSorted);
        Arrays.sort(actualSorted);
        assertArrayEquals(expectedSorted, actualSorted);
    }
}
