package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * This class represents a Tube in the Cartesian
 * coordinate system
 * "Galil ein sofi" in hebrew
 */
public class Tube implements Geometry {

    protected Ray axisRay;
    protected double radius;

    /**
     * Creates a tube given a axis vector and radius
     * @param axisRay Tube's axis vector
     * @param radius Tube's radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        if(radius <= 0)
            throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
    }

    /**
     * Gets the Tube's axis vector
     * @return Tube's axis vector
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Gets the Tube radius
     * @return Tube radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D p0 = axisRay.getP0();
        Vector opposite = axisRay.getDir();
        Vector hypotenuse = point.subtract(p0);
        double t = hypotenuse.dotProduct(opposite);
        Point3D center = isZero(t) ? p0 : p0.add(opposite.scale(t));
        return point.subtract(center);
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
