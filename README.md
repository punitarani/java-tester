# Java-Tester
### Java Testing and Debugging Tool

## Features:
- Compile and run tests automatically with one command.
- Automatically finds input and output files.
- Line-by-line error highlighting.

## Tutorial
### 1. Download the **test.jar**

**Download Link:** https://github.com/punitarani/java-tester/raw/main/test.jar

**Note:** The test.jar file might be blocked from downloading by your browser.

You can **click on the three dots** and select **Keep** to download the file.

![test-jar-cmd](https://github.com/punitarani/java-tester/blob/main/img/DownloadTestJar.png?raw=true)



Assignment directory should look like this:

    </assignment-directory>
    ├── Assignment1.java
    ├── input1.txt
    ├── input2.txt
    ├── output1.txt
    ├── output2.txt
    └── test.jar



### 2. Open **Terminal** and change directory to the assignment directory.
(Can also be done by opening terminal from the assignment directory)


    C:\Users\user> cd </assignmnet-directory>

![assignment-dir-cmd](https://github.com/punitarani/java-tester/blob/main/img/AssignmentDir.png?raw=true)



### 3. Run the test.jar file:


    java -jar test.jar


![test-jar-cmd](https://github.com/punitarani/java-tester/blob/main/img/TerminalTest.png?raw=true)


The results from each test will be saved as result*.txt files in the assignment directory.



- The test results will be displayed in the terminal.

- If there are any failed tests, you will be prompted if you want to debug them.

- It will find the line number of the error and highlights the errors.