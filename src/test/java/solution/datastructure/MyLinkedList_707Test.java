package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyLinkedList_707Test {

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

    @Test
    public void testGetInvalidIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        assertEquals(-1, list.get(-1));
        assertEquals(-1, list.get(1));
        assertEquals(-1, list.get(100));
    }

    @Test
    public void testAddAtNegativeIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(10);
        list.addAtIndex(-1, 5);
        // negative index inserts at head
        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    public void testAddAtIndexBeyondSize() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtIndex(5, 99);
        // index > size, should not insert
        assertEquals(-1, list.get(1));
    }

    @Test
    public void testAddAtIndexEqualsSize() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtIndex(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    public void testDeleteInvalidIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.deleteAtIndex(-1);
        list.deleteAtIndex(5);
        // list unchanged
        assertEquals(1, list.get(0));
    }

    @Test
    public void testDeleteHead() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        list.deleteAtIndex(0);
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testDeleteTail() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        list.deleteAtIndex(2);
        assertEquals(-1, list.get(2));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testGiantCase() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int i = 0; i < 1000; i++) list.addAtTail(i);
        assertEquals(0, list.get(0));
        assertEquals(999, list.get(999));
        assertEquals(500, list.get(500));
        for (int i = 0; i < 500; i++) list.deleteAtIndex(0);
        assertEquals(500, list.get(0));
    }
}
