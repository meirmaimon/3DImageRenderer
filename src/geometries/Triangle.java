package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * This class represents a triangle
 * in the cartesian coordinate system
 */
public class Triangle extends Polygon {
    /**
     * Creates an Triangle given 3 points
     *
     * @param p1 vertices 1
     * @param p2 vertices 2
     * @param p3 vertices 3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = plane.findGeoIntersections(ray);
        if (intersections == null)
            return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double sign1 = alignZero(v.dotProduct(n1));
        if (sign1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double sign2 = alignZero(v.dotProduct(n2));
        if (sign1 * sign2 <= 0 ) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double sign3 = alignZero(v.dotProduct(n3));
        if (sign1 * sign3 <= 0)
            return null;                            //point outside the triangle

        intersections.get(0).geometry = this;
        return intersections;

    }
}


