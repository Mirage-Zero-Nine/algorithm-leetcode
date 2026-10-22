package playground;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.lang.reflect.Field;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestSubArrayTest {
    @ParameterizedTest @MethodSource("cases")
    void returnsLongestEqualRun(int[] values, int[] expected) throws Exception {
        LongestSubArray subject = subject(values);
        assertArrayEquals(expected, subject.FindSubArray());
        assertArrayEquals(expected, subject.FindSubArray());
    }
    static Stream<Arguments> cases() { return Stream.of(
        Arguments.of(new int[]{},new int[]{}),Arguments.of(new int[]{1},new int[]{1}),Arguments.of(new int[]{1,2},new int[]{1}),Arguments.of(new int[]{1,1},new int[]{1,1}),Arguments.of(new int[]{1,1,1},new int[]{1,1,1}),Arguments.of(new int[]{1,2,2},new int[]{2,2}),Arguments.of(new int[]{2,2,1,1,1},new int[]{1,1,1}),Arguments.of(new int[]{2,2,1,1},new int[]{2,2}),Arguments.of(new int[]{0,0,1,2},new int[]{0,0}),Arguments.of(new int[]{0,1,2},new int[]{0}),Arguments.of(new int[]{3,3,2,2,2,1},new int[]{2,2,2}),Arguments.of(new int[]{-1,-1,-1,0},new int[]{-1,-1,-1}),Arguments.of(new int[]{1,1,2,2,2,3},new int[]{2,2,2}),Arguments.of(new int[]{5,4,4,4,4},new int[]{4,4,4,4}),Arguments.of(new int[]{7,8,9,9,9},new int[]{9,9,9}),Arguments.of(new int[]{2,2,2,1,1},new int[]{2,2,2}),Arguments.of(new int[]{-2,-2,0,0,0,0},new int[]{0,0,0,0}),Arguments.of(new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE},new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE}),Arguments.of(new int[]{Integer.MAX_VALUE,0},new int[]{Integer.MAX_VALUE}),Arguments.of(new int[]{4,4,4,4,4,4},new int[]{4,4,4,4,4,4})
    ); }
    private static LongestSubArray subject(int[] values) throws Exception { LongestSubArray s=new LongestSubArray(values.length); Field f=LongestSubArray.class.getDeclaredField("randomArray"); f.setAccessible(true); System.arraycopy(values,0,(int[])f.get(s),0,values.length); return s; }
}
