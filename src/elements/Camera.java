package elements;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.assertTrue;
import static primitives.Util.isZero;

public class Camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    public Camera(Point3D p0, Vector vUp, Vector vTo)  {
        // check if vUp and vTo are orthogonal:
        if (isZero(vUp.dotProduct(vTo))) {
            // normalize the vectors:
            this.p0 = p0;
            this.vUp = vUp.normalized();
            this.vTo = vTo.normalized();
            this.vRight = (vTo.crossProduct(vUp)).normalized();
        }
        // TODO else ?
    }
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        return null;
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }
}
