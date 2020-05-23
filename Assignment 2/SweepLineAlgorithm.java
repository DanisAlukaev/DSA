import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Danis Alukaev BS19-02.
 * 2.4 Sweep Line Algorithm.
 * Implementation of the Sweep Line Algorithm (Cormen, 2011, pp. 1023 - 1025)
 *
 * link to the submission in CodeForces:
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/272963/submission/74188809
 */

public class SweepLineAlgorithm {
    /**
     * method segmentsIntersects checks whether two line segments s1(point1, point2), s2(point 3, point 4) intersect
     * ----------------------------------------------------------------------------------------------------------
     *
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
     * ----------------------------------------------------------------------------------------------------------
     *
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
     * ----------------------------------------------------------------------------------------------------------
     *
     * @param point1 - the first endpoint of segment
     * @param point2 - the second endpoint of segment
     * @param point3 - the point to check that it lies between endpoints of segment
     * @return true if a point lies on segment, false - otherwise
     */

    static boolean onSegment(MyPoint point1, MyPoint point2, MyPoint point3) {
        return Math.min(point1.x, point2.x) <= point3.x && point3.x <= Math.max(point1.x, point2.x) && Math.min(point1.y, point2.y) <= point3.y && point3.y <= Math.max(point1.y, point2.y);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * More efficient way to read input
     * Original source: https://pastebin.com/2y4kFUzp
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");

    static String nextToken() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        int n = nextInt(); // number of segments
        PairPointSegment[] arrP = new PairPointSegment[2 * n]; // array of pairs (endpoint, segment)
        for (int i = 0; i < n; i++) {
            int xp = nextInt();
            int yp = nextInt();
            int xq = nextInt();
            int yq = nextInt();
            arrP[2 * i] = new PairPointSegment(new MyPoint(xp, yp), new MySegment(new MyPoint(xp, yp), new MyPoint(xq, yq))); // store first endpoint of segment
            arrP[2 * i + 1] = new PairPointSegment(new MyPoint(xq, yq), new MySegment(new MyPoint(xp, yp), new MyPoint(xq, yq))); // store second endpoint of segment
        }
        QuickSort<PairPointSegment> sorting = new QuickSort<>();
        sorting.sortingAlgorithm(arrP, 0, 2 * n - 1);
        AVLTree<MySegment, MySegment> tree = new AVLTree<>();
        boolean intersected = false;
        for (int j = 0; j < arrP.length && !intersected; j++) { // treat all pairs Point-Segment
            if (arrP[j].point.compareTo(arrP[j].segment.getLeftPoint()) == 0) {
                // if we met left endpoint of some segment
                tree.insert(arrP[j].segment, arrP[j].segment); // insert this segment
                AVLTree<MySegment, MySegment>.MyNode inserted = tree.findNode(tree.root, arrP[j].segment); // find node that stores segment
                AVLTree<MySegment, MySegment>.MyNode successor = tree.findSuccessor(inserted); // find successor (segment above)
                AVLTree<MySegment, MySegment>.MyNode predecessor = tree.findPredecessor(inserted); // find predecessor (segment below)
                if (successor != null && segmentsIntersects(successor.value.point_1, successor.value.point_2, inserted.value.point_1, inserted.value.point_2)) {
                    // if segment above exists and intersects current segment
                    System.out.println("INTERSECTION");
                    System.out.println(arrP[j].segment);
                    System.out.println(successor.value);
                    intersected = true;
                } else if ((predecessor != null && segmentsIntersects(predecessor.value.point_1, predecessor.value.point_2, inserted.value.point_1, inserted.value.point_2))) {
                    // if segment below exists and intersects current segment
                    System.out.println("INTERSECTION");
                    System.out.println(arrP[j].segment);
                    System.out.println(predecessor.value);
                    intersected = true;
                }
            }
            if (arrP[j].point.compareTo(arrP[j].segment.getRightPoint()) == 0) {
                // if we met right endpoint of some segment
                AVLTree<MySegment, MySegment>.MyNode inserted = tree.findNode(tree.root, arrP[j].segment); // find node that stores segment
                AVLTree<MySegment, MySegment>.MyNode successor = tree.findSuccessor(inserted); // find successor (segment above)
                AVLTree<MySegment, MySegment>.MyNode predecessor = tree.findPredecessor(inserted); // find predecessor (segment below)
                if (successor != null && predecessor != null
                        && segmentsIntersects(successor.value.point_1, successor.value.point_2, predecessor.value.point_1, predecessor.value.point_2)) {
                    // if segments above and below exist and intersect
                    System.out.println("INTERSECTION");
                    System.out.println(predecessor.value);
                    System.out.println(successor.value);
                    intersected = true;
                }
                tree.deleteNode(arrP[j].segment);
            }
        }
        if (!intersected) System.out.println("NO INTERSECTIONS");
    }
}

