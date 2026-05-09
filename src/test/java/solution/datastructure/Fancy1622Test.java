package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Fancy1622Test {

    @Test
    public void testHappyCases() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        assertEquals(10, fancy.getIndex(0));
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        assertEquals(26, fancy.getIndex(0));
        assertEquals(34, fancy.getIndex(1));
        assertEquals(20, fancy.getIndex(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        Fancy_1622 fancy = new Fancy_1622();
        assertEquals(-1, fancy.getIndex(0));
        fancy.append(1);
        assertEquals(1, fancy.getIndex(0));
    }

    @Test
    public void testLargeCase() {
        Fancy_1622 fancy = new Fancy_1622();
        for (int i = 1; i <= 5; i++) fancy.append(i);
        fancy.addAll(10);
        assertEquals(11, fancy.getIndex(0));
        assertEquals(15, fancy.getIndex(4));
    }
}
