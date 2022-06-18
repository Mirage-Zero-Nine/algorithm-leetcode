package playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structure of Trie.
 *
 * @author BorisMirage
 * Time: 2019/07/03 22:02
 * Created with IntelliJ IDEA
 */

public class Trie {
    /**
     * Nodes in trie.
     */
    private char val;       // value of current node
    private final Map<Character, Trie> m = new HashMap<>();       // save children of current trie
    private boolean end = false;

    /**
     * Initialization.
     */
    Trie() {
    }

    /**
     * Set current node's value.
     *
     * @param val value to be set
     */
    void setVal(char val) {
        this.val = val;
    }

    /**
     * Add child to current node.
     *
     * @param val children value
     */
    void addChild(char val) {
        this.m.put(val, new Trie());
        this.m.get(val).setVal(val);
    }

    /**
     * Mark current node as end of word.
     *
     * @param b boolean to be set
     */
    void setEnd(boolean b) {
        this.end = b;
    }

    /**
     * Check if current node is an end of a word.
     *
     * @return true if current node is an end of a word, false otherwise
     */
    boolean isEnd() {
        return this.end;
    }

    /**
     * Return the child based on given value.
     *
     * @param val value of child
     * @return child node
     */
    Trie getChild(char val) {
        return m.get(val);
    }

    /**
     * Get value of current node.
     *
     * @return value in current node
     */
    char getVal() {
        return this.val;
    }

    /**
     * Check if given char is contained in current node's child.
     *
     * @param val given value
     * @return true if given char is in child
     */
    boolean containsChild(char val) {
        return m.containsKey(val);
    }

    List<Trie> getAllChildren() {
        return new ArrayList<>(m.values());
    }
}
