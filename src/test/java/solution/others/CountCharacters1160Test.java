package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountCharacters1160Test {

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
}
