package geometries;

import primitives.Ray;

/**
 * This class extends Tube
 * Represents a Cylinder in the Cartesian
 * Coordinate system
 */
public class Cylinder extends Tube{

    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive");
        this.height = height;
    }
}
