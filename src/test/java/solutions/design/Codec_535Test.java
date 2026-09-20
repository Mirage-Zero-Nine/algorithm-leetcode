package solutions.design;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testEncodedUrlUsesTinyUrlPrefix() {
        String encoded = test.encode("https://example.com/prefix");

        assertTrue(encoded.startsWith("https://tinyurl.com/"));
        assertTrue(encoded.length() > "https://tinyurl.com/".length());
    }

    @Test
    public void testReservedCharactersRoundTripUnchanged() {
        String url = "https://example.com/a%2Fb;c,d?x=1%202&y=%26%3D#frag%2Fpart";

        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testRawUnicodeUrlRoundTrip() {
        String url = "https://例え.テスト/道/猫?q=край&emoji=☃️";

        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testMaximumContractUrlLengthRoundTrip() {
        String prefix = "https://example.com/";
        String url = prefix + "a".repeat(10_000 - prefix.length());

        assertEquals(10_000, url.length());
        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testDistinctUrlsWithSameJavaHashCodeRemainIndependent() {
        String first = "Aa";
        String second = "BB";

        assertEquals(first.hashCode(), second.hashCode());
        String firstTiny = test.encode(first);
        String secondTiny = test.encode(second);

        assertNotEquals(firstTiny, secondTiny);
        assertEquals(first, test.decode(firstTiny));
        assertEquals(second, test.decode(secondTiny));
    }

    @Test
    public void testDistinctUrlsReceiveDistinctTinyUrls() {
        Set<String> tinyUrls = new HashSet<>();

        for (int i = 0; i < 500; i++) {
            String url = "https://unique.example.com/resource/" + i;
            String tiny = test.encode(url);

            assertTrue(tinyUrls.add(tiny));
            assertEquals(url, test.decode(tiny));
        }

        assertEquals(500, tinyUrls.size());
    }

    @Test
    public void testPreviouslyEncodedUrlRemainsDecodableAfterLaterEncodes() {
        String original = "https://example.com/first";
        String tiny = test.encode(original);

        for (int i = 0; i < 50; i++) {
            test.encode("https://example.com/later/" + i);
        }

        assertEquals(original, test.decode(tiny));
    }

    @Test
    public void testRepeatedDecodeReturnsSameOriginalUrl() {
        String url = "https://example.com/repeated-decode";
        String tiny = test.encode(url);

        assertEquals(url, test.decode(tiny));
        assertEquals(url, test.decode(tiny));
        assertEquals(url, test.decode(tiny));
    }

    @Test
    public void testUnknownDecodeDoesNotAffectSubsequentEncoding() {
        assertNull(test.decode("https://tinyurl.com/not-created-by-this-instance"));

        String url = "https://example.com/after-unknown";
        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testSeparateInstancesHaveIndependentState() {
        Codec_535 first = new Codec_535();
        Codec_535 second = new Codec_535();
        String firstUrl = "https://first.example.com/page";
        String secondUrl = "https://second.example.com/page";
        String firstTiny = first.encode(firstUrl);

        assertEquals(firstUrl, first.decode(firstTiny));
        assertNull(second.decode(firstTiny));

        String secondTiny = second.encode(secondUrl);

        assertEquals(secondUrl, second.decode(secondTiny));
    }

    @Test
    public void testUrlsDifferingOnlyByCaseRemainDistinct() {
        String lower = "https://example.com/path";
        String upper = "https://example.com/PATH";
        String lowerTiny = test.encode(lower);
        String upperTiny = test.encode(upper);

        assertNotEquals(lowerTiny, upperTiny);
        assertEquals(lower, test.decode(lowerTiny));
        assertEquals(upper, test.decode(upperTiny));
    }

    @Test
    public void testEmptyQueryAndFragmentRoundTrip() {
        List<String> urls = List.of(
                "https://example.com/path?",
                "https://example.com/path#",
                "https://example.com/path?#"
        );

        for (String url : urls) {
            assertEquals(url, test.decode(test.encode(url)));
        }
    }

    @Test
    public void testVeryLongQueryRoundTrip() {
        String prefix = "https://example.com/search?q=";
        String url = prefix + "term+" + "value%20".repeat((10_000 - prefix.length() - 5) / 8);

        assertTrue(url.length() <= 10_000);
        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testDuplicateEncodesDoNotCreateAdditionalMapping() {
        String url = "https://example.com/duplicate";
        String firstTiny = test.encode(url);

        for (int i = 0; i < 100; i++) {
            assertEquals(firstTiny, test.encode(url));
        }

        assertEquals(url, test.decode(firstTiny));
    }

    @Test
    public void testUrlsWithPortsCredentialsAndPunctuationRoundTrip() {
        String url = "https://user:pass@example.com:8443/a~b!$&'()*+,;=/?a=1&b=two";

        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testWhitespaceCharactersArePreservedByMapping() {
        String url = "https://example.com/path%20with%20spaces?q=a+b%09c";

        assertEquals(url, test.decode(test.encode(url)));
    }

    @Test
    public void testZeroLengthAndNonEmptyUrlsHaveIndependentMappings() {
        String emptyTiny = test.encode("");
        String nonEmpty = "https://example.com/non-empty";
        String nonEmptyTiny = test.encode(nonEmpty);

        assertNotEquals(emptyTiny, nonEmptyTiny);
        assertEquals("", test.decode(emptyTiny));
        assertEquals(nonEmpty, test.decode(nonEmptyTiny));
    }

    @Test
    public void testInterleavedUrlsRemainMappedToTheirOwnValues() {
        String first = "https://example.com/interleave/first";
        String second = "https://example.com/interleave/second";
        String firstTiny = test.encode(first);
        String secondTiny = test.encode(second);

        assertEquals(first, test.decode(firstTiny));
        assertEquals(second, test.decode(secondTiny));
        assertEquals(first, test.decode(test.encode(first)));
        assertEquals(second, test.decode(test.encode(second)));
    }

    @Test
    public void testLongUrlMappingIsStoredAsAnExactString() {
        String url = "https://example.com/" + "segment/".repeat(1000) + "end";
        String tiny = test.encode(url);

        assertNotNull(tiny);
        assertEquals(url, test.decode(tiny));
    }

    @Test
    public void testSpecialNumericAndBooleanLookingUrlsRemainStrings() {
        List<String> urls = List.of(
                "0",
                "-1",
                "true",
                "null",
                "https://example.com/0"
        );

        for (String url : urls) {
            assertEquals(url, test.decode(test.encode(url)));
        }
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
        return List.of(
                "test.com",
                "a.com",
                "a",
                "b",
                "google.com",
                "leetcode.com/problems/design-tinyurl"
        );
    }
}
