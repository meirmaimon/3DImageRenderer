package elements;

import primitives.Color;

/**
 * This class represents different kinds of
 * lights in the scene
 */
abstract class Light {
    /**
     * Light's intensity
     */
    protected Color intensity;
    /**
     * Light's side size
     */
    protected double size = 0;

    /**
     * Constructor create a Light with the intensity given
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * this method returns the intensity of the light
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }


    /**
     * Gets the Light's size
     * @return Light's size
     */
    public double getSize() {
        return size;
    }
}
