package solution.others;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:08
 * Created with IntelliJ IDEA
 */

public class RomanToInt13Test {
    private final RomanToInt_13 test = new RomanToInt_13();

    @Test
    public void test() {
        for (int i = 0; i < romanGenerator().size(); i++) {
            Assertions.assertEquals(i + 1, test.romanToInt(romanGenerator().get(i)));
        }
    }

    @Test
    public void testSpecialCase() {
        Assertions.assertEquals(58, test.romanToInt("LVIII"));
        Assertions.assertEquals(1994, test.romanToInt("MCMXCIV"));
    }

    private List<String> romanGenerator() {
        return Lists.newArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        );
    }
}