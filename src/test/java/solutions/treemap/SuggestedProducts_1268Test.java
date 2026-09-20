package solutions.treemap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SuggestedProducts_1268Test {

    private final SuggestedProducts_1268 test = new SuggestedProducts_1268();

    @Test
    public void testHappyCases() {
        assertEquals(
            List.of(
                List.of("mobile", "moneypot", "monitor"),
                List.of("mobile", "moneypot", "monitor"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad")
            ),
            test.suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse")
        );

        assertEquals(
            List.of(List.of("baggage", "bags", "banner"), List.of("baggage", "bags", "banner"),
                List.of("baggage", "bags"), List.of("bags")),
            test.suggestedProducts(new String[]{"bags", "baggage", "banner", "box", "cloths"}, "bags")
        );
    }

    @Test
    public void testNegativeCase() {
        assertEquals(List.of(List.of(), List.of()), test.suggestedProducts(new String[]{"havana"}, "zz"));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertEquals(List.of(), test.suggestedProducts(new String[]{}, ""));
        assertEquals(List.of(List.of("a")), test.suggestedProducts(new String[]{"a"}, "a"));
    }

    @Test
    public void testLargeCase() {
        String[] products = {
            "car", "carbon", "card", "care", "career", "cargo", "carpet", "carpool", "carry", "cart"
        };
        assertEquals(
            List.of(
                List.of("car", "carbon", "card"),
                List.of("car", "carbon", "card"),
                List.of("car", "carbon", "card"),
                List.of("care", "career")
            ),
            test.suggestedProducts(products, "care")
        );
    }

    @Test
    public void testHappySingleMatch() {
        assertEquals(
            List.of(List.of("apple"), List.of("apple"), List.of("apple"), List.of("apple"), List.of("apple")),
            test.suggestedProducts(new String[]{"apple", "banana", "cherry"}, "apple")
        );
    }

    @Test
    public void testHappyAllMatch() {
        assertEquals(
            List.of(List.of("aa", "ab", "ac")),
            test.suggestedProducts(new String[]{"aa", "ab", "ac", "ad"}, "a")
        );
    }

    @Test
    public void testNegativeNoMatchFromStart() {
        assertEquals(
            List.of(List.of(), List.of(), List.of()),
            test.suggestedProducts(new String[]{"apple", "banana"}, "xyz")
        );
    }

    @Test
    public void testEdgeSingleProduct() {
        assertEquals(
            List.of(List.of("hello"), List.of("hello"), List.of("hello"), List.of("hello"), List.of("hello")),
            test.suggestedProducts(new String[]{"hello"}, "hello")
        );
    }

    @Test
    public void testEdgePartialMatchThenNone() {
        assertEquals(
            List.of(List.of("abc"), List.of("abc"), List.of()),
            test.suggestedProducts(new String[]{"abc", "bcd"}, "abz")
        );
    }

    @Test
    public void testGiantCase() {
        String[] products = new String[100];
        for (int i = 0; i < 100; i++) {
            products[i] = "prod" + String.format("%03d", i);
        }
        List<List<String>> result = test.suggestedProducts(products, "prod");
        assertEquals(4, result.size());
        for (List<String> r : result) {
            assertEquals(3, r.size());
        }
    }

    @Test
    public void testThreeLimitAndLexicographicOrderAtEveryPrefix() {
        assertEquals(List.of(List.of("aa", "aab", "aac"), List.of("aa", "aab", "aac")),
            test.suggestedProducts(new String[]{"aac", "aad", "aa", "aab"}, "aa"));
    }

    @Test
    public void testNoMatchAfterSeveralMatchingPrefixes() {
        assertEquals(List.of(List.of("abcd", "abef"), List.of("abcd", "abef"), List.of("abcd"), List.of()),
            test.suggestedProducts(new String[]{"abcd", "abef"}, "abcz"));
    }

    @Test
    public void testProductsAlreadySorted() {
        assertEquals(List.of(List.of("alpha", "alpine"), List.of("alpha", "alpine")),
            test.suggestedProducts(new String[]{"alpha", "alpine", "beta"}, "al"));
    }

    @Test
    public void testProductsReverseSorted() {
        assertEquals(List.of(List.of("aa", "ab", "ac")),
            test.suggestedProducts(new String[]{"ac", "ab", "aa"}, "a"));
    }

    @Test
    public void testSearchWordLongerThanAnyProduct() {
        assertEquals(List.of(List.of("a"), List.of()),
            test.suggestedProducts(new String[]{"a", "b"}, "ab"));
    }

    @Test
    public void testPrefixBoundaryDoesNotIncludeNeighborWords() {
        assertEquals(List.of(
                List.of("app", "apple", "apply"), List.of("app", "apple", "apply"),
                List.of("app", "apple", "apply"), List.of("apple", "apply")),
            test.suggestedProducts(new String[]{"app", "apple", "apply", "banana"}, "appl"));
    }

    @Test
    public void testMaximumSearchWordLengthShape() {
        String[] products = {"a", "aa", "aaa"};
        List<List<String>> result = test.suggestedProducts(products, "a".repeat(10));
        assertEquals(10, result.size());
        assertEquals(List.of("a", "aa", "aaa"), result.get(0));
        assertEquals(List.of("aa", "aaa"), result.get(1));
        assertEquals(List.of("aaa"), result.get(2));
        for (int i = 3; i < result.size(); i++) assertEquals(List.of(), result.get(i));
    }

    @Test
    public void testExactlyThreeMatchingProducts() {
        assertEquals(List.of(List.of("cat", "cater", "cattle")),
            test.suggestedProducts(new String[]{"cattle", "cat", "cater"}, "c"));
    }

    @Test
    public void testNoMatchAtFinalCharacter() {
        assertEquals(List.of(List.of("dog", "dot"), List.of("dog", "dot"), List.of("dog"), List.of()),
            test.suggestedProducts(new String[]{"dog", "dot"}, "dogs"));
    }

    @Test
    public void testSearchWordEmptyWithProducts() {
        assertEquals(List.of(), test.suggestedProducts(new String[]{"a", "b"}, ""));
    }
}
