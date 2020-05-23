
import java.util.NoSuchElementException;

public class Lists {

    /*
    public static void main(String[] args) {
        ListDA<Integer> listDA = new ListDA<>();
        ListDLL<Integer> listDLL = new ListDLL<>();
        System.out.println("---------------RESULT--------------------");
        System.out.println(listDA.toString());
        System.out.println(listDLL.toString());
    }
    */
}
/**
 * Double-linked List Implementation
 * <p>
 * Danis Alukaev BS-19-02
 **/
class ListDLL<T>{
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
        if (i < 0 || i >= size + 1)  // if there is no index i in list
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
            if (head != null) head.prev = null; // set reference to previous(first) element to null
        } else if (i == size - 1) {
            deleted = tail.element; // save the value of last node
            tail = tail.prev; // now "tail" is the penultimate node
            if (tail != null) tail.next = null; // set reference to next (last) element to null
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
        if (found) delete(toDelete);
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
        if (head != null) head.prev = null; // set reference to previous(first) element to null
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
        if (tail != null) tail.next = null; // set reference to next (last) element to null
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
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        MyNode tmp = head;
        while (tmp.next != null) {
            str.append(tmp.element + ", ");
            tmp = tmp.next;
        }
        str.append(tmp.element + " ]");
        return str.toString();
    }

    /**
     * method size() returns the size of DLL
     **/
    public int size() {
        return size;
    }
}



/**
 * Dynamic Array List Implementation
 * <p>
 * Danis Alukaev BS-19-02
 **/
class ListDA<T>{
    private int size = 0; // current size of DLL
    private static final int CAPACITY = 1; // the initial capacity of array
    private Object[] elements; // array where the elements of type T will be stored

    /**
     * Constructor of ListDA that initializes the newly created object
     * it assigns the initial properties of object
     **/
    public ListDA() {
        elements = new Object[CAPACITY];
    }

