package solution.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 * Implement the BrowserHistory class:
 * 1. BrowserHistory_1472(string homepage) Initializes the object with the homepage of the browser.
 * 2. void visit(string url) visits url from the current page. It clears up all the forward history.
 * 3. string back(int steps) Move steps back in history.
 * 4. string forward(int steps) Move steps forward in history.
 * If you can only move x steps in the history and steps > x, you will return only x steps.
 * Return the current url after moving back/forward in history at most steps.
 *
 * @author BorisMirage
 * Time: 2020/06/21 08:36
 * Created with IntelliJ IDEA
 */

public class BrowserHistory_1472 {
    List<String> history = new ArrayList<>();
    int position = 0;

    /**
     * Keep a linked list to store the visited pages. Use an integer as the current position.
     * Note that if visit a new page, all later history will be removed.
     *
     * @param homepage start page
     */
    public BrowserHistory_1472(String homepage) {
        history.add(homepage);
    }

    /**
     * Add a new visited URL.
     * Note that when visited a new URL, the forward history will be cleared.
     *
     * @param url visited page
     */
    public void visit(String url) {
        history.subList(position + 1, history.size()).clear();      // clean all forward history
        history.add(url);
        position++;
    }

    /**
     * Move steps back in history. If steps larger than max number in history, return the largest one in history list.
     *
     * @param steps move steps back in history
     * @return the current url after moving back in history at most steps
     */
    public String back(int steps) {
        position = Math.max(0, position - steps);

        return history.get(position);
    }

    /**
     * Move steps forward in history. If steps larger than max number in history, return the first one in history list.
     *
     * @param steps move forward x steps in the history
     * @return the current url after forwarding in history at most steps
     */
    public String forward(int steps) {
        position = Math.min(history.size() - 1, position + steps);

        return history.get(position);
    }
}

