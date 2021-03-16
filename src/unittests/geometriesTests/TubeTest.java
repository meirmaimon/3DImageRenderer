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
     * {@link Tube#getAxisRay()}
     */
    @Test
    public void getAxisRay() {
        // ============ Equivalence Partitions Tests ==============
        // TC01
        Point3D p3= new Point3D(1,2,2);
        Vector v = new Vector(2,2,2);
        Ray r = new Ray(p3,v);
        double rad = 3;
        Tube t = new Tube(r,rad);
        assertEquals("not the Axis Ray ", r , t.getAxisRay());

    }

    /**
     * Test method for
     * {@link Tube#getRadius()}
     */
    @Test
    public void testGetRadius() {
        // ============ Equivalence Partitions Tests ==============
        // TC01
        Point3D p3= new Point3D(1,2,2);
        Vector v = new Vector(2,2,2);
        Ray r = new Ray(p3,v);
        double rad = 3;
        Tube t = new Tube(r,rad);
        assert( t.getRadius() == rad);

    }

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
}