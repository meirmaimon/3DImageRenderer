package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * This class is used for super sampling method
 * represent a surface with numbers of points on
 * the a given ray will have to check all the rays
 * that going throw the points on the surface
 */
class BlackBoard {

    //private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height;
    private double width;
    //private int numRays;
    private double rX;
    private double rY;
    private Point3D pC;
    private List<Point2D> points;

    /**
     * Number of rays in Column and row
     */
    public static int NUM_RAYS = 15;

    /**
     * Creates blackboard orthogonal to the vector given with
     * position height and width given
     * @param pC position of the blackboard (the middle point)
     * @param vTo vector orthogonal ot the
     * @param height BlackBoards height
     * @param width BlackBoards width
     */
    public BlackBoard(Point3D pC, Vector vTo,double height, double width) {
        this.height = height;
        this.width = width;
        this.pC = pC;
        createAxis(vTo);
        this.rX = width / NUM_RAYS;                 //Pixel width
        this.rY = height / NUM_RAYS;                //Pixel Height
        points = new LinkedList<Point2D>();
        createPoints();
    }

    /**
     * Creates a BlackBoard with given param
     * @param vRight X axis
     * @param vUp Y axis
     * @param height BlackBoards height
     * @param width BlackBoards width
     */
    public BlackBoard(Point3D pC , Vector vUp,Vector vRight, double height, double width) {
        this.pC = pC;
        this.vUp = vUp.normalize();
        if (!isZero(vRight.dotProduct(vUp)))
            throw new IllegalArgumentException("BlackBoards Vup and Vright must ber orthogonal");
        this.vRight = vRight.normalize();
        this.height = height;
        this.width = width;
        this.rX = width / NUM_RAYS;                 //Pixel width
        this.rY = height / NUM_RAYS;                //Pixel Height
        points = new LinkedList<Point2D>();
        createPoints();                     // generate points when BlackBoard created
    }

    /**
     * Creates 2D points and add them to points list
     */
    public void createPoints() {
        for (int i = 0; i < NUM_RAYS; i++) {
            for (int j = 0; j < NUM_RAYS; j++) {
                double x = (j - (NUM_RAYS - 1) / 2) * rX;
                double y = -(i - (NUM_RAYS - 1) / 2) * rY;
                points.add(new Point2D(x,y));
            }

        }
    }

    /**
     * Generate list of vectors from point given
     *  to the points on the BlackBoards
     * @param p0 the start point of the vector
     * @return  list of vectors from point given to all the points
     *          in the BlackBoard
     */
    public List<Vector> generateVectors(Point3D p0){
        Point3D pIJ = pC;
        double x,y ;
        List<Vector> vectors = new LinkedList<Vector>();
        for (Point2D point:points) {
            x = point.getX();
            y = point.getY();
            if (!isZero(x))
                pIJ = pIJ.add(vRight.scale(x));
            if (!isZero(y))
                pIJ = pIJ.add(vUp.scale(y));
            vectors.add(pIJ.subtract(p0));
        }
        return vectors;
    }

    /**
     * Creates Y Axis and X axis
     * that are original to vector given
     * @param vTo the vector the axis will be orthogonal to
     */
    private void createAxis(Vector vTo){
        Point3D vToHead = vTo.getHead();
        double y = vToHead.getY();
        double z = vToHead.getZ();
        Vector yAxis = new Vector(new Point3D(0,-z,y));
        Vector xAxis = vTo.crossProduct(yAxis);
        this.vUp = yAxis;
        this.vRight = xAxis;
    }

}
