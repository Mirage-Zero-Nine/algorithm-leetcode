package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/31 00:29
 * Created with IntelliJ IDEA
 */

public class FindAllConcatenatedWordsInADict472Test {

    private final FindAllConcatenatedWordsInADict_472 test = new FindAllConcatenatedWordsInADict_472();

    @Test
    public void test() {
        String[] words = {"cat", "dog", "catdog"};
        assertIterableEquals(Lists.newArrayList("catdog"), test.findAllConcatenatedWordsInADict(words));
    }
}