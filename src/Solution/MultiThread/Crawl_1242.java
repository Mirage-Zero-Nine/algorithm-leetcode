package Solution.MultiThread;

import Lib.HtmlParser;

import java.util.*;
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

        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        Crawler.visited = new HashSet<>();      // static property belongs to class
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

        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        CrawlerWithMap crawler = new CrawlerWithMap(startUrl, hostname, htmlParser);
        CrawlerWithMap.map = new ConcurrentHashMap<>();
        CrawlerWithMap.result = ConcurrentHashMap.newKeySet();
        Thread thread = new Thread(crawler);
        thread.start();

        CrawlerWithMap.joinThread(thread);
        return new ArrayList<>(CrawlerWithMap.result);
    }
}

/**
 * Implemented the Crawler that
 */
class Crawler implements Runnable {
    String startURL;
    String hostName;
    HtmlParser htmlParser;

    public static volatile HashSet<String> visited;     // visible to all other threads

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
     */
    @Override
    public void run() {
        if (this.startURL.startsWith(hostName) && !visited.contains(this.startURL)) {
            addUrl(visited, startURL);
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
     * Add visited URL to hash set. Thread safe by keyword synchronized.
     *
     * @param visited hash set contains all visited URL
     * @param url     URL to be added
     */
    public synchronized void addUrl(Set<String> visited, String url) {
        visited.add(url);
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
        if (this.startUrl.startsWith(hostname) && !result.contains(this.startUrl)) {

            result.add(this.startUrl);
            List<Thread> threads = new ArrayList<>();

            for (String s : htmlParser.getUrls(startUrl)) {
                Crawler crawler = new Crawler(s, hostname, htmlParser);
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