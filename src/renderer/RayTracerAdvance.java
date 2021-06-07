package renderer;

import elements.DirectionalLight;
import elements.FinitLight;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import static primitives.Util.alignZero;

/**
 * This class implements advance ray
 * tracing.using Super Sampling model
 */
public class RayTracerAdvance extends RayTracerBasic {


    /**
     * Constructor create ray tracer basic
     * with given scene
     *
     * @param scene given scene
     */
    public RayTracerAdvance(Scene scene) {
        super(scene);
    }

    /**
     * check how much the point is shaded
     *
     * @param light    light source
     * @param l        light direction
     * @param n        point normal
     * @param geoPoint geoPoint being calcilated
     * @return level of transparency
     */
    public double softTransparency(FinitLight light, Vector l, Vector n, Intersectable.GeoPoint geoPoint, double nv) {
        double sum = 0;
        int count = 0;
        double lightSize = light.getSize();
        Point3D pC = light.getPosition();
        BlackBoard blackBoard = new BlackBoard(pC, l, lightSize, lightSize);
        var vectors = blackBoard.generateVectors(geoPoint.point);
        for (Vector vec : vectors) {
            Vector vecNegative = vec.scale(-1).normalize();
            double nVec = alignZero(n.dotProduct(vecNegative));
            sum += super.transparency(light, vecNegative, n, geoPoint, nv, nVec);
            count++;
        }
        return sum / count;
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
    protected Color calcLocalEffects(Intersectable.GeoPoint intersection, Vector v, Vector n, double vn, double k) {

        Geometry geometry = intersection.geometry;
        Point3D p = intersection.point;
        Material material = geometry.getMaterial();

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(p);
            double ln = alignZero(l.dotProduct(n));
            double ktr = (lightSource instanceof DirectionalLight) ?
                    super.transparency(lightSource, l, n, intersection, vn, ln) : softTransparency((FinitLight) lightSource,
                    l, n, intersection, vn);
            // sign(ln) == sing(vn)
            if (ktr * k > MIN_CALC_COLOR_K) {
                Color lightIntensity = lightSource.getIntensity(p).scale(ktr);
                color = color.add(lightIntensity.scale(calcDiffusive(material.kD, ln) + //
                        calcSpecular(ln, material.kS, l, n, v, material.nShininess)));
            }

        }
        return color;
    }
}
