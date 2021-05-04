package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a camera
 * Affect the position from where we look at the scene
 * and also the size of view plane
 */
public class Camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    /**
     * Creates a camera with
     * position and direction given
     *
     * @param p0  starting position
     * @param vUp angle of the camera
     * @param vTo direction of the camera
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        // check if vUp and vTo are orthogonal:
        if (isZero(vUp.dotProduct(vTo))) {
            // normalize the vectors:
            this.p0 = p0;
            this.vUp = vUp.normalized();
            this.vTo = vTo.normalized();
            this.vRight = (vTo.crossProduct(vUp)).normalized();
        }
        else
            throw new IllegalArgumentException("vUp must ber orthogonal to vTo");
    }

    /**
     * Set the size of the view plan
     *
     * @param width  width of the view plan
     * @param height heoght of the view plan
     * @return The camera with the view plan
     */
    public Camera setViewPlaneSize(double width, double height) {
        if (alignZero(width - width) < 0 || alignZero(height - height) < 0)
            throw new IllegalArgumentException("View plane size can't be 0");
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Set the distance of the camera from the view plan
     *
     * @param distance distance of the camera from the view plan
     * @return camera with distance
     */
    public Camera setDistance(double distance) {
        if (alignZero(distance - distance) < 0)
            throw new IllegalArgumentException("distance from camera cant be 0 ");
        this.distance = distance;
        return this;
    }

    /**
     * This method returns the ray that going through
     * the pixel given
     *
     * @param nX view plan x's pixels
     * @param nY view plan y's pixels
     * @param j  index of columns
     * @param i  index of rows
     * @return the ray that going through the given pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        if ((i < 0 || i > (nY - 1)) || (j < 0 || j > (nX - 1)))
            throw new IllegalArgumentException("Pixels index between 0 to N-1");
        double rX = height / nX;
        double rY = width / nY;
        Point3D pC = p0.add(vTo.scale(distance));
        double xJ = (j - (nX - 1) / 2.0) * rX;
        double yI = ((nY - 1) / 2.0 - i) * rY;
        Point3D pIJ = pC;
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(p0, pIJ.subtract(p0));
    }

    /**
     * Gets the position of the camera
     * @return position of the camera
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * Gets vector that represents the angle of
     * the camera
     * @return vector that represents the angle of
     *  the camera
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Gets vector that represents
     * the direction of the camera
     * @return the direction of the camera
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Gets the orthogonal vector of vTo and
     * Vup
     * @return the orthogonal vector of vTo and Vup
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Gets the Width of the view plan
     * @return Width of the view plan
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the Height of the view plan
     * @return Gets the Height of the view plan
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the distance from the view plan
     * @return distance from the view plan
     */
    public double getDistance() {
        return distance;
    }
}
