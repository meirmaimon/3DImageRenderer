package geometries;

import primitives.*;


import java.util.LinkedList;
import java.util.List;

/**
 * This class represent collection of geometries
 */
public class Geometries implements Intersectable {

    private List<Intersectable> intersectables = new LinkedList<>();

    /**
     * Default Ctor
     * Creates an empty list
     */
    public Geometries() {
    }

    /**
     * Create a list with all the given geometries
     *
     * @param geometries given geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * This method add a geometry into to list
     *
     * @param geometries basic geometry
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries)
            intersectables.add(geometry);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersectionsPoints = null;
        for (Intersectable geometry : intersectables) {
            List<GeoPoint> intersections = geometry.findGeoIntersections(ray);
            if (intersections == null) continue;             // There were no intersection points
            if (intersectionsPoints == null)
                intersectionsPoints = new LinkedList<>(intersections);
            else
                intersectionsPoints.addAll(intersections);
        }
        return intersectionsPoints;
    }
}
