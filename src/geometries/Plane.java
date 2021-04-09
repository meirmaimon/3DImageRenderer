package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

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
     * @throws IllegalArgumentException when the points are in the same line
     */
    public Plane(Point3D p1 ,Point3D p2 ,Point3D p3) {
        this.q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        // if v1 and v2 are co-lined - cross-product will generate zero vector -> throwing exception
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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector v = ray.getDir();
        Point3D p0 = ray.getP0();

        double nv = normal.dotProduct(v);
        if (isZero(nv)) {
            // no solution - the Ray is parallel to the plane
            return null;
        }
        double t;
        if (p0.equals((q0))) {
            // the subtract will create the zero vector and will fail
            // in this case the points are equal so they are the intersection
            // assumption: if the Ray included in the Plane, return only the first point
            t = 0;
        }
        else {
            double nQMinusP0 = (q0.subtract(p0)).dotProduct(normal);
            t = alignZero(nQMinusP0 / nv);
        }

        if (t == 0) {
            // endless solutions - the plane include the ray
            List<Point3D> intersections = new LinkedList<Point3D>();
            intersections.add(p0);
            return intersections;
        }
        if (t > 0) {
            List<Point3D> intersections = new LinkedList<Point3D>();
            intersections.add(ray.getPoint(t));
            return intersections;
        }
        return null; // TODO when t is negative
    }
}
