package solution.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group.
 * The items and the groups are zero indexed. A group can have no item belonging to it.
 * Return a sorted list of the items such that:
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 * @author BorisMirage
 * Time: 2019/09/22 16:26
 * Created with IntelliJ IDEA
 */

public class SortItems_1203 {
    /**
     * Two level topological sorting.
     * Construct the relationship of items and its prior items.
     * Then calculate the topological sort of groups, check if there is any conflict.
     * Finally, calculate topological sort of items in group, find if there is any conflict.
     *
     * @param n           number of items
     * @param m           number of group
     * @param group       group list
     * @param beforeItems prior item list
     * @return any solution if there is more than one solution and return an empty list if there is no solution
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();     // each group's item
        Map<Integer, Integer> groupIn = new HashMap<>();                // indegree of each group
        Map<Integer, Set<Integer>> groupAdj = new HashMap<>();      // dependency of group
        Map<Integer, Integer> itemIn = new HashMap<>();                 // indegree of each item
        Map<Integer, Set<Integer>> itemAdj = new HashMap<>();       // dependency of item

        int minGroup = m;

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = minGroup++;      // assign none-group elements a group for mapping
            }

            List<Integer> l = groupToItems.getOrDefault(group[i], new ArrayList<>());
            l.add(i);
            groupToItems.put(group[i], l);
        }

        for (int i = 0; i < beforeItems.size(); i++) {
            int toGroup = group[i];
            for (int from : beforeItems.get(i)) {
                int fromGroup = group[from];
                if (toGroup != fromGroup) {     // different group
                    Set<Integer> set = groupAdj.getOrDefault(fromGroup, new HashSet<>());
                    if (!set.contains(toGroup)) {
                        set.add(toGroup);
                        groupAdj.put(fromGroup, set);
                        groupIn.put(toGroup, groupIn.getOrDefault(toGroup, 0) + 1);
                    }
                } else {        // same group
                    Set<Integer> set = itemAdj.getOrDefault(from, new HashSet<>());
                    set.add(i);
                    itemAdj.put(from, set);
                    itemIn.put(i, itemIn.getOrDefault(i, 0) + 1);
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> groupAns = new ArrayList<>();

        for (int i = 0; i < minGroup; i++) {         // verify group order
            if (groupIn.getOrDefault(i, 0) == 0) {
                q.offer(i);
                groupAns.add(i);
            }
        }

        while (!q.isEmpty()) {
            int curGroup = q.poll();
            for (int next : groupAdj.getOrDefault(curGroup, new HashSet<>())) {
                groupIn.put(next, groupIn.get(next) - 1);
                if (groupIn.get(next) == 0) {
                    q.offer(next);
                    groupAns.add(next);
                }
            }
        }

        if (groupAns.size() != minGroup) {      // found cycle in groups
            return new int[0];
        }

        List<Integer> out = new ArrayList<>();

        for (int groupId : groupAns) {      // verify item order
            List<Integer> items = groupToItems.getOrDefault(groupId, new ArrayList<>());
            int num = 0;
            for (int item : items) {
                if (itemIn.getOrDefault(item, 0) == 0) {
                    q.offer(item);
                    out.add(item);
                    num++;
                }
            }

            while (!q.isEmpty()) {
                int currentItem = q.poll();
                for (int next : itemAdj.getOrDefault(currentItem, new HashSet<>())) {
                    itemIn.put(next, itemIn.get(next) - 1);
                    if (itemIn.get(next) == 0) {
                        q.offer(next);
                        out.add(next);
                        num++;
                    }
                }
            }
            if (num != items.size()) {
                return new int[0];
            }
        }

        return out.stream().mapToInt(i -> i).toArray();
    }
}
