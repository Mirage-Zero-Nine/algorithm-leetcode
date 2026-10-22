package library.randomnode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NodeTest {
    @Test void defaultConstructor() { Node n = new Node(); assertEquals(0, n.val); assertNull(n.next); assertNull(n.random); assertNull(n.left); assertNull(n.right); }
    @Test void valueConstructor() { assertEquals(4, new Node(4).val); }
    @Test void listConstructorLinksPointers() { Node next = new Node(2), random = new Node(3); Node n = new Node(1, next, random); assertSame(next, n.next); assertSame(random, n.random); }
    @Test void treeConstructorLinksPointers() { Node left = new Node(2), right = new Node(3), random = new Node(4); Node n = new Node(1, left, right, random); assertSame(left, n.left); assertSame(right, n.right); assertSame(random, n.random); }
    @Test void boundaries() { assertEquals(Integer.MIN_VALUE, new Node(Integer.MIN_VALUE).val); assertEquals(Integer.MAX_VALUE, new Node(Integer.MAX_VALUE).val); }
    @Test void pointersAreMutable() { Node n = new Node(); n.next = n; n.random = n; assertSame(n, n.next); assertSame(n, n.random); }
}
