package playground;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparatorSampleTest {
    @Test void constructorRunsComparatorExamples() {
        PrintStream original = System.out; ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try { System.setOut(new PrintStream(bytes)); new ComparatorSample(); }
        finally { System.setOut(original); }
        String output = bytes.toString();
        assertTrue(output.contains("apple")); assertTrue(output.contains("[")); assertTrue(output.contains("[I@"));
    }
}
