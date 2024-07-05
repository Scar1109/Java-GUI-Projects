/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysearch;
/**
 *
 * @author Tigger
 */
public class BinarySearch {
    static int numSearches;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String [] mySortedArray = {"black", "blue", "brown", "green", "orange", "peach", "purple", "red", "salmon", "white"};
        System.out.println ("Binary search: " + binarySearch(mySortedArray, 0, mySortedArray.length-1, "green"));
        System.out.println ("Number of searches: " + numSearches);
        numSearches = 0;

        System.out.println ("Binary search: " + binarySearch(mySortedArray, 0, mySortedArray.length-1, "orange"));
        System.out.println ("Number of searches: " + numSearches);
        numSearches = 0;

        System.out.println ("Binary search: " + binarySearch(mySortedArray, 0, mySortedArray.length-1, "pink"));
        System.out.println ("Number of searches: " + numSearches);
        numSearches = 0;

        System.out.println ("Binary search: " + binarySearch(mySortedArray, 0, mySortedArray.length-1, "salmon"));
        System.out.println ("Number of searches: " + numSearches);
        numSearches = 0;
    }

    /** precondition: Array A must be sorted in alphabetical order
     * Parameters: A - sorted array
     * left - leftmost index in array
     * right - rightmost index in array
     * V - item to be looked for in the array
     */
     public static Boolean binarySearch(String [] A, int left, int right, String V){
         int middle;
         numSearches ++;
         if (left > right) {
             return false;
         }

         middle = (left + right)/2;
         int compare = V.compareTo(A[middle]);
         if (compare == 0) {
             return true;
         }
         if (compare < 0) {
             return binarySearch(A, left, middle-1, V);
         } else {
             return binarySearch(A, middle + 1, right, V);
         }
     }
}
