/*
// uncomment to use jUnit tests
import org.junit.Test;
import static org.junit.Assert.*;
*/

/**
 * @author Danis Alukaev BS19-02.
 * 2.2 Sorting.
 * Implementation of the Quick Sort algorithm with Lomuto partition scheme
 */

public class Sorting_2_2 {

 /*
    // uncomment to use jUnit tests
    @Test
    public void TestInteger(){
        Integer[] arr = {1, 2, 8, 1, 4, 5, 4};
        int n = arr.length;
        QuickSort<Integer> sort = new QuickSort<>();
        sort.sortingAlgorithm(arr, 0, n - 1);
        Integer result[] = {1, 1, 2, 4, 4, 5, 8};
        assertEquals(result, arr);
    }

    @Test
    public void TestRandom(){
        boolean lessOrEqual = true;
        Integer[] array = new Integer[(int)(Math.random() * 50)];
        for (int i = 0; i < array.length; i++)
            array[i] = ((int)(Math.random() * 101) - 50);
        QuickSort<Integer> sort = new QuickSort<>();
        sort.sortingAlgorithm(array, 0, array.length - 1);
        for(int i = 1; i < array.length; i++)
            if(array[i-1] > array[i]) lessOrEqual = false;
        assertEquals(true, lessOrEqual);
    }

    @Test
    public void TestString(){
        String[] array = {"a", "c", "cd", "e", "b", "d", "aa", "be"};
        int n = array.length;
        QuickSort<String> sort = new QuickSort<>();
        sort.sortingAlgorithm(array, 0, n - 1);
        String result[] = {"a", "aa", "b", "be", "c", "cd", "d", "e"};
        assertEquals(result, array);
    }
 */

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
    int partitionAlgorithm(T[] container, int start, int end) {
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