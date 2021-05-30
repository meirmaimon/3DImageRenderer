package primitives;

/**
 * This class is PDS. represent the material of the
 * geometries in the scene
 */
public class Material {
    public double kD = 0.0;
    public double kT = 0.0;
    public double kR = 0.0;
    public double kS = 0.0;
    public int nShininess = 0;

    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

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
