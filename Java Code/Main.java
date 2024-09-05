import java.util.ArrayList;
import java.util.List;



public class Main {
  public static void main(String args[]) {
        List<Point> inputPoints = new ArrayList (0);
        //Randomly generatees 100 points to perform the function on.
        for (int i = 0; i < 100; i++) {
            Point template = new Point();
            double xValue = Math.random() * 100;
            double yValue = Math.random() * 100;
            template.x = xValue;
            template.y = yValue;
            inputPoints.add(template);
        }

        Point origin = new Point();
        origin.x = 0;
        origin.y = 0;
        int numberOfPoints = 5;
        Point[] answer = FindNClosestPoints(inputPoints, origin, numberOfPoints);
        
        int num = 2;
    }

    //Main written point, takes in a number as the last parameter and finds that many close points to the origin.
    static Point[] FindNClosestPoints(List<Point> pointArray, Point originPoint, int n) {
        Point[] answerArray = new Point[n];
        //Populates the array with the first few points.
        for (int a = 0; a < n; a++) {
            answerArray[a] = pointArray.get(a);
        }
        double largestDistance;
        //Goes through the rest of the array of points and swaps out the largest (right most slot) value in answerArray if it is larger than the distance to the new point.
        for (int a = n + 1; a < pointArray.size(); a++) {
            answerArray = bubbleSortPoints(answerArray, originPoint);
            largestDistance = answerArray[n - 1].distanceTo(originPoint);
            if (pointArray.get(a).distanceTo(originPoint) < largestDistance) {
                answerArray[n - 1] = pointArray.get(a);
            }
        }
        return answerArray;
    }

    //Point class meant to emulate the point object specifier from the problem specification.
    static class Point {
        double x;
        double y;
        double distanceTo(Point toCheckPoint) {
            double yChange = this.y - toCheckPoint.y;
            double xChange = this.x - toCheckPoint.x;
            return Math.pow(Math.pow(yChange,2) + Math.pow(xChange, 2),0.5);
        }
    }

    //Bubble sorts the points inputted by their distance to the 2nd point parameter with larger distances coming last (right most and largest position).
    static Point[] bubbleSortPoints(Point[] nLengthList, Point originPoint) {
        Point bubbled;
        int position;
        int endOfList = nLengthList.length - 1;
        boolean sorted = false;
        int passes = 0;
        while (!sorted) {
            sorted = true;
            position = 0;
            while (position != endOfList - passes) {
                position++;
                double newPositionDistance = nLengthList[position].distanceTo(originPoint);
                double bubbledDistance = nLengthList[position - 1].distanceTo(originPoint);
                if (newPositionDistance < bubbledDistance) {
                    bubbled = nLengthList[position - 1];
                    nLengthList[position - 1] = nLengthList[position];
                    nLengthList[position] = bubbled;
                    sorted = false;
                }
            }
            passes++;
        }
        return nLengthList;
    }
}

