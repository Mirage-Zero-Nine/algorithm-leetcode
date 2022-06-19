package library.util;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:41
 * Created with IntelliJ IDEA
 */

public class ListParserTest {

    @Test
    public void test() {
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[1,2,3]"));
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[1, 2,3]"));
        assertIterableEquals(Lists.newArrayList(1, 2, 3), ListParser.parseList("[ 1   , 2,3 ]"));
        assertIterableEquals(Lists.newArrayList(1), ListParser.parseList("[1]"));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList(null));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("["));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[]"));
        assertThrows(InvalidParameterException.class, () -> ListParser.parseList("[1,2,,3]"));
    }
}