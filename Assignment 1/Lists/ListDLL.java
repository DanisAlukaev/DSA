
import java.util.NoSuchElementException;

/**
 * Double-linked List Implementation
 * <p>
 * Danis Alukaev BS-19-02
 **/

public class ListDLL<T> {

    /**
     * Node implementation
     * contains internal variables:
     * > element (type T) - object that will be stored in node
     * > next (type T) - next node in DLL
     * > prev (type T) - previous node in DLL
     * and the constructor MyNode that initializes the newly created object
     **/
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        public MyNode(T element, MyNode next, MyNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Definition of variables:
     * > head (type MyNode) - the first node in DLL
     * > tail (type MyNode) - the last node in DLL
     * > size (type int) - current size of DLL
     **/
    private MyNode head;
    private MyNode tail;
    private int size;

    /**
     * Constructor of ListDLL that initializes the newly created object
     * it assigns the initial properties of object
     **/
    public ListDLL() {
        size = 0;
    }

    /**
     * >method isEmpty() checks if the list is empty
     * and returns true if size is equal 0
     * false - in other cases
     **/
    public boolean isEmpty() {
        return size == 0;
    }

    //TODO test for 0 element

    /**
     * method add(i, e) adds element e at position i
     */
    public T add(int i, T t) {
        if (i < 0)  // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " |  Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        int currentNode = 0; // index of current node
        MyNode temp = head; // we start traversing with the first node that is head
        MyNode Nprev = null; // Nprev will store reference to previous node with respect to current
        MyNode Nnext = null; // Nnext will store current node
        T previousValue = null; // store the previous value
        if (i == 0) { // if we add to first position
            MyNode newFirst = new MyNode(t, head, null); // creating new first node
            if (head != null) { // if head exists
                previousValue = head.element; // save previous value
                head.prev = newFirst; // [newFirst] <- [head]
            }
            head = newFirst; //now "head" is the new first node
            if (tail == null) // if size == 1 and tail is null
                tail = newFirst; // new node become tail
        } else if (i == size - 1) { // if we add to last position
            MyNode changeLast = new MyNode(t, null, tail); // creating new last node
            if (tail != null) { // if tail exists
                previousValue = tail.element; // save previous value
                changeLast.next = tail; // [changeLast] <- [tail]
                changeLast.prev = tail.prev.next; // [tail.prev.next] <- [changeLast]
                tail.prev.next = changeLast; // [tail.prev.next] -> [changeLast]
                tail.prev = changeLast; // [tail] -> [changeLast]
            }
            if (head == null) // if size == 1 and head is null
                head = changeLast; // new node become head
        } else if (i == size) { // if we add to the end of list
            MyNode newN = new MyNode(t, null, tail); // create new last node
            tail.next = newN; // [tail] -> [newN]
            tail = newN; // newN becomes tail
        } else { // otherwise
            while (temp != null) { // while nodes are not run out
                if (currentNode == i) { // if index of current node is equal to i
                    Nprev = temp.prev; // store previous node in Nprev
                    Nnext = temp; // store current node in Nnext (it will be next for new node)
                    previousValue = Nnext.element; // save previous value of node's element
                }
                currentNode++; // increment index of current node
                temp = temp.next; // go to the next node
            }
            MyNode newN = new MyNode(t, Nnext, Nprev); // create new node
            Nprev.next = newN; // [Nprev] <- [newN] [current]
            Nnext.prev = newN; // [Nprev] <- [newN] -> [current]
        }
        size++; // increment size of list
        return previousValue; // return previous value
    }

    /**
     * >addFirst(t) adds element t to the start of the list
     **/
    public T addFirst(T t) {
        MyNode newFirst = new MyNode(t, head, null); // creating new first node
        T previousValue = null; // stores previous value of first node
        if (head != null) { // if head exists
            previousValue = head.element; // save previous value
            head.prev = newFirst; // [newFirst] <- [head]
        }
        head = newFirst; //now "head" is the new first node
        if (tail == null) // if size == 1 and tail is null
            tail = newFirst; // new node become tail
        size++; // increment size of list
        return previousValue; // return previous value of last node
    }

    /**
     * >method addLast(t) adds element t to the end of the list
     */
    public T addLast(T t) {
        MyNode newLast = new MyNode(t, null, tail); // creating new last node
        T previousValue = null; // stores previous value of last node
        if (tail != null) { // if tail exists
            previousValue = tail.element; // save previous value
            tail.next = newLast; // [tail] -> [newLast]
        }
        tail = newLast; //now "tail" is the new last node
        if (head == null) // if size == 1 and head is null
            head = newLast; // new node become head
        size++; // increment size of list
        return previousValue; // return previous value of last node
    }


    //TODO add verification about size

    /**
     * method delete(i) removes element at position i;
     */

    public T delete(int i) {

        T deleted = null; // "deleted" will store the element of the deleted node
        int currentNode = 0; // index of current node
        MyNode temp = head; // we start traversing with the first node that is "head"
        MyNode Nprev = null; // "Nprev" will store the previous node in respect to current
        MyNode Nnext = null; // "Nnext" will store the next node in respect to current
        if (isEmpty())
            throw new NoSuchElementException(); // cause NoSuchElementException if there is no last elements in list
        if (i < 0 || i >= size)  // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " |  Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        if (i == 0) {
            deleted = head.element;  // save the value of first node
            head = head.next; // now "head" is the second node
            if(head != null) head.prev = null; // set reference to previous(first) element to null
            size--; // decrement the size of list
        } else if (i == size - 1) {
            deleted = tail.element; // save the value of last node
            tail = tail.prev; // now "tail" is the penultimate node
            if(tail != null) tail.next = null; // set reference to next (last) element to null
            size--; // decrement the size of list
        } else {
            while (temp != null) { // while nodes are not run out
                if (currentNode == i) { // if index of current node is equal to i
                    Nprev = temp.prev; // store previous node in Nprev
                    deleted = temp.element; // store value of deleted node
                    Nnext = temp.next; // store previous node in Nnext
                }
                currentNode++; // increment index
                temp = temp.next; // go to next node
            }
            Nprev.next = Nnext; // [Nprev] [current] [Nnext] becomes [Nprev] -> [Nnext]
            Nnext.prev = Nprev; // [Nprev] [current] [Nnext] becomes [Nprev] <- [Nnext]
        }
        size--; // decrement the size of list
        return deleted; // return value of deleted node
    }

