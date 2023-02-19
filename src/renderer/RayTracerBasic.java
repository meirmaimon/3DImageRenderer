package renderer;

import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;

import static primitives.Util.*;

import java.util.List;

import primitives.Vector;
import scene.Scene;

/**
 * This class extends RayTracerBase
 * implements basic ray tracer
 * every intersection point has the same color
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * The maximum depth we have determined for the reflection and refraction in
     * advance.
     */
    protected static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The lowest we will lower the reflection and refractions coefficient to,
     * before we deemed it to become irrelevant.
     */
    protected static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * The initial value of the reflection and refractions coefficient.
     */
    protected static final double INITIAL_K = 1.0;

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
     * Function to calculate the color of a point in the scene.
     *
     * @param intersection - A point in the scene, which is on a geometric shape.
     * @param ray          - The ray from the camera that hit the above point.
     * @return Color at the given point
     */
    protected Color calcColor(GeoPoint intersection, Ray ray) {
        return calcColor(intersection, ray.getDir(), MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Function to calculate the color of a point in the scene, with additional
     * checks for reflection and refraction, to determine global effects and local
     * effects.
     *
     * @param intersection - A point in the scene, which is on a geometric shape.
     * @param v            - The ray direction that hits the above point.
     * @param level        - The current depth of the recursion.
     * @param k            - The current coefficient of K (depends on the
     *                     recursion).
     * @return Color at the given point
     */
    protected Color calcColor(GeoPoint intersection, Vector v, int level, double k) {
        Vector n = intersection.geometry.getNormal(intersection.point);
        double vn = v.dotProduct(n);
        if (isZero(vn)) return Color.BLACK;
        Color color = intersection.geometry.getEmission().add(calcLocalEffects(intersection, v, n, vn, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, v, n, vn, level, k));
    }

    /**
     * Calculates the color at the given points, taking into account the reflection
     * and refraction.
     *
     * @param gp    - A point in the scene, which is on a geometric shape.
     * @param v     - The ray direction that hits the above point.
     * @param n     - Normal to surface at intersection
     * @param vn    - v*n - dot-product
     * @param level - The current depth of the recursion.
     * @param k     - The current coefficient of K (depends on the recursion).
     * @return Color at the given point.
     */
    protected Color calcGlobalEffects(GeoPoint gp, Vector v, Vector n, double vn, int level, double k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n, vn), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * A function to continue the ray of the reflection the refraction, building a
     * new one for the next level of the recursion.
     *
     * @param ray   - The updated ray of the reflection and refraction.
     * @param level - The current depth of the recursion.
     * @param kx    - The original coefficient.
     * @param kkx   - The current coefficient of K (depends on the recursion).
     * @return light intensity of a global effect
     */
    protected Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray.getDir(), level - 1, kkx).scale(kx));
    }

    /**
     * Add calculated light contribution from all light sources.
     *
     * @param intersection - A point in the scene, which is on a geometric shape
     * @param v            - The ray direction that hits the above point.
     * @param n            - Normal to surface at intersection
     * @param vn           - v*n - dot-product
     * @return The color of the geometry according to the local variables, such as
     * the material and the light sources.
     */
    protected Color calcLocalEffects(GeoPoint intersection, Vector v, Vector n, double vn, double k) {

        Geometry geometry = intersection.geometry;
        Point3D p = intersection.point;
        Material material = geometry.getMaterial();

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(p);
            double ln = alignZero(l.dotProduct(n));
            double ktr = transparency(lightSource, l, n, intersection ,vn ,ln);
            // sign(ln) == sing(vn)
            if (ktr * k > MIN_CALC_COLOR_K) {
                Color lightIntensity = lightSource.getIntensity(p).scale(ktr);
                color = color.add(lightIntensity.scale(calcDiffusive(material.kD, ln) + //
                        calcSpecular(ln, material.kS, l, n, v, material.nShininess)));
            }

        }
        return color;
    }

    /**
     * Calculating  the diffuse component of the object for the Phong model
     *
     * @param kD diffusion factor
     * @param ln The dot product of the lights' vector with the normal
     * @return the diffuse component
     */
    protected double calcDiffusive(double kD, double ln) {
        if (ln < 0)
            ln = -ln;
        return (kD * ln);
    }


    /**
     * Creating a ray of the reflection.
     *
     * @param p  - The point of the reflection, the point we hit the object.
     * @param v  - The vector we hit the object at.
     * @param n  - The normal to the point hit.
     * @param vn - v*n (dot-product)
     * @return The new reflected ray.
     */
    protected Ray constructReflectedRay(Point3D p, Vector v, Vector n, double vn) {
        return new Ray(p, v.subtract(n.scale(2 * vn)), n);
    }

    /**
     * Creating a ray of the refrection.
     *
     * @param p - The point of the refrection, the point we hit the object.
     * @param v - The vector we hit the object at.
     * @param n - The normal to the point hit.
     * @return The new refracted ray.
     */
    protected Ray constructRefractedRay(Point3D p, Vector v, Vector n) {
        return new Ray(p, v, n);
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
    protected double calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = -v.dotProduct(r);
        if (vr <= 0)
            return 0;
        return ks * Math.pow(vr, nShininess);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Finding the closest intersections to the rays origin.
     *
     * @param ray - The ray we will search to find where it lands.
     * @return The closest GeoPoint in the rays path.
     */
    protected GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

     protected double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint , double vn ,double ln) {
        if (vn*ln <= 0 )         // sign(ln) != sing(vn)
            return 0;
         Vector lightDirection = l.scale(-1); // from point to light source
         Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
         double lightDistance = light.getDistance(geoPoint.point);
         var intersections = scene.geometries.findGeoIntersections(lightRay);
         if (intersections == null)
             return 1.0;
         double ktr = 1.0;
         for (GeoPoint gp : intersections) {
             if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                 ktr *= gp.geometry.getMaterial().kT;
                 if (ktr < MIN_CALC_COLOR_K)
                     return 0.0;
             }
         }
         return ktr;
     }
}
