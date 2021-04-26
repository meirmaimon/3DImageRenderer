package unittests.primitivesTests;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.Assert.*;

/**
 * Test Point 3D
 */
public class Point3DTest {

    /**
     * Test method for
     * {@link primitives.Point3D#add(Vector)}
     */
    @Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point3D p1 = new Point3D(0,1,0);
        assertEquals("Wrong point" ,new Point3D(0,2,0),p1.add(new Vector(0,1,0)) );
        // =============== Boundary Values Tests ==================
        //TC02: move to origin
        assertEquals("Wrong point" ,Point3D.ZERO,p1.add(new Vector(0,-1,0)));
    }

    /**
     * Test methods for {@link primitives.Point3D#distance(Point3D)}
     */
    @Test
    public void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point3D p = new Point3D(0,1,0);
        assertEquals("Wrong point" ,1,p.distance(new Point3D(0,2,0)),0.00000000001 );
        // =============== Boundary Values Tests ==================
        // TC02: Distance from itself
        assertEquals("Wrong point" ,0,p.distance(new Point3D(0,1,0)),0.00000000001 );
    }
}