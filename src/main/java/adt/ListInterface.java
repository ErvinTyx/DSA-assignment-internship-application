
package adt;


public interface ListInterface <T> {
  
  //Adds a new entry to the end of the list.
  public boolean add(T newEntry);
  
  //Adds a new entry at a specified position within the list.
  public boolean add(int newPosition, T newEntry);
  
  //Removes the entry at a given position from the list.
  public T remove(int givenPosition);

  //Removes all entries from the list.
  public void clear();
  
  
  //Retrieves the entry at a given position in the list.
  public T getEntry(int givenPosition);
  
  //Sees whether the list contains a given entry
  public boolean contains(T anEntry);

  //Gets the number of entries in the list.
  public int getNumberOfEntries();
  
  //To see whether the list is empty
  public boolean isEmpty();
  
  //To see whether the list is full
  public boolean isFull();  
  
  public int size();
  
  public T get(int index);
  
  // Sets the element at a given index.
  public void set(int index, T element);
}
