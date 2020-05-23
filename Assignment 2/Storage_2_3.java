/*
// uncomment to use jUnit tests
import org.junit.Test;
import static org.junit.Assert.*;
*/

import java.util.ArrayList;

/**
 * @author Danis Alukaev BS19-02.
 * 2.3 Storage.
 * Implementation of the AVL tree.
 * Implemented test units, to use it - uncomment import block, tests, and tree generators.
 */

public class Storage_2_3 {

    /*
    // uncomment to use jUnit tests
    @Test
    public void insertTree1() {
        AVLTree<String, Integer> tree = createTree1();
        String result = "[(#1, 5, 4), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#7, 9, 3), (#3, 8, 1), (#6, 10, 2), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteLeftLeafTree1_1() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(2);
        String result = "[(#1, 5, 4), (#2, 3, 2), (#5, 4, 1), (#7, 9, 3), (#3, 8, 1), (#6, 10, 2), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteLeftLeafTree1_2() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(9);
        String result = "[(#1, 5, 3), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#7, 10, 2), (#3, 8, 1), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteRightLeafTree1_1() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(4);
        String result = "[(#1, 5, 4), (#2, 3, 2), (#4, 2, 1), (#7, 9, 3), (#3, 8, 1), (#6, 10, 2), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }
    @Test
    public void deleteRightLeafTree1_2() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(16);
        String result = "[(#1, 5, 3), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#7, 9, 2), (#3, 8, 1), (#6, 10, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteLeftChildTree1() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(3);
        String result = "[(#1, 5, 4), (#2, 4, 2), (#4, 2, 1), (#7, 9, 3), (#3, 8, 1), (#6, 10, 2), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteRightChildTree1() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(8);
        String result = "[(#1, 5, 3), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#6, 10, 2), (#7, 9, 1), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteNodeWithTwoChildrenTree1_1() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(10);
        String result = "[(#1, 5, 3), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#7, 9, 2), (#3, 8, 1), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteRoot() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(5);
        String result = "[(#1, 8, 3), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#6, 10, 2), (#7, 9, 1), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteNotExisted() {
        AVLTree<String, Integer> tree = createTree1();
        tree.deleteNode(0);
        String result = "[(#1, 5, 4), (#2, 3, 2), (#4, 2, 1), (#5, 4, 1), (#7, 9, 3), (#3, 8, 1), (#6, 10, 2), (#8, 16, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void findSuccessorTree1() {
        AVLTree<String, Integer> tree = createTree1();
        String result = "(#3, 8, 1)";
        assertEquals(result, tree.findSuccessor(tree.root).toString());
    }

    @Test
    public void findPredecessorTree1() {
        AVLTree<String, Integer> tree = createTree1();
        String result = "(#5, 4, 1)";
        assertEquals(result, tree.findPredecessor(tree.root).toString());
    }

    @Test
    public void findRootNodeTree1() {
        AVLTree<String, Integer> tree = createTree1();
        String result = "(#1, 5, 4)";
        assertEquals(result, tree.findNode(tree.root, 5).toString());
    }

    @Test
    public void insertTree2() {
        AVLTree<String, Integer> tree = createTree2();
        String result = "[(#3, 4, 4), (#5, 2, 3), (#4, 1, 2), (#6, 0, 1), (#9, 3, 1), (#2, 7, 3), (#8, 6, 2), (#10, 5, 1), (#1, 9, 2), (#7, 8, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteLeftLeftTree2() {
        AVLTree<String, Integer> tree = createTree2();
        tree.deleteNode(1);
        String result = "[(#3, 4, 4), (#5, 2, 2), (#6, 0, 1), (#9, 3, 1), (#2, 7, 3), (#8, 6, 2), (#10, 5, 1), (#1, 9, 2), (#7, 8, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteLeftRightTree2() {
        AVLTree<String, Integer> tree = createTree2();
        tree.deleteNode(3);
        String result = "[(#3, 4, 4), (#4, 1, 2), (#6, 0, 1), (#5, 2, 1), (#2, 7, 3), (#8, 6, 2), (#10, 5, 1), (#1, 9, 2), (#7, 8, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteRightRightTree2() {
        AVLTree<String, Integer> tree = createTree2();
        tree.deleteNode(10);
        String result = "[(#3, 4, 4), (#5, 2, 3), (#4, 1, 2), (#6, 0, 1), (#9, 3, 1), (#2, 7, 3), (#8, 6, 2), (#10, 5, 1), (#1, 9, 2), (#7, 8, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }

    @Test
    public void deleteRightLeftTree2() {
        AVLTree<String, Integer> tree = createTree2();
        tree.deleteNode(6);
        String result = "[(#3, 4, 4), (#5, 2, 3), (#4, 1, 2), (#6, 0, 1), (#9, 3, 1), (#2, 7, 3), (#10, 5, 1), (#1, 9, 2), (#7, 8, 1)]";
        assertEquals(result, tree.traversalPreorderList().toString());
    }
    */

    /**
     * uncomment to use jUnit tests
     * T(n) = O(1)
     * @return the created tree
     */
    /*
    private AVLTree<String, Integer> createTree1() {
        AVLTree<String, Integer> tree = new AVLTree<>();
        tree.insert("#1", 5);
        tree.insert("#2", 3);
        tree.insert("#3", 8);
        tree.insert("#4", 2);
        tree.insert("#5", 4);
        tree.insert("#6", 10);
        tree.insert("#7", 9);
        tree.insert("#8", 16);
        return tree;
    }
    */


    /**
     * uncomment to use jUnit tests
     * T(n) = O(1)
     * @return the created tree
     */
    /*
    private AVLTree<String, Integer> createTree2() {
        AVLTree<String, Integer> tree = new AVLTree<>();
        tree.insert("#1", 9);
        tree.insert("#2", 7);
        tree.insert("#3", 4);
        tree.insert("#4", 1);
        tree.insert("#5", 2);
        tree.insert("#6", 0);
        tree.insert("#7", 8);
        tree.insert("#8", 6);
        tree.insert("#9", 3);
        tree.insert("#10", 5);
        return tree;
    }
    */

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