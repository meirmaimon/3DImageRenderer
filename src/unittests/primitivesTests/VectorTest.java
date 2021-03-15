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
    public void testGetHead() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test to get the head of the vector
        Vector v1 = new Vector(1,1,1);
        assertEquals("getHead() fail",new Point3D(1,1,1),v1.getHead());
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}
     */
    @org.junit.Test
    public void testSubtract() {
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
    public void testScale() {
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
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: acute angle
        Vector v1 = new Vector(1,0,0);
        Vector v2 = new Vector(1,1,0);
        assert(1==v2.dotProduct(v1));

        //TC02: obtuse angles
        Vector v3 = new Vector(-1,1,0);
        assert(-1 == v3.dotProduct(v1));

        // =============== Boundary Values Tests ==================
        //TC03:orthogonal vectors
        Vector v4 = new Vector(0,1,0);
        assert(0 == v4.dotProduct(v1));

        //TC05:straight angle
        Vector v5 = new Vector(-1,0,0);
        assert(-1 == v5.dotProduct(v1));

        //TC02:angle 0
        Vector v6 = new Vector(2,0,0);
        assert(2 == v6.dotProduct(v1));
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */

    @org.junit.Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }




    @org.junit.Test
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: positive
        Vector v1 = new Vector(1,2,1);
        double lengthSquared = v1.lengthSquared();
        assert( 6 == lengthSquared);

        // TC02: negative
        Vector v2 = new Vector(-1,-2,-1);
        double lengthSquared1 = v2.lengthSquared();
        assert(6 == lengthSquared1);

        // =============== Boundary Values Tests ==================
    }

    @org.junit.Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: positive
        Vector v1 = new Vector(1,2,2);
        assert( 3 == v1.length() );

        // TC02: negative
        Vector v2 = new Vector(-1,-2,-2);
        assert(3 == v2.length());

        // =============== Boundary Values Tests ==================
    }

    @org.junit.Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: positive
        Vector v1 = new Vector(1,2,2);
        assertEquals("cant normalize" , new Vector(1/3,2/3,2/3),v1.normalize());

        // TC02: negative
        Vector v2 = new Vector(-1,-2,-2);
        assertEquals("cant normalize" , new Vector(-1/3,-2/3,-2/3),v2.normalize());

        // TC03: already normalize
        Vector v3 = new Vector(1/(Math.sqrt(3)),1/(Math.sqrt(3)),1/(Math.sqrt(3)));
        assertEquals("cant normalize" , new Vector(1,1,1),v2.normalize());

        // =============== Boundary Values Tests ==================
    }

    @org.junit.Test
    public void testNormalized() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: positive
        Vector v1 = new Vector(1,2,2);
        assertEquals("cant normalize" , new Vector(1/3,2/3,2/3),v1.normalize());

        // TC02: negative
        Vector v2 = new Vector(-1,-2,-2);
        assertEquals("cant normalize" , new Vector(-1/3,-2/3,-2/3),v2.normalize());

        // TC03: already normalize
        Vector v3 = new Vector(1/(Math.sqrt(3)),1/(Math.sqrt(3)),1/(Math.sqrt(3)));
        assertEquals("cant normalize" , new Vector(1,1,1),v2.normalize());

        // =============== Boundary Values Tests ==================
    }
}