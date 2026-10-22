package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

/**
 * @author BorisMirage
 * Time: 2025/03/31 18:37
 * Created with IntelliJ IDEA
 */

public class MinicCdTest {
    private MinicCd minicCd = new MinicCd();

    @Test
    public void test() {
        assertEquals("/home", minicCd.minicCd("/home/example/example1","../.././find/../."));
        assertEquals("/home/example", minicCd.minicCd("/home/example/example1",".."));
        assertEquals("/home/user/docs/folder1", minicCd.minicCd("/home/user/docs", "folder1"));
        assertEquals("/home/user/folder2", minicCd.minicCd("/home/user/docs", "../folder2"));
        assertEquals("/home/folder3", minicCd.minicCd("/home/user/docs", "../../folder3"));
        assertEquals("/folder1/folder2", minicCd.minicCd("/", "folder1/folder2"));
    }

    @Test
    public void test2() {
        assertEquals("/", minicCd.minicCd("/", "../.."));
        assertEquals("/", minicCd.minicCd("/", "../.././."));
        assertEquals("/", minicCd.minicCd("/", "../../././home/.."));
    }

    @Test
    public void test3() {
        assertEquals("", minicCd.minicCd("", "../.."));
        assertEquals("/", minicCd.minicCd("/", ""));
        assertEquals("", minicCd.minicCd(null, "../../././home/.."));
        assertEquals("/", minicCd.minicCd("/", null));
    }

    @ParameterizedTest
    @MethodSource("moreCases")
    void normalizesAdditionalPaths(String path, String command, String expected) {
        assertEquals(expected, minicCd.minicCd(path, command));
    }

    static Stream<Arguments> moreCases() {
        return Stream.of(
                Arguments.of("/", ".", "/"), Arguments.of("/a", ".", "/a"), Arguments.of("/a", "..", "/"),
                Arguments.of("/a/b", "../c", "/a/c"), Arguments.of("/a/b", "../../c", "/c"),
                Arguments.of("/a/b", "../../../c", "/c"), Arguments.of("/a/b", "././d", "/a/b/d"),
                Arguments.of("/a/b", "c/../d", "/a/b/d"), Arguments.of("/a/b", ".././../", "/"),
                Arguments.of("/a/b", "e/f", "/a/b/e/f"), Arguments.of("/a", "b/../../c", "/c"),
                Arguments.of("/a/b/c", "../../x/y", "/a/x/y"), Arguments.of("/a", "./b/./c", "/a/b/c"),
                Arguments.of("/a/b", "..//c", "/a/c"), Arguments.of("/a/b", "d//e", "/a/b/d/e"),
                Arguments.of("/one", "../two/../three", "/three"), Arguments.of("/one/two", "../../", "/"),
                Arguments.of("/one/two", "./three/../../four", "/one/four"), Arguments.of("/one", "..", "/"),
                Arguments.of("/one", "four", "/one/four")
        );
    }
}
