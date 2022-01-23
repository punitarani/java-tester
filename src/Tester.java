import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Tester {
    private File[] input_files;
    private File[] output_files;
    private File[] result_files;
    private int[] tests;

    private File assignment_dir;
    private File assignment_file;

    private ArrayList<Integer> results;
    private ArrayList<Integer> failedTests;

    private String os_command;

    
    /**
     * Constructor
     * @param inputs input files
     * @param outputs output files
     * @param results results files
     * @param tests tests to run
     */
    public Tester(File[] inputs,
                  File[] outputs,
                  File[] results,
                  int[] tests,
                  String os_command){
        setInputFiles(inputs);
        setOutputFiles(outputs);
        setResultFiles(results);
        setTests(tests);
        setOSCommand(os_command);
    }



    /**
     * Get the input files
     * @return input files
     */
    public File[] getInputFiles(){
        return this.input_files;
    }

    /**
     * Get the output files
     * @return output files
     */
    public File[] getOutputFiles(){
        return this.output_files;
    }

    /**
     * Get the result files
     * @return result files
     */
    public File[] getResultFiles(){
        return this.result_files;
    }

    /**
     * Get the tests to run
     * @return tests to run
     */
    public int[] getTests(){
        return this.tests;
    }

    
    /**
     * Get the assignment directory
     * @return assignment directory
     */
    public File getAssignmentDir(){
        return this.assignment_dir;
    }

    /**
     * Get the assignment file
     * @return assignment file
     */
    public File getAssignmentFile(){
        return this.assignment_file;
    }

    /**
     * Get the test results
     * @return test results
     */
    public ArrayList<Integer> getResults(){
        return this.results;
    }

    /**
     * Get the failed tests
     * @return failed tests
     */
    public ArrayList<Integer> getFailedTests(){
        return this.failedTests;
    }

    /**
     * Get the os_command based on OS
     * @return OS base command based on OS
     */
    public String getOSCommand(){
        return this.os_command;
    }


    /**
     * Set the input files
     * @param input_files input files
     */
    public void setInputFiles(File[] input_files){
        this.input_files = input_files;
    }

    /**
     * Set the output files
     * @param output_files output files
     */
    public void setOutputFiles(File[] output_files){
        this.output_files = output_files;
    }

    /**
     * Set the result files
     * @param result_files result files
     */
    public void setResultFiles(File[] result_files){
        this.result_files = result_files;
    }

    /**
     * Set the tests to run
     * @param tests tests to run
     */
    public void setTests(int[] tests){
        this.tests = tests;
    }

    
    /**
     * Set the assignment directory
     * @param assignment_dir assignment directory
     */
    public void setAssignmentDir(File assignment_dir){
        this.assignment_dir = assignment_dir;
    }

    /**
     * Set the assignment file
     * @param assignment_file assignment file
     */
    public void setAssignmentFile(File assignment_file){
        this.assignment_file = assignment_file;
    }

    /**
     * Set the test results
     * @param results test results
     */
    public void setResults(ArrayList<Integer> results){
        this.results = results;
    }

    /**
     * Set the failed tests
     * @param failedTests failed tests
     */
    public void setFailedTests(ArrayList<Integer> failedTests){
        this.failedTests = failedTests;
    }

    /**
     * Set OSCommand based on OS
     */
    public void setOSCommand(String os_command){
        this.os_command = os_command;
    }


    /**
     * Run the tests
     * @return Test results
     */
    public ArrayList<Integer> runTests() throws IOException, InterruptedException {
        input_files = this.getInputFiles();
        output_files = this.getOutputFiles();
        result_files = this.getResultFiles();
        tests = this.getTests();

        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Integer> failedTests = new ArrayList<>();

        for (int _test: tests){
            System.out.println("\nRunning test " + _test);
            boolean test = runTest(_test);

            String result;
            if (test){
                result = "PASSED";
                results.add(1);
            }
            else{
                result = "FAILED";
                results.add(0);
                failedTests.add(_test);
            }

            System.out.println("Test " + _test + ": " + result);
        }

        this.setResults(results);
        this.setFailedTests(failedTests);

        return results;
    }

    /**
     * Run a single test
     * @param test test to run
     * @return test result
     */
    private boolean runTest(int test) throws IOException, InterruptedException {
        boolean passed;

        String inputFile = "input" + test + ".txt";
        String outputFile = "output" + test + ".txt";
        String resultFile = "result" + test + ".txt";

        // Create command string to run
        String command = os_command;
        command += "cd \"" + this.getAssignmentDir().getAbsolutePath() + "\"";
        command += " && java " + getAssignmentFile().getName().replace(".java", "")
                + " < " + inputFile + " > " + resultFile;

        // Run command
        Process t = Runtime.getRuntime().exec(command);

        // Wait for process to finish
        t.waitFor();

        // Compare files
        passed = compareFiles(new File (getAssignmentDir().getAbsolutePath() + "\\" + resultFile),
                              new File(getAssignmentDir().getAbsolutePath() + "\\" + outputFile));

        return passed;
    }

    /**
     * Compare two files
     * @param actual actual file
     * @param expected expected file
     * @return true if files are equal, false otherwise
     */
    private boolean compareFiles(File actual, File expected) throws IOException {
        // Initialize Scanners
        Scanner expected_scanner = new Scanner(expected);
        Scanner actual_scanner = new Scanner(actual);

        // Compare files
        while (expected_scanner.hasNextLine() && actual_scanner.hasNextLine()){
            String expected_line = expected_scanner.nextLine();
            String actual_line = actual_scanner.nextLine();

            if (!expected_line.equals(actual_line)){
                return false;
            }
        }

        // Close Scanners
        // expected_scanner.close();
        // actual_scanner.close();

        return !(expected_scanner.hasNextLine() && actual_scanner.hasNextLine());
    }

    
    /**
     * Debug Failed Tests
     */
    public void debug() throws IOException {
        output_files = this.getOutputFiles();
        result_files = this.getResultFiles();
        tests = this.getTests();

        for (int _test: getFailedTests()){
            System.out.println("\nDebugging FAILED Test " + _test);
            debugTest(_test);
        }
    }

    
    /**
     * Debug a single test
     * @param test test to debug
     */
    private void debugTest(int test) throws IOException {
        File outputFile = new File(getAssignmentDir().getAbsolutePath() + "\\" + "output" + test + ".txt");
        File resultFile = new File(getAssignmentDir().getAbsolutePath() + "\\" + "result" + test + ".txt");

        // Create Scanners
        Scanner expected_scanner = new Scanner(outputFile);
        Scanner actual_scanner = new Scanner(resultFile);

        // Initialize variables
        int line = 0;

        // Compare files
        while (expected_scanner.hasNextLine() && actual_scanner.hasNextLine()){
            line++;

            String expected_line = expected_scanner.nextLine();
            String actual_line = actual_scanner.nextLine();

            if (!expected_line.equals(actual_line)){
                System.out.println("\nError on line " + line);
                System.out.println("Expected : " + expected_line);
                System.out.println("Actual   : " + actual_line);

                StringBuilder error= new StringBuilder();

                // Convert to char array
                char[] expected_chars = expected_line.toCharArray();
                char[] actual_chars = actual_line.toCharArray();

                // Find differences
                for (int i = 0; i < expected_chars.length; i++){
                    try
                    {
                        if (expected_chars[i] != actual_chars[i]){
                            error.append("^");
                        }
                        else {
                            error.append(" ");
                        }
                    }

                    catch (ArrayIndexOutOfBoundsException e){
                        error.append("^");
                    }
                }

                // Print error string
                System.out.println("Error    : " + error);
            }
        }


        // Check if there are more lines in the files
        if (expected_scanner.hasNextLine() || actual_scanner.hasNextLine()){
            line ++;
            System.out.println("\nError on line " + line);

            // Print missing or extra lines error message
            if (expected_scanner.hasNextLine()){
                System.out.println("Missing Lines:");
            }
            else {
                System.out.println("Extra Lines:");
            }

            // Print the missing or extra lines
            while (expected_scanner.hasNextLine()){

                System.out.println("Expected : " + expected_scanner.nextLine());
            }

            while (actual_scanner.hasNextLine()){
                System.out.println("Actual   : " + actual_scanner.nextLine());
            }
        }

        System.out.println("\n");

        // Close Scanners
        expected_scanner.close();
        actual_scanner.close();
    }
}
