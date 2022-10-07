package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/07 00:05
 * Created with IntelliJ IDEA
 */

public class LadderLength127Test {

    private final LadderLength_127 test = new LadderLength_127();

    @Test
    public void test() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
        wordList = Lists.newArrayList("a", "b", "c");
        assertEquals(2, test.ladderLength("a", "c", wordList));
    }

    @Test
    public void testImpossible() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log");
        assertEquals(0, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testInvalidInput() {
        List<String> wordList = Lists.newArrayList();
        assertEquals(0, test.ladderLength("a", "c", wordList));
    }
}