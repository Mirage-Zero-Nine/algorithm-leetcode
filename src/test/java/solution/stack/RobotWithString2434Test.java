package solution.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/09 22:39
 * Created with IntelliJ IDEA
 */

public class RobotWithString2434Test {

    private final RobotWithString_2434 test = new RobotWithString_2434();

    @Test
    public void test() {
        assertEquals("azz", test.robotWithString("zza"));
    }

    @Test
    public void test1(){
        assertEquals("abc", test.robotWithString("bac"));
        assertEquals("addb", test.robotWithString("bdda"));
        assertEquals("aabbccdd", test.robotWithString("dcbaabcd"));
        assertEquals("acebh", test.robotWithString("beach"));
        assertEquals("eekstrlpmomwzqummz", test.robotWithString("mmuqezwmomeplrtskz"));
    }
}