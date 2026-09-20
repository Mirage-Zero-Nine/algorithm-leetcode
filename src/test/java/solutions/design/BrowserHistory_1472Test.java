package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void shouldStartAtTheProvidedHomepage() {
        BrowserHistory_1472 browser = new BrowserHistory_1472("leetcode.com");

        assertEquals("leetcode.com", browser.back(0));
        assertEquals("leetcode.com", browser.back(1));
        assertEquals("leetcode.com", browser.forward(1));
    }

    @Test
    public void shouldMoveBackOnePageThroughAVisitChain() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");

        assertEquals("b.com", test.back(1));
        assertEquals("a.com", test.back(1));
        assertEquals("about:blank", test.back(1));
    }

    @Test
    public void shouldMoveForwardByExactDistances() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        test.back(3);

        assertEquals("b.com", test.forward(2));
        assertEquals("c.com", test.forward(1));
    }

    @Test
    public void navigationAloneShouldNotDiscardForwardHistory() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        test.back(2);

        assertEquals("b.com", test.forward(1));
        assertEquals("c.com", test.forward(1));
    }

    @Test
    public void visitFromTheMiddleShouldDiscardOnlyTheForwardBranch() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("a.com", test.back(2));
        test.visit("replacement.com");

        assertEquals("replacement.com", test.forward(100));
        assertEquals("a.com", test.back(1));
        assertEquals("about:blank", test.back(1));
    }

    @Test
    public void visitFromHomepageShouldDiscardTheEntireForwardBranch() {
        test.visit("a.com");
        test.visit("b.com");
        test.back(10);
        test.visit("new-root-branch.com");

        assertEquals("new-root-branch.com", test.forward(10));
        assertEquals("about:blank", test.back(1));
    }

    @Test
    public void zeroStepsShouldPreserveADeepCurrentPage() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        test.back(1);

        assertEquals("b.com", test.back(0));
        assertEquals("b.com", test.forward(0));
        assertEquals("c.com", test.forward(1));
    }

    @Test
    public void largeStepsShouldClampAtBothEnds() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");

        assertEquals("about:blank", test.back(1_000_000));
        assertEquals("c.com", test.forward(1_000_000));
    }

    @Test
    public void repeatedBoundaryNavigationShouldRemainStable() {
        test.visit("a.com");
        test.visit("b.com");

        assertEquals("about:blank", test.back(100));
        assertEquals("about:blank", test.back(100));
        assertEquals("b.com", test.forward(100));
        assertEquals("b.com", test.forward(100));
    }

    @Test
    public void duplicateUrlsShouldStillRepresentSeparateHistoryPositions() {
        test.visit("same.com");
        test.visit("same.com");
        test.visit("other.com");
        test.visit("same.com");

        assertEquals("other.com", test.back(1));
        assertEquals("same.com", test.back(1));
        assertEquals("other.com", test.forward(1));
        assertEquals("same.com", test.forward(1));
    }

    @Test
    public void urlsWithDotsAndMaximumContractLengthShouldBeStoredVerbatim() {
        String homepage = "a.................b";
        String url = "c.................d";
        BrowserHistory_1472 browser = new BrowserHistory_1472(homepage);

        browser.visit(url);

        assertEquals(url, browser.forward(1));
        assertEquals(homepage, browser.back(1));
    }

    @Test
    public void independentInstancesShouldNotShareHistoryOrPosition() {
        BrowserHistory_1472 first = new BrowserHistory_1472("first.com");
        BrowserHistory_1472 second = new BrowserHistory_1472("second.com");
        first.visit("first-child.com");

        assertEquals("first-child.com", first.forward(1));
        assertEquals("second.com", second.back(100));
        second.visit("second-child.com");
        assertEquals("second-child.com", second.forward(100));
        assertEquals("first-child.com", first.back(0));
    }

    @Test
    public void visitingTheCurrentUrlShouldAppendOneHistoryEntry() {
        test.visit("a.com");
        test.visit("a.com");

        assertEquals("a.com", test.back(1));
        assertEquals("about:blank", test.back(1));
        assertEquals("a.com", test.forward(2));
    }

    @Test
    public void aVisitAfterForwardShouldUseThePageActuallyDisplayed() {
        test.visit("a.com");
        test.visit("b.com");
        test.visit("c.com");
        assertEquals("a.com", test.back(2));
        assertEquals("b.com", test.forward(1));
        test.visit("d.com");

        assertEquals("d.com", test.forward(100));
        assertEquals("b.com", test.back(1));
    }

    @Test
    public void backAndForwardShouldRemainCorrectAfterSeveralBranchReplacements() {
        test.visit("a.com");
        test.visit("b.com");
        test.back(1);
        test.visit("c.com");
        test.back(1);
        test.visit("d.com");
        test.visit("e.com");

        assertEquals("d.com", test.back(1));
        assertEquals("about:blank", test.back(2));
        assertEquals("e.com", test.forward(10));
    }

    @Test
    public void maximumPositiveStepsShouldNotOverflowForwardPosition() {
        test.visit("a.com");
        test.visit("b.com");
        test.back(1);

        assertEquals("b.com", test.forward(Integer.MAX_VALUE));
        assertEquals("about:blank", test.back(Integer.MAX_VALUE));
    }

    @Test
    public void seededOperationStreamsShouldMatchAnIndependentListOracle() {
        long[] seeds = {0L, 1L, 7L, 42L, 1472L, 2023L, 65_537L, 123_456L, 0x5EEDL,
                0xBEEFL, 0x1234_5678L, 0x7FFF_FFFFL, 0xCAFE_BABEL, 0xDEAD_BEEFL,
                9_876_543_210L, 11L, 99L, 314_159L, 2_000_000L, Long.MAX_VALUE};

        for (long seed : seeds) {
            assertSeededStream(seed, 300);
        }
    }

    @Test
    public void exactMaximumCallWorkloadShouldMatchAnIndependentOracle() {
        BrowserHistory_1472 browser = new BrowserHistory_1472("home.com");
        HistoryOracle oracle = new HistoryOracle("home.com");
        Random random = new Random(1_472_5000L);

        for (int operation = 0; operation < 5000; operation++) {
            int kind = operation % 5;
            int steps = random.nextInt(101);
            if (kind == 0) {
                String url = operation % 13 == 0 ? "repeat.com" : streamUrl(operation, random);
                browser.visit(url);
                oracle.visit(url);
            } else if (kind <= 2) {
                assertEquals(oracle.back(steps), browser.back(steps));
            } else {
                assertEquals(oracle.forward(steps), browser.forward(steps));
            }
        }

    }

    private void assertSeededStream(long seed, int operationCount) {
        BrowserHistory_1472 browser = new BrowserHistory_1472("home.com");
        HistoryOracle oracle = new HistoryOracle("home.com");
        Random random = new Random(seed);

        for (int operation = 0; operation < operationCount; operation++) {
            int kind = random.nextInt(3);
            int steps = random.nextInt(101);
            if (kind == 0) {
                String url = operation % 11 == 0 ? "repeat.com" : streamUrl(operation, random);
                browser.visit(url);
                oracle.visit(url);
            } else if (kind == 1) {
                assertEquals(oracle.back(steps), browser.back(steps), "seed=" + seed + " op=" + operation);
            } else {
                assertEquals(oracle.forward(steps), browser.forward(steps), "seed=" + seed + " op=" + operation);
            }
        }

        assertEquals(oracle.current(), browser.back(0), "seed=" + seed + " final position");
    }

    private static String streamUrl(int operation, Random random) {
        char first = (char) ('a' + operation % 26);
        char second = (char) ('a' + random.nextInt(26));
        return "page" + first + second + ".com";
    }

    private static final class HistoryOracle {
        private final List<String> pages = new ArrayList<>();
        private int position;

        private HistoryOracle(String homepage) {
            pages.add(homepage);
        }

        private void visit(String url) {
            while (pages.size() > position + 1) {
                pages.remove(pages.size() - 1);
            }
            pages.add(url);
            position++;
        }

        private String back(int steps) {
            position = Math.max(0, position - steps);
            return current();
        }

        private String forward(int steps) {
            int last = pages.size() - 1;
            int available = last - position;
            position = steps >= available ? last : position + steps;
            return current();
        }

        private String current() {
            return pages.get(position);
        }
    }
}
