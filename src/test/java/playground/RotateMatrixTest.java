package playground;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class RotateMatrixTest {
    private final RotateMatrix rotate = new RotateMatrix();
    @Test void clockwiseAndBackRestoresTwoByTwo() { int[][] m={{1,2},{3,4}}; rotate.rotateClockwise(m); assertArrayEquals(new int[][]{{3,1},{4,2}},m); rotate.rotateAntiClockwise(m); assertArrayEquals(new int[][]{{1,2},{3,4}},m); }
    @Test void clockwiseThreeByThree() { int[][] m={{1,2,3},{4,5,6},{7,8,9}}; assertArrayEquals(new int[][]{{7,4,1},{8,5,2},{9,6,3}},rotate.rotateClockwise(m)); }
    @Test void anticlockwiseThreeByThree() { int[][] m={{1,2,3},{4,5,6},{7,8,9}}; assertArrayEquals(new int[][]{{3,6,9},{2,5,8},{1,4,7}},rotate.rotateAntiClockwise(m)); }
    @Test void fourClockwiseRotationsRestore() { int[][] m={{-1,0,2},{3,4,5},{6,7,8}}; for(int i=0;i<4;i++) rotate.rotateClockwise(m); assertArrayEquals(new int[][]{{-1,0,2},{3,4,5},{6,7,8}},m); }
    @Test void fourAnticlockwiseRotationsRestore() { int[][] m={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}; for(int i=0;i<4;i++) rotate.rotateAntiClockwise(m); assertArrayEquals(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}},m); }
    @Test void oneByOneIsUnchanged() { int[][] m={{Integer.MIN_VALUE}}; assertArrayEquals(new int[][]{{Integer.MIN_VALUE}},rotate.rotateClockwise(m)); assertArrayEquals(new int[][]{{Integer.MIN_VALUE}},rotate.rotateAntiClockwise(m)); }
    @Test void twoByTwoNegative() { int[][] m={{-1,-2},{-3,-4}}; assertArrayEquals(new int[][]{{-3,-1},{-4,-2}},rotate.rotateClockwise(m)); }
    @Test void twoByTwoAnticlockwise() { int[][] m={{-1,-2},{-3,-4}}; assertArrayEquals(new int[][]{{-2,-4},{-1,-3}},rotate.rotateAntiClockwise(m)); }
    @Test void preservesCenter() { int[][] m={{1,2,3},{4,99,6},{7,8,9}}; rotate.rotateClockwise(m); assertEquals(99,m[1][1]); }
    @Test void returnsSameArrayReference() { int[][] m={{1}}; org.junit.jupiter.api.Assertions.assertSame(m,rotate.rotateClockwise(m)); }
    @Test void handlesZeros() { int[][] m={{0,0},{0,0}}; assertArrayEquals(new int[][]{{0,0},{0,0}},rotate.rotateClockwise(m)); }
    @Test void clockwiseFourByFour() { int[][] m={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}; assertArrayEquals(new int[][]{{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}},rotate.rotateClockwise(m)); }
    @Test void anticlockwiseFourByFour() { int[][] m={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}; assertArrayEquals(new int[][]{{4,8,12,16},{3,7,11,15},{2,6,10,14},{1,5,9,13}},rotate.rotateAntiClockwise(m)); }
    @Test void repeatedIndependentCalls() { int[][] a={{1,2},{3,4}}; int[][] b={{5,6},{7,8}}; rotate.rotateClockwise(a); rotate.rotateClockwise(b); assertArrayEquals(new int[][]{{3,1},{4,2}},a); assertArrayEquals(new int[][]{{7,5},{8,6}},b); }
    @Test void diagonalValuesMoveCorrectly() { int[][] m={{1,0,0},{0,2,0},{0,0,3}}; assertArrayEquals(new int[][]{{0,0,1},{0,2,0},{3,0,0}},rotate.rotateClockwise(m)); }
    @Test void anticlockwiseDiagonalValuesMoveCorrectly() { int[][] m={{1,0,0},{0,2,0},{0,0,3}}; assertArrayEquals(new int[][]{{0,0,3},{0,2,0},{1,0,0}},rotate.rotateAntiClockwise(m)); }
    @Test void maxIntValues() { int[][] m={{Integer.MAX_VALUE,Integer.MIN_VALUE},{0,-1}}; assertArrayEquals(new int[][]{{0,Integer.MAX_VALUE},{-1,Integer.MIN_VALUE}},rotate.rotateClockwise(m)); }
    @Test void negativeAndPositiveValues() { int[][] m={{-3,2},{1,-4}}; assertArrayEquals(new int[][]{{1,-3},{-4,2}},rotate.rotateClockwise(m)); }
    @Test void antiThenClockwiseRestores() { int[][] m={{1,2,3},{4,5,6},{7,8,9}}; rotate.rotateAntiClockwise(m); rotate.rotateClockwise(m); assertArrayEquals(new int[][]{{1,2,3},{4,5,6},{7,8,9}},m); }
    @Test void clockwiseAndBackRestoresFourByFour() { int[][] m={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}; rotate.rotateClockwise(m); rotate.rotateAntiClockwise(m); assertArrayEquals(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}},m); }
    private static void assertEquals(int expected,int actual){org.junit.jupiter.api.Assertions.assertEquals(expected,actual);}
}
