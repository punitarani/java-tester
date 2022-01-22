# Java-Tester
## Java Testing Tool

Run Java tests from the command line.

Compare the test results with the expected outputs.

## Usage

Download **test.jar** and paste it into a folder containing the assignment files to test.

Make sure the folder contains the following files:
* Assignment*.java
* input*.txt
* output*.txt
* test.jar


### Run Tests

Run the following commands in the terminal:

Change Directory to the folder containing the assignment files:

    C:\Users\user> cd </assignmnet-directory>


Run the test.jar file:

    C:\Users\user\Assigments\1> java -jar test.jar


## Tutorial
1. Download the **test.jar**

Assignment directory should look like this:

    </assignment-directory>
    ├── Assignment1.java
    ├── input1.txt
    ├── input2.txt
    ├── output1.txt
    ├── output2.txt
    └── test.jar

2. Open **Terminal** and change directory to the assignment directory.
(Can also be done by opening terminal from the assignment directory)

![assignment-dir-cmd](https://github.com/punitarani/java-tester/blob/main/img/AssignmentDir.png?raw=true)

3. Run the test.jar file:


    java -jar test.jar


![test-jar-cmd](https://github.com/punitarani/java-tester/blob/main/img/TerminalTest.png?raw=true)


The results from each test will be saved as result*.txt files in the assignment directory.
