package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.stream.Collectors;

import static primitives.Util.alignZero;

/**
 * This class Represents a Ray in the 3D Cartesian\
 * Coordinate system
 */
public class Ray {
    private Point3D p0;
    private Vector dir;

    /**
     * Creates a ray with given a point and direction
     *
     * @param p0  The Ray's head
     * @param dir The Ray's direction
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }
    private static final double DELTA = 0.1;

    public Ray(Point3D head, Vector direction, Vector normal) {
        p0 = head.add(normal.scale(alignZero(direction.dotProduct(normal)) > 0 ? DELTA : -DELTA));
        dir = direction;
    }

    /**
     * Gets the ray's head
     *
     * @return Ray's head
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * Gets the direction vector of the ray
     *
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
     *
     * @param t scalar
     * @return the point at the end of the direction vector
     * of the ray after being scaled
     */
    public Point3D getPoint(double t) {
        Vector v = dir.scale(t);
        return p0.add(v);
    }

    /**
     * Return the closest point to the ray's start
     * from the points given
     *
     * @param intersectionPoints the points given
     * @return the closest point to the ray's start
     */
    public Point3D findClosestPoint(List<Point3D> intersectionPoints) {
        if (intersectionPoints == null)
            return null;
        return findClosestGeoPoint(intersectionPoints //
                .stream().map(p -> new GeoPoint(null, p)) //
                .collect(Collectors.toList())
        ).point;
    }

    /**
     * Gets the closest GeoPoint to the ray's head
     * from list of points
     *
     * @param intersectionPoints points calculate distance from
     * @return closest GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersectionPoints) {
        if (intersectionPoints == null)
            return null;
        GeoPoint closestGeoPoint = null;
        double min = Double.MAX_VALUE;

        for (GeoPoint geoPoint : intersectionPoints) {
            double distance = p0.distance(geoPoint.point);
            if (min > distance) {
                min = distance;
                closestGeoPoint = geoPoint;
            }
        }
        return closestGeoPoint;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
