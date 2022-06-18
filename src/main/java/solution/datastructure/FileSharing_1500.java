package solution.datastructure;

import java.util.*;

/**
 * We will use a file-sharing system to share a very large file which consists of m small chunks with IDs from 1 to m.
 * When users join the system, the system should assign a unique ID to them.
 * The unique ID should be used once for each user, but when a user leaves the system, the ID can be reused again.
 * Users can request a chunk of the file, the system should return a list of IDs of all the users who have this chunk.
 * After that, if at least one other has this chunk, the user who requested the chunk will get it.
 *
 * @author BorisMirage
 * Time: 2020/07/03 11:30
 * Created with IntelliJ IDEA
 */

public class FileSharing_1500 {
    HashMap<Integer, HashSet<Integer>> userToChunk = new HashMap<>();      // user -> owns chunk
    ArrayList<HashSet<Integer>> chunkToUser;        // chunk id -> list of user id
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    /**
     * Keep two hash maps to store the relationship.
     * One map stores mapping from user to all the chunks this user owns.
     * One map stores the relationship from the chunk id to all users own this chunk.
     * Keep a heap to store the available id, starts from 1.
     * If the heap is empty, add next integer of current assigned user id to the heap.
     * If a user leaves, add this user id to the heap, since it can be assigned again.
     *
     * @param m size of chunk
     */
    public FileSharing_1500(int m) {
        this.chunkToUser = new ArrayList<>(m);
        for (int i = 0; i <= m; i++) {
            chunkToUser.add(new HashSet<>());
        }
        pq.add(1);
    }

    /**
     * A new user comes in. Note that this user may owns no chunk.
     *
     * @param ownedChunks list of chunks this user owns
     * @return id assigned to this user
     */
    public int join(List<Integer> ownedChunks) {
        int userID = pq.poll();

        if (pq.isEmpty()) {
            pq.add(userID + 1);
        }

        userToChunk.put(userID, new HashSet<>(ownedChunks));

        for (Integer chunk : ownedChunks) {
            chunkToUser.get(chunk).add(userID);
        }

        return userID;
    }

    /**
     * A user leaves the system. The id will be put back to id pool.
     *
     * @param userID leave user
     */
    public void leave(int userID) {
        HashSet<Integer> tmp = userToChunk.get(userID);

        for (Integer chunk : tmp) {
            chunkToUser.get(chunk).remove(userID);
        }
        userToChunk.remove(userID);
        pq.add(userID);
    }

    /**
     * A user request certain chunk.
     * Note that if there exist user has the requested chunk, then the request user will have this chunk as well.
     *
     * @param userID  user call the request
     * @param chunkID requested chunk
     * @return all users owns the request chunk in ascending order
     */
    public List<Integer> request(int userID, int chunkID) {
        List<Integer> out = new ArrayList<>(chunkToUser.get(chunkID));
        out.sort(Comparator.comparingInt(o -> o));

        if (out.size() > 0) {
            chunkToUser.get(chunkID).add(userID);
            userToChunk.get(userID).add(chunkID);
        }

        return out;
    }
}
