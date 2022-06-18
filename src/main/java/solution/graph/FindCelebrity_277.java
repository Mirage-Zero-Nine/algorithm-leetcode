package solution.graph;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is to ask question to get information of whether A knows B.
 * Find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party.
 * Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 *
 * @author BorisMirage
 * Time: 2019/07/18 17:23
 * Created with IntelliJ IDEA
 */

public class FindCelebrity_277 {
    /**
     * Celebrity has no out degree and n indegree.
     * Traverse from 0 to n - 1, if current celebrity knows anyone, then this one is a possible celebrity.
     * After this traverse, the only possible celebrity can be found.
     * Traverse again to confirm if this celebrity has any out degree, or if exists anyone this celebrity does not know.
     *
     * @param n n guests
     * @return celebrity's label if there is a celebrity in the party, if there is no celebrity, return -1
     */
    public int findCelebrity(int n) {
        int celebrity = 0;      // starts at 0

        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i)) {
                celebrity = i;
            }
        }

        for (int i = 0; i < n; i++) {

            if ((i != celebrity) && (knows(celebrity, i) || !knows(i, celebrity))) {
                return -1;
            }
        }

        return celebrity;
    }

    /**
     * Definition of knows function.
     * This function is only for reference, it has no function until the true relation of guests are given.
     *
     * @param a first guest
     * @param b second guest
     * @return true or false
     */
    private boolean knows(int a, int b) {
        return a > b;
    }
}
