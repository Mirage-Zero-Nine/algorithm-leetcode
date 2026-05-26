package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ThroneInheritance_1600Test {

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

    @Test
    public void testKingDies() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "prince");
        ti.death("king");
        assertEquals(List.of("prince"), ti.getInheritanceOrder());
    }

    @Test
    public void testAllDie() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "prince");
        ti.death("king");
        ti.death("prince");
        assertEquals(List.of(), ti.getInheritanceOrder());
    }

    @Test
    public void testDeepHierarchy() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("a");
        ti.birth("a", "b");
        ti.birth("b", "c");
        ti.birth("c", "d");
        assertEquals(List.of("a", "b", "c", "d"), ti.getInheritanceOrder());
    }

    @Test
    public void testDeathDoesNotRemoveChildren() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "prince");
        ti.birth("prince", "grandson");
        ti.death("prince");
        assertEquals(List.of("king", "grandson"), ti.getInheritanceOrder());
    }

    @Test
    public void testMultipleChildrenOrder() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "a");
        ti.birth("king", "b");
        ti.birth("king", "c");
        assertEquals(List.of("king", "a", "b", "c"), ti.getInheritanceOrder());
    }

    @Test
    public void testBirthAfterDeath() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        ti.birth("king", "prince");
        ti.death("prince");
        ti.birth("prince", "grandson");
        // prince is dead but grandson inherits
        assertEquals(List.of("king", "grandson"), ti.getInheritanceOrder());
    }

    @Test
    public void testGiantFamily() {
        ThroneInheritance_1600 ti = new ThroneInheritance_1600("king");
        for (int i = 0; i < 100; i++) {
            ti.birth("king", "child" + i);
        }
        for (int i = 0; i < 100; i++) {
            ti.birth("child" + i, "grandchild" + i);
        }
        List<String> order = ti.getInheritanceOrder();
        assertEquals(201, order.size());
        assertEquals("king", order.get(0));
    }
}
