package Solution.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * In a project, you have a list of required skills req_skills, and a list of people.
 * The i-th person people[i] contains a list of skills that person has.
 * Consider a sufficient team:
 * A set of people that for every required skill in req_skills.
 * We can represent these teams by the index of each person.
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * You may return the answer in any order.  It is guaranteed an answer exists.
 * Constraints:
 * 1. 1 <= req_skills.length <= 16
 * 2. 1 <= people.length <= 60
 * 3. 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * 4. Elements of req_skills and people[i] are (respectively) distinct.
 * 5. req_skills[i][j], people[i][j][k] are lowercase English letters.
 * 6. Every skill in people[i] is a skill in req_skills.
 * 7. It is guaranteed a sufficient team exists.
 *
 * @author BorisMirage
 * Time: 2019/09/17 19:49
 * Created with IntelliJ IDEA
 */

public class SmallestSufficientTeam_1125 {

    private List<Integer> min = new ArrayList<>();      // min size of group

    /**
     * Backtracking + pruning.
     * Convert each person's skill into bits for better union operation.
     *
     * @param req_skills required skills
     * @param people     skill that each person has
     * @return any sufficient team of the smallest possible size, represented by the index of each person
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int[] skill = new int[people.size()];
        HashMap<String, Integer> skillMap = new HashMap<>();

        int n = 0;
        for (String s : req_skills) {
            skillMap.put(s, n++);       // mapping skills to ith bits
        }

        for (int i = 0; i < people.size(); i++) {
            int p = 0;
            for (int j = 0; j < people.get(i).size(); j++) {
                p |= 1 << skillMap.get(people.get(i).get(j));
            }
            skill[i] = p;
        }

        backtracking(0, skill, findDuplicated(skill), n, new LinkedList<>());

        int[] out = new int[min.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = min.get(i);
        }

        return out;
    }


    /**
     * Backtracking with pruning to find min size of group.
     * First, if current group size is smaller than min group, skip current round.
     * Second, if a person's skill is fully covered by others, skip this person.
     * After that, find a person whose skill can cover the first missing skill and continue the backtracking process.
     *
     * @param totalSkill current total having skills, in integer
     * @param skill      skill that each person owning
     * @param duplicated mark person whose skill is entirely covered by someone else
     * @param n          total skills number
     * @param tmp        one possible min group
     */
    private void backtracking(int totalSkill, int[] skill, boolean[] duplicated, int n, List<Integer> tmp) {

        if (totalSkill == (1 << n) - 1) {       // if all bits are 1, then all skills has been satisfied
            if (min.size() == 0 || tmp.size() < min.size()) {       // find min group size
                min = new ArrayList<>(tmp);
            }
        }

        if (min.size() != 0 && tmp.size() >= min.size()) {      // pruning 1: pass all larger groups
            return;
        }

        int zeroBit = 0;
        while (((totalSkill >> zeroBit) & 1) == 1) {        // find first missing skill
            zeroBit++;
        }

        for (int i = 0; i < skill.length; i++) {
            if (!duplicated[i]) {
                int person = skill[i];
                if (((person >> zeroBit) & 1) == 1) {       // if current person has the missing skill
                    tmp.add(i);
                    backtracking(totalSkill | person, skill, duplicated, n, tmp);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }


    /**
     * Mark true if there exist one element whose all bits are covered by another element.
     *
     * @param skill skill array
     * @return boolean array mark all covered elements
     */
    private boolean[] findDuplicated(int[] skill) {
        boolean[] out = new boolean[skill.length];
        for (int i = 0; i < skill.length; i++) {
            for (int j = i + 1; j < skill.length; j++) {
                if ((skill[i] & skill[j]) == Math.min(skill[i], skill[j])) {
                    out[(skill[i] > skill[j]) ? j : i] = true;      // the covered one marked as true
                }
            }
        }

        return out;
    }
}
