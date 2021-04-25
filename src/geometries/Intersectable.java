package geometries;
import primitives.*;
import java.util.List;

/**
 * This interface for basic geometries to implement
 * contains a method that return a list of all the
 * intersection point between a ray and a geometry
 */
public interface Intersectable {
    /**
     * This method return a list of all the intersection
     * between given ray and geometry
     * @param ray Ray that intersect
     * @return the intersection point
     */
    List<Point3D> findIntersections(Ray ray);
}
