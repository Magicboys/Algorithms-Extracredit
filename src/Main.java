//Authored by Tyler Watson
//Algorithms Extracredit Assignment
//3-10-2022


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Closest point answer - distance of one
//(45,46)
//(45,47)

public class Main {

    public static void main(String[] args) {
        //File reading
        System.out.println("Enter name of data file (Test file is called \"input.txt\"): ");

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

        //Sort input by x coordinate via mergesort
        ArrayList<Point> sortedList = MergeSort(points, true);

        /*
        //Debug for mergesort
        for(int i = 0; i < sortedList.size(); i++) {
            Point temp = sortedList.get(i);
            System.out.println(i + " X:" + temp.getX() + " Y:" + temp.getY());
        }*/

        //TODO: pass the list of points into the find closest point algorithm method

        //TODO: time brute force and efficient method and output the comparison
    }

    //Purpose: This method takes in an arraylist of points and then runs an efficient closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] EfficientClosestPair(ArrayList<Point> points) {
        Point[] result = null;

        return result;
    }

    //Purpose: This method takes in an arraylist of points and then runs a brute force closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] BruteForceClosestPair(ArrayList<Point> points) {
        Point result[] = null;

        return result;
    }

    //Purpose: runs a merge sort algorithm
    //Parameters: takes in a list of unsorted points. If boolean xy is true then we sort via x coordinate and if boolean xy is false then we sort via y
    //Returns: a sorted list
    private static ArrayList<Point> MergeSort(ArrayList<Point> points, boolean xy) {
        //Base case
        if (points.size() <= 1) {
            return points;
        }

        //Initialize a list which we will add sorted values to
        ArrayList<Point> sortedList = new ArrayList<>();

        //Find midpoint
        int midPoint = points.size()/2;

        //Get a list of points to the right and left of the midpoint
        ArrayList<Point> leftOfMidPoint = new ArrayList<Point>();
        for (int i = 0; i < midPoint; i++) {
            leftOfMidPoint.add(points.get(i));
        }

        ArrayList<Point> rightOfMidPoint = new ArrayList<Point>();
        for (int i = midPoint; i < points.size(); i++) {
            rightOfMidPoint.add(points.get(i));
        }



        //Subset of point list to the right and left of midpoint
        ArrayList<Point> leftHalf = MergeSort(leftOfMidPoint, xy);
        ArrayList<Point> rightHalf = MergeSort(rightOfMidPoint, xy);

        //Now merge the two sides of the list
        int leftPointer = 0;
        int rightPointer = 0;
        for(int i = 0; i < (leftHalf.size()+rightHalf.size()); i++) {

            if (leftPointer != -1 && rightPointer != -1) {
                if (xy) {
                    //Sort via x coordinate
                    if (leftHalf.get(leftPointer).getX() < rightHalf.get(rightPointer).getX()) {
                        sortedList.add(leftHalf.get(leftPointer));
                        leftPointer++;

                        //Left side of the list should now be exhausted if this statement is true
                        if (leftPointer > leftHalf.size()-1) {
                            leftPointer = -1;
                        }
                    } else {
                        sortedList.add(rightHalf.get(rightPointer));
                        rightPointer++;

                        //right side of the list should now be exhausted if this statement is true
                        if (rightPointer > rightHalf.size()-1) {
                            rightPointer = -1;
                        }
                    }
                } else {
                    //Sort via y coordinate
                    if (leftHalf.get(leftPointer).getY() < rightHalf.get(rightPointer).getY()) {
                        sortedList.add(leftHalf.get(leftPointer));
                        leftPointer++;

                        //Left side of the list should now be exhausted if this statement is true
                        if (leftPointer > leftHalf.size()-1) {
                            leftPointer = -1;
                        }
                    } else {
                        sortedList.add(rightHalf.get(rightPointer));
                        rightPointer++;

                        //right side of the list should now be exhausted if this statement is true
                        if (rightPointer > rightHalf.size()-1) {
                            rightPointer = -1;
                        }
                    }
                }

            } else if (leftPointer != -1) {
                sortedList.add(leftHalf.get(leftPointer));
                leftPointer++;

                //Left side of the list should now be exhausted if this statement is true
                if (leftPointer > leftHalf.size()-1) {
                    leftPointer = -1;
                }
            } else {
                sortedList.add(rightHalf.get(rightPointer));
                rightPointer++;

                //right side of the list should now be exhausted if this statement is true
                if (rightPointer > rightHalf.size()-1) {
                    rightPointer = -1;
                }
            }
        }

        //Return sorted list
        return sortedList;
    }


    //Purpose: Handles file reading by taking in a filename and then parsing the file to get a list of points
    //Returns: This function returns null if the read is unsuccessful and it will return a list of points if it is successful
    private static ArrayList<Point> FileRead(String fileName) {
        ArrayList<Point> points = new ArrayList<Point>();

        //First get all the data from the file
        try {
            File file = new File(fileName);
            Scanner readText = new Scanner(file);

            //Read file line by line
            while (readText.hasNextLine()) {
                String rawData = readText.nextLine();

                //Parse the line and get the x and y value
                String data  = rawData.substring(1,rawData.length()-1);
                String[] getXY = data.split(",");
                int x = -1;
                int y = -1;
                try {
                    x = Integer.parseInt(getXY[0]);
                    y = Integer.parseInt(getXY[1]);
                } catch (Exception e) {
                    System.out.println("Error while reading file, incorrect formatting!");
                }

                //Add the point to the array list
                Point local = new Point(x,y);
                points.add(local);
            }
            //By this point we have gone through the whole file
            readText.close();
        } catch (FileNotFoundException error) {
            System.out.println("Unable to find the file!");
            return null;
        }


        System.out.println("Found " + points.size() + " points in "  + fileName + "!");

        return points;
    }

    //TODO Remove later, just a function to quickly get 100 random points
    private static void printPoints(){
        Point[] points = new Point[100];

        for (int i = 0; i <100; i++) {
            long x = Math.round(Math.random()*100)+1;
            long y = Math.round(Math.random()*100)+1;
            Point local = new Point((int)x,(int)y);
            points[i] = local;
            System.out.println("(" + x + "," + y + ")");
        }

        boolean noDupes = true;
        for(int i = 0; i < 100; i++) {
            Point temp = points[i];
            for(int j = 0; j < 100; j++){
                if (j != i) {
                    if (points[j].getX() == temp.getX() && points[j].getY() == temp.getY()) {
                        noDupes = false;
                    }
                }
            }
        }

        if (noDupes == false) {
            System.out.println("Dupes found!");
        } else {
            System.out.println("No dupes found!");
        }
    }



}
