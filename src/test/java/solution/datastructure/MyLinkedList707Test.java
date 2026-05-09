package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyLinkedList707Test {

    @Test
    public void testHappyCases() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyLinkedList_707 list = new MyLinkedList_707();
        assertEquals(-1, list.get(0));
        list.addAtHead(1);
        list.deleteAtIndex(0);
        assertEquals(-1, list.get(0));
    }

    @Test
    public void testLargeCase() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int i = 0; i < 5; i++) list.addAtTail(i);
        assertEquals(0, list.get(0));
        assertEquals(4, list.get(4));
        list.deleteAtIndex(2);
        assertEquals(3, list.get(2));
    }
}
