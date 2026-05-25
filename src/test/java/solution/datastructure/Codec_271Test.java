package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Codec_271Test {

    private final Codec_271 test = new Codec_271();

    @Test
    public void testHappyCases() {
        List<String> input = List.of("hello", "world");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(""), test.decode(test.encode(List.of(""))));
        assertEquals(List.of("a"), test.decode(test.encode(List.of("a"))));
    }

    @Test
    public void testLargeCase() {
        List<String> input = List.of("abc", "def", "ghi", "jkl", "mno");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testEmptyListRoundTrip() {
        assertEquals(List.of(), test.decode(test.encode(List.of())));
    }

    @Test
    public void testSlashCharactersInStrings() {
        List<String> input = List.of("/", "//", "a/b/c", "end/");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testDigitPrefixesInsideStrings() {
        List<String> input = List.of("123", "9lives", "0/0/0", "42answer");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testMixedEmptyAndNonEmptyStrings() {
        List<String> input = List.of("", "x", "", "yz", "");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testDecodeRawEmptyString() {
        assertEquals(List.of(), test.decode(""));
    }

    @Test
    public void testMalformedEncodedStringThrows() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> test.decode("/5/ab"));
    }

    @Test
    public void testGiantRoundTripCase() {
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            input.add("v" + i + "_".repeat(5) + "x".repeat(20));
        }
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testEmptyListEncodeDecode() {
        List<String> input = List.of();
        String encoded = test.encode(input);
        assertEquals(input, test.decode(encoded));
    }

    @Test
    public void testSingleEmptyString() {
        List<String> input = List.of("");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testMultipleEmptyStrings() {
        List<String> input = List.of("", "", "", "", "");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testStringsContainingDelimiterPattern() {
        // The impl uses /LENGTH/ as delimiter pattern — test strings that mimic it
        List<String> input = List.of("/5/hello", "/0/", "///", "/100/abc", "a/3/bcd");
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testStringsWithNewlinesAndSpecialCharsAndUnicode() {
        List<String> input = List.of(
                "line1\nline2\nline3",
                "tab\there",
                "\r\n\r\n",
                "émojis: \uD83D\uDE00\uD83D\uDE80\uD83C\uDF1F",
                "中文测试",
                "null\0byte",
                "backslash\\escape"
        );
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testLongStrings() {
        String longA = "a".repeat(10000);
        String longMixed = "/3/".repeat(3333) + "/";
        List<String> input = List.of(longA, longMixed, "x".repeat(10000));
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testLargeListRandomSeed42() {
        Random rng = new Random(42L);
        List<String> input = new ArrayList<>();
        String chars = "abcdefghijklmnopqrstuvwxyz/0123456789\n\t ";
        for (int i = 0; i < 1000; i++) {
            int len = rng.nextInt(50);
            StringBuilder sb = new StringBuilder(len);
            for (int j = 0; j < len; j++) {
                sb.append(chars.charAt(rng.nextInt(chars.length())));
            }
            input.add(sb.toString());
        }
        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testPropertyRoundTrip() {
        // Arbitrary lists should always roundtrip
        List<List<String>> cases = List.of(
                List.of("a", "b", "c"),
                List.of("", "nonempty", ""),
                List.of("only"),
                List.of("/", "//", "/0/", "/10/abcdefghij")
        );
        for (List<String> input : cases) {
            assertEquals(input, test.decode(test.encode(input)), "Failed for: " + input);
        }
    }

    @Test
    public void testSingleElementWithDelimiterLikeContent() {
        // Single-element list where the content looks exactly like encoded data
        List<String> input = List.of("/5/hello/5/world");
        assertEquals(input, test.decode(test.encode(input)));
    }
}
