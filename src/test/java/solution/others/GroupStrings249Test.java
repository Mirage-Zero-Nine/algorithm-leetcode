package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GroupStrings249Test {

    private final GroupStrings_249 test = new GroupStrings_249();

    @Test
    public void testHappyCases() {
        List<List<String>> result = test.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"});
        assertEquals(4, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.groupStrings(null).size());
        assertEquals(1, test.groupStrings(new String[]{"a"}).size());
    }

    @Test
    public void testLargeCase() {
        List<List<String>> result = test.groupStrings(new String[]{"a", "b", "c", "d"});
        assertEquals(1, result.size());
    }
}
