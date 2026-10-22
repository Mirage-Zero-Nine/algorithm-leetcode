package playground;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

class FindAllDeadZerosTest {
    @ParameterizedTest(name="case {index}") @MethodSource("cases") void countsDeadZeroBranches(int[][] matrix,int row,int col,int expected){ assertEquals(expected,new FindAllDeadZeros().findAllDeadZeros(matrix,row,col)); }
    static Stream<Arguments> cases(){ return Stream.of(
      Arguments.of(new int[][]{{0,0,0},{0,1,0},{0,0,0}},1,1,0),
      Arguments.of(new int[][]{{0,0,0},{0,0,0},{0,0,0}},1,1,0),
      Arguments.of(new int[][]{{0,0,0},{0,0,1},{0,0,0}},1,2,0),
      Arguments.of(new int[][]{{0,0,0,0,0},{0,0,1,1,0},{0,1,0,0,1},{0,1,1,1,0}},1,2,2),
      Arguments.of(new int[][]{{0,0,1,1,1,0},{1,0,0,0,0,1},{1,0,1,1,0,1},{1,0,1,1,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}},5,3,0),
      Arguments.of(new int[][]{{0,1,1,1,1,0},{1,0,0,0,0,1},{1,0,1,1,0,1},{1,0,0,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}},5,3,14),
      Arguments.of(new int[][]{{0,0,0,0,0,0},{1,0,0,0,0,1},{1,0,1,1,0,1},{1,0,1,1,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}},0,0,0),
      Arguments.of(new int[][]{{0}},0,0,0),Arguments.of(new int[][]{{1}},0,0,0),Arguments.of(new int[][]{{0,1},{1,0}},0,0,0),
      Arguments.of(new int[][]{{0,0},{0,0}},0,0,0),Arguments.of(new int[][]{{0,1,0},{1,0,1},{0,1,0}},1,0,1),
      Arguments.of(new int[][]{{0,1,0},{1,0,1},{0,1,0}},1,2,1),Arguments.of(new int[][]{{0,0,0},{1,1,1},{0,0,0}},1,0,0),
      Arguments.of(new int[][]{{0,1,0,1},{0,0,0,0},{0,1,0,1}},1,1,0),Arguments.of(new int[][]{{0,0,0,0},{0,1,1,0},{0,1,1,0},{0,0,0,0}},1,1,0),
      Arguments.of(new int[][]{{0,0,0},{0,1,0},{0,0,0}},0,1,0),Arguments.of(new int[][]{{0,1,0},{0,0,0},{0,1,0}},1,0,0),
      Arguments.of(new int[][]{{0,0,0,0},{0,1,1,0},{0,0,0,0}},1,1,0),Arguments.of(new int[][]{{0,0,0},{1,1,1},{0,0,0}},1,1,0)
    ); }
    @Test void rejectsNullAndEmpty(){ assertThrows(IllegalArgumentException.class,()->new FindAllDeadZeros().findAllDeadZeros(null,0,0)); assertThrows(IllegalArgumentException.class,()->new FindAllDeadZeros().findAllDeadZeros(new int[0][0],0,0)); }
}
