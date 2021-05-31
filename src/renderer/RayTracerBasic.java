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
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The lowest we will lower the reflection and refractions coefficient to,
     * before we deemed it to become irrelevant.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * The initial value of the reflection and refractions coefficient.
     */
    private static final double INITIAL_K = 1.0;

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
     * Function to calculate the color of a point in the scene.
     *
     * @param intersection - A point in the scene, which is on a geometric shape.
     * @param ray          - The ray from the camera that hit the above point.
     * @return Color at the given point
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Function to calculate the color of a point in the scene, with additional
     * checks for reflection and refraction, to determine global effects and local
     * effects.
     *
     * @param intersection - A point in the scene, which is on a geometric shape.
     * @param ray          - The ray from the camera that hit the above point.
     * @param level        - The current depth of the recursion.
     * @param k            - The current coefficient of K (depends on the
     *                     recursion).
     * @return Color at the given point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission().add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * Calculates the color at the given points, taking into account the reflection
     * and refraction.
     *
     * @param gp    - A point in the scene, which is on a geometric shape.
     * @param v     - The ray from the camera that hit the above point.
     * @param level - The current depth of the recursion.
     * @param k     - The current coefficient of K (depends on the recursion).
     * @return Color at the given point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
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
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }

    /**
     * Add calculated light contribution from all light sources.
     *
     * @param intersection - A point in the scene, which is on a geometric shape
     * @param ray          - The ray from the camera that hit the above point
     * @return The color of the geometry according to the local variables, such as
     * the material and the light sources.
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {

        Geometry geometry = intersection.geometry;
        Point3D p = intersection.point;
        Material material = geometry.getMaterial();

        Vector l, n = geometry.getNormal(p), v = ray.getDir();
        double ln, sign = alignZero(v.dotProduct(n));

        Color color = Color.BLACK;
        if (sign == 0)
            return color;

        for (LightSource lightSource : scene.lights) {
            l = lightSource.getL(p);
            ln = alignZero(l.dotProduct(n));
            if (sign * ln > 0) { // sign(ln) == sing(vn)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(p).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material.kD, ln) + //
                            calcSpecular(ln, material.kS, l, n, v, material.nShininess)));
                }
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
    private double calcDiffusive(double kD, double ln) {
        if (ln < 0)
            ln = -ln;
        return (kD * ln);
    }

    /**
     * Check if a particular point is shaded or not
     *
     * @param l - The vector from light to point (p)
     * @param n - The normal at the point (p)
     * @return False - there is shadow, True - there is no shadow
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
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

    /**
     * Creating a ray of the reflection.
     *
     * @param p - The point of the reflection, the point we hit the object.
     * @param v - The vector we hit the object at.
     * @param n - The normal to the point hit.
     * @return The new reflected ray.
     */
    private Ray constructReflectedRay(Point3D p, Vector v, Vector n) {
        return new Ray(p, v.subtract(n.scale(2 * alignZero(v.dotProduct(n)))), n);
    }

    /**
     * Creating a ray of the refrection.
     *
     * @param p - The point of the refrection, the point we hit the object.
     * @param v - The vector we hit the object at.
     * @param n - The normal to the point hit.
     * @return The new refracted ray.
     */
    private Ray constructRefractedRay(Point3D p, Vector v, Vector n) {
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
    private double calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess) {
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
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

}
