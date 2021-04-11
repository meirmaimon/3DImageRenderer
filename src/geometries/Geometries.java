package geometries;
import primitives.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> intersectables;
    /**
     * Default Ctor
     * Cretes an empty list
     */
    public Geometries() {
        this.intersectables = new LinkedList<Intersectable>();
    }

    /**
     * Create a list with all the given geometries
     * @param geometries given geometries
     */
    public Geometries(Intersectable... geometries){
        intersectables = new LinkedList<>();
        for (Intersectable geometry: geometries)
            intersectables.add(geometry);
    }

    /**
     * This method add a geometry into to list
     * @param geometries basic geometry
     */
    public void add(Intersectable... geometries){
        for (Intersectable geometry: geometries)
            intersectables.add(geometry);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if (intersectables.isEmpty()) return null;      //there no geometry to intersect with
        List<Point3D> intersections_points = new LinkedList<>();
        for (Intersectable geometry:intersectables) {
            List<Point3D> intersection = geometry.findIntersections(ray);
            if (intersection == null) continue;             // There were no intersection points
            intersections_points.addAll(intersection);
        }
        if (intersections_points.isEmpty()) return null;
        return intersections_points;
    }
}
