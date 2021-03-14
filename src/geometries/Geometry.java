package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This interface is meant for basic geometries
 * to implement
 * returning the normal vector to a point
 */
public interface Geometry {
    Vector getNormal(Point3D point);

}
