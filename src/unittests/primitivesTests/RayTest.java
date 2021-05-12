package unittests.primitivesTests;
import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test for Ray
 */
public class RayTest {
    /**
     * Test for {@link primitives.Ray#findClosestGeoPoint(List)}
     */
    @Test
    public void findClosestGeoPoint() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: closest point is in the middle of the list
        List<GeoPoint> points = new LinkedList<GeoPoint>();
        Ray r1 = new Ray(new Point3D(1.75,0.,0), new Vector(1,0,0));
        GeoPoint p2 = new GeoPoint(new Sphere(Point3D.ZERO,2),new Point3D(2,0,0));
        GeoPoint p3 = new GeoPoint(new Sphere(Point3D.ZERO,3),new Point3D(3,0,0));
        GeoPoint p1 = new GeoPoint(new Sphere(Point3D.ZERO,1),new Point3D(1,0,0));
        points.add(p1);
        points.add(p2);
        points.add(p3);
        assertEquals("Wrong point" ,p2,r1.findClosestGeoPoint(points));

        // =============== Boundary Values Tests ==================

        // TC02:Empty list
        List<GeoPoint> points1 = new LinkedList<GeoPoint>();
        assertEquals("Wrong point" , null,r1.findClosestGeoPoint(points1));
        // TC03: First point is the closest
        Ray r2 = new Ray(Point3D.ZERO, new Vector(1,0,0));
        assertEquals("Wrong point" , p1,r2.findClosestGeoPoint(points));
        // TC04:Last is closest
        Ray r3 = new Ray(new Point3D(2.75,0.,0), new Vector(1,0,0));
        assertEquals("Wrong point" , p3 ,r3.findClosestGeoPoint(points));
    }
}