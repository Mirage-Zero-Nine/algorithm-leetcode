package solutions.stack;

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

    @Test public void testEmptyString() {
        assertEquals(0, solver.lengthLongestPath(""));
    }

    @Test public void testOnlyDirectories() {
        assertEquals(0, solver.lengthLongestPath("a\n\tb\n\t\tc"));
    }

    @Test public void testDeepNesting() {
        // a/b/c/d.txt = 12
        assertEquals(11, solver.lengthLongestPath("a\n\tb\n\t\tc\n\t\t\td.txt"));
    }

    @Test public void testMultipleRootFiles() {
        // "a.txt" = 5, "b.txt" = 5
        assertEquals(5, solver.lengthLongestPath("a.txt\nb.txt"));
    }

    @Test public void testLongFileName() {
        // dir/longfilename.ext = 4 + 16 = 20 (with /)
        assertEquals(20, solver.lengthLongestPath("dir\n\tlongfilename.ext"));
    }

    @Test public void testGiant() {
        // Build deep path: d0/d1/d2/.../d99/f.txt
        StringBuilder sb = new StringBuilder("d0");
        for (int i = 1; i < 100; i++) {
            sb.append("\n").append("\t".repeat(i)).append("d").append(i);
        }
        sb.append("\n").append("\t".repeat(100)).append("f.txt");
        int result = solver.lengthLongestPath(sb.toString());
        assertTrue(result > 0);
    }
}
