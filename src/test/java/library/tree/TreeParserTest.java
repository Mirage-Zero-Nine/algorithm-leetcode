package library.tree;

import static library.tree.TreeParser.convertToList;
import static library.tree.TreeParser.deserialize;
import static library.tree.TreeParser.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.Lists;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/30 12:14
 * Created with IntelliJ IDEA
 */

public class TreeParserTest {

    @Test
    public void testStandard() {
        List<Integer> expected = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertIterableEquals(expected, convertToList(deserialize("1,2,3,4,5,6,7,8,9,10")));
        assertEquals("1,2,3,4,5,6,7,8,9,10", serialize(deserialize("1,2,3,4,5,6,7,8,9,10")));
    }

    @Test
    public void testWithNull() {
        List<Integer> expected = Lists.newArrayList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        assertIterableEquals(expected, convertToList(deserialize("3,5,1,6,2,0,8,null,null,7,4")));
        assertEquals("3,5,1,6,2,0,8,null,null,7,4", serialize(deserialize("3,5,1,6,2,0,8,null,null,7,4")));
    }

    @Test
    public void testNull() {
        List<Integer> expected = Lists.newArrayList((Integer) null);
        assertIterableEquals(expected, convertToList(deserialize("null")));
        assertEquals("null", serialize(deserialize("null")));
    }

    @Test
    public void testRootOnly() {
        List<Integer> expected = Lists.newArrayList(1);
        assertIterableEquals(expected, convertToList(deserialize("1")));
        assertEquals("1", serialize(deserialize("1")));
    }

    @Test
    public void testHashNullMarkersAndNegativeValues() {
        String data = "-1,#,2,#,-3";
        List<Integer> expected = Lists.newArrayList(-1, null, 2, null, -3);

        assertIterableEquals(expected, convertToList(deserialize(data)));
        assertEquals("-1,null,2,null,-3", serialize(deserialize(data)));
    }

    @Test void testTrimmedTokens() { assertEquals("1,2,3", serialize(deserialize(" 1, 2 ,3 "))); }
    @Test void testNullMarkerHash() { assertEquals("null", serialize(deserialize("#"))); }
    @Test void testTrailingNullsAreRemoved() { assertEquals("1,2,3", serialize(deserialize("1,2,3,null,null,null,null"))); }
    @Test void testInternalNullsAreRetained() { assertEquals("1,null,2", serialize(deserialize("1,#,2"))); }
    @Test void testLeftOnlyChild() { assertIterableEquals(Lists.newArrayList(1, 2), convertToList(deserialize("1,2,#"))); }
    @Test void testRightOnlyChild() { assertIterableEquals(Lists.newArrayList(1, null, 2), convertToList(deserialize("1,#,2"))); }
    @Test void testNegativeRoot() { assertEquals(-7, deserialize("-7").val); }
    @Test void testIntegerBoundaries() { assertEquals("-2147483648,2147483647", serialize(deserialize("-2147483648,2147483647"))); }
    @Test void testDuplicateValuesPreserved() { assertEquals("4,4,4,4", serialize(deserialize("4,4,4,4"))); }
    @Test void testLeadingAndTrailingWhitespace() { assertEquals("5,6", serialize(deserialize(" 5 , 6 "))); }
    @Test void testConvertNullResultIsFresh() { assertEquals(Lists.newArrayList((Integer) null), convertToList(null)); }
    @Test void testSerializeDeserializeRoundTripSparse() {
        String data = "10,5,15,null,7,null,20";
        assertEquals(data, serialize(deserialize(data)));
    }
    @Test void testEmptyDataRejected() { assertThrows(NumberFormatException.class, () -> deserialize("")); }
    @Test void testMissingRootValueRejected() { assertThrows(NumberFormatException.class, () -> deserialize(",1")); }
    @Test void testInvalidIntegerRejected() { assertThrows(NumberFormatException.class, () -> deserialize("x")); }
    @Test void testRepeatedDeserializationIndependent() {
        TreeNode first = deserialize("1,2");
        TreeNode second = deserialize("3,4");
        first.val = 9;
        assertEquals(3, second.val);
    }
}
