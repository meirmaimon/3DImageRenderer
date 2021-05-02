package unittests.primitivesTests;

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

    @Test
    public void findClosestPoint() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: closest point is in the middle of the list
        List<Point3D> points = new LinkedList<Point3D>();
        Ray r1 = new Ray(new Point3D(1.75,0.,0), new Vector(1,0,0));
        points.add(new Point3D(1,0,0));
        points.add(new Point3D(2,0,0));
        points.add(new Point3D(3,0,0));
        assertEquals("Wrong point" , new Point3D(2,0,0),r1.findClosestPoint(points));

        // =============== Boundary Values Tests ==================

        // TC02:Empty list
        List<Point3D> points1 = new LinkedList<Point3D>();
        assertEquals("Wrong point" , null,r1.findClosestPoint(points1));
        // TC03: First point is the closest
        Ray r2 = new Ray(Point3D.ZERO, new Vector(1,0,0));
        assertEquals("Wrong point" , new Point3D(1,0,0),r2.findClosestPoint(points));
        // TC04:Last is closest
        Ray r3 = new Ray(new Point3D(2.75,0.,0), new Vector(1,0,0));
        assertEquals("Wrong point" , new Point3D(3,0,0),r3.findClosestPoint(points));
    }
}