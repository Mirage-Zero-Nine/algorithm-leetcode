package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityParser_1410Test {
    private final EntityParser_1410 solver = new EntityParser_1410();

    @Test public void testGtAndAmpAndLt() {
        assertEquals("x > y && x < y is always false", solver.entityParser("x &gt; y &amp;&amp; x &lt; y is always false"));
    }

    @Test public void testInvalidEntity() {
        assertEquals("& is an HTML entity but &ambassador; is not.", solver.entityParser("&amp; is an HTML entity but &ambassador; is not."));
    }

    @Test public void testQuot() {
        assertEquals("and I quote: \"...\"", solver.entityParser("and I quote: &quot;...&quot;"));
    }

    @Test public void testNoEntity() {
        assertEquals("Stay home! Practice on Leetcode :)", solver.entityParser("Stay home! Practice on Leetcode :)"));
    }

    @Test public void testFrasl() {
        assertEquals("leetcode.com/problemset/all", solver.entityParser("leetcode.com&frasl;problemset&frasl;all"));
    }

    @Test public void testNestedEntity() {
        assertEquals("&gt;", solver.entityParser("&amp;gt;"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.entityParser(""));
    }

    @Test public void testNull() {
        assertEquals("", solver.entityParser(null));
    }

    @Test public void testApos() {
        assertEquals("it's fine", solver.entityParser("it&apos;s fine"));
    }

    @Test public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append("&amp;");
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 500; i++) expected.append("&");
        assertEquals(expected.toString(), solver.entityParser(sb.toString()));
    }

    @Test public void testAmpersandAlone() {
        // '&' without matching ';' before end of string
        assertEquals("&hello", solver.entityParser("&hello"));
    }
    @Test public void testEachEntityTogether() { assertEquals("\"'&></", solver.entityParser("&quot;&apos;&amp;&gt;&lt;&frasl;")); }
    @Test public void testUnknownWithSemicolon() { assertEquals("&unknown;", solver.entityParser("&unknown;")); }
    @Test public void testUnknownWithoutSemicolon() { assertEquals("&unknown", solver.entityParser("&unknown")); }
    @Test public void testEntityAtEdges() { assertEquals("&x&", solver.entityParser("&amp;x&amp;")); }
    @Test public void testPlainAmpersandText() { assertEquals("a&b", solver.entityParser("a&b")); }
    @Test public void testRepeatedMixed() { assertEquals("<>/&", solver.entityParser("&lt;&gt;&frasl;&amp;")); }
    @Test public void testCaseSensitiveUnknown() { assertEquals("&AMP;", solver.entityParser("&AMP;")); }
    @Test public void testTrailingPlainText() { assertEquals("<&tail", solver.entityParser("&lt;&tail")); }
    @Test public void testRepeatedInvocation() { assertEquals("&",solver.entityParser("&amp;")); assertEquals("<",solver.entityParser("&lt;")); }
}
