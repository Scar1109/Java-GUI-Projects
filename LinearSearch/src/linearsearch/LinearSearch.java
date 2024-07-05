/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearsearch;

/**
 *
 * @author Tigger
 */
public class LinearSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String [] myArray = {"red", "green", "yellow", "purple", "orange", "brown"};
       String [] mySortedArray = {"blue", "brown", "green", "orange", "purple", "red", "yellow"};

       System.out.println("Sequential search: green " + linearSearch(myArray, "green"));
       System.out.println("Sequential search: orange " + linearSearch(myArray, "orange"));
       System.out.println("Sequential search: pink "+ linearSearch(myArray, "pink"));

       System.out.println("Sorted sequential search: green " + sortedLinearSearch(mySortedArray, "green"));
       System.out.println("Sorted sequential search: orange " + sortedLinearSearch(mySortedArray, "orange"));
       System.out.println("Sorted sequential search: pink " + sortedLinearSearch(mySortedArray, "pink"));

    }
static public Boolean linearSearch(String [] A, String B) {
    for (int k=0; k<A.length; k++) {
        if (A[k].equals(B)) {
            return true;
        }
    }
    return false;
}

static public Boolean sortedLinearSearch (String [] A, String B) {
    for (int k=0; k<A.length; k++) {
        int compare = A[k].compareTo(B);
        if (compare == 0) {
            return true;
        }
        if (compare > 0) {
            return false;
        }
    }
    return false;
}
}
