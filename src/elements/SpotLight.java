package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
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
        this.direction = direction.normalized();
    }
    @Override
    public Color getIntensity(Point3D p){
        double dotProduct = Util.alignZero(direction.dotProduct(getL(p)));
        if (dotProduct <= 0)
            return Color.BLACK;             //intensity.scale(0)
        return super.getIntensity(p).scale(dotProduct);
    }
    @Override
    public SpotLight setSize(double size) {this.size = size;return this;}

}
