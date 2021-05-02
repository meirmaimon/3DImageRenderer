package elements;

import primitives.Color;

public class AmbientLight {
    Color iA;
    double kA;
    Color intensity;
    //TODO

    public AmbientLight(Color iA, double kA) {
        this.iA = iA;
        this.kA = kA;
    }

    public AmbientLight(Color iA, double kA, Color intensity) {
        this.iA = iA;
        this.kA = kA;
        this.intensity = intensity;
    }

    public Color getIntensity() {
        return intensity;
    }
}
