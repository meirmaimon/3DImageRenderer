package primitives;

import java.util.Objects;

/**
 * This class Represents a Ray in the 3D Cartesian\
 * Coordinate system
 */
public class Ray {
    private Point3D p0;
    private Vector dir;

    /**
     * Creates a ray with given a point and direction
     * @param p0 The Ray's head
     * @param dir The Ray's direction
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * Gets the ray's head
     * @return Ray's head
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * Gets the direction vector of the ray
     * @return Ray's direction
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * This methods returns the point on the ray
     * that is on the end of the direction vector
     * after scaling
     * @param t scalar
     * @return the point at the end of the direction vector
     * of the ray after being scaled
     */
    public Point3D getPoint(double t){
        Vector v = dir.scale(t);
        return p0.add(v);
    }


    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
