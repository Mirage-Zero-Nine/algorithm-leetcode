package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/21 13:46
 * Created with IntelliJ IDEA
 */

public class BrowserHistory1472Test {

    private BrowserHistory_1472 test;

    @BeforeEach
    public void setUp() {
        test = new BrowserHistory_1472("about:blank");
    }

    @Test
    public void test() {
        test.visit("google.com");
        test.visit("facebook.com");
        test.visit("youtube.com");
        assertEquals("facebook.com", test.back(1));
        assertEquals("google.com", test.back(1));
        assertEquals("facebook.com", test.forward(1));
        test.visit("linkedin.com");
        assertEquals("linkedin.com", test.forward(2));
        assertEquals("google.com", test.back(2));
        assertEquals("about:blank", test.back(7));
    }

    @Test
    public void testInvalid() {
        assertEquals("about:blank", test.back(10));
        assertEquals("about:blank", test.forward(10));
    }

    @Test
    public void test1() {
        test.visit("cgrt.com");
        test.visit("tip.com");
        assertEquals("about:blank", test.back(9));
        test.visit("kttzxgh.com");
        assertEquals("kttzxgh.com", test.forward(7));
        test.visit("crqje.com");
        test.visit("iybch.com");
        assertEquals("iybch.com", test.forward(5));
        test.visit("uun.com");
        assertEquals("about:blank", test.back(10));
        test.visit("hci.com");
        test.visit("whula.com");
        assertEquals("whula.com", test.forward(10));
    }
}