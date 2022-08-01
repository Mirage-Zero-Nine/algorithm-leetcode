package solution.datastructure;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 16:05
 * Created with IntelliJ IDEA
 */

public class Codec535Test {
    private final Codec_535 test = new Codec_535();

    @Test
    public void test() {
        generateTestUrl().forEach(
                url -> assertEquals(url, test.decode(test.encode(url)))
        );
    }

    @Test
    public void testEncode() {
        generateTestUrl().forEach(
                url -> assertNotEquals(url, test.encode(url))
        );
    }

    private List<String> generateTestUrl() {
        return Lists.newArrayList(
                "test.com",
                "a.com",
                "a",
                "b",
                "google.com",
                "leetcode.com/problems/design-tinyurl"
        );
    }
}