package solution.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/11 14:23
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates1047Test {

    private final RemoveDuplicates_1047 test = new RemoveDuplicates_1047();

    @Test
    public void test() {
        assertEquals("ca", test.removeDuplicates("abbaca"));
        assertEquals("ay", test.removeDuplicates("azxxzy"));
    }
}