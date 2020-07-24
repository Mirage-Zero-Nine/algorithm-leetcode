package Solution.Others;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * @author BorisMirage
 * Time: 2019/01/14 13:40
 * Created with IntelliJ IDEA
 */

public class GetRow_119 {
    /**
     * @param rowIndex kth index
     * @return kth index row of the Pascal's triangle.
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < res.size() - 1; j++) {
                res.set(j, res.get(j) + res.get(j + 1));
            }
            res.add(0, 1);
        }
        return res;
    }

    public static void main(String[] args) {
        GetRow_119 gen = new GetRow_119();
        System.out.println(gen.getRow(3));
    }
}
