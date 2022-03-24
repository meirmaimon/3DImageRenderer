package geometries;

import primitives.*;
import static geometries.Intersectable.GeoPoint;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This interface for basic geometries to implement
 * contains a method that return a list of all the
 * intersection point between a ray and a geometry
 */
public interface Intersectable {
    /**
     * This class is PDS contain
     * a point and the geometry its belong
     */
    public static class GeoPoint {
        /**
         * The GeoPoint Geometry
         */
        public Geometry geometry;
        /**
         * the GeoPoint point
         */
        public Point3D point;

        /**
         * Creats a geoPoint with geometry and point
         * @param geometry geometry of the point
         * @param point point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof Point3D)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return point.equals(geoPoint.point) && geometry == geoPoint.geometry;       //the same geometry
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * This method return a list of all the intersection
     * between given ray and geometry
     *
     * @param ray Ray that intersect
     * @return the intersection point
     */
    default List<Point3D> findIntersections(Ray ray){
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    /**
     * This methods return a list of all the geoPoints
     * that the ray intersect with
     * @param ray given ray
     * @return list of all the geoPoints intersection
     */
    List<GeoPoint> findGeoIntersections(Ray ray);
}
