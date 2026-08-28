package solutions.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list.
 * Find all shortest transformation sequence(s) from beginWord to endWord.
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 1. Return an empty list if there is no such transformation sequence.
 * 2. All words have the same length.
 * 3. All words contain only lowercase alphabetic characters.
 * 4. No duplicates in the word list. beginWord and endWord are non-empty and are different.
 *
 * @author BorisMirage
 */
public class FindLadders_126 {

    // =====================================================================
    // Approach 1: existing direct-mutation BFS + reverse DFS
    // =====================================================================

    /**
     * Finds all shortest ladders with the original direct-mutation BFS.
     *
     * <p>For every dequeued word, this approach tries each possible replacement
     * character. The BFS map stores all shortest predecessors for each word;
     * the DFS below follows those links backward from {@code endWord}.</p>
     *
     * @param beginWord the starting word; it does not need to be in
     *                  {@code wordList}
     * @param endWord   the required final word; it must be in {@code wordList}
     * @param wordList  the dictionary of allowed transformed words
     * @return all shortest transformation sequences, or an empty list when no
     * valid sequence exists
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Defensive checks for null, empty, mismatched, or equal inputs.
        if (beginWord == null
                || endWord == null
                || wordList == null
                || beginWord.isEmpty()
                || endWord.isEmpty()
                || wordList.isEmpty()
                || beginWord.length() != endWord.length()
                || beginWord.equals(endWord)) {
            return new ArrayList<>();
        }

        // Work on a private set: BFS removes visited words from this set, but
        // the caller's wordList must remain unchanged.
        Set<String> words = wordList.stream()
                .filter(Objects::nonNull)
                .filter(w -> !w.equals(beginWord))
                .filter(w -> w.length() == beginWord.length())
                .collect(Collectors.toSet());

        if (!words.contains(endWord)) {
            return new ArrayList<>();
        }

        // map[child] contains every word from the previous BFS level that can
        // transform into child. It is a DAG of shortest-path choices.
        Map<String, List<String>> map = new HashMap<>();
        if (!bfs(beginWord, endWord, words, map)) {
            return new ArrayList<>();
        }

        // BFS has established the shortest distance; DFS now enumerates all
        // combinations of predecessor choices without searching longer paths.
        List<List<String>> output = new ArrayList<>();
        dfs(endWord, beginWord, map, new ArrayList<>(List.of(endWord)), output);
        return output;
    }

    /**
     * Builds the predecessor map by trying every replacement character at
     * every position in each word.
     *
     * <p>{@code size} freezes the current BFS level. A discovered word stays
     * available until that level is complete so that multiple words in the
     * level can register themselves as predecessors.</p>
     */
    private boolean bfs(
            String begin,
            String end,
            Set<String> words,
            Map<String, List<String>> map) {

        Queue<String> q = new ArrayDeque<>(List.of(begin));
        int size = q.size();

        while (!q.isEmpty()) {
            // Only words already in the queue at this point belong to this
            // level; newly queued words must wait for the next level.
            Set<String> usedWords = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (charLooping(q.poll(), end, words, usedWords, q, map)) {
                    // The target is dequeued only after every possible parent
                    // from the previous level has already been recorded.
                    return true;
                }
            }

            // Do this after processing the entire level. Removing a word
            // earlier would lose another shortest predecessor.
            words.removeAll(usedWords);
            size = q.size();
        }

