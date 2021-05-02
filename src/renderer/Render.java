package renderer;

import elements.Camera;
import primitives.Color;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    ImageWriter imageWriter;
    Scene scene;
    Camera camera;
    RayTracerBase rayTracerBase;

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage(){
        if(imageWriter == null || camera == null
                || rayTracerBase == null || scene == null)
            throw new MissingResourceException("missing data","Render","d");
        throw new UnsupportedOperationException();
    }

    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("no imageWriter", "Render", "d");
        int nx = imageWriter.getNx();
        int ny = imageWriter.getNx();

        for (int i = 0; i < ny; i += interval) {
            for (int j = 0; j < nx; j++) {
                imageWriter.writePixel(j, i, color);
            }
        }
        for (int i = 0; i < ny; i++) {
            for (int j = 0; j < nx; j += interval) {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

    public void writeToImage(){
        if (imageWriter == null)
            throw new MissingResourceException("no imageWriter", "Render", "d");
        imageWriter.writeToImage();
    }
}
