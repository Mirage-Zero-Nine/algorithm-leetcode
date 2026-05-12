package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ThroneInheritance1600Test {

    @Test
    public void testHappyCases() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "andy");
        ti.birth("king", "bob");
        ti.birth("king", "catherine");
        ti.birth("andy", "matthew");
        ti.birth("bob", "alex");
        ti.birth("bob", "asha");
        assertEquals(List.of("king", "andy", "matthew", "bob", "alex", "asha", "catherine"), ti.getInheritanceOrder());
        ti.death("bob");
        assertEquals(List.of("king", "andy", "matthew", "alex", "asha", "catherine"), ti.getInheritanceOrder());
    }

    @Test
    public void testEdgeCases() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        assertEquals(List.of("king"), ti.getInheritanceOrder());
    }

    @Test
    public void testLargeCase() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        for (int i = 1; i <= 5; i++) ti.birth("king", "child" + i);
        assertEquals(6, ti.getInheritanceOrder().size());
    }
}