        return false;
    }

    /**
     * Generates all one-letter mutations of the current word and records the
     * valid mutations in the BFS queue and predecessor map.
     *
     * <p>{@code usedWords} contains words discovered during the current BFS
     * level. A word may have more than one predecessor at that level, so it is
     * added to the queue only once while every valid predecessor is retained
     * in {@code map}.</p>
     *
     * @param current   the word currently being expanded
     * @param end       the target word
     * @param words     the unvisited dictionary words
     * @param usedWords words already queued during the current BFS level
     * @param q         the BFS queue
     * @param map       the predecessor map used later by DFS
     * @return {@code true} when {@code current} is the target word;
     * otherwise {@code false}
     */
    private boolean charLooping(String current,
                                String end,
                                Set<String> words,
                                Set<String> usedWords,
                                Queue<String> q,
                                Map<String, List<String>> map) {
        if (current.equals(end)) {
            return true;
        }

        char[] arr = current.toCharArray();
        // Change one position at a time. The character array avoids creating
        // a new base word for every attempted mutation.
        for (int j = 0; j < arr.length; j++) {
            char c = arr[j];
            // Try all lowercase replacements for this position. Only words
            // present in the remaining dictionary are graph neighbors.
            for (char k = 'a'; k <= 'z'; k++) {
                if (k != c) {
                    arr[j] = k;
                    String tmp = new String(arr);
                    if (words.contains(tmp)) {
                        // Queue each word once per level, but preserve all
                        // same-level predecessors for every shortest path.
                        if (!usedWords.contains(tmp)) {
                            q.add(tmp);
                            usedWords.add(tmp);
                        }
                        map.computeIfAbsent(tmp, _ -> new ArrayList<>()).add(current);
                    }
                }
            }

            // Restore the original character before changing the next position.
            arr[j] = c;
        }
        return false;
    }

    /**
     * Walks the predecessor map backward from {@code end} to {@code begin}.
     *
     * <p>The initial call uses {@code begin = endWord} and
     * {@code current = [endWord]}. Each predecessor is inserted at the front,
     * so a completed path is already ordered from the original begin word to
     * the original end word.</p>
     */
    private void dfs(
            String begin,
            String end,
            Map<String, List<String>> map,
            List<String> current,
            List<List<String>> output) {
        if (begin.equals(end)) {
            // The reverse walk reached the original begin word; copy the
            // current path because the deque continues to be backtracked.
            output.add(new ArrayList<>(current));
            return;
        }

        // Every predecessor represents one branch of a possible shortest
        // ladder. BFS guarantees that these links move one level toward the
        // original begin word, so the predecessor graph has no cycles.
        // Backtracking restores the path for the next branch.
        map.get(begin).forEach(s -> {
            current.addFirst(s);
            dfs(s, end, map, current, output);
            current.removeFirst();
        });
    }

    // =====================================================================
    // Approach 2: wildcard pattern indexing
    // =====================================================================

    /**
     * Finds all shortest ladders by indexing each dictionary word under its
     * wildcard patterns, such as {@code h*t}, {@code ho*}, and {@code *it}.
     *
     * <p>Words in the same bucket differ in at most the wildcard position, so
     * the BFS can inspect dictionary neighbors without trying all 25 letters
     * at every position. This is useful when the dictionary or word length is
     * larger than the original problem constraints.</p>
     *
     * @param beginWord the starting word
     * @param endWord   the required final word
     * @param wordList  the dictionary of allowed transformed words
     * @return all shortest transformation sequences, or an empty list when no
     * valid sequence exists
     */
    public List<List<String>> findLaddersWithPatternIndexing(
            String beginWord,
            String endWord,
            List<String> wordList) {
        if (hasInvalidInput(beginWord, endWord, wordList)) {
            return new ArrayList<>();
        }

        Set<String> words = validWords(beginWord, wordList);
        if (!words.contains(endWord)) {
            return new ArrayList<>();
        }

        // The dictionary is the set of possible graph vertices. The begin
        // word is a starting point, not a transformed word, so it is removed
        // from the candidate set after validation.
        words.remove(beginWord);

        // Each word contributes one pattern per character position. Sharing a
        // pattern means two words differ at no more than that position.
        Map<String, List<String>> patternIndex = buildPatternIndex(words);
        Map<String, List<String>> predecessors = new HashMap<>();

        if (!buildPredecessorsByPattern(beginWord, endWord, words, patternIndex, predecessors)) {
            return new ArrayList<>();
        }

        return buildPathsFromPredecessors(beginWord, endWord, predecessors);
    }

    /**
     * Performs BFS over wildcard buckets and records predecessor links.
     *
     * <p>A whole level is processed before {@code usedThisLevel} is removed
     * from {@code words}. Thus, if two current words point to the same
     * candidate, both are stored as predecessors even though the candidate is
     * queued only once.</p>
     */
    private boolean buildPredecessorsByPattern(
            String begin,
            String end,
            Set<String> words,
            Map<String, List<String>> patternIndex,
            Map<String, List<String>> predecessors) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(begin);
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            // Freeze the current distance from begin. Nodes added below are
            // exactly one transformation farther away.
            int levelSize = queue.size();
            Set<String> usedThisLevel = new HashSet<>();

            for (int i = 0; i < levelSize; i++) {
                String current = queue.remove();
                if (current.equals(end)) {
                    // The target was discovered on the preceding level, so
                    // all of its shortest predecessors have been collected.
                    found = true;
                    continue;
                }

                for (int position = 0; position < current.length(); position++) {
                    // A wildcard bucket replaces one position with '*'; every
                    // bucket member is a possible one-letter neighbor.
                    String pattern = wildcardPattern(current, position);
                    List<String> bucket = patternIndex.get(pattern);
                    if (bucket == null) {
                        continue;
                    }

                    for (String candidate : bucket) {
                        // Visited words are kept in the index for simplicity,
                        // so the set check prevents revisiting older levels.
                        if (!words.contains(candidate)) {
                            continue;
                        }
                        if (usedThisLevel.add(candidate)) {
                            // Queue once, but do not use this condition to
                            // decide whether the predecessor should be saved.
                            queue.add(candidate);
                        }

                        // A candidate may have several parents in this level;
                        // all of them are needed to enumerate every answer.
                        predecessors
                                .computeIfAbsent(candidate, ignored -> new ArrayList<>())
                                .add(current);
                    }
                }
            }

            // Delay removal until all current-level words have inspected their
            // buckets, preserving converging shortest paths.
            words.removeAll(usedThisLevel);
        }

        return found;
    }

    /**
     * Builds a wildcard index for all valid dictionary words.
     *
     * <p>The index is a map from a pattern such as {@code h*t} to all words
     * matching that pattern. It is built once and reused during BFS.</p>
     */
    private Map<String, List<String>> buildPatternIndex(Set<String> words) {
        Map<String, List<String>> patternIndex = new HashMap<>();
        for (String word : words) {
            for (int position = 0; position < word.length(); position++) {
                String pattern = wildcardPattern(word, position);
                patternIndex
                        .computeIfAbsent(pattern, ignored -> new ArrayList<>())
                        .add(word);
            }
        }
        return patternIndex;
    }

    /**
     * Returns the wildcard form for one character position.
     * For example, {@code hot} at position {@code 1} becomes {@code h*t}.
     */
    private String wildcardPattern(String word, int position) {
        char[] characters = word.toCharArray();
        characters[position] = '*';
        return new String(characters);
    }

    // =====================================================================
    // Approach 3: bidirectional BFS
    // =====================================================================

    /**
     * Finds all shortest ladders using bidirectional BFS.
     *
     * <p>The smaller frontier is expanded at each step. Edges are oriented
     * from the {@code beginWord} side toward the {@code endWord} side even
     * when the current expansion is moving backward. Once the frontiers meet,
     * the graph contains only shortest-path edges and the predecessor DFS
     * constructs every ladder.</p>
     *
     * @param beginWord the starting word
     * @param endWord   the required final word
     * @param wordList  the dictionary of allowed transformed words
     * @return all shortest transformation sequences, or an empty list when no
     * valid sequence exists
     */
    public List<List<String>> findLaddersBidirectional(
            String beginWord,
            String endWord,
            List<String> wordList) {
        if (hasInvalidInput(beginWord, endWord, wordList)) {
            return new ArrayList<>();
        }

        Set<String> dictionary = validWords(beginWord, wordList);
        if (!dictionary.contains(endWord)) {
            return new ArrayList<>();
        }
        dictionary.add(beginWord);

        // front is the level currently expanded; back is the opposing
        // frontier. The direction flag tells edge construction which side is
        // closer to the original begin word.
        Set<String> front = new HashSet<>(Set.of(beginWord));
        Set<String> back = new HashSet<>(Set.of(endWord));

        // Endpoints are already present in their respective frontiers. Every
        // other word may be discovered by only one side of the search.
        Set<String> unvisited = new HashSet<>(dictionary);
        unvisited.remove(beginWord);
        unvisited.remove(endWord);

        Map<String, List<String>> predecessors = new HashMap<>();
        boolean expandingFromBegin = true;
        boolean found = false;

        while (!front.isEmpty() && !back.isEmpty() && !found) {
            // Expanding the smaller frontier keeps the bidirectional search balanced.
            if (front.size() > back.size()) {
                // The two frontiers are interchangeable for distance search.
                // Flip the flag because the newly selected front is now the
                // frontier growing from the original end word.
                Set<String> swap = front;
                front = back;
                back = swap;
                expandingFromBegin = !expandingFromBegin;
            }

            // Do not add newly discovered words to unvisited until this whole
            // frontier has been processed. This keeps same-level alternatives.
            Set<String> nextFront = new HashSet<>();
            for (String current : front) {
                // Neighbors are generated from the current frontier word. A
                // meeting edge joins this side directly to the other frontier.
                for (String neighbor : neighbors(current, dictionary)) {
                    if (back.contains(neighbor)) {
                        // Keep processing this level so all shortest meeting
                        // edges are included in the predecessor graph.
                        found = true;
                        addOrientedPredecessor(
                                predecessors, current, neighbor, expandingFromBegin);
                    } else if (!found && unvisited.contains(neighbor)) {
                        // Once one meeting edge is found, do not continue into
                        // a deeper level; only shortest-path edges are wanted.
                        nextFront.add(neighbor);
                        addOrientedPredecessor(
                                predecessors, current, neighbor, expandingFromBegin);
                    }
                }
            }

            // Remove a complete level only after all of its parents were examined.
            unvisited.removeAll(nextFront);
            front = nextFront;
        }

        if (!found) {
            return new ArrayList<>();
        }

        return buildPathsFromPredecessors(beginWord, endWord, predecessors);
    }

    /**
     * Returns every dictionary word one mutation away from {@code word}.
     * This approach deliberately uses direct mutation for neighbor generation;
     * bidirectional BFS reduces the number of expanded levels, while this
     * helper still tries each alphabet replacement at each position.
     */
    private Set<String> neighbors(String word, Set<String> dictionary) {
        Set<String> result = new HashSet<>();
        char[] characters = word.toCharArray();

        for (int position = 0; position < characters.length; position++) {
            char original = characters[position];
            for (char replacement = 'a'; replacement <= 'z'; replacement++) {
                if (replacement == original) {
                    continue;
                }

                characters[position] = replacement;
                String candidate = new String(characters);
                if (dictionary.contains(candidate)) {
                    result.add(candidate);
                }
            }
            // Restore the word before mutating the next position.
            characters[position] = original;
        }

        return result;
    }

    /**
     * Adds a graph edge as a predecessor link, regardless of which direction
     * the bidirectional search is currently expanding.
     *
     * <p>When expanding from begin, the edge is {@code current -> neighbor}.
     * When expanding from end, the physical search sees the reverse direction,
     * so the stored predecessor relationship is flipped back to
     * {@code neighbor -> current} in begin-to-end terms.</p>
     */
    private void addOrientedPredecessor(
            Map<String, List<String>> predecessors,
            String current,
            String neighbor,
            boolean expandingFromBegin) {
        String child = expandingFromBegin ? neighbor : current;
        String parent = expandingFromBegin ? current : neighbor;
        predecessors
                .computeIfAbsent(child, ignored -> new ArrayList<>())
                .add(parent);
    }

    // =====================================================================
    // Shared helpers: path reconstruction and defensive input handling
    // =====================================================================

    /**
     * Expands predecessor links backward from end to begin while keeping the
     * path in forward order in the deque.
     */
    private List<List<String>> buildPathsFromPredecessors(
            String begin,
            String end,
            Map<String, List<String>> predecessors) {
        List<List<String>> output = new ArrayList<>();
        Deque<String> currentPath = new ArrayDeque<>();

        // Start at the end and prepend predecessors until begin is reached.
        currentPath.add(end);
        collectPaths(end, begin, predecessors, currentPath, output);
        return output;
    }

    /**
     * DFS helper for materializing all paths from predecessor links.
     */
    private void collectPaths(
            String current,
            String begin,
            Map<String, List<String>> predecessors,
            Deque<String> currentPath,
            List<List<String>> output) {
        if (current.equals(begin)) {
            output.add(new ArrayList<>(currentPath));
            return;
        }

        List<String> parents = predecessors.get(current);
        if (parents == null) {
            // No predecessor means this branch cannot reach the original begin.
            return;
        }

        // Explore every predecessor choice; the deque is restored after each
        // recursive call so branches do not contaminate one another.
        parents.forEach(parent -> {
            currentPath.addFirst(parent);
            collectPaths(parent, begin, predecessors, currentPath, output);
            currentPath.removeFirst();
        });
    }

    /**
     * Applies the defensive input policy used by the pattern-indexed and
     * bidirectional approaches. Approach 1 keeps its original inline checks.
     * LeetCode supplies valid lowercase words, but local callers/tests may
     * provide null, empty, equal, or differently sized inputs.
     */
    private boolean hasInvalidInput(String beginWord, String endWord, List<String> wordList) {
        return beginWord == null
                || endWord == null
                || wordList == null
                || beginWord.isEmpty()
                || endWord.isEmpty()
                || wordList.isEmpty()
                || beginWord.length() != endWord.length()
                || beginWord.equals(endWord);
    }

    /**
     * Filters null and wrong-length dictionary entries so defensive tests
     * cannot break the pattern-indexed or bidirectional implementations. The
     * official problem constraint already guarantees unique, lowercase,
     * same-length entries; lowercase validation is therefore not needed here.
     */
    private Set<String> validWords(String beginWord, List<String> wordList) {
        return wordList.stream()
                .filter(Objects::nonNull)
                .filter(w -> w.length() == beginWord.length())
                .collect(Collectors.toSet());
    }
}
