package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReorderLogFiles937Test {

    private final ReorderLogFiles_937 test = new ReorderLogFiles_937();

    @Test
    public void testHappyCases() {
        assertArrayEquals(
            new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"},
            test.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})
        );
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new String[]{"a1 9 2 3 1"}, test.reorderLogFiles(new String[]{"a1 9 2 3 1"}));
    }

    @Test
    public void testLargeCase() {
        String[] result = test.reorderLogFiles(new String[]{"a1 1 2", "b1 abc", "c1 def", "d1 3 4"});
        // letter logs come before digit logs
        assertEquals("b1 abc", result[0]);
    }
}
