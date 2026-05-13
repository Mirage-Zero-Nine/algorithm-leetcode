package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReorderLogFiles_937Test {

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

    @Test
    public void testDigitOrderAndIdentifierTies() {
        assertArrayEquals(
            new String[]{"let1 same text", "let2 same text", "dig1 3 4", "dig2 1 2"},
            test.reorderLogFiles(new String[]{"dig1 3 4", "let2 same text", "dig2 1 2", "let1 same text"})
        );
        assertArrayEquals(
            new String[]{"l1 act car", "l2 act zoo", "d1 4 5"},
            test.reorderLogFiles(new String[]{"d1 4 5", "l2 act zoo", "l1 act car"})
        );
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertArrayEquals(new String[]{"let1 art"},
            test.reorderLogFiles(new String[]{"let1 art"}));
        assertArrayEquals(new String[]{"dig1 8 2", "dig2 3 6"},
            test.reorderLogFiles(new String[]{"dig1 8 2", "dig2 3 6"}));
        assertArrayEquals(new String[]{"a1 aa", "b1 ab", "c1 9 2"},
            test.reorderLogFiles(new String[]{"b1 ab", "c1 9 2", "a1 aa"}));
    }

    @Test
    public void testGiantCase() {
        String[] logs = new String[]{
            "dig1 8 1 5 1", "let4 zebra apple", "let2 art zero", "dig2 3 6", "let1 art can",
            "let3 art can", "dig3 2 2", "let5 own kit dig", "dig4 7 7", "let6 alpha beta"
        };
        assertArrayEquals(
            new String[]{
                "let6 alpha beta", "let1 art can", "let3 art can", "let2 art zero",
                "let5 own kit dig", "let4 zebra apple", "dig1 8 1 5 1", "dig2 3 6", "dig3 2 2", "dig4 7 7"
            },
            test.reorderLogFiles(logs)
        );
    }

    @Test
    public void testAllLetterLogs() {
        assertArrayEquals(
            new String[]{"a abc", "b bcd", "c cde"},
            test.reorderLogFiles(new String[]{"c cde", "a abc", "b bcd"})
        );
    }

    @Test
    public void testAllDigitLogs() {
        // Digit logs preserve original order
        assertArrayEquals(
            new String[]{"a 1 2", "b 3 4", "c 5 6"},
            test.reorderLogFiles(new String[]{"a 1 2", "b 3 4", "c 5 6"})
        );
    }

    @Test
    public void testIdentifierTieBreaking() {
        // Same content, sorted by identifier
        assertArrayEquals(
            new String[]{"a1 hello", "b1 hello", "z1 hello"},
            test.reorderLogFiles(new String[]{"z1 hello", "a1 hello", "b1 hello"})
        );
    }

    @Test
    public void testMixedWithSingleWord() {
        assertArrayEquals(
            new String[]{"id1 a", "id2 b", "id3 1"},
            test.reorderLogFiles(new String[]{"id3 1", "id1 a", "id2 b"})
        );
    }
}
