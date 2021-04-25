package geometries;
import primitives.*;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.isZero;

/**
 * This class represents a triangle
 * in the cartesian coordinate system
 */
public class Triangle extends Polygon{
    /**
     * Creates an Triangle given 3 points
     * @param p1 vertices 1
     * @param p2 vertices 2
     * @param p3 vertices 3
     */
    public Triangle(Point3D p1 ,Point3D p2 , Point3D p3){
        super(p1,p2,p3);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> p = plane.findIntersections(ray);
        if (p == null) {
            return null;
        }
        Point3D p0 = ray.getP0();
        Vector v =  ray.getDir();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);
        try {
            Vector n1 = v1.crossProduct(v2);
            n1.normalize();
            Vector n2 = v2.crossProduct(v3);
            n2.normalize();
            Vector n3 = v3.crossProduct(v1);
            n3.normalize();
            double sign1 =  v.dotProduct(n1);
            double sign2 =  v.dotProduct(n2);
            double sign3 =  v.dotProduct(n3);
            if (sign1 * sign2 <=  0 || sign1 * sign3 <= 0)
                return null;                            //point outside the triangle
            return p;

        }
        catch (Exception e){
            return null;
        }           //Vector 0

    }
}



