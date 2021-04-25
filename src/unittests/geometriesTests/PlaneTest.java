package unittests.geometriesTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
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

    @Test
    public void testFindIntersections() {
        // assumption: if the Ray included in the Plane, return only the first point
        Plane plane = new Plane(new Point3D(1, 0, 0),new Vector(1,0,0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane (1 points)
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(0, 0, 1),
                new Vector(2, 1, 0)));
        assertEquals("Ray crosses plane - Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(new Point3D(1, 0.5, 1)), result);

        // TC02: Ray does not intersect the plane (0 point)
        assertEquals("Ray's line out of plane", null,
                plane.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(-1, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC11: Ray is included in the plane (there is no intersection)
        List<Point3D> result11 = plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals("Ray is included in the plane - Wrong number of points", null, result11);

        // TC12: Ray does not included in the plane (0 points)
        assertEquals("Ray does not included in the plane", null,
                plane.findIntersections(new Ray(new Point3D(0.5d , 0, 0), new Vector(0, 1, 0))));

        // **** Group: Ray is orthogonal to the plane
        // TC13: Ray starts before the plane (1 point)
        List<Point3D> result13 = plane.findIntersections(new Ray(new Point3D(0.5 , 0, 0), new Vector(1, 0, 0)));
        assertEquals("Ray starts before the plane - Wrong number of points", 1, result13.size());
        assertEquals("Ray starts before the plane", List.of(new Point3D(1, 0, 0)), result13);

        // TC14: Ray starts at the plane (1 points)
        List<Point3D> result14 = plane.findIntersections(new Ray(new Point3D(1 , 0, 0), new Vector(1, 0, 0)));
        assertEquals("Ray starts at the plane - Wrong number of points", null, result14);

        // TC15: Ray starts after the plane (0 points)
        assertEquals(" Ray starts at the plane", null,
                plane.findIntersections(new Ray(new Point3D(2 , 0, 0), new Vector(0, 0, 1))));

        // **** Group: Ray is neither orthogonal nor parallel to the plane
        // TC16: Ray begins at the plane (𝑃0 is in the plane, but not the ray)
        List<Point3D> result16 = plane.findIntersections(new Ray(new Point3D(1 , 1, 1), new Vector(6, 1, 1)));
        assertEquals("Ray begins at the plane - Wrong number of points", null, result16);

        // TC17: Ray begins in the same point which appears as reference point in the plane (Q)
        List<Point3D> result17 = plane.findIntersections(new Ray(new Point3D(1 , 0, 0), new Vector(6, 1, 1)));
        assertEquals("Ray begins in the same point which appears as reference point in the plane - Wrong number of points", null, result17);
    }
}