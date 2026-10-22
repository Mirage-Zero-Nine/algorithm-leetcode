package library.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class NodeTest {
    @Test void defaultFields() { Node n = new Node(); assertEquals(0, n.val); assertNull(n.neighbors); }
    @Test void valueConstructor() { assertEquals(4, new Node(4, null).val); }
    @Test void neighborsConstructor() { Node child = new Node(2, null); Node n = new Node(1, List.of(child)); assertSame(child, n.neighbors.get(0)); }
    @Test void emptyNeighborsPreserved() { Node n = new Node(1, List.of()); assertNotNull(n.neighbors); assertTrue(n.neighbors.isEmpty()); }
    @Test void duplicateNeighborsAllowed() { Node child = new Node(2, null); Node n = new Node(1, List.of(child, child)); assertEquals(2, n.neighbors.size()); }
    @Test void selfReferenceAllowed() { Node n = new Node(); n.neighbors = List.of(n); assertSame(n, n.neighbors.get(0)); }
}
