package renderer;

import elements.LightSource;
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
     * number of rays to cast to BlackBoard
     * in row and column
     */
    protected int numRays = 25;
    /**
     * Constructor create ray tracer basic
     * with given scene
     * create map of BlackBoard for each light source, so it will be create once for all points
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
    public double transparency(LightSource light, Vector l, Vector n, Intersectable.GeoPoint geoPoint, double nv,double ln) {
        double lightSize = light.getSize();
        if(lightSize == 0 || numRays == 1)
            return super.transparency(light,l, n,geoPoint,nv,ln);
        double sum = 0;
        int count = 0;

        Point3D pC = light.getPosition();
        BlackBoard blackBoard = new BlackBoard(pC, l, lightSize, lightSize,numRays);
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
     * Set the number of rays that cast to the blackboard
     * in row and column
     * @param numRays number of rays that cast to the blackboard
     */
    public RayTracerAdvance setNumRays(int numRays) {
        this.numRays = numRays;
        return this;
    }

}
