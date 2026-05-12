package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FileSharing1500Test {

    @Test
    public void testHappyCases() {
        FileSharing_1500 fs = new FileSharing_1500(4);
        assertEquals(1, fs.join(List.of(1, 2)));
        assertEquals(2, fs.join(List.of(2, 3)));
        assertEquals(List.of(1, 2), fs.request(1, 2));
        fs.leave(1);
        assertEquals(1, fs.join(List.of(2, 4)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        assertEquals(1, fs.join(List.of()));
        assertEquals(List.of(), fs.request(1, 1));
    }

    @Test
    public void testLargeCase() {
        FileSharing_1500 fs = new FileSharing_1500(5);
        int u1 = fs.join(List.of(1, 2, 3));
        int u2 = fs.join(List.of(3, 4, 5));
        List<Integer> result = fs.request(u1, 4);
        assertTrue(result.contains(u2));
    }
}
