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
}
