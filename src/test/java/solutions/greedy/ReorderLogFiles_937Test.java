package solutions.greedy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    // ===== NEW TESTS =====

    @Test
    public void testEmptyArray() {
        assertArrayEquals(new String[]{}, test.reorderLogFiles(new String[]{}));
    }

    @Test
    public void testSingleLetterLog() {
        assertArrayEquals(new String[]{"a1 abc def"}, test.reorderLogFiles(new String[]{"a1 abc def"}));
    }

    @Test
    public void testSingleDigitLog() {
        assertArrayEquals(new String[]{"a1 1 2 3"}, test.reorderLogFiles(new String[]{"a1 1 2 3"}));
    }

    @Test
    public void testAllLetterLogsSortedByContentThenId() {
        assertArrayEquals(
            new String[]{"b1 alpha", "a1 beta", "c1 gamma"},
            test.reorderLogFiles(new String[]{"c1 gamma", "a1 beta", "b1 alpha"})
        );
    }

    @Test
    public void testAllDigitLogsStableOrder() {
        assertArrayEquals(
            new String[]{"z1 9 9 9", "a1 1 1 1", "m1 5 5 5"},
            test.reorderLogFiles(new String[]{"z1 9 9 9", "a1 1 1 1", "m1 5 5 5"})
        );
    }

    @Test
    public void testDigitLogsPreserveRelativeOrderAtEnd() {
        assertArrayEquals(
            new String[]{"let1 abc", "let2 def", "dig3 1 2", "dig1 9 8", "dig2 3 4"},
            test.reorderLogFiles(new String[]{"dig3 1 2", "let2 def", "dig1 9 8", "let1 abc", "dig2 3 4"})
        );
    }

    @Test
    public void testLetterLogsSameContentSortById() {
        assertArrayEquals(
            new String[]{"a1 same content here", "b2 same content here", "z9 same content here"},
            test.reorderLogFiles(new String[]{"z9 same content here", "b2 same content here", "a1 same content here"})
        );
    }

    @Test
    public void testLeetCodeExample() {
        assertArrayEquals(
            new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"},
            test.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})
        );
    }

    @Test
    public void testLongContentMultipleWords() {
        assertArrayEquals(
            new String[]{"x1 alpha beta gamma delta epsilon", "y1 zeta eta theta iota kappa", "z1 1 2 3 4 5 6 7 8 9"},
            test.reorderLogFiles(new String[]{"z1 1 2 3 4 5 6 7 8 9", "y1 zeta eta theta iota kappa", "x1 alpha beta gamma delta epsilon"})
        );
    }

    @Test
    public void testPropertyLetterLogsBeforeDigitLogs() {
        String[] logs = {"d1 3 4", "l1 abc", "d2 5 6", "l2 xyz", "d3 7 8", "l3 mno"};
        String[] result = test.reorderLogFiles(logs);
        boolean seenDigit = false;
        for (String log : result) {
            String content = log.split(" ", 2)[1];
            boolean isDigit = Character.isDigit(content.charAt(0));
            if (isDigit) seenDigit = true;
            if (seenDigit) assertTrue(isDigit, "Letter-log found after digit-log: " + log);
        }
    }

    @Test
    public void testPropertyDigitLogsMaintainRelativeOrder() {
        String[] logs = {"d3 7 8", "l1 abc", "d1 3 4", "l2 xyz", "d2 5 6"};
        String[] result = test.reorderLogFiles(logs);
        // Extract digit logs from result and verify they match original relative order
        java.util.List<String> digitLogsResult = new java.util.ArrayList<>();
        for (String log : result) {
            if (Character.isDigit(log.split(" ", 2)[1].charAt(0))) {
                digitLogsResult.add(log);
            }
        }
        assertEquals(java.util.List.of("d3 7 8", "d1 3 4", "d2 5 6"), digitLogsResult);
    }
}
