package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Spot light extends class point light
 * represents a source light
 * the intensity affect both by the direction
 * and distance
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
        this.direction = direction.normalize();
    }
    @Override
    public Color getIntensity(Point3D p){
        double dotProduct = direction.dotProduct(getL(p));
        if (dotProduct <= 0)
            return Color.BLACK;             //intensity.scale(0)
        return super.getIntensity(p).scale(dotProduct);
    }
}