    /**
     * method isEmpty() checks if the list is empty
     * and returns true if size is equal 0
     * false - in other cases
     * <p>
     * Time Complexity is O(1) - constant
     **/
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * method add(int i, T t) adds provided element t at position i
     * firstly, it checks the potential overflow and whether the variable i is valid
     * then, it shifts all elements by one slot and assigns element with index i to t
     * finally, it returns the previous value of the first element
     * <p>
     * Time Complexity is O(n) - linear
     **/
    public void add(int i, T t) {
        if (size == elements.length) // checks potential overflow
            ensureCapacity(); // increase capacity
        size++; // increment size of list
        if (i < 0 || i >= size)  // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " |  Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        for (int j = size - 1; j > i; j--) { // treat elements of list with indexes in range [i+1, n]
            elements[j] = elements[j - 1]; // element shifts to right
        }
        elements[i] = t; // store element at index i
    }

    /**
     * method addFirst(T t) adds element t to the start of the list
     * firstly, it checks the potential overflow
     * then it shifts all elements by one slot and assigns the first element of array to t
     * finally, it returns the previous value of the first element
     * <p>
     * Time Complexity is O(n) - linear
     **/
    public void addFirst(T t) {
        if (size == elements.length) // checks potential overflow
            ensureCapacity(); // increase capacity
        for (int j = size; j > 0; j--) // treat elements of list with indexes in range [1, n]
            elements[j] = elements[j - 1]; // element shifts to right
        size++; // increment size
        elements[0] = t; // store element at index 0
    }

    /**
     * method addLast(T t) adds element t to the end of the list
     * firstly, it checks the potential overflow
     * then it assigns the value of last element to t
     * finally, it returns the previous value of the last element
     * <p>
     * Time Complexity is O(n)- linear
     **/
    public void addLast(T t) {
        if (size == elements.length) // checks potential overflow
            ensureCapacity(); // increase capacity
        elements[size] = t; //store element at last position
        size++; // increment size
    }

    // TODO

    /**
     * method delete(T t) remove all entries of element with value t from the list
     * it creates new array with the same length, then it copies all elements except the element with value t
     * <p>
     * Time Complexity is O(n)- linear
     */
    public boolean delete(T t) {
        boolean found = false; // is element with value t found
        Object[] newElements = new Object[elements.length]; // creating new array with size of "elements"
        int index = 0; // stores current index

        for (int i = 0; i < size; i++) { // treat all elements
            if (!found && elements[i] == t) { // if we have not found element with value t and current element has this value
                found = true; // we have found such element
            } else {
                newElements[index] = elements[i]; // // copy elements in temporary array
                index++; // increment index
            }
        }
        elements = newElements; // move temporary array to main array "elements"
        if (found)
            size--; // decrement size
        return found; // return Is element with value t found?
    }

    /**
     * method delete(int i) remove the last element from the list
     * it checks whether the variable i is valid
     * then, creates new array with the same length, then it copies all elements except the element with index i
     * after that it copies the remaining elements with one position left shifting
     * finally, it returns the value of deleted element
     * <p>
     * Time Complexity is O(n)- linear
     */
    public T delete(int i) {
        if (i < 0 || i >= size) // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        Object[] newElements = new Object[elements.length]; // creating new array with size of "elements"
        T deletedValue = null; // save value of element that will be deleted
        if (size == 0) { // if list is empty
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + 0); // cause IndexOutOfBoundException
        } else if (size == 1) { // if size is 1
            deletedValue = (T) elements[i]; // save value of deleted element
            elements = newElements; // move temporary array to main array "elements"
        } else {
            deletedValue = (T) elements[i]; // save value of deleted element
            for (int j = 0; j < i; j++) // treat all values with index less than i
                newElements[j] = elements[j]; // copy elements in temporary array
            for (int j = i; j < size - 1; j++) // treat all values with index greater than i
                newElements[j] = elements[j + 1]; // copy elements in temporary array
            size--; // decrement size
            elements = newElements; // move temporary array to main array "elements"
        }
        return deletedValue; // return value of element that will be deleted
    }

    /**
     * method deleteFirst() remove the first element from the list
     * it creates new array with the same length, then it copies all elements except the first
     * and return the value of deleted element
     * <p>
     * Time Complexity is O(n)- linear
     */
    public T deleteFirst() {
        Object[] newElements = new Object[elements.length]; // creating new array with size of "elements"
        T deletedValue = null; // save value of element that will be deleted
        if (size == 0) { // if list is empty
            throw new IndexOutOfBoundsException("Treated index: " + 0 + " | Size of the Dynamic Array " + 0);
        } else if (size == 1) { // if size is 1
            deletedValue = (T) elements[0]; // save value of deleted element
            elements = newElements; // move temporary array to main array "elements"
        } else {
            deletedValue = (T) elements[0]; // save value of deleted element
            for (int i = 0; i < size - 1; i++) { // treat all values with index greater than 1
                newElements[i] = elements[i + 1]; // copy elements in temporary array
            }
            elements = newElements; // move temporary array to main array "elements"
        }
        size--; // decrement size
        return deletedValue; // return value of element that will be deleted
    }

    /**
     * method deleteLast() remove the last element from the list
     * it creates new array with the same length, then it copies all elements except the last
     * and return the value of deleted element
     * <p>
     * Time Complexity is O(n)- linear
     */
    public T deleteLast() {
        Object[] newElements = new Object[elements.length]; // creating new array with size of "elements"
        T deletedValue; //
        if (size == 0) { // if list is empty
            throw new IndexOutOfBoundsException("Treated index: " + 0 + " | Size of the Dynamic Array " + 0);
        } else if (size == 1) { // if size is 1
            deletedValue = (T) elements[elements.length - 1]; // save value of deleted element
            elements = newElements; // move temporary array to main array "elements"
        } else {
            deletedValue = (T) elements[elements.length - 1]; // save value of deleted element
            for (int i = 0; i < size - 1; i++) // treat all elements except the last one
                newElements[i] = elements[i]; // copy elements in temporary array
            elements = newElements; // move temporary array to main array "elements"
        }
        size--; // decrement size
        return deletedValue; // return previous value of last element
    }

    /**
     * method set(int i, T t) replaces element at position i with new element e
     * it changes the value of element stored in an array with index i
     * and returns an object that was stored in array with index i before value changing
     * <p>
     * Time Complexity is O(1) - constant
     **/
    public T set(int i, T t) {
        if (i < 0 || i >= size) // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        T previousValue = (T) elements[i]; // store previous value of element with index i
        elements[i] = t; // set element with index i to t
        return previousValue; // return previous value of element with index i
    }

    /**
     * method get(int i) retrieve element at position i
     * it returns an object stored in an array with index i
     * <p>
     * Time Complexity is O(1) - constant
     **/
    @SuppressWarnings("unchecked")
    public T get(int i) {
        if (i < 0 || i >= size) // if there is no index i in list
            throw new IndexOutOfBoundsException("Treated index: " + i + " | Size of the Dynamic Array " + size()); // cause IndexOutOfBoundException
        return (T) elements[i]; // return element with index i
    }

    /** Assistive methods **/


    /**
     * method size() returns the size of the Dynamic Array
     * <p>
     * Time Complexity is O(1) - constant
     **/
    public int size() {
        return size;
    }

    /**
     * method ensureCapacity() double the capacity of the Dynamic Array
     * it creates new array with increased length, then copies all elements of current array in this array
     * and then assigns current array to new one
     * <p>
     * Time Complexity is O(n)- linear
     **/
    private void ensureCapacity() {
        int newSize = elements.length * 2;
        Object[] newElements = new Object[newSize];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * method toString() returns the string interpretation of the Dynamic Array
     * <p>
     * Time Complexity is O(n)- linear
     **/
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i = 0; i < size - 1; i++) {
            str.append(elements[i] + ", ");
        }
        if(!isEmpty()) str.append(elements[size - 1]);
        str.append(" ]");
        return str.toString();
    }
}