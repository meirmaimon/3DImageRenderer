package unittests.geometriesTests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing Sphere
 */
public class SphereTest {
    /**
     * Test methods for
     * {@link Sphere#getCenter()}
     */
    @Test
    public void getCenter() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test
        Sphere sp = new Sphere(new Point3D(1,1,1),1);
        Point3D p = sp.getCenter();
        assertEquals("getCenter() wrong result",new Point3D(1,1,1),p);
    }
    /**
     * Test methods for
     * {@link Sphere#getRadius()}
     */
    @Test
    public void testGetRadius() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test
        Sphere sp = new Sphere(new Point3D(1,1,1),1);
        assert(1==sp.getRadius());
    }

    /**
     * Test methods for
     * {@link geometries.Sphere#getNormal(Point3D)}
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test
        Sphere sp = new Sphere(Point3D.ZERO , 1);
        Point3D p = new Point3D(1,0,0);
        Vector normal = sp.getNormal(p);
        assertEquals("Sphere getNormal() wrong result",new Vector(1,0,0),normal);
    }
}