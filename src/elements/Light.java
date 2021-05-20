package elements;

import primitives.Color;

/**
 * TODO
 */
abstract class Light {
    protected Color intensity;

    /**
     * TODO
     * @param intensity
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
}
