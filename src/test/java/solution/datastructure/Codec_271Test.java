package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
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
}
