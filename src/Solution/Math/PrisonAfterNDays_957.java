package Solution.Math;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 * @author BorisMirage
 * Time: 2019/07/19 16:41
 * Created with IntelliJ IDEA
 */

public class PrisonAfterNDays_957 {
    /**
     * Mod 14.
     *
     * @param cells given cells
     * @param N     N days
     * @return the state of the prison after N days
     */
    public int[] prisonAfterNDays(int[] cells, int N) {

        for (N = (N - 1) % 14 + 1; N > 0; --N) {
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i) {
                cells2[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
            }
            cells = cells2;
        }

        return cells;
    }
}
