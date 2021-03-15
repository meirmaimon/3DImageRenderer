package unittests.primitivesTests;

import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Testing Vector
 */
public class VectorTest {

    /**
     * Test method for
     * {@link primitives.Vector#add(Vector)}
     */
    @org.junit.Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: vectors with the same sign
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(1,2,3);
        Vector v3 = v1.add(v2);
        assertEquals("Bad vector addition",new Vector(2,3,4),v3);

        //TC02: vectors with different sign

        Vector v4 = new Vector(-1,1,1);
        v3 = v4.add(v2);
        assertEquals("Bad vector addition",new Vector(0,3,4),v3);

        Vector v5 = new Vector(1,-1,1);
        v3 = v5.add(v2);
        assertEquals("Bad vector addition",new Vector(2,1,4),v3);

        Vector v6 = new Vector(1,1,-1);
        v3 = v6.add(v2);
        assertEquals("Bad vector addition",new Vector(2,3,2),v3);

        Vector v7 = new Vector(1,-1,-1);
        v3 = v7.add(v2);
        assertEquals("Bad vector addition",new Vector(2,1,2),v3);

        Vector v8 = new Vector(-1,1,-1);
        v3 = v8.add(v2);
        assertEquals("Bad vector addition",new Vector(0,3,2),v3);

        Vector v9 = new Vector(-1,-1,1);
        v3 = v9.add(v2);
        assertEquals("Bad vector addition",new Vector(0,1,4),v3);

        // =============== Boundary Values Tests ==================
        // test zero vector from adding 2 opposite vectors
        Vector v10 = new Vector(-1,-1,-1);
        try {
            Vector v11 = v10.add(v1);
            fail("add() vector zero does not throw an exception");
        } catch (Exception e){}
    }

    /**
     * Test method for
     * {@link Vector#getHead()}
     */
    @org.junit.Test
    public void getHead() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test to get the head of the vector
        Vector v1 = new Vector(1,1,1);
        assertEquals("getHead() fail",new Point3D(1,1,1),v1.getHead());
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}
     */
    @org.junit.Test
    public void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: vectors with the same sign
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(1,2,3);
        Vector v3 = v1.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(0,-1,-2),v3);

        //TC02: vectors with different sign

        Vector v4 = new Vector(-1,1,1);
        v3 = v4.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(-2,-1,-2),v3);

        Vector v5 = new Vector(1,-1,1);
        v3 = v5.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(0,-3,-2),v3);

        Vector v6 = new Vector(1,1,-1);
        v3 = v6.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(0,-1,-4),v3);

        Vector v7 = new Vector(1,-1,-1);
        v3 = v7.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(0,-3,-4),v3);

        Vector v8 = new Vector(-1,1,-1);
        v3 = v8.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(-2,-1,-4),v3);

        Vector v9 = new Vector(-1,-1,1);
        v3 = v9.subtract(v2);
        assertEquals("Bad vector subtraction",new Vector(-2,-3,-2),v3);

        // =============== Boundary Values Tests ==================
        // test zero vector from subtract 2 identical vectors
        try {
            Vector v11 = v1.subtract(v1);
            fail("subtract identical vectors  does not throw an exception");
        } catch (Exception e){}

    }

    /**
     * Test method for
     * {@link primitives.Vector#scale(double)}
     */
    @org.junit.Test
    public void scale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: positive
        Vector v1 = new Vector(1,1,1);
        Vector v2 = v1.scale(2);
        assertEquals("Bad scaling" , new Vector(2,2,2),v2);

        //TC02: negative
        v2 = v1.scale(-2);
        assertEquals("Bad scaling" , new Vector(-2,-2,-2),v2);

        // =============== Boundary Values Tests ==================
        // test zero
        try {
            Vector v3 = v1.scale(0);
            fail("scale with 0 does not throw an exception");
        } catch (Exception e){}
    }

    /**
     * Test method for
     * {@link primitives.Vector#dotProduct(Vector)}
     */
    @org.junit.Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: acute angle
        Vector v1 = new Vector(1,0,0);
        Vector v2 = new Vector(1,1,0);
        assertTrue(isZero(v2.dotProduct(v1)-1));

        //TC02: obtuse angles
        Vector v3 = new Vector(-1,1,0);
        assertTrue(isZero(v3.dotProduct(v1)+1));

        // =============== Boundary Values Tests ==================
        //TC03:orthogonal vectors
        Vector v4 = new Vector(0,1,0);
        assertTrue(isZero(v4.dotProduct(v1)-0));

        //TC05:straight angle
        Vector v5 = new Vector(-1,0,0);
        assertTrue(isZero(v5.dotProduct(v1)+1));

        //TC02:angle 0
        Vector v6 = new Vector(2,0,0);
        assertTrue(isZero(v6.dotProduct(v1)-2));
    }

    @org.junit.Test
    public void crossProduct() {
    }

    /**
     * Test method for
     * {@link Vector#lengthSquared()}
     */
    @org.junit.Test
    public void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:simple test
        Vector v1 = new Vector(1,2,3);
        assertTrue(isZero(v1.lengthSquared() - 14));

    }
    /**
     * Test method for
     * {@link Vector#length()}
     */
    @org.junit.Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:simple test
        Vector v1 = new Vector(0,3,4);
        assertTrue(isZero(v1.length()-5));
    }
    /**
     * Test method for
     * {@link Vector#normalize()}
     */
    @org.junit.Test
    public void normalize() {
    }

    @org.junit.Test
    public void normalized() {
    }
}