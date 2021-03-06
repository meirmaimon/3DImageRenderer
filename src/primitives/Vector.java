package primitives;

public class Vector {
    private Point3D head;


    public Vector(Point3D head) {
        setHead(head);
    }

    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        if(head.x.coord == 0 && head.y.coord == 0 && head.z.coord == 0) // vector 0
            throw new IllegalArgumentException("Cannot construct vector zero");
        this.head = head;
    }
}
