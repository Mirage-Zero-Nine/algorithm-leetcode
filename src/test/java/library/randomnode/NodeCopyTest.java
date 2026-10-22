package library.randomnode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NodeCopyTest {
    @Test void defaultConstructor() { NodeCopy n = new NodeCopy(); assertEquals(0, n.val); assertNull(n.left); assertNull(n.right); assertNull(n.random); }
    @Test void valueConstructor() { assertEquals(5, new NodeCopy(5).val); }
    @Test void treeConstructorLinksPointers() { NodeCopy left = new NodeCopy(1), right = new NodeCopy(2), random = new NodeCopy(3); NodeCopy n = new NodeCopy(4, left, right, random); assertSame(left, n.left); assertSame(right, n.right); assertSame(random, n.random); }
    @Test void negativeValue() { assertEquals(-7, new NodeCopy(-7).val); }
    @Test void boundaries() { assertEquals(Integer.MIN_VALUE, new NodeCopy(Integer.MIN_VALUE).val); assertEquals(Integer.MAX_VALUE, new NodeCopy(Integer.MAX_VALUE).val); }
    @Test void pointersAreMutable() { NodeCopy n = new NodeCopy(); n.left = n; n.random = n; assertSame(n, n.left); assertSame(n, n.random); }
}
