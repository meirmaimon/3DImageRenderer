package renderer;

import elements.Camera;
import primitives.Color;

import java.util.MissingResourceException;

/**
 * This class creates from the scene the color matrix of the image
 */
public class Render {
    private ImageWriter imageWriter;
    private Camera camera;
    private RayTracerBase rayTracer;

    /**
     * Sets image Writer
     *
     * @param imageWriter imageWriter
     * @return this
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Set camera
     *
     * @param camera camera
     * @return this
     */
    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * set ray tracer
     *
     * @param rayTracerBase ray tracer base
     * @return this
     */
    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracer = rayTracerBase;
        return this;
    }


    /**
     * The method will loop on all the pixels of the ViewPlane,
     * for each pixel a ray will be built and for each ray we will get a color from the ray renderer.
     * we put the color in the appropriate pixel of the image maker (writePixel)
     */
    public void renderImage() {
        if (imageWriter == null || camera == null || rayTracer == null)
            throw new MissingResourceException("missing data", "Render", "d");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, rayTracer.traceRay(camera.constructRayThroughPixel(nX, nY, i, j)));
            }
        }
    }

    /**
     * A method that creates a grid of lines
     *
     * @param interval size of square
     * @param color    color of line
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("no imageWriter", "Render", "d");
        int nx = imageWriter.getNx();
        int ny = imageWriter.getNy();

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

    /**
     * The method will first check that a blank value has been entered in the field of the image maker .
     * and then run (delegate!) The appropriate method of the image maker.
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("no imageWriter", "Render", "d");
        imageWriter.writeToImage();
    }
}
