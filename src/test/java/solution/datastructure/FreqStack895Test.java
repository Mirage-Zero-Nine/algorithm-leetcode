package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FreqStack895Test {

    @Test
    public void testHappyCases() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(5); fs.push(7); fs.push(5); fs.push(7); fs.push(4); fs.push(5);
        assertEquals(5, fs.pop());
        assertEquals(7, fs.pop());
        assertEquals(5, fs.pop());
        assertEquals(4, fs.pop());
    }

    @Test
    public void testEdgeCases() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(1);
        assertEquals(1, fs.pop());
    }

    @Test
    public void testLargeCase() {
        FreqStack_895 fs = new FreqStack_895();
        for (int i = 0; i < 5; i++) fs.push(1);
        fs.push(2); fs.push(2);
        assertEquals(1, fs.pop());
        assertEquals(1, fs.pop());
    }
}
