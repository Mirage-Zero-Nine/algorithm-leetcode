package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountCharacters_1160Test {

    private final CountCharacters_1160 test = new CountCharacters_1160();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        assertEquals(10, test.countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countCharacters(new String[]{}, "abc"));
        assertEquals(0, test.countCharacters(new String[]{"abc"}, "xy"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.countCharacters(new String[]{"abc", "def", "ghi"}, "abcdefghi"));
    }

    @Test
    public void testSingleCharWords() {
        assertEquals(3, test.countCharacters(new String[]{"a", "b", "c"}, "abc"));
    }

    @Test
    public void testDuplicateCharsNeeded() {
        // "aa" needs two 'a's but chars only has one
        assertEquals(0, test.countCharacters(new String[]{"aa"}, "a"));
    }

    @Test
    public void testDuplicateCharsAvailable() {
        assertEquals(2, test.countCharacters(new String[]{"aa"}, "aa"));
    }

    @Test
    public void testAllWordsGood() {
        assertEquals(6, test.countCharacters(new String[]{"ab", "cd", "ef"}, "abcdef"));
    }

    @Test
    public void testNoWordsGood() {
        assertEquals(0, test.countCharacters(new String[]{"xyz", "uvw"}, "abc"));
    }

    @Test
    public void testEmptyChars() {
        assertEquals(0, test.countCharacters(new String[]{"a", "b"}, ""));
    }

    @Test
    public void testGiantCase() {
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) {
            words[i] = "a";
        }
        // Each word "a" can be formed from chars "a"
        assertEquals(1000, test.countCharacters(words, "a"));
    }
}
