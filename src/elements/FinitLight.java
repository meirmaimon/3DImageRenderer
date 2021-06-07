package elements;

import primitives.Point3D;

/**
 * This class represents a finit light source
 * that has size and position
 */
public interface FinitLight extends LightSource {

    /**
     * Gets the Light's size
     * @return Light's size
     */
    public double getSize();

    /**
     * Gets the position of the light
     * @return light position
     */
    public Point3D getPosition();

}
