package primitives;

/**
 * This class is PDS. represent the material of the
 * geometries in the scene
 */
public class Material {
    public double kD;
    public double kS;
    public int nShininess;

    public Material() {
        this.kD = 0;
        this.kS = 0;
        this.nShininess = 0;
    }

    /**
     * Gets the attenuation factor kD
     * @return Gets the attenuation factor kD
     */
    public double getKd() {
        return kD;
    }
    /**
     * Gets the attenuation factor kS
     * @return Gets the attenuation factor kS
     */
    public double getKs() {
        return kS;
    }
    /**
     * Gets the shininess factor
     * @return Gets the shininess.
     */
    public int getShininess() {
        return nShininess;
    }

    /**
     * Sets the attenuation factor kD
     * @param kD attenuation factor
     * @return this
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the attenuation factor kS
     * @param kS attenuation factor kS
     * @return this
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the shininess
     * @param nShininess the shininess factor
     * @return this
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }





}
