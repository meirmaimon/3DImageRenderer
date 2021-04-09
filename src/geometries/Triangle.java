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
        //checking if the p is inside the triangle
        int size = vertices.size();
       // List<Vector> vectors = new LinkedList<Vector>();
        Point3D p0 = ray.getP0();       //ray start point
        Vector v = ray.getDir();
        double sign = 0;                //sign of the cross product between the normal and the ray direction

        for (int i = 0; i < size; i++) {
            Point3D p_i = vertices.get(i);
            Point3D p_i1 = vertices.get((i + 1) % size); //last vector cross product with the first vector
            Vector n_i = null;
            try {
                Vector v_i = p_i.subtract(p0);
                Vector v_i1 = p_i1.subtract(p0);
                n_i = v_i.crossProduct(v_i1);
            } catch (Exception e) {             //Vector Zero
                return null;
            }
            double checkDotProduct = v.dotProduct(n_i);
            if (isZero(checkDotProduct))
                return null;
            if (i == 0) {         // first iteration
                sign = checkDotProduct / Math.abs(checkDotProduct);
            }
            if (sign * checkDotProduct < 0)       // not the same sign
                return null;
        }
        return p;
    }
}



