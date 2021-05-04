package unittests.ImageWriterTest;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.Assert.*;

/**
 * Test for ImageWriterTest
 */
public class ImageWriterTest {
    /**
     * Test for {@link ImageWriter#writeToImage()}
     */
    @Test
    public void writeToImageTest() {
        ImageWriter iw = new ImageWriter("FirstTry", 800 , 500);
        int nx = iw.getNx();
        int ny = iw.getNy();
        Color background = new Color(241,17,251);
        Color line = new Color(10,150,50);
        for (int i = 0; i < ny; i++)
            for (int j = 0; j < nx; j++)
                iw.writePixel(j, i, background);
        for (int i = 0; i < ny; i+=50)
            for (int j = 0; j < nx; j++)
                iw.writePixel(j, i, line);
        for (int i = 0; i < ny; i++)
            for (int j = 0; j < nx; j+=50)
                iw.writePixel(j, i, line);
        iw.writeToImage();
    }
    /**
     * Test for {@link ImageWriter#writePixel(int, int, Color)}
     */
    @Test
    public void writePixelTest() {
    }
}