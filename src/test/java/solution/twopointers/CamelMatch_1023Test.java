package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CamelMatch_1023Test {

    private final CamelMatch_1023 test = new CamelMatch_1023();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(true, false, true, true),
            test.camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer"}, "FB"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.camelMatch(new String[]{}, "FB"));
        assertEquals(List.of(true), test.camelMatch(new String[]{"FooBar"}, "FooBar"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(true, false),
            test.camelMatch(new String[]{"FooBar", "FooBarTest"}, "FooBar"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(List.of(true, true, true),
            test.camelMatch(new String[]{"CompetitiveProgramming", "CounterPick", "ControlPanel"}, "CP"));
        assertEquals(List.of(true, false, true),
            test.camelMatch(new String[]{"InternetExplorer", "InternalErrorLog", "IronEagle"}, "IE"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(List.of(false), test.camelMatch(new String[]{"FooBar"}, ""));
        assertEquals(List.of(true, false),
            test.camelMatch(new String[]{"lowercase", "Lowercase"}, "l"));
        assertEquals(List.of(false, false),
            test.camelMatch(new String[]{"SimpleTest", "sampleText"}, "STT"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(
            List.of(true, false, false, false),
            test.camelMatch(
                new String[]{"AlphaBetaGammaDelta", "ABCD", "AlphaBetaGammaDeltaX", "alphaBetaGammaDelta"},
                "ABGD")
        );
    }

    @Test
    public void testAllLowercase() {
        assertEquals(List.of(true), test.camelMatch(new String[]{"abc"}, "a"));
        assertEquals(List.of(true), test.camelMatch(new String[]{"abc"}, "abc"));
    }

    @Test
    public void testPatternLongerThanQuery() {
        assertEquals(List.of(false), test.camelMatch(new String[]{"Fo"}, "FooBar"));
    }

    @Test
    public void testSingleCharQueries() {
        assertEquals(List.of(true, false), test.camelMatch(new String[]{"a", "A"}, "a"));
    }

    @Test
    public void testGiantManyQueries() {
        String[] queries = new String[50];
        for (int i = 0; i < 50; i++) {
            queries[i] = "FooBar";
        }
        List<Boolean> result = test.camelMatch(queries, "FB");
        assertEquals(50, result.size());
        assertEquals(true, result.get(0));
        assertEquals(true, result.get(49));
    }
}
