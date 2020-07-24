package Solution.DynamicProgramming;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * @author BorisMirage
 * Time: 2019/01/09 19:32
 * Created with IntelliJ IDEA
 */

public class Generate_118 {
    /**
     * Dynamic programming.
     * dp(i, j) = dp(i - 1, j - 1) + dp(i - 1, j)
     *
     * @param numRows number of total rows
     * @return Pascal's triangle in linked list
     */
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (numRows == 0) {
            return out;
        }

        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new LinkedList<>();
            for (int j = 0; j <= i - 1; j++) {
                temp.add((j == 0) ? 1 : out.get(i - 1).get(j - 1) + out.get(i - 1).get(j));     // first line has one element
            }
            temp.add(1);
            out.add(temp);
        }

        return out;
    }

    public static void main(String[] args) {
        Generate_118 gen = new Generate_118();
        System.out.println(gen.generate(4));
    }
}

