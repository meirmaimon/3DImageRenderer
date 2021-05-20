package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents the source of direct light
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     * the constructor get intensity and direction
     * and call the constructor of the father with intensity
     * and set direction
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    //TODO
    public Vector getL(Point3D p) {
        return null;
    }
}