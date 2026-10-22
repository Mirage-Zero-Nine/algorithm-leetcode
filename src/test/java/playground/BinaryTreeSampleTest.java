package playground;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeSampleTest {
    @Test void mainPrintsAllThreeTraversalSections() {
        PrintStream original = System.out; ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try { System.setOut(new PrintStream(bytes)); BinaryTreeSample.main(new String[0]); }
        finally { System.setOut(original); }
        String output = bytes.toString();
        assertTrue(output.contains("Preorder traversal")); assertTrue(output.contains("Inorder traversal")); assertTrue(output.contains("Postorder traversal"));
        assertTrue(output.contains("1 2 4 8 9 5 3 6 14 15 7 12 13 16 17 "));
        assertTrue(output.contains("8 4 9 2 5 1 14 6 15 3 12 7 13 16 17 "));
        assertTrue(output.contains("8 9 4 5 2 14 15 6 12 17 16 13 7 3 1 "));
    }
}
