package unittests.geometriesTests;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing Triangle
 */
public class TriangleTest {
    /**
     * Test methods for
     * {@link geometries.Triangle#findIntersections(Ray)}
     */
    @Test
    public void findIntersections(){
        // ============ Equivalence Partitions Tests ==============
        Triangle tr =new Triangle(new Point3D(0,0,0) ,new Point3D(1,0,0),new Point3D(0,1,0));

        // TC01: Point is outside against edge of the triangle (0 points)
        assertEquals("Triangle findIntersections() bad result", null ,
                tr.findIntersections(new Ray(new Point3D(1.5,-2,1),new Vector(0,0,-1))));

        // TC02: Point is outside against vertex of the triangle (0 points)
        assertEquals("Triangle findIntersections() bad result", null ,
                tr.findIntersections(new Ray(new Point3D(1.5,-0.5,1),new Vector(0,0,-1))));

        // TC03: Point is inside the triangle (1 points)
        Point3D p1 = new Point3D(0.25,0.25,0);
        assertEquals("Triangle findIntersections() bad result", List.of(p1),
                tr.findIntersections(new Ray(new Point3D(0.25,0.25,1),new Vector(0,0,-1))));

        // =============== Boundary Values Tests ==================
        // TC04: Point is on the edge of the triangle (0 points)
        assertEquals("Triangle findIntersections() bad result", null,
                tr.findIntersections(new Ray(new Point3D(0.5,0.5,1),new Vector(0,0,-1))));

        // TC05: Point is on the vertex of the triangle (0 points)
        assertEquals("Triangle findIntersections() bad result", null,
                tr.findIntersections(new Ray(new Point3D(1,0,1),new Vector(0,0,-1))));

        // TC04: Point is on edge's continuation (0 points)
        assertEquals("Triangle findIntersections() bad result", null,
                tr.findIntersections(new Ray(new Point3D(2,0,1),new Vector(0,0,-1))));
    }
}
