package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/07 11:05
 * Created with IntelliJ IDEA
 */

public class CountBattleships419Test {

    private CountBattleships_419 test;

    @BeforeEach
    public void setUp() {
        test = new CountBattleships_419();
    }

    @Test
    public void test() {
        char[][] board = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        assertEquals(2, test.countBattleships(board));
    }

    @Test
    public void testquick() {
        char[][] board = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        assertEquals(2, test.countBattleshipsQuick(board));
    }

    @Test
    public void test1() {
        char[][] board = {
                {'X', '.', '.', 'X'},
        };
        assertEquals(2, test.countBattleships(board));
    }

    @Test
    public void test1quick() {
        char[][] board = {
                {'X', '.', '.', 'X'},
        };
        assertEquals(2, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleships_NullBoard_ThrowsException() {
        char[][] board = null;
        assertEquals(0, test.countBattleships(board));
        assertEquals(0, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleships_BoardEmpty_ReturnsZero() {
        char[][] board = new char[0][0];
        assertEquals(0, test.countBattleships(board));
    }

    @Test
    public void testCountBattleships_SingleX_ReturnsOne() {
        char[][] board = new char[1][1];
        board[0][0] = 'X';
        assertEquals(1, test.countBattleships(board));
    }

    @Test
    public void testCountBattleshipsQuick_BoardEmpty_ReturnsZero() {
        char[][] board = new char[0][0];
        assertEquals(0, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleshipsQuick_SingleX_ReturnsOne() {
        char[][] board = new char[1][1];
        board[0][0] = 'X';
        assertEquals(1, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleshipsQuick_HorizontalTwoXs_ReturnsOne() {
        char[][] board = new char[1][2];
        board[0][0] = 'X';
        board[0][1] = 'X';
        assertEquals(1, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleshipsQuick_VerticalTwoXs_ReturnsOne() {
        char[][] board = new char[2][1];
        board[0][0] = 'X';
        board[1][0] = 'X';
        assertEquals(1, test.countBattleshipsQuick(board));
    }

    @Test
    public void testCountBattleships_HorizontalTwoXs_ReturnsOne() {
        char[][] board = new char[1][2];
        board[0][0] = 'X';
        board[0][1] = 'X';
        assertEquals(1, test.countBattleships(board));
    }

    @Test
    public void testCountBattleships_VerticalTwoXs_ReturnsOne() {
        char[][] board = new char[2][1];
        board[0][0] = 'X';
        board[1][0] = 'X';
        assertEquals(1, test.countBattleships(board));
    }
}
