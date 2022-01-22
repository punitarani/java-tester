
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Initialize scanner
        Scanner scnr = new Scanner(System.in);


        // Get current working directory
        String currentDir = System.getProperty("user.dir");

        // Initialize Assignment
        Assignment A = new Assignment(currentDir);


        // Run Tests
        ArrayList<Integer> results = A.runTests();


        // Print Results
        if (results.size() == 0) {
            System.out.println("No test cases found");
        }

        else if (!A.getPassedTests()){
            System.out.println("\nDo you want to debug the failed test cases? (y/n)");
            String dbg_input = scnr.nextLine().toLowerCase();

            if (dbg_input.equals("y")) {
                A.debugTests();
            }
            else{
                System.out.println("\nNot Debugging");
                System.out.println("Good Job!\n");
            }

        }

        else {
            System.out.println("\nGood Job! All test cases passed\n");
        }

        // Close Scanner
        scnr.close();
    }
}
