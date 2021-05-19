package primitives;

public class Material {
    public double kD;
    public double kS;
    public int nShininess;

    public Material() {
        this.kD = 0;
        this.kS = 0;
        this.nShininess = 0;
    }

    public double getKd() {
        return kD;
    }

    public double getKs() {
        return kS;
    }

    public int getShininess() {
        return nShininess;
    }

    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }





}
