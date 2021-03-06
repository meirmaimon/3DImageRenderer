package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a Plane in the 3D cartesian
 * coordinate system
 */
public class Plane implements Geometry {

    Point3D q0;
    Vector normal;

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    public Plane(Point3D p1 ,Point3D p2 ,Point3D p3) {
        this.q0 = p1;
        this.normal = null;          //full implementation in week2
    }

    public Point3D getQ0() {
        return q0;
    }

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
