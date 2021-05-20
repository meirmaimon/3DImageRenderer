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
    public Color getIntensity(Point3D p);

    public Vector getL(Point3D p);
}

