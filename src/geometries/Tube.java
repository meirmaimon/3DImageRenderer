package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
