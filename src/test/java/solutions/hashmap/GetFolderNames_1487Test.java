package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class GetFolderNames_1487Test {

    private final GetFolderNames_1487 test = new GetFolderNames_1487();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new String[]{"pes", "fifa", "gta", "pes(1)"},
            test.getFolderNames(new String[]{"pes", "fifa", "gta", "pes"}));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new String[]{"a"}, test.getFolderNames(new String[]{"a"}));
        assertArrayEquals(new String[]{"a", "a(1)", "a(2)"},
            test.getFolderNames(new String[]{"a", "a", "a"}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new String[]{"a", "a(1)", "a(2)", "b", "b(1)"},
            test.getFolderNames(new String[]{"a", "a", "a", "b", "b"}));
    }

    @Test
    public void testAllUnique() {
        assertArrayEquals(new String[]{"x", "y", "z"},
            test.getFolderNames(new String[]{"x", "y", "z"}));
    }

    @Test
    public void testNameWithParentheses() {
        assertArrayEquals(new String[]{"a(1)", "a", "a(1)(1)"},
            test.getFolderNames(new String[]{"a(1)", "a", "a(1)"}));
    }

    @Test
    public void testManyDuplicates() {
        assertArrayEquals(new String[]{"a", "a(1)", "a(2)", "a(3)", "a(4)"},
            test.getFolderNames(new String[]{"a", "a", "a", "a", "a"}));
    }

    @Test
    public void testNegativeEmptyName() {
        assertArrayEquals(new String[]{"", "(1)", "(2)"},
            test.getFolderNames(new String[]{"", "", ""}));
    }

    @Test
    public void testMixedDuplicates() {
        assertArrayEquals(new String[]{"gta", "gta(1)", "gta(2)", "avalon"},
            test.getFolderNames(new String[]{"gta", "gta", "gta", "avalon"}));
    }

    @Test
    public void testConflictWithSuffix() {
        assertArrayEquals(new String[]{"a", "a(1)", "a(1)(1)", "a(2)"},
            test.getFolderNames(new String[]{"a", "a", "a(1)", "a"}));
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        String[] input = new String[n];
        String[] expected = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = "folder";
            expected[i] = i == 0 ? "folder" : "folder(" + i + ")";
        }
        assertArrayEquals(expected, test.getFolderNames(input));
    }
    @Test void extra01() { assertArrayEquals(new String[]{"x","x(1)"}, test.getFolderNames(new String[]{"x","x"})); }
    @Test void extra02() { assertArrayEquals(new String[]{"a","b"}, test.getFolderNames(new String[]{"a","b"})); }
    @Test void extra03() { assertArrayEquals(new String[]{"a","a(1)","a(2)","a(3)"}, test.getFolderNames(new String[]{"a","a","a","a"})); }
    @Test void extra04() { assertArrayEquals(new String[]{"z"}, test.getFolderNames(new String[]{"z"})); }
    @Test void extra05() { assertArrayEquals(new String[]{"","(1)"}, test.getFolderNames(new String[]{"",""})); }
    @Test void extra06() { assertArrayEquals(new String[]{"foo","foo(1)","foo(2)"}, test.getFolderNames(new String[]{"foo","foo(1)","foo"})); }
    @Test void extra07() { assertArrayEquals(new String[]{"a(1)","a(1)(1)"}, test.getFolderNames(new String[]{"a(1)","a(1)"})); }
    @Test void extra08() { assertArrayEquals(new String[]{"p","p(1)","p(2)"}, test.getFolderNames(new String[]{"p","p","p"})); }
    @Test void extra09() { assertArrayEquals(new String[]{"a","b","a(1)","b(1)"}, test.getFolderNames(new String[]{"a","b","a","b"})); }
    @Test void extra10() { assertArrayEquals(new String[]{"q","q(1)","q(2)","q(3)"}, test.getFolderNames(new String[]{"q","q","q","q"})); }
}
