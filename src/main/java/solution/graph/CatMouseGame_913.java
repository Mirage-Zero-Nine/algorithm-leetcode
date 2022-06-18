package solution.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 * The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
 * Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.
 * During each player's turn, they must travel along one edge of the graph that meets where they are.
 * For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 * Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 * Then, the game can end in 3 ways:
 * 1. If ever the Cat occupies the same node as the Mouse, the Cat wins.
 * 2. If ever the Mouse reaches the Hole, the Mouse wins.
 * 3. If ever a position is repeated (ie. the players are in the same position), the game is a draw.
 * Given a graph, and assuming both players play optimally, return 1 if Mouse win, 2 if Cat win, and 0 if is a draw.
 *
 * @author BorisMirage
 * Time: 2019/09/20 22:14
 * Created with IntelliJ IDEA
 */

public class CatMouseGame_913 {
    private final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2, MOUSE_TURN = 1, CAT_TURN = 2;

    /**
     * Minimax problem.
     * Use two 3D arrays as map, stores the status and child number based on each node and turn.
     * After the array is constructed, use a "reversed BFS" to color all nodes.
     * The queue records all nodes that the winner of current node with mouse, cat's position and turn is fixed.
     * Each time, poll out the node at the head of queue, color children based on children's remaining connected nodes.
     * If child's winner is fixed, add this child node to queue.
     * Finally, after all colorable nodes are colored, check the start position to find winner.
     *
     * @param graph given graph edges
     * @return 1 if Mouse win, 2 if Cat win, and 0 if is a draw
     */
    public int catMouseGame(int[][] graph) {
        int N = graph.length;

        /*
         * status[node] : who wins in the end if start from this node
         * status[mouse_location][cat_location][turn] */
        int[][][] statusArr = new int[50][50][3]; //

        /*
         * childNum[node]: the number of neutral children of this node
         * childNum[mouse_location][cat_location][turn] */
        int[][][] childNum = new int[50][50][3];

        for (int m = 0; m < N; ++m) {           // mouse location
            for (int c = 0; c < N; ++c) {       // cat location
                childNum[m][c][MOUSE_TURN] = graph[m].length;
                childNum[m][c][CAT_TURN] = graph[c].length;
                for (int x : graph[c]) {
                    if (x == 0) {       // cat can not stay at the hole (position 0).
                        childNum[m][c][CAT_TURN]--;
                        break;
                    }
                }
            }
        }

        /*
         * enqueued : all nodes are known who wins in the end.
         * Nodes with DRAW status are not in this queue. */
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            for (int t = 1; t <= 2; ++t) {      // turn
                statusArr[0][i][t] = MOUSE_WIN;        // mouse wins if it is at the hole (position 0).
                q.add(new int[]{0, i, t, MOUSE_WIN});
                if (i > 0) {
                    statusArr[i][i][t] = CAT_WIN;      // cat wins if mouse and cat are at the same location.
                    q.add(new int[]{i, i, t, CAT_WIN});
                }
            }
        }

        /*
         * The previous queue has already record nodes with their status.
         * Next step is to finish the coloring, percolate nodes that already known the winner. */
        while (!q.isEmpty()) {
            int[] node = q.remove();

            int mouseLoc = node[0], catLoc = node[1], turn = node[2], status = node[3];

            for (int[] parent : parents(graph, mouseLoc, catLoc, turn)) {       // for every parent of this node (m, c, t)
                int mouseLoc2 = parent[0], catLoc2 = parent[1], turn2 = parent[2];

                if (statusArr[mouseLoc2][catLoc2][turn2] == DRAW) {     // if parent node is a draw

                    /*
                     * Find if the parent node can make a winning move (mouse to MOUSE_WIN, or cat to CAT_WIN).
                     * If so, then child node status is fixed. */
                    if (turn2 == MOUSE_TURN && status == MOUSE_WIN || turn2 == CAT_TURN && status == CAT_WIN) {
                        statusArr[mouseLoc2][catLoc2][turn2] = status;
                        q.add(new int[]{mouseLoc2, catLoc2, turn2, status});
                    } else {

                        /*
                         * Otherwise, this parent has neutral children_num[parent]--.
                         * Enqueue if all children of this parent are colored as losing moves. */
                        childNum[mouseLoc2][catLoc2][turn2]--;
                        if (childNum[mouseLoc2][catLoc2][turn2] == 0) {

                            /*
                             * All children of this node's winner is fixed. And the turn is previous move.
                             * If previous turn is mouse, then current move is cat.
                             * If previous turn is cat, then current move is mouse. */
                            statusArr[mouseLoc2][catLoc2][turn2] = (turn2 == MOUSE_TURN) ? CAT_WIN : MOUSE_WIN;
                            q.add(new int[]{mouseLoc2, catLoc2, turn2, statusArr[mouseLoc2][catLoc2][turn2]});
                        }
                    }
                }
            }
        }

        /*
         * Mouse is at location 1, cat is at location 2.
         * Mouse moves first. */
        return statusArr[1][2][MOUSE_TURN];
    }


    /**
     * Find parents of current node, based on mouse and cat's position, and current turn (m, c, t).
     *
     * @param graph    given graph
     * @param mouseLoc mouse position
     * @param catLoc   cat position
     * @param turn     MOUSE_TURN = 1, CAT_TURN = 2
     * @return nodes can be reached based on specific turn
     */
    private List<int[]> parents(int[][] graph, int mouseLoc, int catLoc, int turn) {
        List<int[]> out = new ArrayList<>();

        if (turn == CAT_TURN) {
            for (int m2 : graph[mouseLoc]) {
                out.add(new int[]{m2, catLoc, MOUSE_TURN});
            }
        } else {
            for (int c2 : graph[catLoc]) {
                if (c2 > 0) {       // cat can not stay at the hole (position 0).
                    out.add(new int[]{mouseLoc, c2, CAT_TURN});
                }
            }
        }

        return out;
    }
}
