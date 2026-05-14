package solution.unionfind;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        for (int i = 0; i < 20; i++) {
            sb.append(base);
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

    private List<solution.unionfind.SmallestStringWithSwaps_1202Test.Data> generateTestData() {
        return Lists.newArrayList(
                Data.builder()
                        .inputString("dcab")
                        .inputList(Lists.newArrayList(
                                Lists.newArrayList(0, 3),
                                Lists.newArrayList(1, 2)
                        ))
                        .expected("bacd")
                        .build(),
                Data.builder()
                        .inputString("dcab")
                        .inputList(Lists.newArrayList(
                                Lists.newArrayList(0, 3),
                                Lists.newArrayList(1, 2),
                                Lists.newArrayList(0, 2)
                        ))
                        .expected("abcd")
                        .build(),
                Data.builder()
                        .inputString("cba")
                        .inputList(Lists.newArrayList(
                                Lists.newArrayList(0, 1),
                                Lists.newArrayList(1, 2)
                        ))
                        .expected("abc")
                        .build()
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
