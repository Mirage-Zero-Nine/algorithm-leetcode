package solution.bfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:24
 * Created with IntelliJ IDEA
 */

public class OpenLock752Test {
    private final OpenLock_752 test = new OpenLock_752();

    @Test
    public void test() {
        assertEquals(6, test.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        assertEquals(-1, test.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
        assertEquals(-1, test.openLock(new String[]{"0000"}, "8888"));
    }
}