/*
// uncomment to use jUnit tests
import org.junit.Test;
import static org.junit.Assert.*;
*/

/**
 * @author Danis Alukaev BS19-02.
 * 2.1 Segment intersection.
 * 1. Implementation of the algorithm determining if there exists an intersection
 * point of two line segments
 * 2. Paragraph about importance of this algorithm in a form of
 * code comment
 * 3. Sweep Line algorithm description
 */

/////////////////////////////////////////////// # 2.1.2 /////////////////////////////////////////////////////////////

/** Importance of line intersection algorithm:
 * In the early 1970s, microprocessor design became a geometric problem. Since the main trend in design was
 * Very-large-scale integration (VLSI) computer engineers needed a powerful tool to check wires intersection and distance
 * to circuits neighbors. For instance, the naive implementation of line intersection algorithm was used in first Computer-Aided
 * Design (CAD) checker. Moreover, checking the intersection of segments is vital in robotics, it used in collision
 * detection and collision avoidance of Unmanned aerial vehicles. Furthermore, such an algorithm used in GIScience to
 * maintain overlay of the city's utility systems and road networks. Also, the line intersection algorithm is common in
 * solid modeling, it used to determine the intersection of object boundaries in Constructive solid geometry(CSG). In computer
 * graphics rendering via ray shooting based on the intersection of the ray with objects computing. Moreover, in computer modeling
 * line intersection algorithm solve problem of hidden-line removal that helps efficiently represent opaque object (which edges or
 * which parts of the edges are hidden by an object itself or by other objects). Furthermore, in computer vision line finding
 * algorithm is based on line intersection algorithm, and it can be extended to other geometrical figures.
 */

/////////////////////////////////////////////// # 2.1.3 /////////////////////////////////////////////////////////////

/** Sweep Line algorithm description
 *  We have set of line segments each of whose given by two endpoints. Our goal is effectively determine whether any two of these segments intersect.
 *  Firstly, we arrange all endpoints of segments according to its x-coordinates, such that they sorted in order from the rightmost point to the leftmost.
 *  The key idea behind it is to pass "vertical" (sweep) line through all these points (from left to right) and:
 *  -If particular point is left endpoint of some segment, check intersection of this segment with segments that are
 *  below and above it. For any segment which left endpoint has greater than current segment's left endpoint x-coordinate we consider current segment either
 *  as the segment above or below based on its y-coordinate.
 *  -If particular point is right endpoint of some segment, stop consider this segment as existing, and check whether the
 *  segments above it and below it intersect.
 *  We need to insert, delete and find segments in some container efficiently, so we have to use self-balancing Binary Search Tree, which allows us
 *  to perform these operations in O(log n).
 *  Consequently, we optimize the searching for intersected lines and perform it O(n log n) time, i.e. sorting can be performed in O(n log n) time, treating 2n
 *  points can be performed in linear time, and all operations on them have O(log n) time complexity.
 */

/////////////////////////////////////////////// # 2.1.1 /////////////////////////////////////////////////////////////

public class SegmentIntersection_2_1 {

    /*
    // uncomment to use jUnit tests
    @Test
    public void TestCross(){
        MyPoint P1 = new MyPoint(1, 2);
        MyPoint P2 = new MyPoint(3, 3);
        MyPoint Q1 = new MyPoint(1, 5);
        MyPoint Q2 = new MyPoint(3, 0);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestOverlay(){
        MyPoint P1 = new MyPoint(1, 1);
        MyPoint P2 = new MyPoint(3, 3);
        MyPoint Q1 = new MyPoint(0, 0);
        MyPoint Q2 = new MyPoint(4, 4);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestSameOrigin(){
        MyPoint P1 = new MyPoint(1, 1);
        MyPoint P2 = new MyPoint(3, 3);
        MyPoint Q1 = new MyPoint(1, 1);
        MyPoint Q2 = new MyPoint(0, 0);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestIntersectionInCoordsOrigin(){
        MyPoint P1 = new MyPoint(1, 1);
        MyPoint P2 = new MyPoint(-1, -1);
        MyPoint Q1 = new MyPoint(-1, 1);
        MyPoint Q2 = new MyPoint(1, -1);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestParallel_1(){
        MyPoint P1 = new MyPoint(1, 1);
        MyPoint P2 = new MyPoint(3, 3);
        MyPoint Q1 = new MyPoint(2, 1);
        MyPoint Q2 = new MyPoint(1, 0);
        assertFalse(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestParallel_2(){
        MyPoint P1 = new MyPoint(2, 2);
        MyPoint P2 = new MyPoint(-1, -1);
        MyPoint Q1 = new MyPoint(1, 1);
        MyPoint Q2 = new MyPoint(-2, -2);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestArbitrary1(){
        MyPoint P1 = new MyPoint(2, 9);
        MyPoint P2 = new MyPoint(1, -1);
        MyPoint Q1 = new MyPoint(-7, 0);
        MyPoint Q2 = new MyPoint(4, 0);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestArbitrary2(){
        MyPoint P1 = new MyPoint(2, 9);
        MyPoint P2 = new MyPoint(1, -1);
        MyPoint Q1 = new MyPoint(-7, 0);
        MyPoint Q2 = new MyPoint(0, 0);
        assertFalse(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestArbitrary3(){
        MyPoint P1 = new MyPoint(0, 50);
        MyPoint P2 = new MyPoint(2, -10);
        MyPoint Q1 = new MyPoint(37, -5);
        MyPoint Q2 = new MyPoint(6, 5);
        assertFalse(segmentsIntersects(P1, P2, Q1, Q2));
    }

    @Test
    public void TestArbitrary4(){
        MyPoint P1 = new MyPoint(13, 34);
        MyPoint P2 = new MyPoint(-12, -15);
        MyPoint Q1 = new MyPoint(0, 11);
        MyPoint Q2 = new MyPoint(9, 5);
        assertTrue(segmentsIntersects(P1, P2, Q1, Q2));
    }
    */

