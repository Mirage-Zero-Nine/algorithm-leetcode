package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/21 13:46
 * Created with IntelliJ IDEA
 */

public class BrowserHistory_1472Test {

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

    @Test
    public void shouldStayOnHomepageWithZeroStepNavigation() {
        assertEquals("about:blank", test.back(0));
        assertEquals("about:blank", test.forward(0));
    }

    @Test
    public void shouldClearForwardHistoryAfterVisitFromMiddle() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("b.com", test.back(1));
        test.visit("d.com");
        assertEquals("d.com", test.forward(5));
        assertEquals("a.com", test.back(2));
    }

    @Test
    public void shouldClampBackToOldestPage() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("about:blank", test.back(100));
        assertEquals("a.com", test.forward(1));
    }

    @Test
    public void shouldClampForwardToLatestPage() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("a.com", test.back(2));
        assertEquals("c.com", test.forward(100));
        assertEquals("c.com", test.forward(1));
    }

    @Test
    public void shouldHandleAlternatingBackAndForward() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("b.com", test.back(1));
        assertEquals("c.com", test.forward(1));
        assertEquals("a.com", test.back(2));
        assertEquals("b.com", test.forward(1));
        assertEquals("a.com", test.back(1));
    }

    @Test
    public void shouldSupportDuplicateUrlVisitsAsDistinctHistoryEntries() {
        test.visit("dup.com");
        test.visit("dup.com");
        test.visit("dup.com");
        assertEquals("dup.com", test.back(1));
        assertEquals("dup.com", test.back(1));
        assertEquals("dup.com", test.forward(2));
    }

    @Test
    public void shouldHandleGiantHistoryTraversal() {
        for (int i = 1; i <= 5000; i++) {
            test.visit("site" + i + ".com");
        }
        assertEquals("site2500.com", test.back(2500));
        assertEquals("site5000.com", test.forward(2500));
        assertEquals("about:blank", test.back(5001));
        assertEquals("site5000.com", test.forward(10000));
    }
}
