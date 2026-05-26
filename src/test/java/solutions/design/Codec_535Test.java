package solutions.design;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 16:05
 * Created with IntelliJ IDEA
 */

public class Codec_535Test {
    private final Codec_535 test = new Codec_535();

    @Test
    public void test() {
        generateTestUrl().forEach(
                url -> assertEquals(url, test.decode(test.encode(url)))
        );
    }

    @Test
    public void testEncode() {
        generateTestUrl().forEach(
                url -> assertNotEquals(url, test.encode(url))
        );
    }

    @Test
    public void testEncodeIsIdempotentForSameLongUrl() {
        String url = "https://example.com/path?a=1&b=2";
        String tiny1 = test.encode(url);
        String tiny2 = test.encode(url);
        assertEquals(tiny1, tiny2);
    }

    @Test
    public void testDecodeUnknownTinyUrlReturnsNull() {
        assertNull(test.decode("https://tinyurl.com/unknown"));
    }

    @Test
    public void testEmptyStringRoundTrip() {
        String tiny = test.encode("");
        assertEquals("", test.decode(tiny));
    }

    @Test
    public void testSingleCharacterRoundTrip() {
        String tiny = test.encode("x");
        assertEquals("x", test.decode(tiny));
    }

    @Test
    public void testLongUrlWithQueryAndFragmentRoundTrip() {
        String url = "https://domain.com/a/b/c?name=alice&lang=en#section-3";
        String tiny = test.encode(url);
        assertEquals(url, test.decode(tiny));
    }

    @Test
    public void testUnicodeLikeSymbolsRoundTrip() {
        String url = "https://domain.com/path/%E4%BD%A0%E5%A5%BD?q=%23%26";
        String tiny = test.encode(url);
        assertEquals(url, test.decode(tiny));
    }

    @Test
    public void testMultipleEncodesRemainDecodable() {
        String a = "https://a.example.com/1";
        String b = "https://b.example.com/2";
        String ta = test.encode(a);
        String tb = test.encode(b);

        assertEquals(a, test.decode(ta));
        assertEquals(b, test.decode(tb));
    }

    @Test
    public void testGiantRoundTripBatch() {
        for (int i = 0; i < 2000; i++) {
            String url = "https://bulk.example.com/resource/" + i + "?q=" + (i * 7);
            String tiny = test.encode(url);
            assertEquals(url, test.decode(tiny));
        }
    }

    private List<String> generateTestUrl() {
        return Lists.newArrayList(
                "test.com",
                "a.com",
                "a",
                "b",
                "google.com",
                "leetcode.com/problems/design-tinyurl"
        );
    }
}