    /**
     * method segmentsIntersects checks whether two line segments s1(point1, point2), s2(point 3, point 4) intersect
     * Time complexity: T(n) = O(1)
     * ----------------------------------------------------------------------------------------------------------
     * @param point1 - the first point of the first segment
     * @param point2 - the second point of the first segment
     * @param point3 - the first point of the second segment
     * @param point4 - the second point of the second segment
     * @return true if two line segments intersect, false - otherwise
     */
    static boolean segmentsIntersects(MyPoint point1, MyPoint point2, MyPoint point3, MyPoint point4) {
        int dir_1 = direction(point3, point4, point1);
        int dir_2 = direction(point3, point4, point2);
        int dir_3 = direction(point1, point2, point3);
        int dir_4 = direction(point1, point2, point4);
        if (((dir_1 > 0 && dir_2 < 0) || (dir_1 < 0 && dir_2 > 0)) && ((dir_3 > 0 && dir_4 < 0) || (dir_3 < 0 && dir_4 > 0)))
            return true;
        else if (dir_1 == 0 && onSegment(point3, point4, point1)) return true;
        else if (dir_2 == 0 && onSegment(point3, point4, point2)) return true;
        else if (dir_3 == 0 && onSegment(point1, point2, point3)) return true;
        else return dir_4 == 0 && onSegment(point1, point2, point4);
    }

    /**
     * method direction computes relative orientations using the cross-product method
     * Time complexity: T(n) = O(1)
     * ----------------------------------------------------------------------------------------------------------
     * @param point1 - the point of the intersection of two oriented segments
     * @param point2 - the endpoint of the first oriented segment
     * @param point3 - the endpoint of the second oriented segment
     * @return cross product of two vectors
     */
    static int direction(MyPoint point1, MyPoint point2, MyPoint point3) {
        MyPoint vector1 = new MyPoint(point3.x - point1.x, point3.y - point1.y);
        MyPoint vector2 = new MyPoint(point2.x - point1.x, point2.y - point1.y);
        return vector1.x * vector2.y - vector1.y * vector2.x;
    }

    /**
     * method onSegment determines whether a point known to be colinear with a segment lies on that segment
     * Time complexity: T(n) = O(1)
     * ----------------------------------------------------------------------------------------------------------
     * @param point1 - the first endpoint of segment
     * @param point2 - the second endpoint of segment
     * @param point3 - the point to check that it lies between endpoints of segment
     * @return true if a point lies on segment, false - otherwise
     */
    static boolean onSegment(MyPoint point1, MyPoint point2, MyPoint point3) {
        return Math.min(point1.x, point2.x) <= point3.x && point3.x <= Math.max(point1.x, point2.x) && Math.min(point1.y, point2.y) <= point3.y && point3.y <= Math.max(point1.y, point2.y);
    }

}

  /* CAN BE USED TO CALCULATE INTERSECTION POINT

    static boolean denominator(MyPoint point1, MyPoint point2, MyPoint point3, MyPoint point4) {
        int den = (point1.x - point2.x) * (point3.y - point4.y) - (point1.y - point2.y) * (point3.x - point4.x);
        if (den != 0) return true;
        else return false;
    }
    static MyPoint calculateIntersectionPoint(MyPoint point1, MyPoint point2, MyPoint point3, MyPoint point4) {
        int den = (point1.x - point2.x) * (point3.y - point4.y) - (point1.y - point2.y) * (point3.x - point4.x);
        int point_x = ((point1.x * point2.y - point1.y * point2.x) * (point3.x - point4.x) - (point1.x - point2.x) * (point3.x * point4.y - point3.y * point4.x)) / den;
        int point_y = ((point1.x * point2.y - point1.y * point2.x) * (point3.y - point4.y) - (point1.y - point2.y) * (point3.x * point4.y - point3.y * point4.x)) / den;
        return new MyPoint(point_x, point_y);
    }

   */

/**
 * Auxiliary class MyPoint
 */
class MyPoint implements Comparable<MyPoint> {

    int x, y; // coordinates of point

    /**
     * Constructor of class MyPoint
     * ----------------------------------------------------------------------------------------------------------
     * @param x - x coordinate of the point
     * @param y - y coordinate of the point
     */
    MyPoint(int x, int y) {
        this.x = x; // set the value of x
        this.y = y; // set the value of y
    }

    /**
     * comparing points firstly by x-coord values, if them are the same compare by y-coord values
     * ----------------------------------------------------------------------------------------------------------
     * @param point - point to be compared
     * @return difference between two points
     */
    public int compareTo(MyPoint point) {
        if (this.x == point.x) return this.y - point.y;
        else return this.x - point.x;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

}