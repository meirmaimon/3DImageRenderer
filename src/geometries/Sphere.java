package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.alignZero;

/**
 * This class represents a Sphere
 * in the Cartesian coordinate system
 * all the points of the sphere has the
 * same distance from the Sphere's center
 */
public class Sphere extends Geometry {

    private Point3D center;
    private double radius;
    private double rSquared;

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
        this.rSquared = radius * radius;
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
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Vector u = null;
        try {
            u = center.subtract(ray.getP0());
        } catch (Exception e) {                 //ray start in the center of the sphere
            return List.of(new GeoPoint(this, ray.getPoint(radius))); // only 1 intersection point in this case
        }

        double tm = alignZero(ray.getDir().dotProduct(u));
        double thSquared = rSquared - (u.lengthSquared() - (tm * tm));
        if (alignZero(thSquared) <= 0) // the line is out of the sphere
            return null;

        double th = Math.sqrt(thSquared);
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);
        if (t1 > 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));
        else
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
    }
}
