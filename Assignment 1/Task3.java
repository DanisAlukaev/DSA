
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Solution of Task 2.3 (PhoneBook)
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/269072/submission/71241876
 * Danis Alukaev BS-19-02
 **/

public class Task3 {

    /**
     * Map initialization
     * <p>
     * as a key, Map accepts the <Contact name> of type String
     * and to store all <Phone numbers> of a particular person Map contains ArrayList of type String
     **/
    static Map<String, ArrayList<String>> PhoneBook = new HashMap<>();

    /**
     * method ADD(name, phone) puts received phone number "phone" in phone list of "name" in "PhoneBook"
     * <p>
     * Time Complexity is O(N) - linear
     *
     * @param name - <Contact name>
     * @param phone - <Phone number>
     */
    static void ADD(String name, String phone) {
        boolean inArray = false; // is <Phone Number> in list of phones of <Contact name>
        if (!PhoneBook.containsKey(name)) // if in the "PhoneBook" there is no element with a key <Contact Name>
            PhoneBook.put(name, new ArrayList<>()); // create element with key <Contact Name>
        for (int i = 0; i < PhoneBook.get(name).size(); i++) // loop that treats all elements of ArrayList that is element of the "PhoneBook" with key <Contact Name>
            if (PhoneBook.get(name).get(i).equals(phone)) // if <Phone Number> is in ArrayList, that is value of the "PhoneBook" with the key <Contact Name>
                inArray = true; // Verified: <Phone Number> is in list of <Contact Name> phones
        if (!inArray)
            PhoneBook.get(name).add(phone); // otherwise, put <Phone Number> in ArrayList, that is value of the "PhoneBook" with the key <Contact Name>
    }

    /**
     * method DELETE(name, phone) remove "name" from "PhoneBook"
     * <p>
     * Time Complexity is O(N) - linear
     *
     * @param name - <Contact name>
     */
    static void DELETE(String name) {
        PhoneBook.remove(name); // remove element with the key <Contact Name>
    }

    /**
     * method DELETE(name, phone) remove phone number "phone" of "name" from "PhoneBook"
     * <p>
     * Time Complexity is O(N) - linear
     *
     * @param name - <Contact name>
     * @param phone - <Phone number>
     */
    static void DELETE(String name, String phone) {
        ArrayList<String> temporary = new ArrayList<String>(); // ArrayList "temporary" will store all phone numbers of <Contact Name> except received <Phone Number>
        if (PhoneBook.get(name) != null) { // if "PhoneBook" contains element with key <Contact name>
            for (int i = 0; i < PhoneBook.get(name).size(); i++) // loop that treats all elements of ArrayList, that is value of the "PhoneBook" with the key <Contact name>
                if (!PhoneBook.get(name).get(i).equals(phone)) // if current phone number is not equal to received <Phone Number>
                    temporary.add(PhoneBook.get(name).get(i)); // add in ArrayList "temporary" current phone number
            PhoneBook.put(name, temporary); // change ArrayList with key <Contact name> to ArrayList "temporary"
        }
    }

    /**
     * method FIND(name) searches for all phone numbers of <Contact name>
     * <p>
     * Time Complexity is O(N) - linear
     *
     * @param name - <Contact name>
     **/
    static void FIND(String name) {
        ArrayList<String> temporary = PhoneBook.get(name); // copying of all phone numbers of <Contact name> in ArrayList "temporary"
        if (temporary == null || temporary.size() == 0) // if "PhoneBook" does not contain elements or there are no elements with key <Contact Name>
            System.out.println("No contact info found for " + name); // output message signalizing that there are no contact info for <Contact Name>
        else { // otherwise
            System.out.print("Found " + temporary.size() + " phone numbers for " + name + ":"); // print message with format: Found <Number of phone numbers> phone numbers for <Contact name>:
            for (int i = 0; i < temporary.size(); i++) // loop that treat all elements of array "temporary"
                System.out.print(" " + temporary.get(i)); // print phone numbers of <Contact Name>
            System.out.println(); // "\n"
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); // retrieve the number of lines
        for (int i = 0; i < n; i++) { // loop that is used to read command lines
            String line = scanner.nextLine(); // retrieve the command type and parameters
            String[] tokens = line.split(" "); // convert input command in string array "tokens"
            // first word of an input string is the type of command (either ADD, DELETE or FIND)

            // checking whether received command is ADD, DELETE or FIND:
            if (tokens[0].equals("ADD")) {
                /**
                 *  if it is ADD the format of the input is following:
                 *  ADD <Contact name>,<Phone number>
                 *  so, we need to split the last word (that contains both part of name and phone) in array "tokens", then restore the full name of contact and call function ADD
                 * */
                String[] remaining = tokens[tokens.length - 1].split(","); // array "remaining" stores last part of <Contact name> and <Phone number>
                String name = ""; // string "name" stores the concatenation of <Contact name> parts
                for (int j = 1; j < tokens.length - 1; j++) // loop that treat all elements of array "tokens" that contain parts of <Contact name>
                    name += tokens[j] + " "; // concatenating all parts of <Contact name>
                name += remaining[0]; // very last part of <Contact name> is stored in array "remaining"
                ADD(name, remaining[1]); // call of function ADD, that takes <Contact name> and <Phone number> as parameters
            } else if (tokens[0].equals("DELETE")) {
                /**
                 *  if it is DELETE the format of the input is following:
                 *  either DELETE <Contact name> or DELETE <Contact name>,<Phone number>
                 *  so, we need to split the last word (that contains both part of name and phone) in array "tokens", then restore the full name of contact
                 *  finally, we have to determine the format of arguments and call appropriate function DELETE
                 * */
                String[] remaining = tokens[tokens.length - 1].split(","); // array "remaining" stores last part of <Contact name> and possible <Phone number>
                String name = ""; // string "name" store the concatenation of <Contact name> parts
                for (int j = 1; j < tokens.length - 1; j++) // loop that treat all elements of array "tokens" that contain parts of <Contact name>
                    name += tokens[j] + " "; // concatenating all parts of <Contact name>
                name += remaining[0]; // very last part of <Contact name> is stored in array "remaining"
                if (remaining.length == 1) // if array "remaining" does not contain <Phone number> its length will be one element that is exactly last part of <Contact name>
                    DELETE(name); // call of function DELETE, that takes <Contact name> as a parameter
                else // otherwise, array "remaining" contains <Phone number>
                    DELETE(name, remaining[1]); // call of function DELETE, that takes <Contact name> and <Phone number> as parameters
            }
            if (tokens[0].equals("FIND")) {
                /**
                 *  if it is FIND the format of the input is following:
                 *  FIND <Contact name>
                 *  so, we need to concatenate all parts of name and call function FIND
                 * */
                String name = tokens[1]; // string "name" store the concatenation of <Contact name> parts
                for (int j = 2; j < tokens.length; j++) // loop that treat all elements of array "tokens" that contain parts of <Contact name>
                    name += " " + tokens[j]; // concatenating all parts of <Contact name>
                FIND(name); // call of function FIND, that takes <Contact name> as a parameter
            }
        }
        scanner.close();
    }
}
