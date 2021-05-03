package unittests.geometriesTests;
import geometries.Plane;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;

/**
 * Testing Tube
 */
public class TubeTest {
    /**
     * Test method for
     * {@link Tube#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        Tube tb = new Tube(new Ray(Point3D.ZERO,new Vector(1,0,0)),1);
        // ============ Equivalence Partitions Tests ==============
        // TC01
        Vector normal1 = tb.getNormal(new Point3D(1,0,1));
        assertEquals("Tube getNormal() wrong result",new Vector(0,0,1),normal1);

        // ============ Boundary Value Analysis Tests ==============
        // TC02 Point against axis head
        Vector normal2 = tb.getNormal(new Point3D(0,0,1));
        assertEquals("Tube getNormal() wrong result",new Vector(0,0,1),normal2);
    }
    /**
     * Test methods for
     * {@link geometries.Tube#findIntersections(Ray)}
     */
    //@Test
    //public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test
      //  fail("not implemented");
   // }
}