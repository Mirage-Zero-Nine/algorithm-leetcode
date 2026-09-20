package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import library.HtmlParser;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for both implementations of the multithreaded web crawler.
 *
 * <p>Each parser is a deterministic in-memory directed graph. Expected sets
 * are either stated explicitly or produced by an independent breadth-first
 * traversal, so the tests do not rely on agreement between the two solutions.
 */
public class Crawl_1242Test {

    @Test
    public void officialExampleCrawlsEveryReachableSameHostPage() {
        Map<String, List<String>> graph = graph(
            "http://news.yahoo.com/news/topics/", List.of(
                "http://news.yahoo.com", "http://news.yahoo.com/news"),
            "http://news.yahoo.com", List.of("http://news.yahoo.com/us"),
            "http://news.yahoo.com/news", List.of(),
            "http://news.yahoo.com/us", List.of(),
            "http://news.google.com", List.of("http://news.yahoo.com"));

        assertBothApproaches(
            "http://news.yahoo.com/news/topics/", graph,
            Set.of(
                "http://news.yahoo.com/news/topics/",
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/us"));
    }

    @Test
    public void officialDifferentHostnameStartDoesNotCrossToYahooPages() {
        Map<String, List<String>> graph = graph(
            "http://news.google.com", List.of(
                "http://news.yahoo.com", "http://news.yahoo.com/news"),
            "http://news.yahoo.com", List.of("http://news.google.com"),
            "http://news.yahoo.com/news", List.of());

        assertBothApproaches(
            "http://news.google.com", graph, Set.of("http://news.google.com"));
    }

    @Test
    public void emptyChildrenStillReturnTheStartPage() {
        assertBothApproaches(
            "http://solo.com/page", graph("http://solo.com/page", List.of()),
            Set.of("http://solo.com/page"));
    }

    @Test
    public void branchesAndLeavesAreAllReturnedRegardlessOfTraversalOrder() {
        Map<String, List<String>> graph = graph(
            "http://site.com/root", List.of(
                "http://site.com/a", "http://site.com/b", "http://site.com/c"),
            "http://site.com/a", List.of("http://site.com/a1", "http://site.com/a2"),
            "http://site.com/b", List.of("http://site.com/b1"),
            "http://site.com/c", List.of(),
            "http://site.com/a1", List.of(),
            "http://site.com/a2", List.of(),
            "http://site.com/b1", List.of());

        assertBothApproaches(
            "http://site.com/root", graph,
            Set.of(
                "http://site.com/root", "http://site.com/a", "http://site.com/b",
                "http://site.com/c", "http://site.com/a1", "http://site.com/a2",
                "http://site.com/b1"));
    }

    @Test
    public void hostnameComparisonRejectsLookalikeDomainsAndOtherSchemes() {
        Map<String, List<String>> graph = graph(
            "http://example.com/root", List.of(
                "http://example.com/valid",
                "http://example.com.evil/should-not-crawl",
                "http://example.commerce/should-not-crawl",
                "https://example.com/different-scheme"),
            "http://example.com/valid", List.of(),
            "http://example.com.evil/should-not-crawl", List.of("http://example.com/hidden"),
            "http://example.commerce/should-not-crawl", List.of(),
            "https://example.com/different-scheme", List.of());

        assertBothApproaches(
            "http://example.com/root", graph,
            Set.of("http://example.com/root", "http://example.com/valid"));
    }

    @Test
    public void duplicateLinksAndConvergingBranchesAreFetchedExactlyOnce() {
        Map<String, List<String>> graph = graph(
            "http://dup.com/root", List.of(
                "http://dup.com/a", "http://dup.com/a", "http://dup.com/b",
                "http://dup.com/b"),
            "http://dup.com/a", List.of("http://dup.com/shared", "http://dup.com/shared"),
            "http://dup.com/b", List.of("http://dup.com/shared"),
            "http://dup.com/shared", List.of());

        assertBothApproaches(
            "http://dup.com/root", graph,
            Set.of("http://dup.com/root", "http://dup.com/a", "http://dup.com/b",
                "http://dup.com/shared"));
    }

    @Test
    public void cyclesTerminateAndEachPageIsParsedOnce() {
        Map<String, List<String>> graph = graph(
            "http://cycle.com/a", List.of("http://cycle.com/b", "http://cycle.com/c"),
            "http://cycle.com/b", List.of("http://cycle.com/a", "http://cycle.com/c"),
            "http://cycle.com/c", List.of("http://cycle.com/a"));

        assertBothApproaches(
            "http://cycle.com/a", graph,
            Set.of("http://cycle.com/a", "http://cycle.com/b", "http://cycle.com/c"));
    }

    @Test
    public void disconnectedSameHostPagesAreNotVisited() {
        Map<String, List<String>> graph = graph(
            "http://graph.com/start", List.of("http://graph.com/reachable"),
            "http://graph.com/reachable", List.of(),
            "http://graph.com/disconnected", List.of("http://graph.com/also-disconnected"),
            "http://graph.com/also-disconnected", List.of());

        assertBothApproaches(
            "http://graph.com/start", graph,
            Set.of("http://graph.com/start", "http://graph.com/reachable"));
    }

    @Test
    public void linksToOnlyExternalHostsProduceTheStartPageOnly() {
        Map<String, List<String>> graph = graph(
            "http://internal.com/start", List.of(
                "http://one.com/a", "http://two.com/b", "http://three.com/c"),
            "http://one.com/a", List.of("http://internal.com/hidden"),
            "http://two.com/b", List.of(),
            "http://three.com/c", List.of());

        assertBothApproaches(
            "http://internal.com/start", graph, Set.of("http://internal.com/start"));
    }

    @Test
    public void rootWithoutPathUsesItsAuthorityAsTheHostname() {
        Map<String, List<String>> graph = graph(
            "http://root.com", List.of("http://root.com/a", "http://other.com/a"),
            "http://root.com/a", List.of("http://root.com/b"),
            "http://root.com/b", List.of(),
            "http://other.com/a", List.of());

        assertBothApproaches(
            "http://root.com", graph,
            Set.of("http://root.com", "http://root.com/a", "http://root.com/b"));
    }

    @Test
    public void selfLoopDoesNotCauseRepeatedParsing() {
        Map<String, List<String>> graph = graph(
            "http://self.com/page", List.of("http://self.com/page"));

        assertBothApproaches(
            "http://self.com/page", graph, Set.of("http://self.com/page"));
    }

    @Test
    public void deepChainReachesEveryPageWithinTheSupportedGraphLimit() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        int count = 180;
        for (int i = 0; i < count; i++) {
            String current = "http://deep.com/page" + i;
            String next = "http://deep.com/page" + (i + 1);
            graph.put(current, i + 1 < count ? List.of(next) : List.of());
        }

        Set<String> expected = expectedReachable("http://deep.com/page0", graph);
        assertEquals(count, expected.size());
        assertBothApproaches("http://deep.com/page0", graph, expected);
    }

    @Test
    public void wideBranchingGraphReachesEveryDirectChild() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        List<String> children = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            String child = "http://wide.com/page" + i;
            children.add(child);
            graph.put(child, List.of());
        }
        graph.put("http://wide.com/root", children);

