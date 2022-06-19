package solution.unionfind;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Value;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 18:59
 * Created with IntelliJ IDEA
 */

public class SmallestStringWithSwaps1202Test {
    private final SmallestStringWithSwaps_1202 test = new SmallestStringWithSwaps_1202();

    @Test
    public void test() {
        generateTestData().forEach(
                data -> assertEquals(data.expected, test.smallestStringWithSwaps(data.inputString, data.inputList))
        );
    }

    private List<solution.unionfind.SmallestStringWithSwaps1202Test.Data> generateTestData() {
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