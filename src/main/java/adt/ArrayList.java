
package adt;

import java.io.Serializable;
import java.util.Iterator;


public class ArrayList<T> implements ListInterface<T>, Serializable, Iterable<T> {

    private T[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity();
        elements[size++] = newEntry;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if (newPosition < 1 || newPosition > size + 1) {
            return false;
        }
        ensureCapacity();
        shiftRight(newPosition);
        elements[newPosition - 1] = newEntry;
        size++;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > size) {
            return null;
        }
        T removedElement = elements[givenPosition - 1];
        shiftLeft(givenPosition);
        size--;
        return removedElement;
    }

    @Override
    public void clear() {
        size = 0;
    }



    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > size) {
            return null;
        }
        return elements[givenPosition - 1];
    }

    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void shiftRight(int position) {
        for (int i = size; i >= position; i--) {
            elements[i] = elements[i - 1];
        }
    }

    private void shiftLeft(int position) {
        for (int i = position - 1; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (T) elements[index];
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        elements[index] = element; // Replace the element at the specified index
    }

    // Implement the Iterable interface
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    // Inner class for the iterator
    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return elements[currentIndex++];
        }
    }
    
    
}