package library.tree;

import static library.tree.TreeParser.convertToList;
import static library.tree.TreeParser.deserialize;
import static library.tree.TreeParser.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
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
}