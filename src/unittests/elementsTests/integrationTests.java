package unittests.elementsTests;

import elements.Camera;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Integration tests
 * testing constructRay and findIntersection
 */
public class integrationTests {
    /**
     * This methods construct ray through all the pixels
     * and counts how many rays intersect with a given geometry
     * @param cam camera
     * @param geometry the geometry being intersect
     * @param nX view plan x's pixels
     * @param nY view plan y's pixels
     * @return number of pixels that their ray intersect with the geometry
     */
    public int countIntersection(Camera cam , Geometry geometry ,int nX,int nY) {
        int count = 0;
        List<Point3D> points;
            for (int i = 0; i < nX; i++){
                for (int j = 0; j < nY; j++) {
                    points = geometry.findIntersections(cam.constructRayThroughPixel(nX, nY, i, j));
                    if (points != null) {
                        count += points.size();
                    }
                }
        }
        return count;
    }

    @Test
    public void testSphereIntersections() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1));
        Sphere sphere = new Sphere(new Point3D(0,0,-3),1);
      // TODO methode for the find intersections
        int intersections = 0;
        intersections += sphere.findIntersections(camera.constructRayThroughPixel(3,3,0,0)).size();
    }

    @Test
    public void testPlaneIntersections() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setViewPlaneSize(3,3).setDistance(1d);
        //TC01: parallel plane (9 point)
        Plane pl = new Plane(new Point3D(0,0,-3) , new Vector(0,0,1));
        assertEquals("wrong number of points" ,9 , countIntersection(camera,pl,3,3));
        //TC02: not parallel(9 point)
        Plane pl1 = new Plane(new Point3D(0,0,-3) , new Vector(0,-0.25,1));
        assertEquals("wrong number of points" ,9 , countIntersection(camera,pl1,3,3));
        //TC03: not parallel(6 point)
        Plane pl2 = new Plane(new Point3D(0,0,-3) , new Vector(0,-1,1));
        assertEquals("wrong number of points" ,6 , countIntersection(camera,pl2,3,3));
    }

    @Test
    public void testTriangleIntersections() {
        //TC01: little triangle(1 point)
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setViewPlaneSize(3,3).setDistance(1d);

        Triangle tr  = new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals("wrong number of points" ,1 , countIntersection(camera,tr,3,3));
        //TC01:triangle(2 point)
        Triangle tr1  = new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals("wrong number of points" ,2 , countIntersection(camera,tr1,3,3));
    }
}
