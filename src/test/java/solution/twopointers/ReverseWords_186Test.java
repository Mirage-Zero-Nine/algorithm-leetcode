package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ReverseWords_186Test {

    private final ReverseWords_186 test = new ReverseWords_186();

    @Test
    public void testHappyCases() {
        char[] arr = "the sky is blue".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("blue is sky the".toCharArray(), arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        char[] arr = "a".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("a".toCharArray(), arr);
    }

    @Test
    public void testLargeCase() {
        char[] arr = "hello world foo bar".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("bar foo world hello".toCharArray(), arr);
    }

    @Test
    public void testAdditionalHappyCases() {
        char[] arr1 = "one two".toCharArray();
        test.reverseWords(arr1);
        assertArrayEquals("two one".toCharArray(), arr1);

        char[] arr2 = "aa bb cc".toCharArray();
        test.reverseWords(arr2);
        assertArrayEquals("cc bb aa".toCharArray(), arr2);
    }

    @Test
    public void testAdditionalEdgeCases() {
        char[] empty = new char[]{};
        test.reverseWords(empty);
        assertArrayEquals(new char[]{}, empty);

        char[] twoWords = "ab cd".toCharArray();
        test.reverseWords(twoWords);
        assertArrayEquals("cd ab".toCharArray(), twoWords);

        char[] palindromeWords = "level civic".toCharArray();
        test.reverseWords(palindromeWords);
        assertArrayEquals("civic level".toCharArray(), palindromeWords);
    }

    @Test
    public void testAdditionalGiantCase() {
        char[] arr = ("one two three four five six seven eight nine ten "
            + "alpha beta gamma delta epsilon zeta eta theta").toCharArray();
        test.reverseWords(arr);
        assertArrayEquals(("theta eta zeta epsilon delta gamma beta alpha "
            + "ten nine eight seven six five four three two one").toCharArray(), arr);
    }

    @Test
    public void testNullInput() {
        test.reverseWords(null); // should not throw
    }

    @Test
    public void testSingleChar() {
        char[] arr = {'x'};
        test.reverseWords(arr);
        assertArrayEquals(new char[]{'x'}, arr);
    }

    @Test
    public void testThreeWords() {
        char[] arr = "I am here".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("here am I".toCharArray(), arr);
    }

    @Test
    public void testSameWords() {
        char[] arr = "go go go".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("go go go".toCharArray(), arr);
    }
}
