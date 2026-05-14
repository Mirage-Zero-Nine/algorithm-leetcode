package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FreqStack_895Test {

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

    @Test
    public void testAllSameElements() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(3); fs.push(3); fs.push(3);
        assertEquals(3, fs.pop());
        assertEquals(3, fs.pop());
        assertEquals(3, fs.pop());
    }

    @Test
    public void testTieBreakByRecency() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(1); fs.push(2); fs.push(3);
        // all freq=1, most recent is 3
        assertEquals(3, fs.pop());
        assertEquals(2, fs.pop());
        assertEquals(1, fs.pop());
    }

    @Test
    public void testPushAfterPop() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(1); fs.push(1); fs.push(2);
        assertEquals(1, fs.pop()); // freq(1)=2 > freq(2)=1
        fs.push(2); // now freq(1)=1, freq(2)=2
        assertEquals(2, fs.pop()); // freq(2)=2 is highest
    }

    @Test
    public void testMultipleFrequencyLevels() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(1); fs.push(2); fs.push(1); fs.push(2); fs.push(1);
        // freq(1)=3, freq(2)=2
        assertEquals(1, fs.pop());
        // freq(1)=2, freq(2)=2, tie: 2 pushed after 1 at freq=2
        assertEquals(2, fs.pop());
        assertEquals(1, fs.pop());
    }

    @Test
    public void testInterleavedPushPop() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(10); fs.push(20); fs.push(10);
        assertEquals(10, fs.pop());
        fs.push(20);
        assertEquals(20, fs.pop());
        assertEquals(20, fs.pop());
        assertEquals(10, fs.pop());
    }

    @Test
    public void testSingleElementRepeated() {
        FreqStack_895 fs = new FreqStack_895();
        fs.push(7); fs.push(7);
        assertEquals(7, fs.pop());
        fs.push(7);
        assertEquals(7, fs.pop());
        assertEquals(7, fs.pop());
    }

    @Test
    public void testManyDistinctElements() {
        FreqStack_895 fs = new FreqStack_895();
        for (int i = 1; i <= 10; i++) fs.push(i);
        // all freq=1, pops in reverse push order
        for (int i = 10; i >= 1; i--) {
            assertEquals(i, fs.pop());
        }
    }

    @Test
    public void testGiantCase() {
        FreqStack_895 fs = new FreqStack_895();
        // push 1..100 each 100 times
        for (int round = 0; round < 100; round++) {
            for (int val = 1; val <= 100; val++) {
                fs.push(val);
            }
        }
        // max freq = 100 for all, tie-break by recency: last pushed round had 1..100
        // at freq=100 stack top is 100
        assertEquals(100, fs.pop());
        assertEquals(99, fs.pop());
    }
}
