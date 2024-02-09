
/* Bailey Garrett
 * module 4 assignment 1 
 * 2/9/24 
 * file comparator checker
 */
import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class ComparatorChecker {
    public static void main(String[] args) {

        String fileName = args[0];
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {

            // int for line to read
            int lineNumber = 0;
            // stack for keeping track of opening operands
            Stack<Character> stack = new Stack<>();

            // loop through each line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                // loop through each character of the line
                for (char c : line.toCharArray()) {
                    // push char to stack if its an opening operand
                    if (c == '(' || c == '{' || c == '[') {
                        stack.push(c);
                        // check for closing operand and pop top from stack
                    } else if (c == ')' || c == '}' || c == ']') {
                        char top = stack.pop();
                        // check if operands are a match if not print error
                        if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                            System.out.println("Comperator error on line " + lineNumber);
                            return;
                        }
                    }
                }
            }
            // check if stack is empty
            if (!stack.isEmpty()) {
                System.out.println("A comparator was never closed");
            } else {
                System.out.println("No errors");
            }

        }
        // Catch any exceptions that occur during file processing
        catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
