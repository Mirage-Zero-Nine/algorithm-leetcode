package playground;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test void newNodeHasDefaultState() { Trie t = new Trie(); assertFalse(t.isEnd()); assertEquals('\0', t.getVal()); assertFalse(t.containsChild('a')); assertNull(t.getChild('a')); assertTrue(t.getAllChildren().isEmpty()); }
    @Test void storesValueAndEnd() { Trie t = new Trie(); t.setVal('r'); t.setEnd(true); assertEquals('r', t.getVal()); assertTrue(t.isEnd()); }
    @Test void addsChild() { Trie t = new Trie(); t.addChild('a'); assertTrue(t.containsChild('a')); assertEquals('a', t.getChild('a').getVal()); assertFalse(t.getChild('a').isEnd()); }
    @Test void childCanBeMarkedEnd() { Trie t = new Trie(); t.addChild('a'); t.getChild('a').setEnd(true); assertTrue(t.getChild('a').isEnd()); }
    @Test void supportsMultipleChildren() { Trie t = new Trie(); t.addChild('a'); t.addChild('b'); assertTrue(t.containsChild('a')); assertTrue(t.containsChild('b')); assertEquals(2, t.getAllChildren().size()); }
    @Test void repeatedPrefixPreservesBranch() { Trie t = new Trie(); t.addChild('a'); t.getChild('a').addChild('b'); t.getChild('a').getChild('b').setEnd(true); t.addChild('a'); assertTrue(t.getChild('a').getChild('b').isEnd()); }
    @Test void duplicateChildDoesNotIncreaseCount() { Trie t = new Trie(); t.addChild('x'); t.addChild('x'); assertEquals(1, t.getAllChildren().size()); }
    @Test void missingChildIsNull() { Trie t = new Trie(); t.addChild('a'); assertNull(t.getChild('z')); assertFalse(t.containsChild('z')); }
    @Test void childTreesAreIndependent() { Trie t = new Trie(); t.addChild('a'); t.addChild('b'); t.getChild('a').setEnd(true); assertTrue(t.getChild('a').isEnd()); assertFalse(t.getChild('b').isEnd()); }
    @Test void allChildrenReturnsSnapshotList() { Trie t = new Trie(); t.addChild('a'); List<Trie> children = t.getAllChildren(); children.clear(); assertTrue(t.containsChild('a')); assertEquals(1, t.getAllChildren().size()); }
    @Test void supportsNonLetterCharacters() { Trie t = new Trie(); t.addChild('0'); t.addChild('-'); assertTrue(t.containsChild('0')); assertTrue(t.containsChild('-')); }
}
