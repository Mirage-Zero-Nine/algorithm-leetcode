package Solution.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to].
 * Reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * Note:
 * 1. If there are multiple valid itineraries, return the itinerary that has the smallest lexical order.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * 2. All airports are represented by three capital letters (IATA code).
 * 3. All tickets form at least one valid itinerary.
 *
 * @author BorisMirage
 * Time: 2019/06/17 17:03
 * Created with IntelliJ IDEA
 */

public class FindItinerary_332 {
    /**
     * DFS search in graph.
     *
     * @param tickets list contains all departure - destination pair
     * @return reconstructed in order itinerary
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> out = new ArrayList<>();

        if (tickets.size() == 0) {
            return out;
        }

        HashMap<String, PriorityQueue<String>> m = new HashMap<>();

        for (List<String> ticket : tickets) {
            if (m.containsKey(ticket.get(0))) {
                m.get(ticket.get(0)).add(ticket.get(1));
            } else {
                PriorityQueue<String> q = new PriorityQueue<>();
                q.add(ticket.get(1));
                m.put(ticket.get(0), q);
            }
        }

        dfs(m, out, "JFK");

        return out;
    }

    /**
     * DFS searching in graph to obtain the output list.
     *
     * @param m     hash map contains departure - destination
     * @param out   output list
     * @param start departure
     */
    private void dfs(HashMap<String, PriorityQueue<String>> m, List<String> out, String start) {

        while (m.containsKey(start) && !m.get(start).isEmpty()) {       // DFS to the end of path
            String destination = m.get(start).poll();
            dfs(m, out, destination);
        }

        out.add(0, start);
    }
}
