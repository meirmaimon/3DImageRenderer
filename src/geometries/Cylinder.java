package geometries;

import primitives.*;
import static primitives.Util.*;

/**
 * This class extends Tube
 * Represents a Cylinder in the Cartesian
 * Coordinate system
 */
public class Cylinder extends Tube{

    private double height;

    /**
     * Creates a Cylinder with given height radius and a ray
     * @param axisRay Cylinder's axis ray
     * @param radius Cylinder's radius - must be greater than 0
     * @param height Cylinder's height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive");
        this.height = height;
    }

    /**
     * This methods returns the height of the cylinder
     * @return Cylinder's height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D p0 = axisRay.getP0();
        Vector opposite = axisRay.getDir();

        if (p0.equals(point))
            return opposite;

        Vector hypotenuse = point.subtract(p0);
        double t = hypotenuse.dotProduct(opposite);
        if (isZero(t - height) || isZero(t))          // point is on the bases of the cylinder
            return opposite;                        // axisRay vector

        Point3D center = p0.add(opposite.scale(t));
        return point.subtract(center);
    }
}
