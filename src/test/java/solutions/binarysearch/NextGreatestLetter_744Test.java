package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class NextGreatestLetter_744Test {

    private final NextGreatestLetter_744 test = new NextGreatestLetter_744();

    @Test
    public void testHappyCases() {
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a'));
        assertEquals('f', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j'));
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'z'));
    }

    @Test
    public void testLargeCase() {
        assertEquals('b', test.nextGreatestLetter(new char[]{'a', 'b', 'c', 'd', 'e'}, 'a'));
    }

    @Test
    public void testTargetBetweenLetters() {
        assertEquals('j', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g'));
    }

    @Test
    public void testSingleElementArrayWrapsAlways() {
        assertEquals('m', test.nextGreatestLetter(new char[]{'m'}, 'a'));
    }

    @Test
    public void testDuplicateLetters() {
        assertEquals('h', test.nextGreatestLetter(new char[]{'a', 'a', 'd', 'h', 'h'}, 'd'));
    }

    @Test
    public void testTargetBeforeFirstLetter() {
        assertEquals('b', test.nextGreatestLetter(new char[]{'b', 'd', 'f'}, 'a'));
    }

    @Test
    public void testTargetEqualsMiddleDuplicate() {
        assertEquals('z', test.nextGreatestLetter(new char[]{'e', 'e', 'e', 'z'}, 'e'));
    }

    @Test
    public void testTargetJustBeforeLargest() {
        assertEquals('z', test.nextGreatestLetter(new char[]{'a', 'm', 'z'}, 'y'));
    }

    @Test
    public void testGiantCase() {
        char[] letters = new char[1000];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) ('a' + (i % 26));
        }
        java.util.Arrays.sort(letters);
        assertEquals('z', test.nextGreatestLetter(letters, 'y'));
    }
    @Test
    public void testAllTargetsForEverySmallLetterSubset() {
        for (int mask = 1; mask < 64; mask++) {
            if (Integer.bitCount(mask) < 2) continue;
            char[] letters = new char[Integer.bitCount(mask) * 2];
            int index = 0;
            for (int i = 0; i < 6; i++) if ((mask & (1 << i)) != 0) {
                letters[index++] = (char) ('b' + i * 4);
                letters[index++] = (char) ('b' + i * 4);
            }
            for (char target = 'a'; target <= 'z'; target++) {
                char expected = letters[0];
                for (char letter : letters) if (letter > target) { expected = letter; break; }
                assertEquals(expected, test.nextGreatestLetter(letters, target));
            }
        }
    }

    @Test
    public void testGiantDuplicatePrefixSkipsEveryEqualLetter() {
        char[] letters = new char[10000];
        java.util.Arrays.fill(letters, 'a');
        letters[9999] = 'z';
        assertEquals('z', test.nextGreatestLetter(letters, 'a'));
        assertEquals('a', test.nextGreatestLetter(letters, 'z'));
    }

    @Test
    public void testStrictSuccessorAcrossConsecutiveDuplicateRuns() {
        assertEquals('d', test.nextGreatestLetter(new char[]{'b', 'b', 'c', 'c', 'd', 'd'}, 'c'));
    }

    @Test
    public void testOfficialExampleWithDuplicateWraparound() {
        assertEquals('x', test.nextGreatestLetter(new char[]{'x', 'x', 'y', 'y'}, 'z'));
    }

    @Test
    public void testExactTargetAtFirstPositionUsesStrictSuccessor() {
        assertEquals('d', test.nextGreatestLetter(new char[]{'b', 'b', 'd', 'f'}, 'b'));
    }

    @Test
    public void testExactTargetAtLastPositionWrapsToFirst() {
        assertEquals('a', test.nextGreatestLetter(new char[]{'a', 'm', 'z'}, 'z'));
    }

    @Test
    public void testTargetsOnBothSidesOfInteriorGaps() {
        char[] letters = {'c', 'h', 'n', 't'};
        assertNext(letters, 'g');
        assertNext(letters, 'h');
        assertNext(letters, 'i');
        assertNext(letters, 'm');
        assertNext(letters, 'n');
    }

    @Test
    public void testTwoElementMinimumArraysAtEveryTargetRegion() {
        char[] letters = {'d', 'q'};
        assertNext(letters, 'a');
        assertNext(letters, 'd');
        assertNext(letters, 'p');
        assertNext(letters, 'q');
        assertNext(letters, 'z');
    }

    @Test
    public void testAllEqualArrayIsSupportedByTheJavaMethod() {
        char[] letters = {'k', 'k', 'k', 'k'};
        assertNext(letters, 'a');
        assertNext(letters, 'k');
        assertNext(letters, 'z');
    }

    @Test
    public void testLowercaseCharacterBounds() {
        char[] letters = {'a', 'm', 'z'};
        assertNext(letters, 'a');
        assertNext(letters, 'y');
        assertNext(letters, 'z');
    }

    @Test
    public void testJavaCharacterBoundsBeyondLeetCodeAlphabet() {
        char[] letters = {'\u0000', '\u0000', '\u0001', '\u7fff', '\uffff'};
        assertNext(letters, '\u0000');
        assertNext(letters, '\u0001');
        assertNext(letters, '\u7fff');
        assertNext(letters, '\uffff');
    }

    @Test
    public void testLongDuplicateRunsAtEveryBoundary() {
        char[] letters = new char[2_001];
        Arrays.fill(letters, 0, 700, 'b');
        Arrays.fill(letters, 700, 1_400, 'm');
        Arrays.fill(letters, 1_400, letters.length, 'y');
        assertNext(letters, 'a');
        assertNext(letters, 'b');
        assertNext(letters, 'm');
        assertNext(letters, 'x');
        assertNext(letters, 'y');
        assertNext(letters, 'z');
    }

    @Test
    public void testMaximumOfficialLengthWithThreeRuns() {
        char[] letters = new char[10_000];
        Arrays.fill(letters, 0, 3_333, 'a');
        Arrays.fill(letters, 3_333, 6_666, 'm');
        Arrays.fill(letters, 6_666, letters.length, 'z');
        assertNext(letters, 'a');
        assertNext(letters, 'l');
        assertNext(letters, 'm');
        assertNext(letters, 'y');
        assertNext(letters, 'z');
    }

    @Test
    public void testInputIsNotMutatedAndRepeatedCallsAreIndependent() {
        char[] first = {'c', 'f', 'j'};
        char[] original = first.clone();
        assertEquals('f', test.nextGreatestLetter(first, 'c'));
        assertArrayEquals(original, first);
        assertEquals('c', test.nextGreatestLetter(first, 'j'));
        assertArrayEquals(original, first);

        char[] second = {'a', 'd', 'q'};
        assertEquals('q', test.nextGreatestLetter(second, 'm'));
        assertEquals('c', test.nextGreatestLetter(first, 'z'));
    }

    @Test
    public void testExhaustiveSmallSortedArraysAgainstLinearOracle() {
        char[] values = {'a', 'f', 'm', 'z'};
        for (int length = 2; length <= 7; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) {
                combinations *= values.length;
            }
            for (int encoded = 0; encoded < combinations; encoded++) {
                char[] letters = new char[length];
                int quotient = encoded;
                for (int i = 0; i < length; i++) {
                    letters[i] = values[quotient % values.length];
                    quotient /= values.length;
                }
                if (!isSorted(letters) || letters[0] == letters[length - 1]) {
                    continue;
                }
                for (char target = 'a'; target <= 'z'; target++) {
                    assertNext(letters, target);
                }
            }
        }
    }

    @Test
    public void testSeededSortedArraysAgainstLinearOracle() {
        Random random = new Random(744_2026L);
        for (int trial = 0; trial < 250; trial++) {
            int length = 2 + random.nextInt(199);
            char[] letters = new char[length];
            for (int i = 0; i < letters.length; i++) {
                letters[i] = (char) ('a' + random.nextInt(26));
            }
            Arrays.sort(letters);
            if (letters[0] == letters[letters.length - 1]) {
                letters[0] = 'a';
                letters[letters.length - 1] = 'z';
                Arrays.sort(letters);
            }
            for (char target = 'a'; target <= 'z'; target++) {
                assertNext(letters, target);
            }
        }
    }

    private void assertNext(char[] letters, char target) {
        char[] original = letters.clone();
        assertEquals(linearOracle(letters, target), test.nextGreatestLetter(letters, target));
        assertArrayEquals(original, letters);
    }

    private static char linearOracle(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }
        return letters[0];
    }

    private static boolean isSorted(char[] letters) {
        for (int i = 1; i < letters.length; i++) {
            if (letters[i - 1] > letters[i]) {
                return false;
            }
        }
        return true;
    }
}
