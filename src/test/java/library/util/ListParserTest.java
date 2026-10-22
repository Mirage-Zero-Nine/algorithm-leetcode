package library.util;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:41
 * Created with IntelliJ IDEA
 */

public class ListParserTest {

    @Test
    public void test() {
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[1,2,3]"));
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[1, 2,3]"));
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[ 1   , 2,3 ]"));
        assertIterableEquals(Lists.newArrayList(1), ListParser.parseList("[1]"));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList(null));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("["));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[]"));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[1,2,,3]"));
    }

    @Test void testNegativeValues() { assertIterableEquals(Lists.newArrayList(-3, 0, 8), ListParser.parseList("[-3,0,8]")); }
    @Test void testIntegerBoundaries() { assertIterableEquals(Lists.newArrayList(Integer.MIN_VALUE, Integer.MAX_VALUE), ListParser.parseList("[-2147483648,2147483647]")); }
    @Test void testNewlineAndTabWhitespace() { assertIterableEquals(Lists.newArrayList(1, 2), ListParser.parseList("[\n1,\t2]")); }
    @Test void testOuterWhitespaceIsAccepted() { assertIterableEquals(Lists.newArrayList(1, 2), ListParser.parseList(" [1,2] ")); }
    @Test void testMissingOpeningBracketIsRejected() { assertThrows(InvalidParameterException.class, () -> ListParser.parseList("1,2]")); }
    @Test void testMissingClosingBracketIsRejected() { assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[1,2")); }
    @Test void testEmptyElementAtEndRejected() { assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[1,,]")); }
    @Test void testEmptyElementAtStartRejected() { assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[,1]")); }
    @Test void testTwoDimensionalEmptyInput() { assertEquals(0, ListParser.parse2dList("[]").size()); }
    @Test void testTwoDimensionalNegativeValues() { assertEquals(java.util.List.of(java.util.List.of(-1, 2)), ListParser.parse2dList("[[-1,2]]")); }
    @Test void testTwoDimensionalMultipleRows() { assertEquals(3, ListParser.parse2dList("[[1],[2],[3]]").size()); }
    @Test void testTwoDimensionalIgnoresNonListText() { assertEquals(java.util.List.of(java.util.List.of(1, 2)), ListParser.parse2dList("prefix [1,2] suffix")); }
    @Test void testLargeList() { assertEquals(100, ListParser.parseList("[" + java.util.stream.IntStream.range(0, 100).mapToObj(String::valueOf).collect(java.util.stream.Collectors.joining(",")) + "]").size()); }
    @Test void testZero() { assertIterableEquals(Lists.newArrayList(0), ListParser.parseList("[0]")); }
    @Test void testDuplicateValues() { assertIterableEquals(Lists.newArrayList(4, 4, 4), ListParser.parseList("[4,4,4]")); }
    @Test void testTwoDimensionalSpacing() { assertEquals(java.util.List.of(java.util.List.of(1, 2)), ListParser.parse2dList("[ [ 1 , 2 ] ]")); }

    @Test
    public void test2dParser() {
        buildTestData().forEach(
                data -> assertIterableEquals(data.expected, ListParser.parse2dList(data.input))
        );
    }

    private List<TestData> buildTestData() {
        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Lists.newArrayList(100, 1, 3, 90));
        List<List<Integer>> expected2 = new ArrayList<>();
        expected2.add(Lists.newArrayList(100, 1, 2, 3, 90));
        expected2.add(Lists.newArrayList(1, 2));
        expected2.add(Lists.newArrayList(2, 3));
        expected2.add(Lists.newArrayList(100, 1,2, 3, 90));
        return Lists.newArrayList(
                TestData.builder()
                        .expected(expected1)
                        .input("[[100, 1,3,  90]]")
                        .build(),
                TestData.builder()
                        .expected(expected2)
                        .input("[[100, 1,2,3,  90], [1,2],[2,3],  [100, 1,2,3,  90]")
                        .build()
        );
    }

    @Builder
    @Value
    static class TestData {
        List<List<Integer>> expected;
        String input;
    }
}
