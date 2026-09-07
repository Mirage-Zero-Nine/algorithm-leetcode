package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
