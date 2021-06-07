package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents the source of direct light
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * the constructor get intensity and direction
     * and call the constructor of the father with intensity
     * and set direction
     *
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }
    /**
     * Set the side's size of the light
     * @param size square's side size
     */
  //  public DirectionalLight setSize(double size) { this.size = size;return this;}

    @Override
    public Color getIntensity(Point3D p) {
        return intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }

    @Override
    public double getDistance(Point3D p) {
        return Double.POSITIVE_INFINITY;
    }

}
