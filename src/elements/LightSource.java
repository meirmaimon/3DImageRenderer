package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents the various light sources
 */
public interface LightSource {

    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
}