/**
 * Auxiliary class MyPoint
 */
class MyPoint implements Comparable<MyPoint> {

    int x, y; // coordinates of point

    /**
     * Constructor of class MyPoint
     * ----------------------------------------------------------------------------------------------------------
     *
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
     *
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

/**
 * Auxiliary class MySegment
 */
class MySegment implements Comparable<MySegment> {

    MyPoint point_1, point_2; // endpoints of segment

    /**
     * Constructor of class MySegment
     *
     * @param point_1 - first endpoint
     * @param point_2 - second endpoint
     */
    MySegment(MyPoint point_1, MyPoint point_2) {
        this.point_1 = point_1; // set the start point
        this.point_2 = point_2; // set the endpoint
    }

    /**
     * Method getLeftPoint() finds left endpoint of current segment
     *
     * @return left endpoint of current segment
     */
    MyPoint getLeftPoint() {
        MyPoint thisLeft;
        if (this.point_1.compareTo(this.point_2) < 0)
            // has lower x-coordinate
            thisLeft = this.point_1;
        else
            thisLeft = this.point_2;
        return thisLeft;
    }

    /**
     * Method getRightPoint() finds right endpoint of current segment
     *
     * @return right endpoint of current segment
     */
    MyPoint getRightPoint() {
        MyPoint thisRight;
        if (this.point_1.compareTo(this.point_2) < 0)
            // has lower x-coordinate
            thisRight = this.point_2;
        else
            thisRight = this.point_1;
        return thisRight;
    }

    /**
     * Method compareTo(segment) compare segments firstly by y-coordinates of left endpoint, then by y-coordinates of right endpoint,
     * then by x-coordinate of left endpoint, then by x-coordinate of right endpoint
     *
     * @param segment - segment which we need to compare
     * @return - -1 if received segment greater than this, 0 if they are equal, 1 if received segment less than this
     */
    public int compareTo(MySegment segment) {

        // determine left and right endpoints
        MyPoint thisLeft, thisRight, segmentLeft, segmentRight;
        thisLeft = this.getLeftPoint();
        thisRight = this.getRightPoint();
        segmentLeft = segment.getLeftPoint();
        segmentRight = segment.getRightPoint();

        // compare segments firstly by y-coordinates of left endpoint, then by y-coordinates of right endpoint,
        // then by x-coordinate of left endpoint, then by x-coordinate of right endpoint
        if (thisLeft.y < segmentLeft.y)
            return -1;
        else if (thisLeft.y == segmentLeft.y) {
            if (thisRight.y < segmentRight.y)
                return -1;
            else if (thisRight.y == segmentRight.y) {
                if (thisLeft.x < segmentLeft.x)
                    return -1;
                else if (thisLeft.x == segmentLeft.x) {
                    return Integer.compare(thisRight.x, segmentRight.x);
                } else return 1;
            } else return 1;
        } else return 1;
    }

    @Override
    public String toString() {
        return point_1.toString() + " " + point_2.toString();
    }
}

/**
 * Auxiliary class PairPointSegment
 */
class PairPointSegment implements Comparable<PairPointSegment> {

    MyPoint point; // endpoint of segment "segment"
    MySegment segment; // segment "segment"

    /**
     * @param point   - the endpoint of segment "segment"
     * @param segment - the segment "segment"
     */
    PairPointSegment(MyPoint point, MySegment segment) {
        this.point = point; // set point
        this.segment = segment; // set segment
    }

    public int compareTo(PairPointSegment pair) {
        if(this.point.x == pair.point.x) {
            // determine whether points in pair are left or right
            boolean thisPointLeft = this.point.compareTo(this.segment.getRightPoint()) != 0;
            boolean pairPointLeft = pair.point.compareTo(pair.segment.getRightPoint()) != 0;
            // if two points have same x-coordinate (but they are right and left endpoints), we give greater priority to right endpoint than the left one of the segment
            if (!thisPointLeft && pairPointLeft)
                return 1;
            else if (thisPointLeft && !pairPointLeft)
                return -1;
        }
        return this.point.compareTo(pair.point);
    }
}


/**
 * Quick Sort with Lomuto partition scheme implementation
 * ----------------------------------------------------------------------------------------------------------
 * time complexity:
 * Worst case: T(n) = T(0) + T(n-1) + theta(n) that is O(n^2)
 * Best case: T(n) = 2T(n/2) + theta(n) that is O(n log n)
 * Average case: O(n log n)
 * ----------------------------------------------------------------------------------------------------------
 * current implementation is in-place because it uses extra space only to store recursive function calls
 * ----------------------------------------------------------------------------------------------------------
 * current implementation is not stable, but considering indexes as a comparison parameter can make it stable
 */
class QuickSort<T extends Comparable<T>> {

