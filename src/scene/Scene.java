package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * This class is PDS class
 * gathering the name background color
 * the ambient light and the geometries
 */
public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    /**
     * Constructor creates a scene with a given name
     * @param name name of the scene
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * Set the Scene's background color
     * @param background Scene's background color
     * @return Scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets Scene ambient light
     * @param ambientLight Scene ambient light
     * @return Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * sets scene geometries
     * @param geometries scene geometries
     * @return scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
