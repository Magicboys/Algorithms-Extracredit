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

        //Implements the brute force method for finding the closet pair
        long startTimeBruteForce = System.nanoTime();
        Point[] BruteForcePair = BruteForceClosestPair(points);
        long finishTimeBruteForce = System.nanoTime();

        System.out.println("Closest point via brute force method:");
        System.out.println("(" + BruteForcePair[0].getX() + "," + BruteForcePair[0].getY() + ") & " + "(" + BruteForcePair[1].getX() + "," + BruteForcePair[1].getY() + ")");
        System.out.println("Brute force method took a total of: " + (finishTimeBruteForce-startTimeBruteForce) + " nanoseconds");


        //TODO: pass the list of points into the find closest point algorithm method
        //Sort input by x coordinate via mergesort - has a time complexity of O(nlogn)
        ArrayList<Point> sortedList = MergeSort(points, true);

        //Finds the closest pair via the closet pair divide and conquer algorithm implementation - has a time complexity of O(nlogn)
        long startTimeEfficentAlgorithm = System.nanoTime();
        Point[] EfficentAlgorithmPair = EfficientClosestPair(sortedList);
        long finishTimeEfficentAlgorithm = System.nanoTime();

        System.out.println("Efficient closest pair of points algorithm result:");
        System.out.println("(" + EfficentAlgorithmPair[0].getX() + "," + EfficentAlgorithmPair[0].getY() + ") & " + "(" + EfficentAlgorithmPair[1].getX() + "," + EfficentAlgorithmPair[1].getY() + ")");
        System.out.println("Efficient closest pair of points algorithm method took a total of: " + (finishTimeEfficentAlgorithm-startTimeEfficentAlgorithm) + " nanoseconds");
    }

    //Purpose: This method takes in an arraylist of points and then runs an efficient closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] EfficientClosestPair(ArrayList<Point> points) {
        Point[] result = new Point[2];

        //Base case
        if (points.size() <= 3) {
            /*
            System.out.println("debug");
            for(int i = 0; i < points.size(); i++) {
                System.out.println(points.get(i).toString());
            }*/
            result[0] = points.get(0);
            result[1] = points.get(1);
            return result;
        }

        //We can just divide by the midpoint since the points are already presorted by x cordinate
        int midIndex = points.size()/2;

        ArrayList<Point> leftHalf = new ArrayList<Point>();
        for(int i = 0; i < midIndex; i++) {
            leftHalf.add(points.get(i));
        }

        ArrayList<Point> rightHalf = new ArrayList<Point>();
        for(int i = midIndex; i < points.size(); i++) {
            rightHalf.add(points.get(i));
        }

        Point[] closestPairLeftHalf = EfficientClosestPair(leftHalf);
        Point[] closestPairRightHalf = EfficientClosestPair(rightHalf);

        //Calculate the minimum distance between the two closest points on each half
        double min = -1;
        if (CalculateDistance(closestPairLeftHalf[0],closestPairLeftHalf[1]) < CalculateDistance(closestPairRightHalf[0], closestPairRightHalf[1])) {
            min = CalculateDistance(closestPairLeftHalf[0],closestPairLeftHalf[1]);
        } else {
            min = CalculateDistance(closestPairRightHalf[0], closestPairRightHalf[1]);
        }

        //Calculate the region around the line of separation
        double lineOfSeparation = points.get(midIndex).getX();
        double separationLeftBound = lineOfSeparation - min;
        double separationRightBound = lineOfSeparation + min;

        //Delete points in the list not within the bound
        for (int i = 0; i < points.size(); i++) {
            if (!((points.get(i).getX() > separationLeftBound) && (points.get(i).getX() < separationRightBound))) {
                points.remove(i);
            }
        }

        //Now sort remaining points by y value
        ArrayList<Point> yValueSortedPoints = MergeSort(points, false);

        //Scan closest 11 points to find the min and check if the min across the line of separation
        double localMin = 1000000;
        for (int i = 0; i < yValueSortedPoints.size(); i++) {

            //Check if the upper bounds hits the last point
            //Since we begin by checking the closest 11 positions from the very bottom, we only need to check the closest 11 positions above the current point
            int upperBounds = -1;
            if(i+11 < yValueSortedPoints.size()) {
                upperBounds = i+11;
            } else {
                upperBounds = yValueSortedPoints.size();
            }

            for (int j = i+1; j < upperBounds; j++) {
                double localDistance = CalculateDistance(yValueSortedPoints.get(i), yValueSortedPoints.get(j));
                if (localDistance < localMin) {
                    localMin = localDistance;
                    result[0] = yValueSortedPoints.get(i);
                    result[1] = yValueSortedPoints.get(j);
                }
            }
        }

        return result;
    }

    //Purpose: This method takes in an arraylist of points and then runs a brute force closest pair algorithm to get the closest pair out of a set of points
    //Returns: provides the two points which are the closest to each other out of the whole set
    private static Point[] BruteForceClosestPair(ArrayList<Point> points) {
        Point result[] = new Point[2];

        double minDistance = 10000000;

        //Brute force method of looping through all point combinations to find the min value
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    double localDistance = CalculateDistance(points.get(i), points.get(j));
                    if (localDistance < minDistance) {
                        minDistance = localDistance;
                        result[0] = points.get(i);
                        result[1] = points.get(j);
                    }
                }
            }
        }
        System.out.println("Min distance found was: " + minDistance + " unit(s)");
        return result;
    }

    //Purpose: calculates the distance between two points
    //Returns: gives a double value which is the calculated distance between the two points
    private static double CalculateDistance(Point one, Point two) {

        double xOne = one.getX();
        double xTwo= two.getX();
        double yOne = one.getY();
        double yTwo = two.getY();

        double xDistance = Math.pow(xOne-xTwo,2.0);
        double yDistance = Math.pow(yOne-yTwo,2.0);

        return Math.sqrt((xDistance + yDistance));

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
