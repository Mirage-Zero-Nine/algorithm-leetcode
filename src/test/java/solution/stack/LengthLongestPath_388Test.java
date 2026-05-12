package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthLongestPath_388Test {
    private final LengthLongestPath_388 solver = new LengthLongestPath_388();

    @Test public void testExample() {
        // dir/subdir2/subsubdir2/file2.ext = 32
        assertEquals(32, solver.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    @Test public void testSimple() {
        // dir/file.ext = 12
        assertEquals(12, solver.lengthLongestPath("dir\n\tfile.ext"));
    }

    @Test public void testNoFile() {
        assertEquals(0, solver.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2"));
    }

    @Test public void testRootFile() {
        assertEquals(8, solver.lengthLongestPath("file.ext"));
    }

    @Test public void testMultipleFiles() {
        // a/b/c.txt = 9, a/d.txt = 7
        assertEquals(9, solver.lengthLongestPath("a\n\tb\n\t\tc.txt\n\td.txt"));
    }
}
