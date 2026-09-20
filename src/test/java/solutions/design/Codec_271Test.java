package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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

    @Test
    public void testEncodeUsesIndependentLengthPrefixedFormat() {
        List<String> input = List.of("hello", "", "/5/world", "42");

        assertEquals("/5/hello/0//8//5/world/2/42", test.encode(input));
    }

    @Test
    public void testDecodeKnownPayloadWithoutEncodingFirst() {
        assertEquals(
                List.of("", "a", "ninechars", "digits/inside"),
                test.decode("/0//1/a/9/ninechars/13/digits/inside"));
    }

    @Test
    public void testLengthPrefixesAcrossDecimalBoundaries() {
        String nine = "b".repeat(9);
        String ten = "c".repeat(10);
        String ninetyNine = "d".repeat(99);
        String oneHundred = "e".repeat(100);
        List<String> input = List.of("", "a", nine, ten, ninetyNine, oneHundred);
        String expected = "/0//1/a/9/" + nine + "/10/" + ten
                + "/99/" + ninetyNine + "/100/" + oneHundred;

        assertEquals(expected, test.encode(input));
        assertEquals(input, test.decode(expected));
    }

    @Test
    public void testEveryByteValueCanAppearInAString() {
        StringBuilder allByteValues = new StringBuilder(256);
        for (int value = 0; value < 256; value++) {
            allByteValues.append((char) value);
        }
        String valueString = allByteValues.toString();

        assertEquals("/256/" + valueString, test.encode(List.of(valueString)));
        assertEquals(List.of(valueString), test.decode("/256/" + valueString));
    }

    @Test
    public void testDuplicateValuesAndOrderingArePreserved() {
        List<String> input = List.of("same", "same", "different", "same", "");

        assertEquals(input, test.decode(test.encode(input)));
    }

    @Test
    public void testEncodeDoesNotMutateCallerList() {
        List<String> input = new ArrayList<>(List.of("first", "", "/second/"));
        List<String> before = new ArrayList<>(input);

        test.encode(input);

        assertEquals(before, input);
    }

    @Test
    public void testDecodeReturnsFreshMutableLists() {
        String encoded = "/3/one/3/two";
        List<String> first = test.decode(encoded);
        List<String> second = test.decode(encoded);

        assertNotSame(first, second);
        first.set(0, "changed");
        assertEquals(List.of("one", "two"), second);
    }

    @Test
    public void testCodecCallsRemainStatelessAcrossInterleavedInputs() {
        List<String> first = List.of("first", "/0/", "");
        List<String> second = List.of("second", "1/2/3");
        String firstEncoded = test.encode(first);
        String secondEncoded = test.encode(second);

        assertEquals(first, test.decode(firstEncoded));
        assertEquals(second, test.decode(secondEncoded));
        assertEquals(first, test.decode(firstEncoded));
    }

    @Test
    public void testSupplementaryCharactersUseJavaStringLength() {
        String emoji = "😀🚀🌟";
        // The implementation's contract is Java strings, so each supplementary
        // code point occupies two UTF-16 chars and contributes two to length().
        assertEquals("/6/" + emoji, test.encode(List.of(emoji)));
        assertEquals(List.of(emoji), test.decode("/6/" + emoji));
    }
}
