import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Assignment {

    private int number;
    private File assignment_dir;
    private File assignment_file;
    private File[] files;

    private int[] tests;

    private File[] inputFiles;
    private File[] outputFiles;
    private File[] resultFiles;

    private Tester tester;
    private ArrayList<Integer> results;
    private ArrayList<Integer> failedTests;
    private boolean passedTests;

    private String OS;

    final String win_base_command = "cmd.exe";
    final String mac_base_command = "/bin/bash";
    final String nix_base_command = "/bin/bash";

    final String win_string_command = "/c";
    final String mac_string_command = "-c";
    final String nix_string_command = "-c";

    private String base_command;
    private String string_command;


    /**
     * Constructor for Assignment
     *
     * @param path Assignment folder path
     */
    public Assignment(String path){
        setOSCommands();

        // Set assignment directory
        setAssignmentDir(new File(path));

        // Get and Set Files in Assignment Directory
        setFiles(this.getFiles(assignment_dir));

        // Get Assignment Number and File
        parseAssignmentFile();
        parseAssignmentNumber();

        parseInputOutputFiles();

        setResultFiles(getResultFiles());
        setTests(getTests());


        setTester(createTester());
    }

    // Setter Methods

    /**
     * Set Assignment Number
     *
     * @param number Assignment number
     */
    public void setAssignmentNumber(int number){
        this.number = number;
    }

    /**
     * Set Assignment Directory
     *
     * @param path Assignment directory path
     */
    public void setAssignmentDir(File path){
        this.assignment_dir = path;
    }

    /**
     * Set Assignment Files
     *
     * @param files Files in Assignment Directory
     */
    public void setFiles(File[] files){
        this.files = files;
    }

    /**
     * Set Assignment's .java File
     *
     * @param filepath Assignment*.java File path
     */
    public void setAssignmentFile(File filepath){
        this.assignment_file = filepath;
    }

    /**
     * Set Tests to run on Assignment
     *
     * @param tests Tests to run
     */
    public void setTests(int[] tests){
        this.tests = tests;
    }

    /**
     * Set Tester for Assignment
     *
     * @param tester Tester for Assignment
     */
    public void setTester(Tester tester){
        this.tester = tester;
    }

    /**
     * Set Input Files
     * @param inputFiles Input Files
     */
    public void setInputFiles(File[] inputFiles){
        this.inputFiles = inputFiles;
    }

    /**
     * Set Output Files
     * @param outputFiles Output Files
     */
    public void setOutputFiles(File[] outputFiles){
        this.outputFiles = outputFiles;
    }

    /**
     * Set Result*.txt Files
     *
     * @param resultFiles Result*.txt Files
     */
    public void setResultFiles(File[] resultFiles){
        this.resultFiles = resultFiles;
    }

    /**
     * Set results array
     *
     * @param results results array
     */
    public void setResults(ArrayList<Integer> results){
        this.results = results;
    }

    /**
     * Set passedTests
     *
     * @param passedTests boolean: passed all tests
     */
    public void setPassedTests(boolean passedTests){
        this.passedTests = passedTests;
    }

    /**
     * Set failedTests array
     *
     * @param failedTests failedTests array
     */
    public void setFailedTests(ArrayList<Integer> failedTests){
        this.failedTests = failedTests;
    }


    /**
     * Set OS of system
     * @param OS OS of system
     */
    public void setOS(String OS){
        this.OS = OS;
    }


    /**
     * Set base command for system
     * @param base_command base command for system
     */
    public void setBaseCommand(String base_command){
        this.base_command = base_command;
    }

    /**
     * Set base command 2 for system
     * @param string_command base command 2 for system
     */
    public void setStringCommand(String string_command){
        this.string_command = string_command;
    }

    /**
     * Set base and string command for system based on OS
     */
    public void setOSCommands(){
        String os = getOS();

        switch (os) {
            case "mac" -> {
                setBaseCommand(mac_base_command);
                setStringCommand(mac_string_command);
            }
            case "nix" -> {
                setBaseCommand(nix_base_command);
                setStringCommand(nix_string_command);
            }
            case "win" -> {
                setBaseCommand(win_base_command);
                setStringCommand(win_string_command);
            }
        }

        System.out.println("base: " + base_command);
        System.out.println("string: " + string_command);
    }


    // Getter Methods

    /**
     * Get Assignment Directory
     * @return Assignment Directory
     */
    public File getAssignmentDir(){
        return this.assignment_dir;
    }

    /**
     * Get Assignment File
     * @return Assignment File
     */
    public File getAssignmentFile(){
        return this.assignment_file;
    }

    /**
     * Get Assignment Number
     * @return Assignment Number
     */
    public int getAssignmentNumber() {
        return this.number;
    }

    /**
     * Get all files in Assignment Directory
     * @return Files in Assignment Directory
     */
    public File[] getFiles(){
        return this.files;
    }

    /**
     * Get input*.txt Files
     * @return input*.txt Files
     */
    public File[] getInputFiles(){
        return this.inputFiles;
    }

    /**
     * Get output*.txt Files
     * @return output*.txt Files
     */
    public File[] getOutputFiles(){
        return this.outputFiles;
    }

    /**
     * Get result*.txt Files
     * @return result*.txt Files
     */
    public File[] getResultFiles(){
        ArrayList<Integer> tests = new ArrayList<>();
        int input_num;
        int output_num;

        for (int i = 1; i < getInputFiles().length+1; i++) {
            input_num = Integer.parseInt(getInputFiles()[i-1].getName().substring(5, 6));

            boolean found = false;

            for (int j = 1; j < getOutputFiles().length+1; j++) {
                output_num = Integer.parseInt(getOutputFiles()[j-1].getName().substring(6, 7));
                if (input_num == output_num) {
                    tests.add(input_num);
                    found = true;
                }
            }

            if (!found) {
                tests.add(input_num);
            }
        }

        int[] test_arr = new int[tests.size()];
        for(int i = 0; i < tests.size(); i++) {
            test_arr[i] = tests.get(i);
        }

        setTests(test_arr);


        // Initialize result files
        File[] result_files = new File[tests.size()];

        int _rf_cntr = 0;
        for (Integer num: tests) {
            File results_file = new File(this.assignment_dir.getPath() + "/results" + num + ".txt");

            result_files[_rf_cntr] = results_file;
            _rf_cntr++;
        }


        this.resultFiles = result_files;
        return result_files;
    }

    /**
     * Get Tests to run on Assignment
     * @return Tests to run
     */
    public int[] getTests(){
        return this.tests;
    }

    /**
     * Get Tester for Assignment
     * @return Tester for Assignment
     */
    private Tester getTester(){
        return this.tester;
    }

    /**
     * Get results array
     * @return results array
     */
    public ArrayList<Integer> getResults(){
        return this.results;
    }

    /**
     * Get passedTests boolean value (true if passed all tests)
     * @return passedTests (boolean)
     */
    public boolean getPassedTests(){
        return this.passedTests;
    }

    /**
     * Get failedTests array
     * @return failedTests array
     */
    public ArrayList<Integer> getFailedTests(){
        return this.failedTests;
    }


    /**
     * Get files in given directory
     * @param path - Directory path
     * @return - Files in given directory
     */
    public File[] getFiles(File path){
        // Get all files in assignment directory
        String[] _files = path.list();

        // Get num of files
        int num_files = 0;
        if (_files != null) {
            num_files = _files.length;
        }

        // Create array of files
        File[] files = new File[num_files];

        // Iterate through files and add to array
        for (int i = 0; i < num_files; i++) {
            files[i] = new File(_files[i]);
        }

        this.files = files;

        return files;
    }


    /**
     * Get the OS of the machine
     * @return OS of the machine: "win" or "mac" or "nix"
     */
    public String getOS(){
        String os;

        if (OS == null) {
            // Get OS
            os = System.getProperty("os.name").toLowerCase();

            // Parse OS
            if (os.contains("win")){
                os = "win";
                setOS(os);
            }

            else if (os.contains("mac")){
                os = "mac";
                setOS(os);
            }

            else if (os.contains("nix") || os.contains("nux") || os.contains("aix")){
                os = "nix";
                setOS(os);
            }

            // Default to win
            else {
                os = "win";
                setOS(os);
            }
        }

        else
        {
            os = this.OS;
        }

        return os;
    }

    /**
     * Get the base command based on OS
     * @return base command based on OS
     */
    public String getBaseCommand(){
        return this.base_command;
    }


    /**
     * Parse Assignment Number
     */
    private void parseAssignmentNumber(){
        String assignment_num = getAssignmentFile().getName();

        // Get assignment number
        assignment_num = assignment_num.split("Assignment")[1];
        assignment_num = assignment_num.split(".java")[0];

        // Convert to int
        int assignment_number = Integer.parseInt(assignment_num);

        // Set assignment number
        setAssignmentNumber(assignment_number);
    }

    private void parseAssignmentFile(){
        File[] files = getFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("Assignment") && file.getName().contains(".java")) {
                    file = new File(getAssignmentDir().toString() + "/" +  file);
                    setAssignmentFile(file);

                    setAssignmentFile(file);
                }
            }
        }
    }

    /**
     * Parse input/output files pair in Assignment directory
     */
    public void parseInputOutputFiles(){
        ArrayList<File> input_files = new ArrayList<>();
        ArrayList<File> output_files = new ArrayList<>();


        // Get all input*.txt files
        for (File file : this.getFiles()) {
            // Split file name
            String[] file_name = file.getName().split("\\.");

            // Check extension to be .txt
            if (file_name[1].equals("txt")) {
                // Check if file name contains "input"
                if (file_name[0].startsWith("input")) {
                    // Check if other characters after input are numbers
                    try
                    {
                        Integer.parseInt(file_name[0].substring(5));
                        input_files.add(file);
                    }
                    catch (NumberFormatException e)
                    {
                        // Do nothing
                    }


                }

                // Check if file name contains "output"
                else if (file_name[0].startsWith("output")) {
                    //Check if other characters after output are numbers
                    try
                    {
                        Integer.parseInt(file_name[0].substring(6));
                        output_files.add(file);
                    }
                    catch (NumberFormatException e)
                    {
                        // Do nothing
                    }
                }
            }
        }

        // Convert to array
        File[] input_files_array = new File[input_files.size()];
        File[] output_files_array = new File[output_files.size()];

        input_files_array = input_files.toArray(input_files_array);
        output_files_array = output_files.toArray(output_files_array);

        // Set input and output files
        setInputFiles(input_files_array);
        setOutputFiles(output_files_array);
    }



    /**
     * Create Tester object with given Assignment parameters
     * @return Tester object for Assignment
     */
    private Tester createTester(){
        // Create Tester

        return new Tester(
                inputFiles,
                outputFiles,
                resultFiles,
                tests,
                new String[] {base_command, string_command}
        );
    }




    /**
     * Compile Assignment*.java file
     */
    private void compile() throws IOException, InterruptedException {
        // Create ProcessBuilder object
        ProcessBuilder pb = new ProcessBuilder();

        // cd to assignment directory
        pb.directory(getAssignmentDir());

        // Build compile command
        String sub_command = "javac \"" + getAssignmentFile().getName() + "\"";

        // Add command to process builder
        System.out.println("Compile command: " + sub_command);
        Process p = pb.command(base_command, string_command, sub_command).start();

        // Wait for process to finish
        p.waitFor();
        System.out.println("Compile finished with code: " + p.exitValue());
    }



    /**
     * Run Tests on Assignment
     * @return results array
     */
    public ArrayList<Integer> runTests() throws IOException, InterruptedException {

        System.out.println("\nTESTING ASSIGNMENT " + getAssignmentNumber() + "\n");

        // Compile
        System.out.println("Compiling " + this.getAssignmentFile().getName());
        this.compile();

        // Get Tester
        Tester AssignmentTester = this.getTester();

        AssignmentTester.setAssignmentDir(getAssignmentDir());
        AssignmentTester.setAssignmentFile(getAssignmentFile());

        // Run tests
        System.out.println("Running tests for Assignment" + getAssignmentNumber());

        ArrayList<Integer> results =  AssignmentTester.runTests();
        this.setResults(results);
        this.setFailedTests(AssignmentTester.getFailedTests());

        // Print results
        printResults();

        return results;
    }

    /**
     * Print results of tests in console
     */
    private void printResults(){
        ArrayList<Integer> results = this.getResults();

        // Initialize variables
        int total_tests = results.size();
        int total_passed = 0;
        int total_failed = 0;

        // Get Stats
        for (Integer result: results) {
            if (result == 1) {
                total_passed++;
            }
            else {
                total_failed++;
            }
        }

        // Set passed tests
        setPassedTests(total_failed == 0);

        // Print Results
        System.out.println("\n\nResults for Assignment " + this.getAssignmentNumber());
        System.out.println("Total Tests: " + total_tests);
        System.out.println("Total Passed: " + total_passed);
        System.out.println("Total Failed: " + total_failed);
    }



    /**
     * Debug Failed Tests
     */
    public void debugTests() throws IOException {
        Tester AssignmentTester = this.getTester();

        System.out.println("Debugging failed tests for Assignment" + getAssignmentNumber());

        AssignmentTester.debug();
    }
}
