package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents the various light sources
 */
public interface LightSource {
    /**
     * Gets the intensity of the light in a given point
     * @param p point given
     * @return Intensity of the light in a given point
     */
    Color getIntensity(Point3D p);

    /**
     * Gets the direction between the source light and
     *  given point
     * @param p the point direction calculate to
     * @return the direction between the source light and the point
     */
    Vector getL(Point3D p);
}

