package library.tree.narytree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class NodeTest {
    @Test void defaultFields() { Node n = new Node(); assertEquals(0, n.val); assertNull(n.children); }
    @Test void valueConstructor() { assertEquals(-3, new Node(-3).val); }
    @Test void childrenConstructor() { Node child = new Node(2); Node n = new Node(1, List.of(child)); assertSame(child, n.children.get(0)); }
    @Test void emptyChildrenPreserved() { Node n = new Node(1, List.of()); assertNotNull(n.children); assertTrue(n.children.isEmpty()); }
    @Test void childrenAreMutableField() { Node n = new Node(1); n.children = new java.util.ArrayList<>(); n.children.add(new Node(2)); assertEquals(2, n.children.get(0).val); }
}
