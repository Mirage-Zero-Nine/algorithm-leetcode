package library;

/**
 * An interface of immutable linked list.
 *
 * @author BorisMirage
 * Time: 2020/03/25 16:07
 * Created with IntelliJ IDEA
 */

public interface ImmutableListNode {
    /**
     * Print value of the current node.
     */
    void printValue();

    /**
     * Return the next node.
     *
     * @return the next node
     */
    ImmutableListNode getNext();
}
