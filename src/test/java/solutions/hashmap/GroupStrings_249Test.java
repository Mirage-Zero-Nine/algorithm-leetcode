package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GroupStrings_249Test {

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

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.groupStrings(new String[]{}).size());
    }

    @Test
    public void testSingleLongString() {
        List<List<String>> result = test.groupStrings(new String[]{"abcdef"});
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).size());
    }

    @Test
    public void testWrapAround() {
        // "az" and "ba" should be in same group: diff is 25 for both
        List<List<String>> result = test.groupStrings(new String[]{"az", "ba"});
        assertEquals(1, result.size());
    }

    @Test
    public void testAllDifferentLengths() {
        List<List<String>> result = test.groupStrings(new String[]{"a", "ab", "abc", "abcd"});
        assertEquals(4, result.size());
    }

    @Test
    public void testAllSameString() {
        List<List<String>> result = test.groupStrings(new String[]{"abc", "abc", "abc"});
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }

    @Test
    public void testTwoCharGroups() {
        // "ab","bc","cd" all have diff=1, "ac","bd" have diff=2
        List<List<String>> result = test.groupStrings(new String[]{"ab", "bc", "cd", "ac", "bd"});
        assertEquals(2, result.size());
    }

    @Test
    public void testGiantCase() {
        String[] arr = new String[100];
        for (int i = 0; i < 100; i++) {
            char c = (char) ('a' + (i % 26));
            arr[i] = "" + c;
        }
        List<List<String>> result = test.groupStrings(arr);
        // all single chars belong to same group
        assertEquals(1, result.size());
    }
}
