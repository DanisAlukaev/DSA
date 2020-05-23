import java.util.Scanner;

/**
 * Solution of Task 2.2 (Balanced parentheses)
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/269072/submission/71242128
 * Danis Alukaev BS-19-02
 **/

public class Task2 {

    /**
     * method checkPair(token1, token2) inspect whether to tokens are opening and closing parenthesis of one type, if so it return true, otherwise - false
     *
     * Time Complexity is O(1) - constant
     *
     * @param token1 - first character to compare
     * @param token2 - second character to compare
     * @return true/false - characters are matched/ do not matched
     */
    static boolean checkPair(char token1, char token2) {
        if (token1 == '(' && token2 == ')')
            return true;
        else if (token1 == '[' && token2 == ']')
            return true;
        else if (token1 == '{' && token2 == '}')
            return true;
        else
            return false;
    }


    public static void main(String[] args) {
        MyStack<Character> stack = new MyStack(); // Definition of stack that will store parentheses sequence
        boolean errorOccurred = false; // shows error occurrence
        int lastCharInLine = 0; // stores length of line
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); // retrieve the number of lines
        for (int i = 0; i < n && !errorOccurred; i++) { // loop that is used to read command lines, loop invariant holds of there is no error in previous line
            String line = scanner.nextLine(); // retrieve the command type and parameters
            char[] temp = line.toCharArray(); // convert line (type String) to temp[] (type char array)
            lastCharInLine = temp.length; // stores length of line
            for (int j = 0; j < temp.length; j++) { // loop that treat all elements of array temp
                if (temp[j] == '{' || temp[j] == '[' || temp[j] == '(') // if current element is open parentheses
                    stack.push(temp[j]); // add element to stack
                if (temp[j] == '}' || temp[j] == ']' || temp[j] == ')') { // if current element is closing parentheses
                    if (stack.isEmpty()) { // if stack is empty
                        System.out.println("Error in line " + (i + 1) + ", column " + (j + 1) + ": unexpected closing '" + temp[j] + "'."); // print error message, problem with unexpected closing <symbol>
                        errorOccurred = true; // error occurred
                        break;
                    }
                    else { // otherwise
                        char toPop = stack.pop(); // take the top element of the stack
                        if (!checkPair(toPop, temp[j])) { // if the top element has not same type with the received parentheses
                            errorOccurred = true; // error occurred
                            // print error message, expected <symbol>, but got <symbol>
                            System.out.print("Error in line " + ( i + 1) + ", column " + (j + 1) + ": expected '");
                            if (toPop == '(') System.out.print(")'");
                            else if (toPop == '{') System.out.print("}'");
                            else if (toPop == '[') System.out.print("]'");
                            System.out.println(", but got '" + temp[j] + "'.");
                            break;
                        }
                    }
                }
            }
        }
        scanner.close();
        if (!stack.isEmpty() & !errorOccurred) { // if stack is not empty and error is not occurred
            // print error message, expected <symbol>, but got end of input.
            System.out.print("Error in line " + n + ", column " + lastCharInLine + ": expected '");
            char toPop = stack.pop(); // take the top element of the stack
            if (toPop == '(') System.out.print(")'");
            else if (toPop == '{') System.out.print("}'");
            else if (toPop == '[') System.out.print("]'");
            System.out.println(", but got end of input.");
        } else if (!errorOccurred) // if error is not occurred and stack is empty
            System.out.println("Input is properly balanced.");
    }
}

class MyStack<T> {
    private int top = -1; // address of top element
    private static final int CAPACITY = 1000; // define the capacity of stack
    Object[] elements = new Object[CAPACITY]; // creating array of objects with capacity "CAPACITY"

    /**
     * method push() adds object e to the top of stack
     *
     * Time Complexity is O(1) - constant
     *
     * @param t - element to be added
     **/
    void push(T t) {
        if (top == CAPACITY - 1) // if address of top element e
            throw new IndexOutOfBoundsException("Stack Overflow | Index: " + (CAPACITY - 1) + "Size: " + CAPACITY); //cause an Stack Overflow exception
        else // otherwise
            elements[++top] = t; // add object e to the top of stack
    }

    /**
     * method pop() returns top element of the stack
     *
     * Time Complexity is O(1) - constant
     *
     * @return - return top element
     **/
    T pop() {
        if (top == -1) // if stack is empty
            throw new IndexOutOfBoundsException("Stack Underflow | Index: " + (-1)); //cause an Stack Underflow exception
        else { // otherwise
            T element = (T) elements[top]; // create object of type E and assign the top element to it
            top--; // decrement the address of the top element
            return element; // return top element
        }
    }

    /**
     * method isEmpty() checks if the list is empty
     * and returns true if address of top element of the stack is equal -1
     * false - in other cases
     *
     * Time Complexity is O(1) - constant
     *
     * @return true/false - stack is empty/ is not empty
     **/
    boolean isEmpty() {
        return top == -1;
    }
}
