package unittests.geometriesTests;

import geometries.Geometries;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class GeometriesTest {

    @Test
    public void TestFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Geometries geometries = new Geometries(new Sphere(new Point3D(1,0,0),1d),
                new Sphere(new Point3D(3,0,0),1d));
        // TC01: Some geometries intersect with the ray (2 points)
        Ray ray1 = new Ray(new Point3D(1,0,2), new Vector(0,0,-1));
        List result = geometries.findIntersections(ray1);
        assertEquals("wrong number of points" , 2, result.size());

        // =============== Boundary Values Tests ==================
        // TC02: Empty Collection  (0 points)
        Geometries geometries1 = new Geometries();
        assertEquals("wrong number of points" , null, geometries1.findIntersections(ray1));
        // TC03: None of the geometries intersect with the ray (0 points)
        Ray ray2 = new Ray(new Point3D(-1,0,0) , new Vector(-1,0,0));
        assertEquals("wrong number of points" , null, geometries.findIntersections(ray2));
        // TC04: One of the geometries intersect with the ray (2 points)
        result =  geometries.findIntersections(ray1);
        assertEquals("wrong number of points" , 2,result.size());
        // TC05: All of the geometries intersect with the ray (4 points)
        Ray ray3 = new Ray(new Point3D(-1,0,0) , new Vector(1,0,0));
        result = geometries.findIntersections(ray3);
        assertEquals("wrong number of points" , 4, result.size());



    }

}