        Set<String> expected = expectedReachable("http://wide.com/root", graph);
        assertEquals(121, expected.size());
        assertBothApproaches("http://wide.com/root", graph, expected);
    }

    @Test
    public void exactMaximumThousandPageGraphIsCrawled() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        List<String> children = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            String child = "http://maximum.com/page" + i;
            children.add(child);
            graph.put(child, List.of());
        }
        graph.put("http://maximum.com/root", children);

        Set<String> expected = expectedReachable("http://maximum.com/root", graph);
        assertEquals(1000, expected.size());
        assertBothApproaches("http://maximum.com/root", graph, expected);
    }

    @Test
    public void generatedGraphMatchesIndependentTraversalOracle() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        for (int i = 0; i < 64; i++) {
            List<String> edges = new ArrayList<>();
            if (i % 2 == 0) {
                edges.add("http://oracle.com/node" + ((i * 7 + 3) % 64));
            }
            if (i % 3 == 0) {
                edges.add("http://oracle.com/node" + ((i * 11 + 5) % 64));
            }
            if (i % 5 == 0) {
                edges.add("http://external.com/node" + i);
            }
            if (i % 7 == 0) {
                edges.add("http://oracle.com/node" + i);
            }
            graph.put("http://oracle.com/node" + i, edges);
        }

        Set<String> expected = expectedReachable("http://oracle.com/node0", graph);
        assertBothApproaches("http://oracle.com/node0", graph, expected);
    }

    @Test
    public void parserIsNotCalledForRejectedOrUnreachableUrls() {
        Map<String, List<String>> graph = graph(
            "http://calls.com/root", List.of(
                "http://calls.com/child", "http://foreign.com/page"),
            "http://calls.com/child", List.of(),
            "http://foreign.com/page", List.of("http://calls.com/never"),
            "http://calls.com/never", List.of());

        Set<String> expected = Set.of("http://calls.com/root", "http://calls.com/child");
        Crawl_1242 crawler = new Crawl_1242();

        RecordingHtmlParser parser = new RecordingHtmlParser(graph);
        assertEquals(expected, Set.copyOf(crawler.crawl("http://calls.com/root", parser)));
        assertEquals(expected, parser.calledUrls());
        assertEquals(1, parser.callCount("http://calls.com/root"));
        assertEquals(1, parser.callCount("http://calls.com/child"));
        assertEquals(0, parser.callCount("http://foreign.com/page"));
        assertEquals(0, parser.callCount("http://calls.com/never"));

        parser = new RecordingHtmlParser(graph);
        assertEquals(expected, Set.copyOf(crawler.crawlWithMap("http://calls.com/root", parser)));
        assertEquals(expected, parser.calledUrls());
        assertEquals(0, parser.callCount("http://foreign.com/page"));
        assertEquals(0, parser.callCount("http://calls.com/never"));
    }

    @Test
    public void missingGraphEntriesAreValidLeafPages() {
        Map<String, List<String>> graph = graph(
            "http://missing.com/root", List.of("http://missing.com/implicit-leaf"));

        assertBothApproaches(
            "http://missing.com/root", graph,
            Set.of("http://missing.com/root", "http://missing.com/implicit-leaf"));
    }

    @Test
    public void repeatedCallsResetVisitedStateForBothApproaches() {
        Crawl_1242 crawler = new Crawl_1242();
        Map<String, List<String>> first = graph(
            "http://first.com/root", List.of("http://first.com/page"),
            "http://first.com/page", List.of());
        Map<String, List<String>> second = graph(
            "http://second.com/root", List.of("http://second.com/page"),
            "http://second.com/page", List.of());

        assertEquals(
            Set.of("http://first.com/root", "http://first.com/page"),
            Set.copyOf(crawler.crawl("http://first.com/root", new RecordingHtmlParser(first))));
        assertEquals(
            Set.of("http://second.com/root", "http://second.com/page"),
            Set.copyOf(crawler.crawl("http://second.com/root", new RecordingHtmlParser(second))));
        assertEquals(
            Set.of("http://first.com/root", "http://first.com/page"),
            Set.copyOf(crawler.crawlWithMap("http://first.com/root", new RecordingHtmlParser(first))));
        assertEquals(
            Set.of("http://second.com/root", "http://second.com/page"),
            Set.copyOf(crawler.crawlWithMap("http://second.com/root", new RecordingHtmlParser(second))));
    }

    @Test
    public void switchingBetweenApproachesDoesNotLeakResults() {
        Crawl_1242 crawler = new Crawl_1242();
        Map<String, List<String>> graph = graph(
            "http://switch.com/root", List.of("http://switch.com/one"),
            "http://switch.com/one", List.of());

        assertEquals(
            Set.of("http://switch.com/root", "http://switch.com/one"),
            Set.copyOf(crawler.crawl("http://switch.com/root", new RecordingHtmlParser(graph))));
        assertEquals(
            Set.of("http://switch.com/root", "http://switch.com/one"),
            Set.copyOf(crawler.crawlWithMap("http://switch.com/root", new RecordingHtmlParser(graph))));
        assertEquals(
            Set.of("http://switch.com/root", "http://switch.com/one"),
            Set.copyOf(crawler.crawl("http://switch.com/root", new RecordingHtmlParser(graph))));
    }

    @Test
    public void returnedListMutationCannotContaminateTheNextCrawl() {
        Crawl_1242 crawler = new Crawl_1242();
        Map<String, List<String>> graph = graph(
            "http://isolation.com/root", List.of("http://isolation.com/page"),
            "http://isolation.com/page", List.of());

        List<String> first = crawler.crawl("http://isolation.com/root", new RecordingHtmlParser(graph));
        first.clear();
        assertEquals(
            Set.of("http://isolation.com/root", "http://isolation.com/page"),
            Set.copyOf(crawler.crawl("http://isolation.com/root", new RecordingHtmlParser(graph))));

        first = crawler.crawlWithMap("http://isolation.com/root", new RecordingHtmlParser(graph));
        first.clear();
        assertEquals(
            Set.of("http://isolation.com/root", "http://isolation.com/page"),
            Set.copyOf(crawler.crawlWithMap("http://isolation.com/root", new RecordingHtmlParser(graph))));
    }

    @Test
    public void nullStartUrlMatchesTheImplementationDefinedFailure() {
        Crawl_1242 crawler = new Crawl_1242();
        RecordingHtmlParser parser = new RecordingHtmlParser(Map.of());

        assertThrows(NullPointerException.class, () -> crawler.crawl(null, parser));
        assertThrows(NullPointerException.class, () -> crawler.crawlWithMap(null, parser));
    }

    @Test
    public void duplicateFanInStillCallsTheSharedTargetOnceInEachApproach() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        List<String> branches = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            String branch = "http://fanin.com/branch" + i;
            branches.add(branch);
            graph.put(branch, List.of("http://fanin.com/shared"));
        }
        graph.put("http://fanin.com/root", branches);
        graph.put("http://fanin.com/shared", List.of());

        Crawl_1242 crawler = new Crawl_1242();
        RecordingHtmlParser parser = new RecordingHtmlParser(graph);
        assertEquals(82, Set.copyOf(crawler.crawl("http://fanin.com/root", parser)).size());
        assertEquals(1, parser.callCount("http://fanin.com/shared"));

        parser = new RecordingHtmlParser(graph);
        assertEquals(82, Set.copyOf(crawler.crawlWithMap("http://fanin.com/root", parser)).size());
        assertEquals(1, parser.callCount("http://fanin.com/shared"));
    }

    private static void assertBothApproaches(
        String startUrl, Map<String, List<String>> graph, Set<String> expected) {

        Crawl_1242 crawler = new Crawl_1242();
        RecordingHtmlParser firstParser = new RecordingHtmlParser(graph);
        List<String> firstResult = crawler.crawl(startUrl, firstParser);
        assertEquals(expected, Set.copyOf(firstResult));
        assertEquals(expected.size(), firstResult.size());
        assertCalledExactlyOnceForEveryReachablePage(expected, firstParser);

        RecordingHtmlParser secondParser = new RecordingHtmlParser(graph);
        List<String> secondResult = crawler.crawlWithMap(startUrl, secondParser);
        assertEquals(expected, Set.copyOf(secondResult));
        assertEquals(expected.size(), secondResult.size());
        assertCalledExactlyOnceForEveryReachablePage(expected, secondParser);
    }

    private static void assertCalledExactlyOnceForEveryReachablePage(
        Set<String> expected, RecordingHtmlParser parser) {
        assertEquals(expected, parser.calledUrls());
        for (String url : expected) {
            assertEquals(1, parser.callCount(url), "parser call count for " + url);
        }
    }

    private static Map<String, List<String>> graph(Object... entries) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            graph.put((String) entries[i], List.copyOf((List<String>) entries[i + 1]));
        }
        return graph;
    }

    /** Independent BFS oracle; it intentionally does not call Crawl_1242.isSameHost. */
    private static Set<String> expectedReachable(
        String startUrl, Map<String, List<String>> graph) {

        String host = independentHostnamePrefix(startUrl);
        Set<String> visited = new LinkedHashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(startUrl);
        while (!queue.isEmpty()) {
            String url = queue.remove();
            if (!independentSameHost(url, host) || !visited.add(url)) {
                continue;
            }
            queue.addAll(graph.getOrDefault(url, List.of()));
        }
        return visited;
    }

    private static String independentHostnamePrefix(String url) {
        int index = url.indexOf('/', 7);
        return index == -1 ? url : url.substring(0, index);
    }

    private static boolean independentSameHost(String url, String host) {
        return url.equals(host) || url.startsWith(host + "/");
    }

    private static final class RecordingHtmlParser implements HtmlParser {
        private final Map<String, List<String>> graph;
        private final Map<String, AtomicInteger> calls = new ConcurrentHashMap<>();

        private RecordingHtmlParser(Map<String, List<String>> graph) {
            this.graph = graph;
        }

        @Override
        public List<String> getUrls(String url) {
            calls.computeIfAbsent(url, ignored -> new AtomicInteger()).incrementAndGet();
            return graph.getOrDefault(url, Collections.emptyList());
        }

        private int callCount(String url) {
            AtomicInteger count = calls.get(url);
            return count == null ? 0 : count.get();
        }

        private Set<String> calledUrls() {
            return new HashSet<>(calls.keySet());
        }
    }
}
