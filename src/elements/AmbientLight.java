package elements;

import primitives.Color;

/**
 * This class represnt the ambient light of the Scene
 * Also can be reference as the light of a cloudy day
 */
public class AmbientLight {
    private Color intensity;

    /**
     * Constructor calc the intensity with
     * Color and reduction factor
     * @param iA Color
     * @param kA reduction factor
     */
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Gets the intensity
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
