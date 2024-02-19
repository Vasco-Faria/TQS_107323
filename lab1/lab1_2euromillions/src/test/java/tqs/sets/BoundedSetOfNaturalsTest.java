/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;
    private BoundedSetOfNaturals setE;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3, 4, 5});
        setE = BoundedSetOfNaturals.fromArray(new int[]{27,20,51});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    @Test
    public void testContains() {
        assertTrue(setB.contains(10), "contains: element not found in set.");
        assertFalse(setB.contains(5), "contains: element found in set.");
    }


    @Test
    public void testIntersects() {
        assertTrue(setB.intersects(setC), "intersects: sets do not intersect.");
        assertFalse(setB.intersects(setD), "intersects: sets intersect.");
        assertTrue(setB.intersects(setE), "intersects: sets do not intersect.");
    }

    //always <5
    @Test
    public void testSize() {
        assertEquals(6, setB.size(), "size: wrong size.");
    }


}
