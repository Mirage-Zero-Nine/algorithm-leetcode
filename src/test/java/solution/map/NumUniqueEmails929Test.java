package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/10 23:14
 * Created with IntelliJ IDEA
 */

public class NumUniqueEmails929Test {

    private final NumUniqueEmails_929 test = new NumUniqueEmails_929();

    @Test
    public void test() {
        String[] emails = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        assertEquals(2, test.numUniqueEmails(emails));
    }
}