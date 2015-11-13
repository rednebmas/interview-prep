import java.util.*;
class Permutation
{
   public static void main(String[] args)
   {
      String sam = "samb";
      printPerms(sam);
   }
   
   private static void printPerms(String str)
   {
      printPermsRecursive(str, "");
   }
   
   private static void printPermsRecursive(String left, String result)
   {
      if (left.length() == 0) {
         System.out.println(result);
         return;
      }
      
      for (int i = 0; i < left.length(); i++) {
         String theResult = result + left.charAt(i);
         
         String newLeft = removeCharAt(left, i);
         printPermsRecursive(newLeft, theResult);
      }   
   }
   
   private static String removeCharAt(String str, int index)
   {
      String leftHalf = str.substring(0,index);
      if (index == str.length() - 1)
      {
         return leftHalf;
      }
      else
      {
         return leftHalf + str.substring(index+1, str.length());
      }   
   }
}