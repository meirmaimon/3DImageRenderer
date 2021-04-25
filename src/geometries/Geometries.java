package geometries;
import primitives.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> intersectables = new LinkedList<Intersectable>();
    /**
     * Default Ctor
     * Creates an empty list
     */
    public Geometries() { }

    /**
     * Create a list with all the given geometries
     * @param geometries given geometries
     */
    public Geometries(Intersectable... geometries){
        add(geometries);
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
        List<Point3D> intersectionsPoints = null;
        for (Intersectable geometry:intersectables) {
            List<Point3D> intersection = geometry.findIntersections(ray);
            if (intersection == null) continue;             // There were no intersection points
            if (intersectionsPoints == null)
                intersectionsPoints = new LinkedList<>();
            intersectionsPoints.addAll(intersection);
        }
        return intersectionsPoints;
    }
}
