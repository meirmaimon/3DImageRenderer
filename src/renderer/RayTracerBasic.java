package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class extends RayTracerBase
 * implements basic ray tracer
 * every intersection point has the same color
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * This constant represents the distance
     * the normal vector being moved
     */
    private static final double DELTA = 0.1;
    /**
     * Constructor create ray tracer basic
     * with given scene
     *
     * @param scene given scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray The method that scans a beam and looks for cuts between the beam and the 3D model of the scene.
     *            If no crop points are found - the background color of the scene will be restored.
     *            Otherwise the point closest to the beginning of the ray will be found then we will find the color at this point using calcColor
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
     *
     * @param p   geoPoint given
     * @param ray the ray from the camera to the point
     * @return color to the point
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        Color i0 = scene.ambientLight.getIntensity().add(p.geometry.getEmission());
        return i0.add(calcLocalEffects(p, ray));
    }

    /**
     * Gets a point and returns color according to the Phong method
     *
     * @param intersection geoPoint given
     * @param ray          the ray from the camera to the point
     * @return color to the point, only the left part of the equation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource,l,n,intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(lightIntensity.scale(calcDiffusive(material.kD, nl) + //
                            calcSpecular(nl, material.kS, l, n, v, material.nShininess)));
                }
            }
        }
        return color;
    }

    /**
     * Gets the parameters of the diffusive part of the equation
     *
     * @param kd the coefficient of the diffuse material
     * @param ln vector l from the light source to the point multiplied by the noirmal from the point
     * @return color scale factor calculated by the diffusive part
     */
    public double calcDiffusive(double kd, double ln) {
        if (ln < 0) ln = -ln;
        return kd * ln;
    }

    /**
     * Gets the parameters of the specular part of the equation
     *
     * @param nl         the angle between n and l vectors
     * @param ks         the coefficient of the specular material
     * @param l          vector l from the light source to the point
     * @param n          the normal vector from the point
     * @param nShininess the shininess coefficient of the material
     * @return color calculated by the specular part
     */
    private double calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = -v.dotProduct(r);
        if (vr <= 0)
            return 0;
        return ks * Math.pow(vr, nShininess);
    }

    /**
     * This methods checks if the point should be shaded
     * by checking if there is an object between the light source and the point
     * @param l light direction
     * @param n normal of the point
     * @param geopoint the geoPoint given
     * @return true if the point should not be shaded
     */
    private boolean unshaded(LightSource light,Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }
}
