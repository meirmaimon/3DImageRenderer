package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This class extends Tube
 * Represents a Cylinder in the Cartesian
 * Coordinate system
 */
public class Cylinder extends Tube{

    double height;

    /**
     * Creates a Cylinder with given height radius and a ray
     * @param axisRay Cylinder's axis ray
     * @param radius Cylinder's radius - must be greater than 0
     * @param height Cylinde's height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive");
        this.height = height;
    }

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
        return null;
    }
}
