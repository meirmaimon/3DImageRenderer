package primitives;

/**
 * This class represents a vector in the Cartesian
 * Coordinate system
 */
public class Vector {
    private Point3D head;

    /**
     * Creates a vector given a point
     *
     * @param head The Vector's head
     * @throws IllegalArgumentException in case of vector 0
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector 0 is not valid");
        this.head = head;
    }

    /**
     * Creates a vector given the values of the
     * head point
     *
     * @param x X value
     * @param y Y value
     * @param z Z value
     * @throws IllegalArgumentException in case of vector 0
     */
    public Vector(double x, double y, double z) {
        head = new Point3D(x, y, z);
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector 0 is not valid");
    }

    /**
     * This methods preforms Vector addition
     *
     * @param vec vector to add
     * @return New vector represents the vector addition
     */
    public Vector add(Vector vec) {
        Point3D add = head.add(vec);    //Point 3D method "add" add the 2 vector coordinate
        return new Vector(add);
    }

    /**
     * Gets the vector's head point
     *
     * @return vector's head point
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * This method preforms Vector Subtraction
     *
     * @param vec vector to subtract
     * @return New vector represents the Vector Subtraction
     */
    public Vector subtract(Vector vec) {
        return head.subtract(vec.head);
    }

    /**
     * This method preforms Scalar multiplication:
     *
     * @param scalar scalar
     * @return New vector represents the Scalar multiplication:
     */
    public Vector scale(double scalar) {
        Point3D scaled = new Point3D(head.x.coord * scalar,
                head.y.coord * scalar,
                head.z.coord * scalar);
        return new Vector(scaled);
    }

    /**
     * This method preforms Dot product
     *
     * @param vec vector to multiply
     * @return Dot product value
     */
    public double dotProduct(Vector vec) {
        Point3D vecHead = vec.head;
        return (head.x.coord * vecHead.x.coord) +
                (head.y.coord * vecHead.y.coord) +
                (head.z.coord * vecHead.z.coord);
    }

    /**
     * This method preforms Cross product
     *
     * @param vec vector to multiply
     * @return Cross product value
     */
    public Vector crossProduct(Vector vec) {
        Point3D vecHead = vec.head;
        double firstLine = (head.y.coord * vecHead.z.coord) - (head.z.coord * vecHead.y.coord);
        double secondLine = (head.z.coord * vecHead.x.coord) - (head.x.coord * vecHead.z.coord);
        double thirdLine = (head.x.coord * vecHead.y.coord) - (head.y.coord * vecHead.x.coord);
        return new Vector(firstLine, secondLine, thirdLine);
    }

    /**
     * This methods returns the squared length of the vector
     *
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        //double distanceSquared = head.distanceSquared(Point3D.ZERO)
        //return dotProduct(this);
        return (head.x.coord * head.x.coord) +
                (head.y.coord * head.y.coord) +
                (head.z.coord * head.z.coord);
    }

    /**
     * This methods returns the length of the vector
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * This method normalize the current vector
     *
     * @return The current vector normalized
     */
    public Vector normalize() {
        double length = length();
        this.head = new Point3D(head.x.coord/length, head.y.coord/length, head.z.coord/length);
        return this;
    }

    /**
     * This method returns normalize of the current vector
     *
     * @return A vector normalized
     */
    public Vector normalized() {
        double length = length();
        return new Vector(head.x.coord/length, head.y.coord/length, head.z.coord/length);
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
