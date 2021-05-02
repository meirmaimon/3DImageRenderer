package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    String name;
    Color background;
    AmbientLight ambientLight;
    Geometries geometries;

    public Scene(String name) {
        this.name = name;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }
}
