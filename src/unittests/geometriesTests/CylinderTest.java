package unittests.geometriesTests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class CylinderTest {
    /**
     * Test for
     * {@link Cylinder#getHeight()}
     */
    @Test
    public void getHeight() {
    }

    /**
     * Test for
     * {@link geometries.Cylinder#getNormal(Point3D)}
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: point on the body of the Cylinder
        Cylinder cy = new Cylinder(new Ray(Point3D.ZERO,new Vector(1,0,0)),1,2);

    }
}