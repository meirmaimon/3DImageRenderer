package geometries;

import primitives.Point3D;

/**
 * This class represents a triangle
 * in the cartesian coordinate system
 */
public class Triangle extends Polygon{

    /**
     * Creats an Triangle given 3 points
     * @param p1 vertices 1
     * @param p2 vertices 2
     * @param p3 vertices 3
     */
    public Triangle(Point3D p1 ,Point3D p2 , Point3D p3){
        super(p1,p2,p3);
    }

}