    /**
     * Partition algorithm takes last element of sequence as pivot, places it at correct position in sorted
     * array, then arrange all smaller than pivot elements to its left, and all greater elements to right side
     *
     * @param container - array to be sorted
     * @param start     - starting index of element
     * @param end       - ending index of element
     * @return partitioning index
     */
    private int partitionAlgorithm(T[] container, int start, int end) {
        T pivot = container[end]; // take last element as a pivot (element to be placed at right position)
        int index = start; // index of smaller element
        for (int i = start; i <= end; i++) {
            // place smaller than pivot elements to left of it
            if (container[i].compareTo(pivot) < 0) {
                T t = container[i];
                container[i] = container[index];
                container[index] = t;
                index++;
            }
        }
        T t = container[end];
        container[end] = container[index];
        container[index] = t;
        return index;

    }

    /**
     * Sorting algorithm split array into two parts and recursively sort both of them
     *
     * @param container - array to be sorted
     * @param start     - starting index of element
     * @param end       - ending index of element
     */
    void sortingAlgorithm(T[] container, int start, int end) {

        if (start < end) {
            // split array and sort both its sides
            int index = partitionAlgorithm(container, start, end); // partitioning index
            sortingAlgorithm(container, start, index - 1);
            sortingAlgorithm(container, index + 1, end);
        }

    }

}

/**
 * Adelson-Velsky Landis (AVL) tree implementation
 *
 * @param <T> - value of the node
 * @param <G> - key of the node
 */
class AVLTree<T, G extends Comparable<G>> {

    /**
     * Auxiliary class MyNode
     * used as building block in AVL tree
     */
    public class MyNode implements Comparable<MyNode> {

        T value; // value of node
        G key; // key of node
        int longest_path; // longest path in subtree rooted with the current node
        MyNode left_child; // left child of the current node
        MyNode right_child; // right child of the current node
        MyNode parent; // the node to which the current node is connected

        /**
         * Constructor of class MyNode
         * Time Complexity: T(n) = O(1)
         *
         * @param value - value of created node
         * @param key - key of created node
         * @param parent - parent of created node
         */
        MyNode(T value, G key, MyNode parent) {
            this.key = key; // set the key of node
            this.value = value; // set the value of node
            this.parent = parent; // set the parent node
            longest_path = 1; // set the default value of longest path in subtree rooted with the current node (current height)
            this.left_child = null; // set children
            this.right_child = null; // to null
        }

        /**
         * compareTo(node) used for comparison of two nodes
         * Time Complexity: T(n) = O(1)
         *
         * @param node - node to be compared with "this" node
         * @return - key comparison result
         */
        public int compareTo(MyNode node) {
            return this.key.compareTo(node.key);
        }

        @Override
        public String toString() {
            return "(" + value.toString() + ", " + key.toString() + ", " + longest_path + ")";

        }
    }


    MyNode root; // root of current tree

    /**
     * Method insert(value, key) adds new node in the tree by comparing its actual key, keys are not unique.
     * Since tree is self-balanced (balancing takes constant time) the method insert(value, key)
     * takes T(n) = O(tree height) = O (log n); where n is number of nodes in the tree.
     *
     * @param value - value to be stored in the tree
     * @param key   - key by which node compared
     * @return root of the tree
     */
    public MyNode insert(T value, G key) {
        if (this.root == null) {
            // tree is empty
            root = new MyNode(value, key, null);
            return root;
        } else
            // otherwise call private method insert
            return insert(this.root, value, key);
    }

    /**
     * Method deleteNode(key) deletes node in the tree by its key, keys are not unique.
     * Since tree is self-balanced (balancing takes constant time), searching in BST takes constant time,
     * the method deleteNode(key) takes T(n) = O(tree height) = O (log n); where n is number of nodes in the tree.
     *
     * @param key - key of node to be deleted
     * @return root of the tree
     */
    public MyNode deleteNode(G key) {
        return deleteNode(this.root, key);
    }

