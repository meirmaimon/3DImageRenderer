package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * This is a Base class for all
 * ray tracer class
 *
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Creates a rayTracerBase
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Calculate the color of the point the given ray
     * intersect with
     * @param ray given ray
     * @return he color of the point the given ray
     */
    public abstract Color traceRay(Ray ray);
}
