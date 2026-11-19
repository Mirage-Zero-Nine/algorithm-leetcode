package solutions.design;

/**
 * Design the CombinationIterator class:
 * CombinationIterator(string characters, int combinationLength)
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 *
 * @author BorisMirage
 * Time: 2026/11/17 21:37
 * Created with IntelliJ IDEA
 */

public class CombinationIterator_1286 {
    private final int combinationLength;
    private final String characters;
    private final int[] characterIndices;
    private boolean hasNext;

    /**
     * Stores the ordered source and initializes the earliest combination. The
     * iterator retains only its current character positions. An empty source or
     * a length greater than the source length starts exhausted.
     *
     * <p>Time complexity: O(k), where {@code k} is the combination length, to
     * initialize the position array.
     *
     * <p>Auxiliary space complexity: O(k) for the retained position array.
     *
     * @param characters        distinct characters in lexicographical order
     * @param combinationLength number of characters in each result
     */
    public CombinationIterator_1286(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        characterIndices = new int[combinationLength];
        for (int position = 0; position < combinationLength; position++) {
            characterIndices[position] = position;
        }
        hasNext = !characters.isEmpty() && characters.length() >= combinationLength;
    }

    /**
     * Returns the current combination and advances the stored positions. Once
     * exhausted, this iterator returns {@code null} on further calls.
     *
     * <p>Time complexity: O(k), where {@code k} is the combination length.
     * Creating the result and advancing positions each take O(k) in the worst
     * case, where {@code k} is the combination length.
     *
     * <p>Auxiliary space complexity: O(k) for the temporary string builder.
     * The returned string is O(k) output and is excluded from auxiliary space.
     * The iterator also retains O(k) positions between calls.
     *
     * @return the next combination, or {@code null} after exhaustion
     */
    public String next() {
        if (!hasNext) {
            return null;
        }

        StringBuilder combination = new StringBuilder(combinationLength);
        for (int characterIndex : characterIndices) {
            combination.append(characters.charAt(characterIndex));
        }

        int rightmostAdvanceablePosition = combinationLength - 1;
        while (rightmostAdvanceablePosition >= 0
                && characterIndices[rightmostAdvanceablePosition]
                == characters.length() - combinationLength + rightmostAdvanceablePosition) {
            // This position is at its last possible index because each later position needs a character.
            rightmostAdvanceablePosition--;
        }
        if (rightmostAdvanceablePosition >= 0) {
            // Start with ab for characters "abcd" and length 2. Move the last chosen
            // letter first to get ab, ac, then ad. When d is stuck, move a to b and
            // reset the next choice to the first letter after b, giving bc. Continue
            // with bd, then cd, and finish when neither chosen position can move.
            // Keeping earlier letters fixed gives every later choice before changing
            // them, so the combinations stay in alphabetical order.
            characterIndices[rightmostAdvanceablePosition]++;
            // Fill later positions with the first available letters after the moved one.
            for (int position = rightmostAdvanceablePosition + 1;
                 position < combinationLength; position++) {
                characterIndices[position] = characterIndices[position - 1] + 1;
            }
        } else {
            hasNext = false;
        }
        return combination.toString();
    }

    /**
     * Reports whether another combination remains to be returned.
     *
     * <p>Time complexity: O(1), because the exhaustion state is stored.
     *
     * <p>Auxiliary space complexity: O(1), because no temporary storage is used.
     *
     * @return {@code true} when {@link #next()} can return another combination
     */
    public boolean hasNext() {
        return hasNext;
    }
}
