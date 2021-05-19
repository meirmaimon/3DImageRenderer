package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * TODO
 */
public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * the constructor get intensity,position and direction
     * and call the constructor of the father with intensity, position
     * and set the direction
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }
}
