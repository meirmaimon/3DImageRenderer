package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * This interface is meant for basic geometries
 * to implement
 * returning the normal vector to a point
 */
public abstract class Geometry implements Intersectable {
    /**
     * the color of the geometry
     */
    protected Color emmission = Color.BLACK;
    /**
     * The Material of the geometry
     */
    protected Material material = new Material();

    /**
     * Gets the material
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Set the material
     * @param material the material of the geometry
     * @return this
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * This methods return the normal vector
     * of the point that on the geometry surface
     * @param point point on the geometry
     * @return the normal vector of the point
     */
    public abstract Vector getNormal(Point3D point);

    /**
     * Gets the emmission color of
     * the geometry
     * @return the emmission color
     */
    public Color getEmission() {
        return emmission;
    }

    /**
     * Sets the geometry's color
     * @param emmission the color
     * @return this
     */
    public Geometry setEmission(Color emmission) {
        this.emmission = emmission;
        return this;
    }
}
