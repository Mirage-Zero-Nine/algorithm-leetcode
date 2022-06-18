package solution.dfs;

import java.util.*;

/**
 * A kingdom consists of a king, his children, his grandchildren, and so on.
 * Every once in a while, someone in the family dies or a child is born.
 * The kingdom has a well-defined order of inheritance that consists of the king as the first member.
 * Let's define the recursive function Successor(x, curOrder), which given a person x and the inheritance order so far.
 * Returns who should be the next person after x in the order of inheritance.
 *
 * @author BorisMirage
 * Time: 2020/09/27 13:46
 * Created with IntelliJ IDEA
 */

public class ThroneInheritance_1600 {
    private final Map<String, List<String>> parent = new HashMap<>();        // stores the parent - child relationship
    private final Set<String> dead = new HashSet<>();                      // stores dead people
    private final String king;

    /**
     * Build a tree and the king is the root. Keep a hash map to store each one and its child.
     * Keep a hash set to store the death people.
     * When a new child birth, add it to map. When a person dead, add it to set (but not remove them from map).
     * When getting the order, do the DFS from root (king), the inheritance order simply follows the DFS traverse order.
     *
     * @param kingName name of king
     */
    public ThroneInheritance_1600(String kingName) {
        king = kingName;
        parent.put(king, new ArrayList<>());
    }

    /**
     * Add the new birth to map.
     *
     * @param parentName parent of child
     * @param childName  name of child
     */
    public void birth(String parentName, String childName) {
        parent.get(parentName).add(childName);
        parent.putIfAbsent(childName, new ArrayList<>());
    }

    /**
     * Add the dead people to dead set, but not remove them from the map.
     *
     * @param name name of dead people
     */
    public void death(String name) {
        dead.add(name);
    }

    /**
     * Get the inheritance order. Starts at king.
     *
     * @return inheritance order starts at king
     */
    public List<String> getInheritanceOrder() {
        List<String> out = new ArrayList<>();
        dfs(king, out);
        return out;
    }

    /**
     * Do the DFS to get the inheritance order. Note that dead people will be excluded.
     *
     * @param parent current parent
     * @param out    output list stores inheritance order
     */
    private void dfs(String parent, List<String> out) {
        if (!dead.contains(parent)) {
            out.add(parent);
        }
        for (String child : this.parent.get(parent)) {
            dfs(child, out);
        }
    }
}
