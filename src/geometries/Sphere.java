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
public class Sphere implements Geometry {

    private Point3D center;
    private double radius;

    /**
     * Creates a Sphere with point a radius given
     *
     * @param center Sphere's center point
     * @param radius Sphere's radius
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be greater than 0");
        this.radius = radius;
    }

    /**
     * Gets the sphere's center point
     *
     * @return sphere's center point
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * Gets the sphere's radius
     *
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
            return List.of(ray.getPoint(radius)); // only 1 intersection point in this case
        }
        double radiusSquared = radius * radius;
        double tm = alignZero(v.dotProduct(u));
        double uSquared = u.lengthSquared();
        if (alignZero(uSquared - radiusSquared) >= 0 && tm <= 0)
            return null;

        double dSquared = uSquared - (tm * tm);
        if (alignZero(dSquared - radiusSquared) >= 0)             //there is no intersection points
            return null;

        double th = Math.sqrt(radiusSquared - dSquared);
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);
        List<Point3D> intersections = new LinkedList<Point3D>();
        intersections.add(ray.getPoint(t1));
        if (t2 > 0) {
            intersections.add(ray.getPoint(t2));
        }
        return intersections;
    }
}
