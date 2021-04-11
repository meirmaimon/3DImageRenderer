package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a Sphere
 * in the Cartesian coordinate system
 * all the points of the sphere has the
 * same distance from the Sphere's center
 */
public class Sphere implements Geometry{

    private Point3D center;
    private double radius;

    /**
     * Creates a Sphere with point a radius given
     * @param center Sphere's center point
     * @param radius Sphere's radius
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        if(radius <= 0)
            throw new IllegalArgumentException("Radius must be greater than 0");
        this.radius = radius;
    }

    /**
     * Gets the sphere's center point
     * @return sphere's center point
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * Gets the sphere's radius
     * @return Gets the sphere's radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        //The radius is orthogonal to the point of tangency
        //and therefore the normal is parallel to the radius
        return point.subtract(center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector v = ray.getDir();
        Vector u = null;
        try {
            u = center.subtract(ray.getP0());
        } catch (Exception e) {                 //ray start in the center of the sphere
            Vector r = v.scale(radius);
            Point3D p1 = center.add(r);
            List<Point3D> intersections = new LinkedList<Point3D>();
            intersections.add(p1);
            return intersections;               // only 1 intersection point in this case
        }
        double uLength = u.length();
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - (tm * tm));
        if (d >= radius)             //there is no intersection points
            return null;
        if ( alignZero(uLength - radius) >= 0)      // ray is outside/on the sphere
            if (alignZero(tm) <= 0)          // ray direction isn't toward the sphere or tangent
                return null;     //no intersections
        double th = Math.sqrt((radius * radius) - (d * d));
        double t1 = tm + th;
        double t2 = tm - th;
        Point3D p1 = ray.getPoint(t1);
        List<Point3D> intersections = new LinkedList<Point3D>();
        if (t2 > 0){
            Point3D p2 = ray.getPoint(t2);
            intersections.add(p2);
            intersections.add(p1);
        }
        else
            intersections.add(p1);
        return intersections;
        }

//        List<Point3D> intersections = new LinkedList<Point3D>();
//        if (isZero(uLength-radius)) {     // ray start on the sphere
//            // ray direction isn't toward the sphere or tangent
//            if (alignZero(tm) <= 0) return null;
//            else {          //ray direction towards the sphere
//                intersections.add(p1);
//                return intersections;
//            }
//        }
//        if (alignZero(uLength - radius) > 0) {            //ray starts outside the sphere and towards the sphere
//            double t2 = tm - th;
//            Point3D p2 = ray.getPoint(t2);
//            intersections.add(p2);
//            intersections.add(p1);
//            return intersections;
//        }
//        if (alignZero(uLength - radius) < 0) {            //ray starts inside the sphere
//            intersections.add(p1);
//            return intersections;
//        }
//        if(isZero(tm)) {                  //ray orthogonal tho the radius and ray is inside the sphere (d<r)
//            intersections.add(p1);
//            return intersections;
//        }
//        return intersections;
//    }


}
