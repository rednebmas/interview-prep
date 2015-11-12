import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Solves Google Code Jam problem: https://code.google.com/codejam/contest/9214486/dashboard
 */
class CodeJamIOError
{
   public static void main(String[] args)
   {
      // read the file
      List<String> lines;
      try {
         lines = Files.readAllLines(Paths.get("A-small-practice.in"), StandardCharsets.UTF_8);
      }
      catch (Exception e) {
         System.out.println("Exception occured: " + e.getMessage());
         return;
      }
      
      // process
      int numberOfTestCases = Integer.parseInt(lines.get(0));
      String result = "";
      for (int i = 0; i < numberOfTestCases; i++)
      {
         int characters = Integer.parseInt(lines.get(i*2 + 1));
         String text = parseIOString(lines.get(i*2 + 2), characters);
         System.out.println(text);
         
         result += "Case #" + (i+1) + ": " + text + "\n";
      }
      
      // print to file
      try {
         PrintWriter out = new PrintWriter("CodeJamIOErrorOutput.txt");
         out.println(result);
         out.close();
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      }
   }

   private static String parseIOString(String str, int characters)
   {
      String result = "";
      for (int i = 0; i < characters; i++)
      {
         int binaryPower = 128;
         int asciiValue = 0;
         for (int j = 0; j < 8; j++)
         {
            if (str.charAt(i*8+j) == 'I') {
               asciiValue += binaryPower;
            }
            binaryPower = binaryPower / 2;
         }
         char asciiChar = (char)asciiValue;
         result = result + asciiChar;
      }
      return result;
   }
}