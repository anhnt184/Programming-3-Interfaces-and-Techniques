
package fi.tuni.prog3.junitattainment;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tuan Anh Nguyen <anh.5.nguyen@tuni.fi>
 */

public class AttainmentTest {

    @Test
    public void testConstructorAndGetters() {
        System.out.println("ConstructerGetters");
        Attainment a = new Attainment("DPMBBE.017", "195358416", 5);
        assertEquals("DPMBBE.017", a.getCourseCode());
        assertEquals("195358416", a.getStudentNumber());
        assertEquals(5, a.getGrade());
    }

    @Test
    public void testConstructorWithInvalidArguments() {
        System.out.println("whenExceptionThrown");
        Exception exception  = assertThrows(IllegalArgumentException.class, () -> {
            Attainment a = new Attainment("DPMBBE.017", "195358416", -1);
        });
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Attainment a = new Attainment("DPMBBE.017", "195358416", 5);
        assertEquals("DPMBBE.017 195358416 5", a.toString());
    }

    @Test
    public void testCompareTo() {
        System.out.println("CompareTo");
        Attainment a1 = new Attainment("DPMBBE.017", "195358416", 5);
        Attainment a2 = new Attainment("DPMBBE.017", "195358417", 4);
        Attainment a3 = new Attainment("ACC.010", "195358416", 4);

        assertTrue(a1.compareTo(a2) < 0, "a1 should be less than a2");
        assertTrue(a1.compareTo(a3) > 0, "a1 should be greater than a3");
        assertTrue(a2.compareTo(a3) > 0, "a2 should be greater than a3");
        assertTrue(a1.compareTo(a1) == 0, "a1 should be equal to a1");
    }
}