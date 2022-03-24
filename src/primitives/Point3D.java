package primitives;
import java.util.Objects;
/**
 * Class Point3D is the basic class representing a Point in the Cartesian
 * coordinate system.
 */
public class Point3D {
    public final Coordinate x;
    public final Coordinate y;
    public final Coordinate z;

    /**
     * Point zero represents the point of the origin
     */
    public final static Point3D ZERO= new Point3D(0,0,0);

    /**
     * Creates a point given 3 coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a Point in the 3D Cartesian
     *  coordinate system.
     * @param x The Point value on the X axis
     * @param y The Point value on the Y axis
     * @param z The Point value on the Z axis
     */
    public Point3D(double x, double y ,double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D)obj;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }

    /**
     * This method move the point in the direction
     * and length of the given vector
     * @param vec The vector that moves the point
     * @return The point after being
     * moved by the vector
     */
    public Point3D add(Vector vec) {
        Point3D head = vec.getHead();
        return new Point3D(x.coord+head.x.coord ,y.coord+head.y.coord ,z.coord+head.z.coord);
    }

    /**
     * This method returns the vector between the point
     * And the point given
     * @param point The point given
     * @return The vector between the point given
     * And the point
     */
    public Vector subtract(Point3D point){
        Point3D temp = new Point3D(x.coord-point.x.coord ,
                y.coord - point.y.coord ,
                z.coord - point.z.coord);
        return new Vector(temp);
    }

    /**
     * This method return the square distance
     * Between the point and the point given
     * @param point The point distance to
     * @return Square distance between the points
     */
    public double distanceSquared(Point3D point){
        double xValue = point.x.coord - x.coord;
        double yValue = point.y.coord - y.coord;
        double zValue = point.z.coord - z.coord;
        return (xValue * xValue) + (yValue * yValue) + (zValue * zValue);
    }
    /**
     * This method return the distance
     * Between the point and the point given
     * @param point The point distance to
     * @return Square distance between the points
     */
    public double distance(Point3D point){
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * This method return the value of the x coordinate
     * @return  the value of the x coordinate
     */
    public double getX() {
        return x.coord;
    }

    /**
     * This method return the value of the x coordinate
     * @return  the value of the y coordinate
     */
    public double getY() {
        return y.coord;
    }

    /**
     * This method return the value of the x coordinate
     * @return  the value of the z coordinate
     */
    public double getZ() {
        return z.coord;
    }
}




