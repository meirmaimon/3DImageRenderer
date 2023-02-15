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

    /**
     * Distance between the light and the given point
     * @param p point calculate distance from
     * @return distace between the light and the point
     */
    double getDistance(Point3D p);

    /**
     * Gets the Light's size
     * @return Light's size
     */
    double getSize();

    /**
     * Gets the position of the light
     * @return light position
     */
    Point3D getPosition();

}

