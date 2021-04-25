package unittests.elementsTests;

import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

public class integrationTests {

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

    }

    @Test
    public void testTriangleIntersections() {

    }
}
