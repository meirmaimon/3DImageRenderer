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
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * Gets a point and returns color - Calculated according to the Phong method
     * @param p geoPoint given
     * @param ray the ray from the camera to the point
     * @return color to the point
     */
    private Color calcColor(GeoPoint p, Ray ray){
        Color i0 = scene.ambientLight.getIntensity().add(p.geometry.getEmission());
       return i0.add(calcLocalEffects(p, ray));
    }

    /**
     * Gets a point and returns color according to the Phong method
     * @param intersection geoPoint given
     * @param ray the ray from the camera to the point
     * @return color to the point, only the left part of the equation
     */
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

    /**
     * Gets the parameters of the diffusive part of the equation
     * @param kd the coefficient of the diffuse material
     * @param l vector l from the light source to the point
     * @param n the normal vector from the point
     * @param lightIntensity the light intensity color of the current light source (Il in the equation)
     * @return color calculated by the diffusive part
     */
    public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity)  {
        return lightIntensity.scale(kd * Math.abs(l.dotProduct(n)));
    }

    /**
     * Gets the parameters of the specular part of the equation
     * @param nl the angle between n and l vectors
     * @param ks the coefficient of the specular material
     * @param l vector l from the light source to the point
     * @param n the normal vector from the point
     * @param nShininess the shininess coefficient of the material
     * @param lightIntensity the light intensity color of the current light source (Il in the equation)
     * @return color calculated by the specular part
     */
    private Color calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2*nl));
        double vr = v.scale(-1).dotProduct(r);
        if(vr <= 0)
            return Color.BLACK;
        return lightIntensity.scale(ks*Math.pow(vr,nShininess));
    }
}
