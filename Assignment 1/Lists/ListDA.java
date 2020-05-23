
/**
 * Dynamic Array List Implementation
 * <p>
 * Danis Alukaev BS-19-02
 **/

public class ListDA<T> {

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

        for(int i = 0; i < size; i++){ // treat all elements
            if(!found && elements[i] == t){ // if we have not found element with value t and current element has this value
                found = true; // we have found such element
            }
            else{
                newElements[index] = elements[i]; // // copy elements in temporary array
                index++; // increment index
            }
        }
        elements = newElements; // move temporary array to main array "elements"
        size --; // decrement size
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
        String temp = "";
        temp += "[ ";
        for (int i = 0; i < elements.length - 1; i++) {
            temp += elements[i] + ",";
        }
        temp += elements[this.elements.length - 1];
        temp += " ]\n";
        return temp;
    }
}
