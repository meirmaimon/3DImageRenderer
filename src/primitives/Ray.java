package primitives;

/**
 * This class Represents a Ray in the 3D Cartesian\
 * Coordinate system
 */
public class Ray {
    Point3D p0;
    Vector dir;

    /**
     * Creates a ray with given a point and direction
     * @param p0 The Ray's head
     * @param dir The Ray's direction
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normelized();
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
