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
    private double kC=1,kL=0,kQ=0;

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

    /**
     * getter for the intensity param
     * @param p the point to calculate its intensity
     * @return the intensity in the current point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double distanceSq = p.distanceSquared(position);
        double denominator = kQ * distanceSq + kL * Math.sqrt(distanceSq) + kC;
        return getIntensity().reduce(denominator);
    }

    /**
     * getter for the l vector param
     * @param p the point to calculate its intensity
     * @return the l vector from the light source to the point
     */
    @Override
    public Vector getL(Point3D p) {return p.subtract(position).normalized();}
}
