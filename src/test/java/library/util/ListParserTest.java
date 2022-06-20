package library.util;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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