package elements;

import primitives.Color;

/**
 * This class represent the ambient light of the Scene
 * Also can be reference as the light of a cloudy day
 */
public class AmbientLight extends Light {

    /**
     * default Constructor
     * send the color black to Light's constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructor calc the intensity with
     * Color and reduction factor
     * @param iA Color
     * @param kA reduction factor
     */

     public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
}
