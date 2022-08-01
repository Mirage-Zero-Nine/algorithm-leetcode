package playground;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 20:52
 * Created with IntelliJ IDEA
 */

public class WordLadderIIITest {
    private final WordLadderIII test = new WordLadderIII();

    @Test
    public void test() {
        generateTestData().forEach(
                data -> assertEquals(data.expected, test.wordLadderIII(data.begin, data.end, data.dictionary))
        );
    }

    @Test
    public void testEmpty() {
        assertIterableEquals(Lists.newArrayList(""), test.wordLadderIII("", "", Lists.newArrayList()));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII("a", "b", Lists.newArrayList()));
        assertIterableEquals(Lists.newArrayList("a"), test.wordLadderIII("a", "a", Lists.newArrayList()));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII("", "a", Lists.newArrayList()));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII("a", "", Lists.newArrayList()));
    }

    @Test
    public void testNull() {
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII(null, null, null));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII("null", null, null));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII(null, "null", null));
        assertIterableEquals(Lists.newArrayList(), test.wordLadderIII(null, "null", Lists.newArrayList("a")));
    }

    private List<TestData> generateTestData() {
        return Lists.newArrayList(
                TestData.builder()
                        .begin("a")
                        .end("d")
                        .dictionary(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g"))
                        .expected(Lists.newArrayList("a", "d"))
                        .build(),
                TestData.builder()
                        .begin("red")
                        .end("tax")
                        .dictionary(Lists.newArrayList("ted", "tex", "red", "tax", "tad", "hop", "rex", "pee"))
                        .expected(Lists.newArrayList("red", "rex", "tex", "tax"))
                        .build(),
                TestData.builder()
                        .begin("hit")
                        .end("cog")
                        .dictionary(Lists.newArrayList("hot", "dog", "lot", "log", "cog", "hop", "tot", "hog"))
                        .expected(Lists.newArrayList("hit", "hot", "hog", "cog"))
                        .build(),
                TestData.builder()
                        .begin("ted")
                        .end("tax")
                        .dictionary(Lists.newArrayList("ted", "tex", "red", "abc", "tad", "hop", "rex", "pee"))
                        .expected(Lists.newArrayList())
                        .build(),
                TestData.builder()
                        .begin("a")
                        .end("a")
                        .dictionary(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h"))
                        .expected(Lists.newArrayList("a"))
                        .build()
        );
    }

    @Builder
    @Value
    static class TestData {
        String begin;
        String end;
        List<String> dictionary;
        List<String> expected;
    }
}