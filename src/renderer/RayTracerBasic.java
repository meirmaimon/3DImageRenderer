package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

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
            return calcColor(ray.findClosestGeoPoint(p), ray);
        }
    }

    /**
     * Gets a point and returns color
     * @param p point given
     * @return ambientLight (for now)
     */
    private Color calcColor(GeoPoint p, Ray ray){
        Color I0 = scene.ambientLight.getIntensity().add(p.geometry.getEmission());
       return I0.add(calcLocalEffects(p, ray));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(nl, ks, l, n, v, nShininess, lightIntensity));

            }
        }
        return color;
    }

    public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity)  {
        return lightIntensity.scale(kd * Math.abs(l.dotProduct(n)));
    }

    private Color calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = n.scale(2 * nl).subtract(l).normalized();
        double vr = Math.max(0, v.dotProduct(r) * (-1));
        return lightIntensity.scale(ks * Math.pow(vr, nShininess));
    }



}