    /**
     * method delete(e) removes element e if it exists in the list;
     **/
    public boolean delete(T t) {
        int currentNode = 0; // the index of current node
        int toDelete = -1;
        boolean found = false;
        MyNode temp = head; // we start traversing with the first node that is "head"
        while (temp != null) { // while nodes are not run out
            if (temp.element == t) { // if element of current node is equal to t
                toDelete = currentNode;
                found = true;
                break;
            }
            currentNode++; // increment the index
            temp = temp.next; // go to the next node
        }
        if(found) delete(toDelete);
        return found; // return index of current index
    }

    /**
     * >method deleteFirst() removes the first element from the list
     **/
    public T deleteFirst() {
        if (isEmpty())
            throw new NoSuchElementException(); // cause NoSuchElementException if there is no last elements in list
        T value = head.element;  // save the value of first node
        head = head.next; // now "head" is the second node
        if(head != null) head.prev = null; // set reference to previous(first) element to null
        size--; // decrement the size of list
        return (T) value; // return value of deleted first element
    }

    /**
     * >method deleteLast() removes the last element from the list
     **/
    public T deleteLast() {
        if (isEmpty())
            throw new NoSuchElementException(); // cause NoSuchElementException if there is no last elements in list
        T value = tail.element; // save the value of last node
        tail = tail.prev; // now "tail" is the penultimate node
        if(tail != null) tail.next = null; // set reference to next (last) element to null
        size--; // decrement the size of list
        return (T) value; // return value of deleted last element
    }

    /**
     * >method set(int i, T t) replaces element at position i with new element e
     **/
    public T set(int i, T t) {
        if (i < 0 || i >= size) // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        int currentNode = 0; // the index of current node
        MyNode temp = head; // we start traversing with the first node that is "head"
        T previousValue = null; // "previousValue" will store the value of node's element before changing
        while (temp != null) { // while nodes are not run out
            if (currentNode == i) { // if current node has the same index that we need to find
                previousValue = temp.element; // save previous value
                temp.element = t; // change the value of element
                break;
            }
            currentNode++; // increment index of current node
            temp = temp.next; // go to the next node
        }
        return (T) previousValue; // return previous value of element in node
    }

    /**
     * >method get(int i) retrieve element at position i
     **/
    public T get(int i) {
        if (i < 0 || i >= size) // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        int currentNode = 0; // the index of current node
        MyNode temp = head; // we start traversing with the first node that is "head"
        while (temp != null) { // while nodes are not run out
            if (currentNode == i) { // if current node has the same index that we need to find
                break; // stop traversing
            }
            currentNode++; // increment index of current node
            temp = temp.next; // go to the next node
        }
        return (T) temp.element; // return element of DLL node with index i
    }

    /** Assistive methods **/

    /**
     * method toString() returns the string interpretation of the Double-Linked-List
     **/
    public String toString() {
        String str = "";
        str += "[ ";
        MyNode tmp = head;
        while (tmp != null) {
            str += tmp.element + ", ";
            tmp = tmp.next;
        }
        if(str.length() > 2) str = str.substring(0, str.length() - 2);
        str += " ]";
        return str;
    }

    /**
     * method size() returns the size of DLL
     **/
    public int size() {
        return size;
    }
}
