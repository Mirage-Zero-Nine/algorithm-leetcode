package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Expand_1087Test {
    private final Expand_1087 solution = new Expand_1087();

    @Test
    void testBasic() {
        String[] result = solution.expand("{a,b}c{d,e}f");
        assertEquals(4, result.length);
    }

    @Test
    void testSingleOption() {
        String[] result = solution.expand("abcd");
        assertEquals(1, result.length);
    }

    @Test
    void testMultipleOptions() {
        String[] result = solution.expand("{a,b}{c,d}");
        assertEquals(4, result.length);
    }

    @Test
    void testThreeOptions() {
        String[] result = solution.expand("{a,b,c}");
        assertEquals(3, result.length);
    }

    @Test
    void testComplex() {
        String[] result = solution.expand("a{b,c}{d,e}f");
        assertEquals(4, result.length);
    }

    @Test
    void testLexicographicOrder() {
        String[] result = solution.expand("{b,a}c");
        assertEquals("ac", result[0]);
        assertEquals("bc", result[1]);
    }

    @Test
    void testSingleChar() {
        String[] result = solution.expand("a");
        assertEquals(1, result.length);
        assertEquals("a", result[0]);
    }

    @Test
    void testAllBraces() {
        String[] result = solution.expand("{a,b}{c,d}{e,f}");
        assertEquals(8, result.length);
    }

    @Test
    void testSingleOptionInBraces() {
        String[] result = solution.expand("{a}b{c}");
        assertEquals(1, result.length);
        assertEquals("abc", result[0]);
    }

    @Test
    void testResultContent() {
        assertArrayEquals(new String[]{"acdf", "acef", "bcdf", "bcef"},
                solution.expand("{a,b}c{d,e}f"));
    }

    @Test
    void testGiantExpansion() {
        // {a,b,c}{d,e,f}{g,h,i} = 27 combinations
        String[] result = solution.expand("{a,b,c}{d,e,f}{g,h,i}");
        assertEquals(27, result.length);
    }

    /**
     * The expected values are generated from independently supplied literal/group
     * components, rather than by calling the implementation under test. This
     * checks content, cardinality, and the lexicographic order required by 1087.
     */
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("validExpansions")
    void expandsEveryValidShape(String input, String[] expected) {
        assertArrayEquals(expected, solution.expand(input));
    }

    static Stream<Arguments> validExpansions() {
        return Stream.of(
                caseOf("a", "a"),
                caseOf("z", "z"),
                caseOf("abc", "abc"),
                caseOf("{a}", "a"),
                caseOf("{z}", "z"),
                caseOf("{b,a}", "a", "b"),
                caseOf("{c,b,a}", "a", "b", "c"),
                caseOf("{a,b}c", "ac", "bc"),
                caseOf("a{b,c}", "ab", "ac"),
                caseOf("a{b,c}d", "abd", "acd"),
                caseOf("{a,b}{c,d}", "ac", "ad", "bc", "bd"),
                caseOf("{b,a}{d,c}", "ac", "ad", "bc", "bd"),
                caseOf("{a,b,c}", "a", "b", "c"),
                caseOf("a{b,c}{d,e}f", "abdf", "abef", "acdf", "acef"),
                caseOf("{a,b}{c,d}{e,f}",
                        "ace", "acf", "ade", "adf", "bce", "bcf", "bde", "bdf"),
                caseOf("{a,b}c{d,e}f", "acdf", "acef", "bcdf", "bcef"),
                caseOf("a{b,c}a", "aba", "aca"),
                caseOf("{a,b}x{y,z}q", "axyq", "axzq", "bxyq", "bxzq"),
                caseOf("m{n,o}p{q,r}s", "mnpqs", "mnprs", "mopqs", "moprs"),
                caseOf("{d,e,f}a", "da", "ea", "fa"),
                caseOf("a{b,c,d}e", "abe", "ace", "ade"),
                caseOf("{a,b,c}{d,e}", "ad", "ae", "bd", "be", "cd", "ce"),
                caseOf("{a,b}literal", "aliteral", "bliteral"),
                caseOf("literal{a,b}", "literala", "literalb"),
                caseOf("{a,b}c{d,e}{f,g}",
                        "acdf", "acdg", "acef", "aceg", "bcdf", "bcdg", "bcef", "bceg"),
                caseOf("a{b,c}d{e,f}g{h,i}",
                        "abdegh", "abdegi", "abdfgh", "abdfgi",
                        "acdegh", "acdegi", "acdfgh", "acdfgi"),
                // Four five-way groups plus six literals make a valid 50-character
                // input and exercise a safely sized 625-result Cartesian product.
                caseOfGenerated("{a,b,c,d,e}{f,g,h,i,j}{k,l,m,n,o}{p,q,r,s,t}uvwxyz",
                        components("abcde", "fghij", "klmno", "pqrst", "u", "v", "w", "x", "y", "z"))
        );
    }

    private static Arguments caseOf(String input, String... expected) {
        return Arguments.of(input, expected);
    }

    private static Arguments caseOfGenerated(String input, String[][] components) {
        return Arguments.of(input, cartesianProduct(components));
    }

    private static String[][] components(String... groups) {
        String[][] components = new String[groups.length][];
        for (int i = 0; i < groups.length; i++) {
            components[i] = groups[i].chars().mapToObj(c -> String.valueOf((char) c)).toArray(String[]::new);
        }
        return components;
    }

    private static String[] cartesianProduct(String[][] components) {
        List<String> values = new ArrayList<>();
        values.add("");
        for (String[] component : components) {
            List<String> next = new ArrayList<>();
            for (String prefix : values) {
                for (String option : component) {
                    next.add(prefix + option);
                }
            }
            values = next;
        }
        return values.toArray(String[]::new);
    }
}
