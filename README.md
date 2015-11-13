# Interview Prep
My implementation of standard computer science data structures and some solutions to fun coding problems.

# Java Cheat Sheet

## Built in data structures
`import java.util.*`

### Hash map
```java
HashMap<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "hey");
map.get(1); // hey
map.get(2); // null
map.size();
   
// iterate over keys
for (Integer key : map.keySet()) {
}
   
// iterate over values
for (String value : map.values()) {
}
   
// keys & values + ability remove during loop
Iterator it = map.entrySet().iterator();
while (it.hasNext()) {
   Map.Entry pair = (Map.Entry)it.next();
   System.out.println(pair.getKey() + " = " + pair.getValue());
   it.remove(); // avoids a ConcurrentModificationException
}
```
### Hash set

   	HashSet<String> set = new HashSet<String>();
   	hs.add("Test");
   	set.add("Test");
   	int size = set.size();
   	System.out.println(size); // size is 1!
   
   	boolean removed = set.remove("not removed");
   	boolean isEmpty = set.isEmpty();
   	boolean contains = set.contains("Test");
   	
    Iterator iter = set.iterator();
    while (iter.hasNext()) {
        System.out.println(iter.next());
    }
      
### Dynamically sized array

    List<String> list = new ArrayList<String>();
    list.add(null); // yup!
    list.add(0, "add element at index");
    list.set(1, "not null anymore");
    list.size();
    list.get(0);
    list.remove(0);

## Files
### Read

	try {
		for (String line : Files.readAllLines(Paths.get("filepath.txt"))) {
    		// readAllLines returns a type List<String>
		}
	} catch (Exception ex) {
		System.out.println("Exception message: " + ex.getMessage());
	}


### Write
	try (PrintStream out = new PrintStream(new FileOutputStream("filename.txt"))) {
    	out.print(string);
    	out.close();
	} catch (Exception ex) {
		System.out.println("Exception message: " + ex.getMessage());
	}
	
## Variable type conversions
### String to integer
	int result = Integer.parseInt("1234");
### Number type to string
	String.valueOf(..);
### String to double
	Double.parseDouble(String s);
### String to float
	Float.parseFloat(String s);
## Syntax of basic OOP
### Constants
	private static final int TABLE_SIZE = 37;
### Class with main
	class Test {
		public static void main(String[] args) {
			// code
		}
	}
	
## Strings

### Length
	stringVar.length();
	
### Equality
	String equalityTest = "Kiteboarding";
	boolean isEqual = "Kiteboarding" == equalityTest; // true
	
### Split a string
	String[] lines = stringVar.split(System.getProperty("line.separator"));

### Substring
	"sam".substring(0,1); // returns "s"
	
### Contains
	"sam".contains("am); // returns true
	
### Char array to string
	char[] a = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
	String b = new String(a);

## Misc
	
### Initialize array with values shorthand
	int[] arr = {1,2,3};
	
### Two dimensional array
	int[][] twoDim = new int[10][10];
	
### Math
Math functions operate on doubles. For floats use `FloatMath` library.
	
	int max = Math.max(11, 12);
	double max = Math.max(11.1, 12); // double input requires double out
	
	Math.floor(1.9);
	Math.floor(1.1);
	Math.sin(Math.PI + Math.E);
	Math.log(100); // natural log
	Math.pow(2, 10); 
	Math.sqrt(256);
	Math.random(); // returns [0, 1)

### Character manipulation example

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

