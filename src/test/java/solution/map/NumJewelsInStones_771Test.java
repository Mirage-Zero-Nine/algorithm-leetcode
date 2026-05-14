package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/09 20:39
 * Created with IntelliJ IDEA
 */

public class NumJewelsInStones_771Test {


    private final NumJewelsInStones_771 test = new NumJewelsInStones_771();

    @Test
    public void test() {
        assertEquals(3, test.numJewelsInStones("aA", "aAAbbbb"));
    }

    @Test
    public void testEmpty() {
        assertEquals(0, test.numJewelsInStones("z", "ZZ"));
    }

    @Test
    public void testHappyCases() {
        assertEquals(6, test.numJewelsInStones("abc", "aaabbc"));
        assertEquals(2, test.numJewelsInStones("z", "zzzzx".substring(0, 2)));
        assertEquals(4, test.numJewelsInStones("aA", "AaAa"));
    }

    @Test
    public void testEdgeAndNegativeCases() {
        assertEquals(0, test.numJewelsInStones("", "abc"));
        assertEquals(0, test.numJewelsInStones("abc", ""));
        assertEquals(1, test.numJewelsInStones("A", "aA"));
        assertEquals(6, test.numJewelsInStones("xyz", "xxyyzz"));
    }

    @Test
    public void testGiantCase() {
        String jewels = "abc";
        String stones = "a".repeat(400) + "b".repeat(300) + "c".repeat(200) + "d".repeat(100);
        assertEquals(900, test.numJewelsInStones(jewels, stones));
    }

    @Test
    public void testCaseSensitivity() {
        assertEquals(0, test.numJewelsInStones("a", "AAA"));
    }

    @Test
    public void testAllJewels() {
        assertEquals(5, test.numJewelsInStones("abcde", "abcde"));
    }

    @Test
    public void testNoJewels() {
        assertEquals(0, test.numJewelsInStones("xyz", "abcdef"));
    }

    @Test
    public void testSingleCharBoth() {
        assertEquals(1, test.numJewelsInStones("a", "a"));
    }

    @Test
    public void testGiantAllMatch() {
        String jewels = "abcdefghijklmnopqrstuvwxyz";
        String stones = "abcdefghij".repeat(100);
        assertEquals(1000, test.numJewelsInStones(jewels, stones));
    }
}
