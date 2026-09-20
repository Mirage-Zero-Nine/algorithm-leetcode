package solutions.unionfind;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Value;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 18:59
 * Created with IntelliJ IDEA
 */

public class SmallestStringWithSwaps_1202Test {
    private final SmallestStringWithSwaps_1202 test = new SmallestStringWithSwaps_1202();

    @Test
    public void test() {
        generateTestData().forEach(
                data -> assertEquals(data.expected, test.smallestStringWithSwaps(data.inputString, data.inputList))
        );
    }

    @Test
    public void testNoPairsKeepsStringUnchanged() {
        assertEquals("leetcode", test.smallestStringWithSwaps("leetcode", Lists.newArrayList()));
    }

    @Test
    public void testSingleCharacterString() {
        assertEquals("a", test.smallestStringWithSwaps("a", Lists.newArrayList()));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", test.smallestStringWithSwaps("", Lists.newArrayList()));
    }

    @Test
    public void testDisconnectedComponents() {
        assertEquals("abcd", test.smallestStringWithSwaps(
                "badc",
                Lists.newArrayList(
                        Lists.newArrayList(0, 1),
                        Lists.newArrayList(2, 3)
                )
        ));
    }

    @Test
    public void testDuplicatePairsDoNotChangeResult() {
        assertEquals("abc", test.smallestStringWithSwaps(
                "bca",
                Lists.newArrayList(
                        Lists.newArrayList(0, 1),
                        Lists.newArrayList(0, 1),
                        Lists.newArrayList(1, 2)
                )
        ));
    }

    @Test
    public void testSelfPairsDoNotAffectOrdering() {
        assertEquals("acb", test.smallestStringWithSwaps(
                "cab",
                Lists.newArrayList(
                        Lists.newArrayList(0, 0),
                        Lists.newArrayList(1, 1),
                        Lists.newArrayList(0, 1)
                )
        ));
    }

    @Test
    public void testMultipleIndependentComponentsWithRepeatedLetters() {
        assertEquals("bacbab", test.smallestStringWithSwaps(
                "cabbab",
                Lists.newArrayList(
                        Lists.newArrayList(0, 2),
                        Lists.newArrayList(1, 3),
                        Lists.newArrayList(4, 5)
                )
        ));
    }

    @Test
    public void testNegativeCaseImpossibleToMoveAcrossComponents() {
        assertEquals("bacd", test.smallestStringWithSwaps(
                "bcad",
                Lists.<List<Integer>>newArrayList(Lists.newArrayList(1, 2))
        ));
    }

    @Test
    public void testGiantCaseFullyConnectedGraph() {
        String base = "zyxwvutsrqponmlkjihgfedcba";
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 1_000) {
            sb.append(base, 0, Math.min(base.length(), 1_000 - sb.length()));
        }
        String input = sb.toString();

        List<List<Integer>> pairs = Lists.newArrayList();
        for (int i = 0; i < input.length() - 1; i++) {
            pairs.add(Lists.newArrayList(i, i + 1));
        }

        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String expected = new String(chars);
        assertEquals(expected, test.smallestStringWithSwaps(input, pairs));
    }

    @ParameterizedTest(name = "case {index}: {0} -> {3}")
    @MethodSource("additionalCases")
    public void testAdditionalSwapComponents(String input, List<List<Integer>> pairs,
                                             String expected, String description) {
        assertEquals(expected, test.smallestStringWithSwaps(input, pairs), description);
    }

    /**
     * These cases independently exercise component-local sorting: a character can move only
     * within its connected component, while every connected component can be rearranged freely.
     */
    private static Stream<Arguments> additionalCases() {
        return Stream.of(
                Arguments.of("ba", pairs(new int[][]{{0, 1}}), "ab", "single swap"),
                Arguments.of("ab", pairs(new int[][]{{0, 1}}), "ab", "already sorted component"),
                Arguments.of("cba", pairs(new int[][]{{0, 2}}), "abc", "two endpoints leave middle fixed"),
                Arguments.of("dcba", pairs(new int[][]{{0, 3}}), "acbd", "isolated indices retain their characters"),
                Arguments.of("dcba", pairs(new int[][]{{0, 1}, {1, 2}}), "bcda", "partial chain component"),
                Arguments.of("dcba", pairs(new int[][]{{0, 1}, {1, 2}, {2, 3}}), "abcd", "chain connects all indices"),
                Arguments.of("zxyabc", pairs(new int[][]{{0, 1}, {3, 4}}), "xzyabc", "two components with isolated indices"),
                Arguments.of("bbacaa", pairs(new int[][]{{0, 3}, {1, 3}, {1, 3}}), "bbacaa", "duplicate edges and repeated characters"),
                Arguments.of("jihgfedcba", pairs(new int[][]{{0, 9}, {1, 8}, {2, 7}, {3, 6}, {4, 5}}), "abcdefghij", "nested pairs form one component"),
                Arguments.of("aabbcc", pairs(new int[][]{{0, 2}, {2, 4}}), "aabbcc", "component values already ordered"),
                Arguments.of("fedcba", pairs(new int[][]{{0, 1}, {2, 3}}), "efcdba", "independent pair order is local"),
                Arguments.of("qwerty", pairs(new int[][]{{1, 2}, {2, 3}, {4, 5}}), "qerwty", "overlapping chain and separate pair"),
                Arguments.of("zyxwv", pairs(new int[][]{{0, 0}, {4, 4}}), "zyxwv", "self-swaps do not connect components"),
                Arguments.of("cabdef", pairs(new int[][]{{0, 1}, {1, 2}, {3, 4}, {4, 5}}), "abcdef", "two independently sortable chains"),
                Arguments.of("bca", pairs(new int[][]{{0, 1}, {0, 1}, {1, 2}}), "abc", "repeated connectivity declarations")
        );
    }

    private static List<List<Integer>> pairs(int[][] edges) {
        List<List<Integer>> result = Lists.newArrayList();
        for (int[] edge : edges) {
            result.add(Lists.newArrayList(edge[0], edge[1]));
        }
        return result;
    }

    private List<solutions.unionfind.SmallestStringWithSwaps_1202Test.Data> generateTestData() {
        return Lists.newArrayList(
                new Data(
                        "dcab",
                        Lists.newArrayList(
                                Lists.newArrayList(0, 3),
                                Lists.newArrayList(1, 2)
                        ),
                        "bacd"),
                new Data(
                        "dcab",
                        Lists.newArrayList(
                                Lists.newArrayList(0, 3),
                                Lists.newArrayList(1, 2),
                                Lists.newArrayList(0, 2)
                        ),
                        "abcd"),
                new Data(
                        "cba",
                        Lists.newArrayList(
                                Lists.newArrayList(0, 1),
                                Lists.newArrayList(1, 2)
                        ),
                        "abc")
        );
    }

    @Builder
    @Value
    public static class Data {
        String inputString;
        List<List<Integer>> inputList;
        String expected;
    }
}
