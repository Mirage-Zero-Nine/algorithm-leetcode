package Solution.BitManipulation;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * @author BorisMirage
 * Time: 2019/07/01 11:04
 * Created with IntelliJ IDEA
 */

public class FindRepeatedDnaSequences_187 {
    /**
     * Encoding each 'A', 'C', 'G', 'T' into binary digit, such as 00, 01, 10, 11.
     * In this way, each 10-letter-long sequences will be 20 bits long, which is under size of an int.
     * Map each subsequence into bits and check if each sequence has been shown before.
     * Note that to set n-th bit of bitmask equal to 0: bitmask &= ~(1 << n).
     *
     * @param s given DNA molecule
     * @return all the 10-letter-long sequences in s
     */
    public List<String> findRepeatedDnaSequences(String s) {

        /* Corner case */
        if (s.length() < 10) {
            return new ArrayList<>();
        }

        int n = s.length();

        HashMap<Character, Integer> m = new HashMap<>();        // save mapping relation
        m.put('A', 0);
        m.put('C', 1);
        m.put('G', 2);
        m.put('T', 3);

        int mask = 0;
        Set<Integer> sequence = new HashSet<>();        // save previous sequence
        Set<String> out = new HashSet<>();

        for (int i = 0; i < 10; i++) {      // first sequence
            mask <<= 2;
            mask |= m.get(s.charAt(i));
        }
        sequence.add(mask);

        for (int i = 1; i < n - 9; i++) {           // rolling hash

            mask <<= 2;                             // left shift to free the last 2 bit
            mask |= m.get(s.charAt(i + 9));         // add a new 2-bits number in the last two bits
            mask &= ~(3 << 20);                     // unset first two bits: 2L-bit and (2L + 1)-bit

            if (sequence.contains(mask)) {
                out.add(s.substring(i, i + 10));
            }
            sequence.add(mask);
        }

        return new ArrayList<>(out);
    }

    public static void main(String[] args) {
        System.out.println(new FindRepeatedDnaSequences_187().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
