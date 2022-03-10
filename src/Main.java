import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    //TODO:take in a list of points based off user input

        //File reading
        System.out.println("Enter name of data file (Main testfile is called \"test1.txt\": ");

        //Declare variables
        String fileName = "";
        ArrayList<Point> points = null;

        //Loop until the user gives a good file to read
        while(true) {
            Scanner userInput = new Scanner(System.in);
            fileName = userInput.nextLine();

            if (!fileName.contains(".txt")) {
                System.out.println("Please input a .txt file.");
            } else {
                System.out.println("Reading data from: " + fileName);

                //Returns null if unable to read file and returns the list of points if read is successful
                points = FileRead(fileName);

                if (points != null) {
                    break;
                } else {
                    System.out.println("The file you inputted is incorrectly formatted, please input a correctly formatted file:");
                }
            }
        }

        //TODO: pass the list of points into the find closest point algorithm method

        //TODO: Return output
    }

    //Purpose: Handles file reading by taking in a filename and then parsing the file to get a list of points
    //Returns: This function returns null if the read is unsuccessful and it will return a list of points if it is successful
    private static ArrayList<Point> FileRead(String fileName) {
        ArrayList<Point> points = new ArrayList<Point>();



        return points;
    }

    //Purpose: This method takes in an arraylist of points and then runs an efficient closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] EfficientClosestPair(ArrayList<Point> points) {
        Point[] result = null;

        return result;
    }

    //Purpose: This method takes in an arraylist of points and then runs a bute force closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] BruteForceClosestPair(ArrayList<Point> points) {
        Point result[] = null;

        return result;
    }



}
