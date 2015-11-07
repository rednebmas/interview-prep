// for printing arrays
import java.util.Arrays;
// for testing
import java.util.Random;

class Sorting
{
   private static boolean showSteps = false;
   
   public static void main(String[] args)
   {
      test();

      int[] toSort = { 4, 5, 3, 1, 2};
      quickSort(toSort);
      
      System.out.print(Arrays.toString(toSort));
   }
   
   /**
    * Tests sorting and picking median of three
    */
   private static void test()
   {
      boolean previousShowSteps = showSteps;
      showSteps = false;
      
      System.out.println("-- Tests --");
      
      // tests all permutations of 3 nums
      System.out.println("- pivot picks median -");
      
      int[] arr = {1,2,3};
      int index = pickPivot(arr, 0, 2);
      System.out.println(index == 1);
      
      int[] arr2 = {3,1,2};
      index = pickPivot(arr2, 0, 2); 
      System.out.println(index == 2);

      int[] arr3 = {2,1,3};
      index = pickPivot(arr3, 0, 2); 
      System.out.println(index == 0);
      
      int[] arr4 = {1,3,2};
      index = pickPivot(arr4, 0, 2); 
      System.out.println(index == 2);
      
      int[] arr5 = {2,3,1};
      index = pickPivot(arr5, 0, 2); 
      System.out.println(index == 0);
      
      int[] arr6 = {3,2,1};
      index = pickPivot(arr6, 0, 2); 
      System.out.println(index == 1);
      
      System.out.println("- sorting tests -");
      System.out.println("no output means tests passed");
      
      // test random arrays with lengths from 2 to 1000
      for (int i = 2; i <= 1000; i++)
      {
         int[] randomArray = randomArrayOfLength(i);
         int[] randomArraySortedByJava = randomArray.clone();
         int[] randomArraySortedByMe = randomArraySortedByJava.clone();
         
         Arrays.sort(randomArraySortedByJava);
         quickSort(randomArraySortedByMe);
         
         boolean result = Arrays.equals(randomArraySortedByJava, randomArraySortedByMe);
         if (!result)
         {
            System.out.println("failed quickSort: " + Arrays.toString(randomArray));
         }
      }
      
      System.out.println("-- End Tests --\n");
      
      showSteps = previousShowSteps;
   }
   
   /**
    * Creates a random array of numbers.
    *
    * @returns array of the specified length with random numbers from 1 to 100
    */
   private static int[] randomArrayOfLength(int length)
   {
      int[] randArray = new int[length];
      Random random = new Random();
      
      for (int i = 0; i < length; i++)
      {
         randArray[i] = random.nextInt(100);
      }
      
      return randArray;
   }
   
   /**
    * Prints a subset of an array
    * 
    * @param lo: inclusive index, from
    * @param hi: inclusive index, to
    */
   private static void printArrayInRange(int[] arr, int lo, int hi)
   {
      System.out.print("[");
      for (int i = lo; i < hi; i++)
      {
         System.out.print(arr[i] + ", ");
      }
      System.out.print(arr[hi]  + "]\n");
   }
   
   /** Quick Sort **/
   
   /**
    * Sorts an integer array.
    */
   public static void quickSort(int[] arr)
   {
      if (arr.length < 1)
      {
         return;
      }
      
      quickSortRecursive(arr, 0, arr.length-1);  
   }
   
   /**
    * Performs the actual sorting.
    * Based on Lecture 20 slides from UW CSE 373 Winter 2014
    */
   private static void quickSortRecursive(int[] arr, int lo, int hi)
   {
      if (hi - lo < 1)
      {
         return;
      }
      
      // get pivot
      int pivotIndex = Sorting.pickPivot(arr, lo, hi);
      int pivot = arr[pivotIndex];
      
      if (showSteps)
      {
         System.out.println("lo: " + lo + ", hi: " + hi);
         System.out.print("State before iter: ");
         printArrayInRange(arr, lo, hi);
         System.out.println("pivot: " + pivot);
      }
      
      // swap pivot to with num at lo
      arr[pivotIndex] = arr[lo];
      arr[lo] = pivot;
      
      if (showSteps)
      {
         System.out.print("swaped pivot state: ");
         printArrayInRange(arr, lo, hi);
      }
      
      // now partition in place
      int bottomPtr = lo + 1;
      int topPtr = hi;
      while (topPtr > bottomPtr)
      {
         if (arr[topPtr] > pivot)
         {
            topPtr--;
         }
         else if (arr[bottomPtr] < pivot)
         {
            bottomPtr++;
         }
         else
         {
            int tmp = arr[bottomPtr];
            arr[bottomPtr] = arr[topPtr];
            arr[topPtr] = tmp;
            
            topPtr--;
            
            if (showSteps)
            {
               System.out.print("swap, new state: ");
               printArrayInRange(arr, lo, hi);
            }
         }
      }
      
      // swap pivot back
      // handles case here where pivot could be the smallest element of the array
      if (arr[lo] > arr[lo+1])
      {
         arr[lo] = arr[bottomPtr];
         arr[bottomPtr] = pivot;
      }
      
      if (showSteps)
      {
         System.out.print("State after iter: ");
         printArrayInRange(arr, lo, hi);
         System.out.println("----");
      }
      
      // now sort top and bottom half the same way
      quickSortRecursive(arr, lo, bottomPtr-1);
      quickSortRecursive(arr, bottomPtr+1, hi);
   }
   
   /**
    * Picks the pivot using the median of the first, last, and middle value
    *
    * @returns the index of the pivot
    */
   private static int pickPivot(int[] arr, int lo, int hi)
   {
      // doesn't matter anyways
      if (hi - lo < 2)
      {
         return lo;
      }
      
      int firstIndex = lo;
      int lastIndex = hi;
      int middleIndex = (hi+lo)/2;
      
      int first = arr[firstIndex];
      int last = arr[lastIndex];
      int middle = arr[middleIndex];
      
      if (showSteps) {
         System.out.println("first: " + first + ", middle: " + middle + ", last: " + last);
      }
      
      // pick the median
      if (last > first)
      {
         if (middle > last)
         {
            return lastIndex;
         }
         else if (middle < first)
         {
            return firstIndex;
         }
         else
         {
            return middleIndex;
         }    
      }
      else // first > last
      {
         if (middle > first)
         {
            return firstIndex;
         }
         else if (middle < last)
         {
            return lastIndex;
         }
         else
         {
            return middleIndex;
         }   
      }
      /**/
   }
}