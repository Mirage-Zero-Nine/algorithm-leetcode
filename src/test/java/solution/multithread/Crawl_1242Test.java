package solution.multithread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import library.HtmlParser;
import org.junit.jupiter.api.Test;

public class Crawl_1242Test {

    @Test
    public void testCrawlVisitsSameHostPages() {
        Crawl_1242 crawler = new Crawl_1242();
        FakeHtmlParser parser = new FakeHtmlParser(Map.of(
            "http://news.com/a", List.of("http://news.com/b", "http://news.com/c"),
            "http://news.com/b", List.of("http://news.com/d"),
            "http://news.com/c", List.of(),
            "http://news.com/d", List.of()
        ));

        Set<String> result = new LinkedHashSet<>(crawler.crawl("http://news.com/a", parser));
        assertEquals(Set.of(
            "http://news.com/a",
            "http://news.com/b",
            "http://news.com/c",
            "http://news.com/d"
        ), result);
    }

    @Test
    public void testCrawlIgnoresDifferentHostsAndCycles() {
        Crawl_1242 crawler = new Crawl_1242();
        FakeHtmlParser parser = new FakeHtmlParser(Map.of(
            "http://site.com/root", List.of("http://site.com/child", "http://other.com/x"),
            "http://site.com/child", List.of("http://site.com/root", "http://site.com/leaf"),
            "http://site.com/leaf", List.of("http://other.com/y")
        ));

        Set<String> result = new LinkedHashSet<>(crawler.crawl("http://site.com/root", parser));
        assertEquals(Set.of(
            "http://site.com/root",
            "http://site.com/child",
            "http://site.com/leaf"
        ), result);
    }

    @Test
    public void testCrawlWithRootHostOnlyAndEmptyChildren() {
        Crawl_1242 crawler = new Crawl_1242();
        FakeHtmlParser parser = new FakeHtmlParser(Map.of(
            "http://solo.com", List.of("http://other.com/page")
        ));

        Set<String> result = new LinkedHashSet<>(crawler.crawl("http://solo.com", parser));
        assertEquals(Set.of("http://solo.com"), result);
    }

    @Test
    public void testNullStartUrlThrows() {
        Crawl_1242 crawler = new Crawl_1242();
        FakeHtmlParser parser = new FakeHtmlParser(Map.of());
        assertThrows(NullPointerException.class, () -> crawler.crawl(null, parser));
        assertThrows(NullPointerException.class, () -> crawler.crawlWithMap(null, parser));
    }

    @Test
    public void testLargeGraphCrawl() {
        Crawl_1242 crawler = new Crawl_1242();
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < 40; i++) {
            List<String> next = new ArrayList<>();
            if (i + 1 < 40) {
                next.add("http://bulk.com/page" + (i + 1));
            }
            if (i + 2 < 40) {
                next.add("http://bulk.com/page" + (i + 2));
            }
            next.add("http://external.com/" + i);
            graph.put("http://bulk.com/page" + i, next);
        }

        Set<String> result = new LinkedHashSet<>(crawler.crawl("http://bulk.com/page0", new FakeHtmlParser(graph)));
        assertEquals(40, result.size());
        assertTrue(result.contains("http://bulk.com/page0"));
        assertTrue(result.contains("http://bulk.com/page39"));
    }

    @Test
    public void testCrawlWithMapCurrentlyOnlyKeepsStartUrl() {
        Crawl_1242 crawler = new Crawl_1242();
        FakeHtmlParser parser = new FakeHtmlParser(Map.of(
            "http://news.com/a", List.of("http://news.com/b", "http://news.com/c"),
            "http://news.com/b", List.of("http://news.com/d"),
            "http://news.com/c", List.of(),
            "http://news.com/d", List.of()
        ));

        Set<String> result = new LinkedHashSet<>(crawler.crawlWithMap("http://news.com/a", parser));
        assertEquals(Set.of("http://news.com/a"), result);
    }

    private static final class FakeHtmlParser implements HtmlParser {
        private final Map<String, List<String>> graph;

        private FakeHtmlParser(Map<String, List<String>> graph) {
            this.graph = graph;
        }

        @Override
        public List<String> getUrls(String url) {
            return graph.getOrDefault(url, Collections.emptyList());
        }
    }
}
