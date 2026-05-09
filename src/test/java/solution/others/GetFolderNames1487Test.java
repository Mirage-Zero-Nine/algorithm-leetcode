package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class GetFolderNames1487Test {

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
}
