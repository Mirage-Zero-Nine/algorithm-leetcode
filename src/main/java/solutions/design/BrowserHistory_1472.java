package solutions.design;

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
    private final List<String> history;
    private int current;

    /**
     * Stores every page on the current history path in an array list and keeps the index of the
     * displayed page.  The list contains no forward branch after {@link #visit(String)}: visiting
     * from the middle first removes that branch, then appends the new page.  This gives constant
     * time navigation while retaining O(n) pages for a path of length n.
     *
     * @param homepage the initial page; it becomes history position zero
     *                 <p>
     *                 Time complexity: O(1), because initialization stores one page.
     *                 Auxiliary space complexity: O(1) beyond the retained history, which initially contains one
     *                 page.
     */
    public BrowserHistory_1472(String homepage) {
        history = new ArrayList<>(List.of(homepage));
        current = 0;
    }

    /**
     * Visits a page from the current position.  Any pages after the current index are no longer
     * reachable because a new visit starts a new forward branch; appending the URL then makes it
     * the displayed page.
     *
     * @param url the page to append to the current history path
     *            <p>
     *            Time complexity: O(f + n) worst case, where f is the number of discarded forward pages and n
     *            is the retained history size when an ArrayList resize copies its backing array; without a
     *            resize, the append is amortized O(1) after the O(f) clear.
     *            Auxiliary space complexity: O(n) worst case for a temporary replacement backing array during
     *            an ArrayList resize, where n is the retained history size; otherwise the operation reuses
     *            the existing backing storage and only retains the resulting O(n)-page history.
     */
    public void visit(String url) {
        history.subList(current + 1, history.size()).clear();
        history.add(url);
        current = history.size() - 1;
    }

    /**
     * Moves toward the homepage by at most {@code steps}.  Clamping the index at zero handles a
     * request larger than the available backward history without creating an invalid position.
     *
     * @param steps the maximum number of positions to move backward
     * @return the page displayed after moving
     * <p>
     * Time complexity: O(1), independent of the number of pages and requested steps.
     * Auxiliary space complexity: O(1), because only the current index changes.
     */
    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    /**
     * Moves toward the newest page by at most {@code steps}.  The available distance is computed
     * before adding it, so even a very large positive step cannot overflow the current index.
     *
     * @param steps the maximum number of positions to move forward
     * @return the page displayed after moving
     * <p>
     * Time complexity: O(1), independent of the number of pages and requested steps.
     * Auxiliary space complexity: O(1), because only the current index changes.
     */
    public String forward(int steps) {
        current += Math.min(history.size() - 1 - current, steps);
        return history.get(current);
    }
}
