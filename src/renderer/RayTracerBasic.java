package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

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

    @Override
    public Color traceRay(Ray ray) {
        return null;
    }
}
