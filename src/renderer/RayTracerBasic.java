package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
/**
 * This class extends RayTracerBase
 * implements basic ray tracer
 * every intersection point has the same color
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * Constructor create ray tracer basic
     * with given scene
     * @param scene given scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray
     * The method that scans a beam and looks for cuts between the beam and the 3D model of the scene.
     * If no crop points are found - the background color of the scene will be restored.
     * Otherwise the point closest to the beginning of the ray will be found then we will find the color at this point using calcColor
     * @return color
     */
    @Override
    public Color traceRay(Ray ray) {
       List<GeoPoint> p = new LinkedList<>(scene.geometries.findGeoIntersections(ray)) ;
        if(p == null){
            return scene.background;
        }
        else{
            return calcColor(ray.findClosestGeoPoint(p));
        }
    }

    /**
     * Gets a point and returns color
     * @param p point given
     * @return ambientLight (for now)
     */
    private Color calcColor(GeoPoint p){
        return scene.ambientLight.getIntensity().add(p.geometry.getEmission());
    }
}
