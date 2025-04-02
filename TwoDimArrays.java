// Import libraries
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/** .
* This program uses 2D arrays and generates marks.
* These marks are then uploaded to an output file.
*
* @author  Zak Goneau
* @version 1.0
* @since   2025-03-31
*/

// Creating class
public final class TwoDimArrays {

    /**
     * This is a private constructor used to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private TwoDimArrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {

        // Initialize variables
        String outputStr = "";

        // Initialize student array list
        ArrayList<String> stuList = new ArrayList<String>();

        // Initialize assignment array list
        ArrayList<String> assignList = new ArrayList<String>();

        // Introduce program to user
        System.out.println("This generates  marks for students\n"
                + " using 2D arrays.\n");

        // Pass the path to the student file as a parameter
        File stuFile = new File("./Unit1-08-students.txt");
        Scanner scannerOne = new Scanner(stuFile);

        // Populate array list
        while (scannerOne.hasNextLine()) {

            // Assign current line to variable
            String line = scannerOne.nextLine();

            // Populate array list
            stuList.add(line);
        }

        // Pass the path to the assignment file as a parameter
        File assignFile = new File("./Unit1-08-assignments.txt");
        Scanner scannerTwo = new Scanner(assignFile);

        // Populate array list
        while (scannerTwo.hasNextLine()) {

            // Assign current line to variable
            String line = scannerTwo.nextLine();

            // Populate array list
            assignList.add(line);
        }

        // Close scanner
        scannerOne.close();
        scannerTwo.close();

        // Get size of student file
        int stuSize = stuList.size();

        // Populate array with student names
        String[] stuArray = new String[stuSize];
        for (int counter = 0; counter < stuSize; counter++) {
            stuArray[counter] = stuList.get(counter);
        }

        // Get size of assignment file
        int assignSize = assignList.size();

        // Populate array with assignment names
        String[] assignArray = new String[assignSize];
        for (int counter = 0; counter < assignSize; counter++) {
            assignArray[counter] = assignList.get(counter);
        }

        // Create 2D array for marks
        String[][] marksArray = new String[stuSize + 1][assignSize + 1];

        // Populate 2D array with titles
        marksArray[0][0] = "Names";
        for (int counter = 1; counter < assignSize; counter++) {
            marksArray[0][counter] = "Assign-0" + counter;
        }

        // Initialize result
        String[][] result = new String[stuSize + 1][assignSize + 1];

        // Call function to calculate marks
        result = generateMarks(stuArray, assignArray, marksArray);

        // Add 2D array to output string
        for (int row = 0; row < stuSize; row++) {
            for (int column = 0; column < assignSize; column++) {
                outputStr += result[row][column] + ",";
            }
            outputStr += "\n";
        }

        // Write output string to marks.csv file
        FileWriter myWriter = new FileWriter("marks.csv");
        myWriter.write(outputStr);
        myWriter.close();
    }

    /**
    * This is the main method.
    *
    * @param stuArray
    * @param assignArray
    * @param marksArray
    * @return marksArray
    */

    // Define function to generate marks
    public static String[][] generateMarks(final String[] stuArray,
            final String[] assignArray, final String[][] marksArray) {

        // Create random number generator
        Random rand = new Random();

        // Initialize random number variable
        String randNum = "";

        // Populate every row in the 2D array
        for (int row = 1; row < stuArray.length + 1; row++) {
            // Populate first column with student names
            marksArray[row][0] = stuArray[row - 1];

            // Populate every column in the row with random numbers
            for (int column = 1; column < assignArray.length + 1; column++) {
                // Generate random number between 0 and 100
                randNum = Double.toString(rand.nextGaussian() * 10 + 75);

                // Populate 2D Array with numbers
                marksArray[row][column] = randNum;
            }
        }

        // return result
        return marksArray;
    }
}

