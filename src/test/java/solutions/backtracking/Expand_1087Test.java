package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Expand_1087Test {
    private final Expand_1087 solution = new Expand_1087();

    @Test
    void testBasic() {
        assertExpansion("{a,b}c{d,e}f", "acdf", "acef", "bcdf", "bcef");
    }

    @Test
    void testSingleOption() {
        assertExpansion("abcd", "abcd");
    }

    @Test
    void testMultipleOptions() {
        assertExpansion("{a,b}{c,d}", "ac", "ad", "bc", "bd");
    }

    @Test
    void testThreeOptions() {
        assertExpansion("{a,b,c}", "a", "b", "c");
    }

    @Test
    void testComplex() {
        assertExpansion("a{b,c}{d,e}f", "abdf", "abef", "acdf", "acef");
    }

    @Test
    void testLexicographicOrder() {
        assertExpansion("{b,a}c", "ac", "bc");
    }

    @Test
    void testSingleChar() {
        assertExpansion("a", "a");
    }

    @Test
    void testAllBraces() {
        assertExpansion("{a,b}{c,d}{e,f}",
                "ace", "acf", "ade", "adf", "bce", "bcf", "bde", "bdf");
    }

    @Test
    void testSingleOptionInBraces() {
        assertExpansion("{a}b{c}", "abc");
    }

    @Test
    void testResultContent() {
        assertExpansion("{a,b}c{d,e}f", "acdf", "acef", "bcdf", "bcef");
    }

    @Test
    void testGiantExpansion() {
        // {a,b,c}{d,e,f}{g,h,i} = 27 combinations
        assertExpansion("{a,b,c}{d,e,f}{g,h,i}",
                cartesianProduct(components("abc", "def", "ghi")));
    }

    @Test
    void testGroupsAndLiteralsCanBeAdjacent() {
        assertExpansion("x{a,b}y{c,d}z", "xaycz", "xaydz", "xbycz", "xbydz");
    }

    @Test
    void testOptionsAreSortedEvenWhenInputIsReversed() {
        assertExpansion("{d,c,b,a}{z,y,x,w}",
                "aw", "ax", "ay", "az", "bw", "bx", "by", "bz",
                "cw", "cx", "cy", "cz", "dw", "dx", "dy", "dz");
    }

    @Test
    void testLexicographicOrderUsesWholeWords() {
        assertExpansion("{a,b}{b,c}", "ab", "ac", "bb", "bc");
    }

    @Test
    void testRepeatedLettersFromDifferentPositionsArePreserved() {
        assertExpansion("a{a,b}a{a,b}", "aaaa", "aaab", "abaa", "abab");
    }

    @Test
    void testFixedPrefixAndSuffix() {
        assertExpansion("prefix{a,b}suffix", "prefixasuffix", "prefixbsuffix");
    }

    @Test
    void testManyOptionsInOneGroup() {
        assertExpansion("{z,y,x,w,v,u}", "u", "v", "w", "x", "y", "z");
    }

    @Test
    void testSeveralOneOptionGroupsBehaveLikeLiterals() {
        assertExpansion("{a}{b}{c}{d}", "abcd");
    }

    @Test
    void testOptionGroupAtEachBoundary() {
        assertExpansion("{a,b}middle{y,z}", "amiddley", "amiddlez", "bmiddley", "bmiddlez");
        assertExpansion("left{a,b}", "lefta", "leftb");
    }

    @Test
    void testMaximumDistinctOptionsWithinOfficialLengthLimit() {
        String input = "{x,w,v,u,t,s,r,q,p,o,n,m,l,k,j,i,h,g,f,e,d,c,b,a}";
        assertEquals(49, input.length());
        assertExpansion(input,
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x");
    }

    @Test
    void testMaximumLiteralLengthWithinOfficialLimit() {
        String input = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx";
        assertEquals(50, input.length());
        assertExpansion(input, input);
    }

    @Test
    void testMaximumThreeWayCartesianExpansionWithinOfficialLimit() {
        String input = "{a,b,c}{d,e,f}{g,h,i}{j,k,l}{m,n,o}{p,q,r}{s,t,u}z";
        assertEquals(50, input.length());
        String[] expected = cartesianProduct(components("abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "z"));
        assertEquals(2187, expected.length);
        assertExpansion(input, expected);
    }

    @Test
    void testSameInstanceCanBeReusedAcrossDifferentShapes() {
        assertExpansion("{b,a}x", "ax", "bx");
        assertExpansion("yz{d,c}", "yzc", "yzd");
        assertExpansion("{b,a}x", "ax", "bx");
    }

    @Test
    void testEmptyInputReturnsNoWordsAccordingToImplementation() {
        // Empty input is outside LeetCode's 1-character minimum, but the implementation
        // deliberately does not emit the empty partial word at its recursion base case.
        assertArrayEquals(new String[0], solution.expand(""));
    }

    /**
     * The expected values are generated from independently supplied literal/group
     * components, rather than by calling the implementation under test. This
     * checks content, cardinality, and the lexicographic order required by 1087.
     */
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("validExpansions")
    void expandsEveryValidShape(String input, String[] expected) {
        assertExpansion(input, expected);
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

    private void assertExpansion(String input, String... expected) {
        String[] actual = solution.expand(input);
        assertArrayEquals(expected, actual);
        assertTrue(isStrictlySortedAndUnique(actual), "result must be strictly lexicographically sorted: " + input);
    }

    private static boolean isStrictlySortedAndUnique(String[] values) {
        String[] sorted = values.clone();
        Arrays.sort(sorted);
        return Arrays.equals(sorted, values)
                && Arrays.stream(values).distinct().count() == values.length;
    }
}
