/**
 * HashTable implementation with Add, Get, and Delete.
 *
 * Currently using jGrasp to develop in (no code completion ☺) and it looks like it doesn't support
 *    @SuppressWarnings("unchecked") 
 * so you will get warnings when compiling.
 */
 
class SBHashTable<K, V>
{
   private final int TABLE_SIZE = 37; // prime number!
   private SBHashTableLinkedList[] table;
   
   /**
    * For testing :)
    */
   public static void main(String[] args)
   {
      System.out.println("-- Test Collisions --");
      SBHashTable<Integer, String> hashTable = new SBHashTable<Integer, String>();
      
      hashTable.add(10, "Collide!, 0");
      hashTable.add(10 + hashTable.TABLE_SIZE, "Collide!, 1");
      hashTable.add(10 + hashTable.TABLE_SIZE * 2, "Collide!, 2");
      System.out.println(hashTable.get(10));
      System.out.println(hashTable.get(10 + hashTable.TABLE_SIZE));
      System.out.println(hashTable.get(10 + hashTable.TABLE_SIZE * 2));
      
      System.out.println("\n-- Test Remove --");
      System.out.println("- Remove middle element in list -");
      hashTable.remove(10 + hashTable.TABLE_SIZE);
      System.out.println(hashTable.get(10));
      System.out.println(hashTable.get(10 + hashTable.TABLE_SIZE) + " (should be null)");
      System.out.println(hashTable.get(10 + hashTable.TABLE_SIZE * 2));
      
      System.out.println("\n- Remove last element in list -");
      hashTable.remove(10 + hashTable.TABLE_SIZE * 2);
      System.out.println(hashTable.get(10));
      System.out.println(hashTable.get(10 + hashTable.TABLE_SIZE * 2) + " (should be null)");
      
      System.out.println("\n- Remove first (only remaining) element in list -");
      hashTable.remove(10);
      System.out.println(hashTable.get(10) + " (should be null)");
   }
   
   /**
    * Constructor
    */
   public SBHashTable()
   {
      // create array of linked list objects to use for table
      this.table = new SBHashTableLinkedList[TABLE_SIZE];
   }
   
   /**
    * Add a key-value pair to the hash table
    */
   public void add(K key, V value)
   {
      int hashCode = key.hashCode();
      
      // get entry
      SBHashTableLinkedList entry = this.table[hashCode % TABLE_SIZE];
      
      // nothing is here yet, simply insert
      if (entry == null)
      {
         entry = new SBHashTableLinkedList();
         this.table[hashCode % TABLE_SIZE] = entry;
      }
      else
      {
         while (entry.next != null)
         {
            entry = entry.next;
         }
         
         entry.next = new SBHashTableLinkedList();
         entry = entry.next;
      }
      
      entry.hashCode = hashCode;
      entry.value = value;
   }
   
   /**
    * Find the value for the associated key
    * 
    * @returns: if key does not exists null, otherwise the associated value
    */
   public V get(K key)
   {
      int hashCode = key.hashCode();
      SBHashTableLinkedList entry = this.getEntryWithHashCode(hashCode);
      
      if (entry == null)
      {
         return null;
      }
      
      return (V)entry.value;
   }
   
   /**
    * Removes the key-value pair from the hash table.
    */
   public void remove(K key)
   {
      int hashCode = key.hashCode();
      
      SBHashTableLinkedList entry = this.table[hashCode % TABLE_SIZE];
      
      if (entry == null)
      {
         System.out.println("Key (" + key.toString() + ") does not exist");
         return;
      }
      
      SBHashTableLinkedList previous = null;
      do
      {
         if (entry.hashCode == hashCode)
         {
            if (previous == null) // first node special case
            {
               this.table[hashCode % TABLE_SIZE] = entry.next;
            }
            // there are more entries in the list, point to the next one
            else if (entry.next != null)
            {
               if (previous == null) // first node
               {
                  this.table[hashCode % TABLE_SIZE] = entry.next;
               }
               else
               {
                  previous.next = entry.next;
               }   
            }
            else // last node
            {
               previous.next = null;
            }
            
            return;
         }
         
         previous = entry;
         entry = entry.next;
      } while (entry != null);
      
      // If this point is reached, the key does not exist
      System.out.println("Key (" + key.toString() + ") does not exist");
   }
   
   /*******************
    * Private methods *
    ******************/
   
   private SBHashTableLinkedList getEntryWithHashCode(int hashCode)
   {
      SBHashTableLinkedList entry = this.table[hashCode % TABLE_SIZE];
      
      if (entry == null)
      {
         return null;
      }
      
      do 
      {
         if (entry.hashCode == hashCode)
         {
            return entry;
         }
         
         // move to next node
         entry = entry.next;

      } while (entry != null);

      return null;
   } 
}