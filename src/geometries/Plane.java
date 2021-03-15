package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a Plane in the 3D cartesian
 * coordinate system
 */
public class Plane implements Geometry {

    private Point3D q0;
    private Vector normal;

    /**
     * Creats a plane given point and normal
     * @param q0 point on the plane
     * @param normal vector normal to the plane
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    /**
     * Creates a plane given three points
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
    public Plane(Point3D p1 ,Point3D p2 ,Point3D p3) {
        this.q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Gets the plane's point
     * @return the plane's point
     */
    public Point3D getQ0() {
        return q0;
    }

    /**
     * gets the normal vector of the plane
     * @return plane's normal vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
