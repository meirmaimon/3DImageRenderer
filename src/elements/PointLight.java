package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this class this class represents the source of spot light in space
 * extends from Light and implements from LightSource
 */
public class PointLight extends Light implements LightSource{
    private Point3D position;
    private double kC,kL,kQ;

    /**
     * constructor that initializes the intensity and
     * the position of the Point Light and set default parameters
     * to kC,kL,kQ
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    /**
     *this method return the kC
     * @param kC
     * @return PointLight
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     *this method return the kL
     * @param kL
     * @return PointLight
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;

    }

    /**
     *this method return the kQ
     * @param kQ
     * @return PointLight
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;

    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.distance(position);
        return getIntensity().scale(1/(kC + kL*distance + kQ*(distance*distance)));
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalized();
    }
}
