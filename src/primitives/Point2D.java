package primitives;

/**
 * This class represents a 2D point
 * in the cartesian coordinate system
 */
public class Point2D {

    public final Coordinate x;
    public final Coordinate y;

    /**
     * Creates a Point in the 3D Cartesian
     *  coordinate system.
     * @param x The Point value on the X axis
     * @param y The Point value on the Y axis
     */
    public Point2D(double x, double y ) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
    }

    public double getX() {
        return x.coord;
    }

    public double getY() {
        return y.coord;

    }
}
