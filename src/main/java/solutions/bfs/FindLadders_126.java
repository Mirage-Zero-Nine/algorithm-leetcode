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
        // corner case
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

        Set<String> words = wordList.stream()
                .filter(Objects::nonNull)
                .filter(w -> !w.equals(beginWord))
                .filter(w -> w.length() == beginWord.length())
                .collect(Collectors.toSet());
        // corner case
        if (!words.contains(endWord)) {
            return new ArrayList<>();
        }

        // BFS records every predecessor that can lead to a word by a shortest route.
        // DFS can then enumerate the combinations represented by that predecessor graph.
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> output = new ArrayList<>();
        if (bfs(beginWord, endWord, words, map)) {
            dfs(endWord, beginWord, map, new ArrayList<>(List.of(endWord)), output);
        }
        return output;
    }

    /**
     * Builds the shortest-path predecessor graph with level-order BFS.
     *
     * <p>{@code size} freezes the number of words in the current BFS level.
     * Words discovered while processing that level are queued for the next
     * level, so every edge recorded in {@code map} advances the transformation
     * by exactly one step. Words discovered in the same level are removed from
     * {@code words} only after the level is complete; this allows multiple
     * current words to become predecessors of the same next word.</p>
     *
     * @param begin the starting word
     * @param end   the target word
     * @param words the remaining unvisited dictionary words; this set is
     *              consumed as BFS progresses
     * @param map   the graph in which each key maps to its shortest-path
     *              predecessors
     * @return {@code true} when the target is reached; otherwise {@code false}
     */
    private boolean bfs(String begin,
                        String end,
                        Set<String> words,
                        Map<String, List<String>> map) {
        Queue<String> q = new ArrayDeque<>(List.of(begin));
        int size = q.size();
        while (!q.isEmpty()) {
            // Every word currently in the queue is the same distance from the start.
            // Newly discovered words belong to the following level.
            Set<String> usedWords = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (generateAndCheckNewWords(q.poll(), end, words, usedWords, q, map)) {
                    return true;
                }
            }

            // Delay removal until all parents in this level have been checked
            // so that converging shortest paths are not lost.
            size = q.size();
            words.removeAll(usedWords);
        }
        return false;
    }

    /**
     * Generates all one-letter mutations of {@code current} and adds valid
     * mutations to the next BFS level.
     *
     * <p>The character array is mutated in place one position at a time. For
     * every candidate present in {@code words}, the method queues it only once
     * per level, but always records {@code current} as a predecessor. The
     * distinction is important: several words in the current level may reach
     * the same candidate, and every such predecessor is needed to reconstruct
     * all shortest ladders.</p>
     *
     * <p>The target is checked when it is removed from the queue rather than
     * when it is first discovered. Therefore, all shortest predecessors have
     * already had an opportunity to register themselves before BFS stops.</p>
     *
     * @param current   the word currently being expanded
     * @param end       the target word
     * @param words     the dictionary words that have not been visited by an
     *                  earlier BFS level
     * @param usedWords words already queued during the current BFS level
     * @param q         the BFS queue containing the current and subsequent levels
     * @param map       the predecessor graph used later by DFS
     * @return {@code true} when {@code current} is the target; otherwise
     * {@code false}
     */
    private boolean generateAndCheckNewWords(String current,
                                             String end,
                                             Set<String> words,
                                             Set<String> usedWords,
                                             Queue<String> q,
                                             Map<String, List<String>> map) {
        if (current.equals(end)) {
            return true;
        }

        // Reuse one array for all mutations instead of constructing a new
        // base word for every position and replacement character.
        char[] array = current.toCharArray();
        for (int j = 0; j < array.length; j++) {
            char c = array[j];

            // Try every lowercase replacement. The original character is
            // harmless here because visited words have already been removed
            // from `words`, and a valid mutation must be in that set.
            for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                array[j] = newChar;
                String newWord = new String(array);
                if (words.contains(newWord)) {
                    // Add each word to the queue once, but save every current word that can reach it.
                    if (!usedWords.contains(newWord)) {
                        usedWords.add(newWord);
                        q.add(newWord);
                    }
                    // Save the current word as a previous step in the path.
                    map.computeIfAbsent(newWord, _ -> new ArrayList<>()).add(current);
                }
            }

            array[j] = c;
        }
        return false;
    }

    /**
     * Enumerates paths by walking the predecessor graph backward from the
     * target to the start.
     *
     * <p>Each predecessor is inserted at the front of {@code tmp}, keeping a
     * completed path in the original start-to-target order. BFS guarantees
     * that these links belong to shortest paths, so DFS only explores valid
     * shortest-path choices.</p>
     *
     * @param begin  the word currently being followed backward
     * @param end    the original starting word; despite the parameter name, this
     *               is the stopping point for the reverse traversal
     * @param map    the predecessor graph built by BFS
     * @param tmp    the path currently being assembled
     * @param output the collection receiving completed paths
     */
    private void dfs(String begin, String end, Map<String, List<String>> map, List<String> tmp, List<List<String>> output) {
        if (begin.equals(end)) {
            output.add(new ArrayList<>(tmp));
            return;
        }

        // Backtrack after each predecessor so the next branch starts with the
        // same partial path.
        map.get(begin).forEach(w -> {
            tmp.addFirst(w);
            dfs(w, end, map, tmp, output);
            tmp.removeFirst();
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
