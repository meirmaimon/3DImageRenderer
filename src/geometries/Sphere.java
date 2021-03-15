package geometries;

import primitives.Point3D;
import primitives.Vector;

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
}
