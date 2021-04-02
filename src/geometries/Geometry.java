package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This interface is meant for basic geometries
 * to implement
 * returning the normal vector to a point
 */
public interface Geometry extends Intersectable {
    /**
     * This methods return the normal vector
     * of the point that on the geometry surface
     * @param point point on the geometry
     * @return the normal vector of the point
     */
    Vector getNormal(Point3D point);

}
