package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Fancy_1622Test {

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

    @Test
    public void testOutOfRangeIndexAfterOperations() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(5);
        fancy.addAll(2);
        fancy.multAll(3);
        assertEquals(-1, fancy.getIndex(1));
    }

    @Test
    public void testAppendAfterTransformsUsesCurrentBaseState() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.addAll(5);
        fancy.multAll(2);
        fancy.append(7);
        assertEquals(7, fancy.getIndex(0));
    }

    @Test
    public void testAddAllAffectsAllExistingElements() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(1);
        fancy.append(2);
        fancy.addAll(4);
        assertEquals(5, fancy.getIndex(0));
        assertEquals(6, fancy.getIndex(1));
    }

    @Test
    public void testMultAllAffectsAllExistingElements() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(3);
        fancy.append(4);
        fancy.multAll(5);
        assertEquals(15, fancy.getIndex(0));
        assertEquals(20, fancy.getIndex(1));
    }

    @Test
    public void testChainedTransformsWithMixedAccess() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(1);
        fancy.addAll(2);
        fancy.multAll(3);
        fancy.append(5);
        fancy.addAll(4);
        assertEquals(9, fancy.getIndex(1));
        assertEquals(13, fancy.getIndex(0));
    }

    @Test
    public void testModBoundaryBehavior() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(1_000_000_006);
        fancy.addAll(2);
        assertEquals(1, fancy.getIndex(0));
    }

    @Test
    public void testGiantCaseManyAppendsAndQueries() {
        Fancy_1622 fancy = new Fancy_1622();
        for (int i = 0; i < 2000; i++) {
            fancy.append(i);
        }
        fancy.addAll(7);
        fancy.multAll(3);
        assertEquals(21, fancy.getIndex(0));
        assertEquals((1999 + 7) * 3, fancy.getIndex(1999));
        assertEquals(-1, fancy.getIndex(2000));
    }
}
