package Solution.Others;

/**
 * You are given a string representing an attendance record for a student.
 * The record only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if record doesn't contain more than 1 'A' (absent) or more than 2 continuous 'L' (late).
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * @author BorisMirage
 * Time: 2020/02/28 15:25
 * Created with IntelliJ IDEA
 */

public class CheckRecord_551 {
    /**
     * Note that only more than 2 continuous 'L' can count failure.
     *
     * @param s given string
     * @return whether the student could be rewarded according to his attendance record
     */
    public boolean checkRecord(String s) {
        int n = s.length();
        int countL = 0, countA = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                if (++countL > 2) {
                    return false;
                }
            } else {
                if (s.charAt(i) == 'A' && ++countA > 1) {
                    return false;
                }
                countL = 0;     // reset if no 2 continuous L
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CheckRecord_551().checkRecord("PPALLL"));        // false
    }
}
