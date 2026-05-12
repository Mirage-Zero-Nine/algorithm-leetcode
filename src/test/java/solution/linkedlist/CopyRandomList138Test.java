package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.randomnode.Node;
import org.junit.jupiter.api.Test;

public class CopyRandomList138Test {

    private final CopyRandomList_138 test = new CopyRandomList_138();

    @Test
    public void testHappyCases() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        n1.next = n2; n1.random = n2; n2.random = n2;
        Node copy = test.copyRandomList(n1);
        assertEquals(1, copy.val);
        assertEquals(2, copy.next.val);
        assertNotSame(n1, copy);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.copyRandomList(null));
        Node single = new Node(); single.val = 5;
        Node copy = test.copyRandomList(single);
        assertEquals(5, copy.val);
        assertNotSame(single, copy);
    }

    @Test
    public void testLargeCase() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        Node n3 = new Node(); n3.val = 3;
        n1.next = n2; n2.next = n3;
        n1.random = n3; n2.random = n1; n3.random = null;
        Node copy = test.copyRandomList(n1);
        assertEquals(1, copy.val);
        assertEquals(3, copy.random.val);
        assertNotSame(n1, copy);
    }
}