    /**
     * Method getBalanceFactor(subtreeRoot) calculates the difference between the heights of right child
     * and left child.
     * Time complexity: T(n) = O(1)
     *
     * @param subtreeRoot - node which balance we have to find
     * @return the balance factor ot the node "subtreeRoot"
     */
    private int getBalanceFactor(MyNode subtreeRoot) {
        if (subtreeRoot == null)
            return 0;
        // return balance of subtree rooted with the received node if this node exists
        return getLongestPath(subtreeRoot.right_child) - getLongestPath(subtreeRoot.left_child);
    }

    /**
     * Method findNode(node, key) searches node with key "key".
     * Time complexity: worst case is T(n) = O(log n)
     *
     * @param node - root of the subtree where we try to find the node
     * @param key  - key of the node to be found
     * @return node if it is found, null - otherwise
     */
    MyNode findNode(MyNode node, G key) {
        if (node == null)
            return null;
        if (node.key.equals(key))
            // current node has key "key"
            return node;
        if (key.compareTo(node.key) < 0) {
            // node lies in left subtree
            if (node.left_child == null) return null;
            else return findNode(node.left_child, key);
        } else {
            // node lies in right subtree
            if (node.right_child == null) return null;
            else return findNode(node.right_child, key);
        }
    }

    /**
     * Method preOrder(node) print the result of preorder traversal of tree
     * Time complexity: T(n) = theta(n)
     *
     * @param node - start node for traversal
     */
    void preOrder(MyNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left_child);
            preOrder(node.right_child);
        }
    }

    /**
     * Method traversalPreorderList() return list of nodes in a preorder traversal
     * Time complexity: T(n) = theta(n)
     *
     * @return arraylist of nodes
     */
    public ArrayList<MyNode> traversalPreorderList() {
        ArrayList<MyNode> nodes = new ArrayList<>();
        traversalPreorder(root, nodes);
        return nodes;
    }

    /**
     * Method traversalPreorder(root, list) stores visited nodes in arraylist "list"
     * Time complexity: T(n) = theta(n)
     *
     * @param root - root of subtree to be preoder traversed
     * @param list - arraylist where nodes stored
     */
    private void traversalPreorder(MyNode root, ArrayList<MyNode> list) {
        if (root != null) {
            list.add(root);
            traversalPreorder(root.left_child, list);
            traversalPreorder(root.right_child, list);
        }
    }

    /**
     * Method inOrder(node) print the result of inorder traversal of tree
     * Time complexity: T(n) = theta(n)
     *
     * @param node - start node for traversal
     */
    void inOrder(MyNode node) {
        if (node != null) {
            inOrder(node.left_child);
            System.out.print(node.value + " ");
            inOrder(node.right_child);
        }
    }

    /**
     * Method traversalInorderList() return list of nodes in a inorder traversal
     * Time complexity: T(n) = theta(n)
     *
     * @return arraylist of nodes
     */
    public ArrayList<MyNode> traversalInorderList() {
        ArrayList<MyNode> nodes = new ArrayList<>();
        traversalInorder(root, nodes);
        return nodes;
    }

    /**
     * Method traversalInorder(root, list) stores visited nodes in arraylist "list"
     * Time complexity: T(n) = theta(n)
     *
     * @param root - root of subtree to be inorder traversed
     * @param list - arraylist where nodes stored
     */
    private void traversalInorder(MyNode root, ArrayList<MyNode> list) {
        if (root != null) {
            traversalInorder(root.left_child, list);
            list.add(root);
            traversalInorder(root.right_child, list);
        }
    }

    /**
     * Method inOrder(node) print the result of postorder traversal of tree
     * Time complexity: T(n) = theta(n)
     *
     * @param node - start node for traversal
     */
    void postOrder(MyNode node) {
        if (node != null) {
            postOrder(node.left_child);
            postOrder(node.right_child);
            System.out.print(node.value + " ");
        }
    }

    /**
     * Method traversalPostorderList() return list of nodes in a postorder traversal
     * Time complexity: T(n) = theta(n)
     *
     * @return arraylist of nodes
     */
    public ArrayList<MyNode> traversalPostorderList() {
        ArrayList<MyNode> nodes = new ArrayList<>();
        traversalPostorder(root, nodes);
        return nodes;
    }

    /**
     * Method traversalPostorder(root, list) stores visited nodes in arraylist "list"
     * Time complexity: T(n) = theta(n)
     *
     * @param root - root of subtree to be postorder traversed
     * @param list - arraylist where nodes stored
     */
    private void traversalPostorder(MyNode root, ArrayList<MyNode> list) {
        if (root != null) {
            traversalPostorder(root.left_child, list);
            traversalPostorder(root.right_child, list);
            list.add(root);
        }
    }

    /**
     * Time complexity: T(n) = O(1)
     *
     * @param subtreeRoot - node which height is required
     * @return longest path in subtree rooted with the received node if this node exists
     */
    private int getLongestPath(MyNode subtreeRoot) {
        if (subtreeRoot == null)
            return 0;
        // return longest path in subtree rooted with the received node if this node exists
        return subtreeRoot.longest_path;
    }

    /**
     * Method insert(node, value, key) adds new node in the tree by comparing its actual key, keys are unique.
     * Since tree is self-balanced (balancing takes constant time) the method insert(node, value, key)
     * takes T(n) = O(tree height) = O (log n); where n is number of nodes in the tree.
     *
     * @param node  - root of subtree where we have to store value
     * @param value - value to be stored in the tree
     * @param key   - key by which node compared
     * @return root of the tree
     */
    private MyNode insert(MyNode node, T value, G key) {

        // Step #1 : add new node as in the regular BST (equal keys are not prohibited)
        if (key.compareTo(node.key) < 0) {
            // if received key less than the key of received node, then we have to add new node in the left subtree
            if (node.left_child == null)
                // child does not exist
                node.left_child = new MyNode(value, key, node);
            else
                // otherwise - insert in left subtree
                node.left_child = insert(node.left_child, value, key);
        } else {
            // if received key greater than the key of received node, then we have to add new node in the right subtree
            if (node.right_child == null)
                // child does not exist
                node.right_child = new MyNode(value, key, node);
            else
                // otherwise - insert in right subtree
                node.right_child = insert(node.right_child, value, key);

        }
        this.root = node = makeBalanced(node); // Step #2 : make tree balanced
        return node; // return inserted node
    }

    /**
     * Method deleteNode(node, key) deletes node in the tree by its key, keys are unique.
     * Since tree is self-balanced (balancing takes constant time), searching in BST takes O(log n),
     * the method deleteNode(node, key) takes T(n) = O(tree height) = O (log n); where n is number of nodes in the tree.
     *
     * @param node - root of subtree where we have to delete node
     * @param key  - key of node to be deleted
     * @return root of the tree
     */
    private MyNode deleteNode(MyNode node, G key) {

        // Step #1 : delete new node as in the regular BST
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            // if received key less than the key of received node, then we have to delete node in the left subtree
            node.left_child = deleteNode(node.left_child, key);
        else if (key.compareTo(node.key) > 0)
            // if received key greater than the key of received node, then we have to delete node in the right subtree
            node.right_child = deleteNode(node.right_child, key);
        else {
            // otherwise - it is node to be deleted
            if (node.left_child == null || node.right_child == null) {
                //if node has no children or one child
                if (node.left_child != null)
                    // set node to its left child
                    node = node.left_child;
                else
                    // otherwise - to right child
                    node = node.right_child;
            } else {
                // otherwise - node has two children
                MyNode successor = findSuccessor(node); // find the successor
                node.key = successor.key; // set node's key to successor's key
                node.right_child = deleteNode(node.right_child, node.key); // delete node
            }
        }
        // If the tree had only one node then return
        if (node != null)
            node = makeBalanced(node); // Step #2 : make tree balanced
        this.root = node;
        return node; // return root
    }

    /**
     * Method makeBalanced(node) rebalances tree if balance factor of current node is not in range [-1, 1]
     * Since we change only several pointers the method makeBalanced(node) takes T(n) = O(1).
     *
     * @param node - root of the subtree to be balanced
     * @return new root after balancing
     */
    private MyNode makeBalanced(MyNode node) {
        // set new longest path of received node
        node.longest_path = 1 + Math.max(getLongestPath(node.left_child), getLongestPath(node.right_child));
        // Step #2: check whether current node becomes unbalanced, and if it is then fix this issue
        int current_balance = getBalanceFactor(node);
        if (current_balance < -1) {
            // left-right case
            if (getLongestPath(node.left_child.right_child) >= getLongestPath(node.left_child.left_child))
                // bring left-right case to left-left case
                node.left_child = leftRotate(node.left_child);
            // left-left case
            node = rightRotate(node);
        } else if (current_balance > 1) {
            // right-left case
            if (getLongestPath(node.right_child.left_child) >= getLongestPath(node.right_child.right_child))
                // bring right-left case to right-right case
                node.right_child = rightRotate(node.right_child);
            // right-right case
            node = leftRotate(node);
        }
        return node; // return new root
    }

    /**
     * Method findSuccessor(node) returns the node followed after the "node" in inorder traversal of BST
     * Time complexity: worst case is T(n) = O(log n).
     *
     * @param node - node which successor we have to find
     * @return successor of the "node"
     */
    MyNode findSuccessor(MyNode node) {
        MyNode current = node;
        if (current == null)
            return null;
        if (current.right_child != null) {
            // minimal child node in right subtree
            current = node.right_child;
            while (current.left_child != null)
                current = current.left_child;
            return current;
        }
        // successor is the node such that its left child subtree store the received node
        MyNode y = current.parent;
        while (y != null && current.equals(y.right_child)) {
            current = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Method findPredecessor(node) returns the node followed before the "node" in inorder traversal of BST
     * Time complexity: worst case is T(n) = O(log n).
     *
     * @param node - node which predecessor we have to find
     * @return predecessor of the "node"
     */
    MyNode findPredecessor(MyNode node) {
        MyNode current = node;
        if (current == null)
            return null;
        if (current.left_child != null) {
            // maximal child node in a right subtree
            current = node.left_child;
            while (current.right_child != null)
                current = current.right_child;
            return current;
        }
        // predecessor is the node such that its right child subtree store the received node
        MyNode y = current.parent;
        while (y != null && current.equals(y.left_child)) {
            current = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Method rightRotate(subtreeRoot) performs the right rotation of subtree.
     * Since we change only several pointers the method rightRotate(subtreeRoot) takes T(n) = O(1).
     *
     * @param subtreeRoot - the root of subtree to be balanced
     * @return new root of subtree
     */
    private MyNode rightRotate(MyNode subtreeRoot) {

    /*                    SCHEME OF RIGHT ROTATION of subtree rooted with 'subtreeRoot'
             input node
                  ^
                  |
                 [1]                                                      [2]
                 / \                                                     /   \
left child ->  [2]  [3]                                                [4]    [1]
               / \              becomes after right rotation          /  \    / \
             [4] [5]                                                [6] [7] [5] [3]
             / \
           [6] [7]

    */
        MyNode newRoot = subtreeRoot.left_child; // create new root, and assign it to left child node of root
        subtreeRoot.left_child = newRoot.right_child;
        newRoot.right_child = subtreeRoot;
        newRoot.parent = subtreeRoot.parent;
        subtreeRoot.parent = newRoot;
        subtreeRoot.longest_path = 1 + Math.max(getLongestPath(subtreeRoot.right_child), getLongestPath(subtreeRoot.left_child));  // calculate longest path for a received node
        newRoot.longest_path = 1 + Math.max(getLongestPath(newRoot.right_child), getLongestPath(newRoot.left_child)); // calculate longest path for left child of a received node
        return newRoot; // return new root of tree
    }

    /**
     * Method leftRotate(subtreeRoot) performs the left rotation of subtree.
     * Since we change only several pointers the method leftRotate(subtreeRoot) takes T(n) = O(1).
     *
     * @param subtreeRoot - the root of subtree to be balanced
     * @return new root of subtree
     */
    private MyNode leftRotate(MyNode subtreeRoot) {

    /*                    SCHEME OF LEFT ROTATION of subtree rooted with 'subtreeRoot'
    input node
         ^
         |
        [1]                                                      [3]
        / \                                                     /   \
      [2]  [3] <-- right child                               [1]     [5]
           / \          becomes after left rotation          / \     / \
         [4] [5]                                           [2] [4] [6] [7]
             / \
           [6] [7]

    */
        MyNode newRoot = subtreeRoot.right_child; // create new root, and assign it to right child node of root
        subtreeRoot.right_child = newRoot.left_child;
        newRoot.left_child = subtreeRoot;
        newRoot.parent = subtreeRoot.parent;
        subtreeRoot.parent = newRoot;
        subtreeRoot.longest_path = 1 + Math.max(getLongestPath(subtreeRoot.right_child), getLongestPath(subtreeRoot.left_child)); // calculate longest path for a received node
        newRoot.longest_path = 1 + Math.max(getLongestPath(newRoot.right_child), getLongestPath(newRoot.left_child)); // calculate longest path for right child of a received node
        return newRoot; // return new root of tree
    }

}
