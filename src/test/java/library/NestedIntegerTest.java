package library;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NestedIntegerTest {
    @Test void emptyStartsAsList() { NestedInteger n = new NestedInteger(); assertFalse(n.isInteger()); assertNotNull(n.getList()); assertTrue(n.getList().isEmpty()); }
    @Test void integerConstructor() { NestedInteger n = new NestedInteger(3); assertTrue(n.isInteger()); assertEquals(3, n.getInteger()); assertNull(n.getList()); }
    @Test void negativeInteger() { assertEquals(-4, new NestedInteger(-4).getInteger()); }
    @Test void nullIntegerConstructorIsInteger() { NestedInteger n = new NestedInteger((Integer) null); assertTrue(n.isInteger()); assertNull(n.getInteger()); }
    @Test void setIntegerConvertsList() { NestedInteger n = new NestedInteger(); n.add(new NestedInteger(1)); n.setInteger(2); assertTrue(n.isInteger()); assertEquals(2, n.getInteger()); assertNull(n.getList()); }
    @Test void addConvertsIntegerToList() { NestedInteger n = new NestedInteger(2); n.add(new NestedInteger(3)); assertFalse(n.isInteger()); assertEquals(1, n.getList().size()); }
    @Test void addPreservesOrder() { NestedInteger n = new NestedInteger(); n.add(new NestedInteger(1)); n.add(new NestedInteger(2)); assertEquals(1, n.getList().get(0).getInteger()); assertEquals(2, n.getList().get(1).getInteger()); }
    @Test void nestedList() { NestedInteger n = new NestedInteger(); NestedInteger child = new NestedInteger(); child.add(new NestedInteger(7)); n.add(child); assertSame(child, n.getList().get(0)); }
    @Test void setIntegerCanBeRepeated() { NestedInteger n = new NestedInteger(); n.setInteger(1); n.setInteger(2); assertEquals(2, n.getInteger()); }
    @Test void addAfterSetIntegerStartsFreshList() { NestedInteger n = new NestedInteger(1); n.add(new NestedInteger(2)); assertEquals(1, n.getList().size()); assertEquals(2, n.getList().get(0).getInteger()); }
    @Test void listCanContainEmptyList() { NestedInteger n = new NestedInteger(); n.add(new NestedInteger()); assertFalse(n.getList().get(0).isInteger()); }
    @Test void listReferenceIsMutableByContract() { NestedInteger n = new NestedInteger(); n.getList().add(new NestedInteger(5)); assertEquals(5, n.getList().get(0).getInteger()); }
}
