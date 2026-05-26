package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/10 23:14
 * Created with IntelliJ IDEA
 */

public class NumUniqueEmails_929Test {

    private final NumUniqueEmails_929 test = new NumUniqueEmails_929();

    @Test
    public void test() {
        String[] emails = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        assertEquals(2, test.numUniqueEmails(emails));
    }

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numUniqueEmails(new String[]{"a@leetcode.com", "a@leetcode.com"}));
        assertEquals(2, test.numUniqueEmails(new String[]{"a@leetcode.com", "b@leetcode.com"}));
        assertEquals(1, test.numUniqueEmails(new String[]{"ab.c+d@leetcode.com", "a.bc+e@leetcode.com"}));
        assertEquals(2, test.numUniqueEmails(new String[]{"name+spam@leetcode.com", "name@other.com"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numUniqueEmails(new String[]{}));
        assertEquals(1, test.numUniqueEmails(new String[]{".a+b@leetcode.com"}));
        assertEquals(1, test.numUniqueEmails(new String[]{"z+y@leetcode.com", "z@leetcode.com"}));
        assertEquals(2, test.numUniqueEmails(new String[]{"a.b+c@leetcode.com", "ab@leetcode.com", "ab@lee.tcode.com"}));
    }

    @Test
    public void testGiantCase() {
        String[] emails = new String[200];
        for (int i = 0; i < 100; i++) {
            emails[i] = "user.name+" + i + "@leetcode.com";
            emails[i + 100] = "username@leetcode.com";
        }
        assertEquals(1, test.numUniqueEmails(emails));
    }

    @Test
    public void testOnlyDots() {
        assertEquals(1, test.numUniqueEmails(new String[]{"a.b.c@domain.com", "abc@domain.com"}));
    }

    @Test
    public void testOnlyPlus() {
        assertEquals(1, test.numUniqueEmails(new String[]{"user+foo@domain.com", "user+bar@domain.com"}));
    }

    @Test
    public void testDifferentDomains() {
        assertEquals(2, test.numUniqueEmails(new String[]{"a@a.com", "a@b.com"}));
    }

    @Test
    public void testSingleEmail() {
        assertEquals(1, test.numUniqueEmails(new String[]{"test@test.com"}));
    }

    @Test
    public void testDotsInDomain() {
        // Dots in domain should NOT be ignored
        assertEquals(2, test.numUniqueEmails(new String[]{"a@lee.tcode.com", "a@leetcode.com"}));
    }

    @Test
    public void testGiantAllDifferentDomains() {
        String[] emails = new String[100];
        for (int i = 0; i < 100; i++) {
            emails[i] = "user@domain" + i + ".com";
        }
        assertEquals(100, test.numUniqueEmails(emails));
    }
}
