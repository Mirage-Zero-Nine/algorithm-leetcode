package Lib;

import java.util.List;

/**
 * HtmlParser interface.
 *
 * @author BorisMirage
 * Time: 2020/03/29 18:02
 * Created with IntelliJ IDEA
 */

public interface HtmlParser {
    /**
     * Return a list of all urls from a webpage of given url.
     * This is a blocking call, that means it will do HTTP request and return when this request is finished.
     *
     * @param url given url
     * @return a list of all urls from a webpage of given url
     */
    List<String> getUrls(String url);
}
