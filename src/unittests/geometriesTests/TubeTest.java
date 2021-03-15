package unittests.geometriesTests;

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
        fail("Not implemented yet");
    }
    /**
     * Test method for
     * {@link Tube#getRadius()}
     */
    @Test
    public void getRadius() {
        fail("Not implemented yet");
    }
    /**
     * Test method for
     * {@link Tube#getNormal(Point3D)}
     */
    @Test
    public void getNormal() {
        Tube tb = new Tube(new Ray(Point3D.ZERO,new Vector(1,0,0)),1);
        Point3D p = new Point3D(1,0,1);
        Vector normal = new Vector(0,0,1);
        assertEquals("Tube getNormal() wrong result",new Vector(0,0,1),normal);
    }
}