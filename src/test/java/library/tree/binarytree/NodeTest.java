package library.tree.binarytree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NodeTest {
    @Test void defaultFields() { Node n = new Node(); assertEquals(0, n.val); assertNull(n.left); assertNull(n.right); assertNull(n.next); assertNull(n.parent); }
    @Test void fiveArgumentConstructorLinksAllReferences() { Node left = new Node(), right = new Node(), next = new Node(), parent = new Node(); Node n = new Node(7, left, right, next, parent); assertSame(left, n.left); assertSame(right, n.right); assertSame(next, n.next); assertSame(parent, n.parent); }
    @Test void constructorSetsValue() { assertEquals(Integer.MIN_VALUE, new Node(Integer.MIN_VALUE, null, null, null, null).val); }
    @Test void fieldsRemainMutable() { Node n = new Node(); n.parent = new Node(); n.next = new Node(); assertNotNull(n.parent); assertNotNull(n.next); }
}
