package primitives;

/**
 * This class is PDS. represent the material of the
 * geometries in the scene
 */
public class Material {
    /**
     * diffusion attenuation factor
     */
    public double kD = 0.0;
    /**
     * transparency attenuation factor
     */
    public double kT = 0.0;
    /**
     * reflection attenuation factor
     */
    public double kR = 0.0;
    /**
     * specular attenuation factor
     */
    public double kS = 0.0;
    /**
     * shininess factor
     */
    public int nShininess = 0;

    /**
     * Set the transparency attenuation factor
     * @param kT transparency attenuation factor
     * @return this
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Set the reflection attenuation factor
     * @param kR reflection attenuation factor
     * @return this
     */
    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the attenuation factor kD
     *
     * @param kD attenuation factor
     * @return this
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the attenuation factor kS
     *
     * @param kS attenuation factor kS
     * @return this
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the shininess
     *
     * @param nShininess the shininess factor
     * @return this
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
