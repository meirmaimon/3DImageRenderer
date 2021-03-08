package primitives;

/**
 * This class represents a vector in the Cartsian
 * Coordinate system
 */
public class Vector {
    private Point3D head;

    public Vector(Point3D head) {
        if (head.x.coord == 0 && head.y.coord == 0 && head.z.coord == 0 )
            throw new IllegalArgumentException("Vector 0 is not valid");
        this.head = head;
    }

    public Vector(double x, double y, double z) {
        if (x == 0 && y == 0 && z == 0)
            throw new IllegalArgumentException("Vector 0 is not valid");
        this.head = new Point3D(x,y,z);
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z ) {
        if (x.coord == 0 && y.coord == 0 && z.coord ==0)
            throw new IllegalArgumentException("Vector 0 is not valid");
        this.head = new Point3D(x,y,z);
    }

    /**
     * This methods preforms Vector addition
     * @param vec vector to add
     * @return New vector represents the vector addition
     */
    public Vector add(Vector vec){
        Point3D add = head.add(vec);    //Point 3D method "add" add the 2 vector coordinate
        return new Vector(add);
    }

    public Point3D getHead() {
        return head;
    }

    /**
     * This method preforms Vector Subtraction
     * @param vec vector to subtract
     * @return New vector represents the Vector Subtraction
     */
    public Vector subtract(Vector vec){
        return head.subtract(vec.getHead());
    }
    /**
     * This method preforms Scalar multiplication:
     * @param scalar scalar
     * @return New vector represents the Scalar multiplication:
     */
    public Vector scale(double scalar){
        Point3D scaled = new Point3D(head.x.coord*scalar ,
                head.y.coord*scalar ,
                head.z.coord*scalar);
        return new Vector(scaled);
    }
    /**
     * This method preforms Dot product
     * @param vec vector to multiply
     * @return Dot product value
     */
    public double dotProduct(Vector vec){
        Point3D vecHead = vec.getHead();
        double dot = (head.x.coord * vecHead.x.coord) +
                     (head.y.coord * vecHead.y.coord) +
                     (head.z.coord * vecHead.z.coord);
        return dot;
    }
    /**
     * This method preforms Cross product
     * @param vec vector to multiply
     * @return Cross product value
     */
    public Vector crossProduct(Vector vec){
        Point3D vecHead = vec.getHead();
        double firstLine = (head.y.coord * vecHead.z.coord) - (head.z.coord * vecHead.y.coord);
        double secondLine = (head.z.coord * vecHead.x.coord) - (head.x.coord * vecHead.z.coord);
        double thirdLine = (head.x.coord * vecHead.y.coord) - (head.y.coord * vecHead.x.coord);
        return new Vector(firstLine,secondLine,thirdLine);
    }

    /**
     * This methods returns the squared length of the vector
     * @return the squared length of the vector
     */
    public double lengthSquared(){
        //double distanceSquared = head.distanceSquared(Point3D.ZERO)
        double distanceSquared = (head.x.coord * head.x.coord) +
                                 (head.y.coord * head.y.coord) +
                                 (head.z.coord * head.z.coord);
        return distanceSquared;
    }
    /**
     * This methods returns the length of the vector
     * @return the length of the vector
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     * This method normalize the current vector
     * @return The current vector normalized
     */
    public Vector normalize(){
        this.head = normalized().getHead();
        return this;
    }
    /**
     * This method returns normalize of the current vector
     * @return A vector normalized
     */
    public Vector normalized(){
        return this.scale(1/length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return head.equals(other.head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}
