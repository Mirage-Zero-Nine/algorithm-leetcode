package solutions.concurrency;

import library.HtmlParser;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Given a url startUrl and an interface HtmlParser.
 * Implement a Multi-threaded web crawler to crawl all links that are under the same hostname as startUrl.
 * Return all urls obtained by your web crawler in any order.
 * Your crawler should:
 * 1. Start from the page: startUrl
 * 2. Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
 * 3. Do not crawl the same link twice.
 * 4. Explore only the links that are under the same hostname as startUrl.
 *
 * @author BorisMirage
 * Time: 2020/03/29 18:04
 * Created with IntelliJ IDEA
 */

public class Crawl_1242 {
    /**
     * Multi thread implemented by a static volatile HashSet and synchronized adding function.
     *
     * @param startUrl   start URL
     * @param htmlParser HTML Parser class
     * @return all urls obtained by web crawler in any order
     */
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {

        String hostname = hostnamePrefix(startUrl);

        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        Crawler.visited = ConcurrentHashMap.newKeySet();        // thread-safe set
        Thread thread = new Thread(crawler);
        thread.start();
        Crawler.joinThread(thread);             // waiting for complete

        return new LinkedList<>(Crawler.visited);
    }

    /**
     * Use ConcurrentHashMap instead of volatile and synchronized keyword.
     *
     * @param startUrl   start URL
     * @param htmlParser HTML Parser class
     * @return all urls obtained by web crawler in any order
     */
    public List<String> crawlWithMap(String startUrl, HtmlParser htmlParser) {

        String hostname = hostnamePrefix(startUrl);

        CrawlerWithMap crawler = new CrawlerWithMap(startUrl, hostname, htmlParser);
        CrawlerWithMap.map = new ConcurrentHashMap<>();
        CrawlerWithMap.result = ConcurrentHashMap.newKeySet();
        Thread thread = new Thread(crawler);
        thread.start();

        CrawlerWithMap.joinThread(thread);
        return new ArrayList<>(CrawlerWithMap.result);
    }

    /**
     * Returns the scheme and authority portion used by this problem's HTTP URLs.
     * The problem guarantees that URLs have no port and use {@code http}, so a
     * slash after the authority is the only delimiter that needs to be handled.
     */
    private static String hostnamePrefix(String url) {
        int index = url.indexOf('/', 7);
        return (index != -1) ? url.substring(0, index) : url;
    }

    /**
     * Tests exact host membership rather than accepting a hostname prefix such
     * as {@code http://example.com.evil}.
     */
    static boolean isSameHost(String url, String hostname) {
        return url.equals(hostname) || url.startsWith(hostname + "/");
    }
}

/**
 * Implemented the Crawler that
 */
class Crawler implements Runnable {
    String startURL;
    String hostName;
    HtmlParser htmlParser;

    /**
     * Shared across all crawler threads. Must be a thread-safe Set; assigned
     * fresh in {@link Crawl_1242#crawl(String, library.HtmlParser)} per call.
     */
    public static volatile Set<String> visited;

    /**
     * Crawler constructor.
     *
     * @param startURL   start URL
     * @param hostName   host name
     * @param htmlParser HTML Parser class
     */
    public Crawler(String startURL, String hostName, HtmlParser htmlParser) {
        this.startURL = startURL;
        this.hostName = hostName;
        this.htmlParser = htmlParser;
    }

    /**
     * Override run method in Crawler.
     *
     * Uses {@code Set#add}'s atomic insert-if-absent semantics (provided by
     * {@link ConcurrentHashMap#newKeySet()}) as the single source of truth
     * for "have we already crawled this URL". A separate contains-then-add
     * check would be a check-then-act race.
     */
    @Override
    public void run() {
        if (Crawl_1242.isSameHost(this.startURL, hostName) && visited.add(startURL)) {
            List<Thread> threads = new LinkedList<>();
            for (String s : htmlParser.getUrls(startURL)) {
                Crawler crawler = new Crawler(s, hostName, htmlParser);
                Thread thread = new Thread(crawler);
                thread.start();
                threads.add(thread);
            }

            for (Thread t : threads) {
                joinThread(t);
            }
        }
    }

    /**
     * One thread to wait until another thread completes its execution.
     *
     * @param thread waits for this thread to die
     */
    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Implemented Crawler with ConcurrentHashMap.
 */
class CrawlerWithMap implements Runnable {
    String startUrl;
    String hostname;
    HtmlParser htmlParser;
    public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    public static Set<String> result = ConcurrentHashMap.newKeySet();

    /**
     * Crawler constructor.
     *
     * @param startURL   start URL
     * @param hostName   host name
     * @param htmlParser HTML Parser class
     */
    public CrawlerWithMap(String startURL, String hostName, HtmlParser htmlParser) {
        this.startUrl = startURL;
        this.hostname = hostName;
        this.htmlParser = htmlParser;
    }

    /**
     * Override run method in Crawler.
     */
    @Override
    public void run() {
        if (Crawl_1242.isSameHost(this.startUrl, hostname) && result.add(this.startUrl)) {

            List<Thread> threads = new ArrayList<>();

            for (String s : htmlParser.getUrls(startUrl)) {
                CrawlerWithMap crawler = new CrawlerWithMap(s, hostname, htmlParser);
                Thread thread = new Thread(crawler);
                thread.start();
                threads.add(thread);
            }
            for (Thread t : threads) {
                joinThread(t);      // wait for all threads to complete
            }
        }
    }

    /**
     * One thread to wait until another thread completes its execution.
     *
     * @param thread waits for this thread to die
     */
    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
