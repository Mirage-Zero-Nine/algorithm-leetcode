package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}