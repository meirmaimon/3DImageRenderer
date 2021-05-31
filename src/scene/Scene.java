package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is PDS class
 * gathering the name background color
 * the ambient light and the geometries
 */
public class Scene {
    /**
     * Scene Name
     */
    public String name;
    /**
     * Scene background color
     */
    public Color background = Color.BLACK;
    /**
     * Scene ambient light
     */
    public AmbientLight ambientLight = new AmbientLight();
    /**
     * Scene Geometries
     */
    public Geometries geometries = new Geometries();
    /**
     * Scene light sources
     */
    public List<LightSource> lights = new LinkedList<LightSource>();


    /**
     * Constructor creates a scene with a given name
     * @param name name of the scene
     */
    public Scene(String name) {
        this.name = name;
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

    /**
     * set Scene Light source
     * @param lights
     * @return scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
