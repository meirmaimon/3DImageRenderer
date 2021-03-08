package primitives;

import java.util.Objects;

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
        this.dir = dir.normalized();
    }

    public Point3D getP0() {
        return p0;
    }

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


    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
