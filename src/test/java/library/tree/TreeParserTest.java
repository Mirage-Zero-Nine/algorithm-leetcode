package library.tree;

import static library.tree.TreeParser.convertToList;
import static library.tree.TreeParser.deserialize;
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
    public void test() {
        List<Integer> expected = Lists.newArrayList(1, 2, 4, 8, 9, 5, 10, 3, 6, 7);
        assertIterableEquals(expected, convertToList(deserialize("1,2,3,4,5,6,7,8,9,10")));
    }

    @Test
    public void testWithNull() {
        List<Integer> expected = Lists.newArrayList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        assertIterableEquals(expected, convertToList(deserialize("3,5,1,6,2,0,8,null,null,7,4")));
    }
}