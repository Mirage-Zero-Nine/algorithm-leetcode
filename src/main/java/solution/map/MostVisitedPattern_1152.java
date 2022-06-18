package solution.map;

import java.util.*;

/**
 * You are given 3 arrays: username, timestamp and website of the same length N.
 * The ith tuple means that the user with name username[i] visited the website website[i] at time timestamp[i].
 * A 3-sequence is a list of not necessarily different websites of length 3 sorted in ascending order by the time of their visits.
 * Find the 3-sequence visited at least once by the largest number of users.
 * If there is more than one solution, return the lexicographically minimum solution.
 * A 3-sequence X is lexicographically smaller than a 3-sequence Y if X[0] < Y[0] or X[0] == Y[0] and (X[1] < Y[1] or X[1] == Y[1] and X[2] < Y[2]).
 * It is guaranteed that there is at least one user who visited at least 3 websites. No user visits two websites at the same time.
 *
 * @author BorisMirage
 * Time: 2019/08/10 16:07
 * Created with IntelliJ IDEA
 */

public class MostVisitedPattern_1152 {
    /**
     * Hash map with sorted linked list.
     *
     * @param username  username list
     * @param timestamp time stamp list
     * @param website   visited website list
     * @return 3-sequence visited at least once by the largest number of users
     */
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = timestamp.length;

        List<List<String>> sessions = new ArrayList<>();
        for (int i = 0; i < n; i++) {       // sort sessions list by time, and avoid web duplicated
            sessions.add(new ArrayList<>());
            sessions.get(i).add(username[i]);
            sessions.get(i).add("" + timestamp[i]);
            sessions.get(i).add(website[i]);
        }

        sessions.sort(Comparator.comparingInt(a -> Integer.parseInt(a.get(1))));

        Map<String, List<String>> visited = new HashMap<>();        //(name, list<web>)
        for (int i = 0; i < n; i++) {
            visited.putIfAbsent(sessions.get(i).get(0), new ArrayList<>());     // add user visited
            visited.get(sessions.get(i).get(0)).add(sessions.get(i).get(2));
        }

        Map<String, Integer> sequence = new HashMap<>();        //(sequence, count)
        int maxCount = 0;
        String maxSequence = "";
        for (String name : visited.keySet()) {
            List<String> list = visited.get(name);
            if (list.size() < 3) {
                continue;
            }
            // build users' all 3-sequences, use set in case duplicated 3-sequences
            Set<String> subsequence = subsequence(list);
            for (String seq : subsequence) {
                sequence.put(seq, sequence.getOrDefault(seq, 0) + 1);
                if (sequence.get(seq) > maxCount) {
                    maxCount = sequence.get(seq);
                    maxSequence = seq;
                } else if (sequence.get(seq) == maxCount && seq.compareTo(maxSequence) < 0) {
                    maxSequence = seq;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(maxSequence.split(",")));
    }

    private Set<String> subsequence(List<String> list) {
        int n = list.size();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    res.add(list.get(i) + "," + list.get(j) + "," + list.get(k));
                }
            }
        }
        return res;
    }
}
