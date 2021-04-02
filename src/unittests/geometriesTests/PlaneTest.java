package unittests.geometriesTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Testing plane
 */
public class PlaneTest {

    /**
     * Test method for 
     * {@link geometries.Plane#getNormal(Point3D)}
     */
    @Test
    public void testGetNormalWithPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01
        Point3D p3= new Point3D(1,2,2);
        Vector v = new Vector(2,2,2);
        Plane p = new Plane(p3,v);
        assertEquals("not the same normal ", v , p.getNormal());

    }
}