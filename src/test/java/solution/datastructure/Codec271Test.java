package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Codec271Test {

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
}
