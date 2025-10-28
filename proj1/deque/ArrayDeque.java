package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int capacity;
    private int size;
    private int first;
    private int last;

    /**
     * Creates the empty ArrayDeque
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
        capacity = 8;
    }

    /**
     * resize the items
     */
    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (last < first) {
            System.arraycopy(items, first, a, 0, this.capacity - first);
            System.arraycopy(items, 0, a, this.capacity - first, last);
        } else {
            System.arraycopy(items, first, a, 0, last - first);
        }
        items = a;
        this.capacity = capacity;
        first = 0;
        last = size;
    }

    /**
     * Adds x to the first of the Deque
     */
    @Override
    public void addFirst(T item) {
        if ((first - 1 + capacity) % capacity == last) {
            resize(items.length * 2);
        }
        first = (first - 1 + capacity) % capacity;
        items[first] = item;
        size++;
    }

    /**
     * Adds x to the last of the Deque
     */
    @Override
    public void addLast(T item) {
        if ((first - 1 + capacity) % capacity == last) {
            resize(items.length * 2);
        }
        items[last] = item;
        last = (last + 1) % capacity;
        size++;
    }

    /**
     * returns the size
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Prints the Deque
     */
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (int i = first; i != last; i = (i + 1) % capacity) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
        return;
    }

    /**
     * Deletes the first of the Deque,returns the first, if the item not exists, returns null
     */
    @Override
    public T removeFirst() {
        if (first == last) {
            return null;
        }
        if (size < items.length / 4 && items.length > 16) {
            resize(items.length / 2);
        }
        T i = items[first];
        first = (first + 1) % capacity;
        size--;
        return i;
    }

    /**
     * Deletes the back of the Deque, returns the back item, if the item not exists returns null
     */
    @Override
    public T removeLast() {
        if (first == last) {
            return null;
        }
        if (size < items.length / 4 && items.length > 16) {
            resize(items.length / 2);
        }
        last = (last - 1 + capacity) % capacity;
        T i = items[last];
        size--;
        return i;
    }

    /**
     * returns the lengths just for test
     */
    public int capacity() {
        return items.length;
    }

    /**
     * returns the index item of the Deque, if the items not existed, returns the null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        int i = (first + index) % capacity;
        return items[i];
    }

    /**
     * returns an iterator
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }


    private class ArrayDequeIterator implements Iterator<T> {
        private int position;
        private int hasIt;

        public ArrayDequeIterator() {
            position = first;
            hasIt = 0;
        }

        @Override
        public boolean hasNext() {
            if (hasIt >= size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T i = items[position];
            position = (position + 1) % capacity;
            hasIt++;
            return i;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        Deque<T> other = (Deque<T>) o;

        if (this.size() != other.size()) {
            return false;
        }

        int i = 0;
        for (T x : this) {
            T otherValue = other.get(i);
            if (!x.equals(otherValue)) {
                return false;
            }
            i++;
        }
        return true;
    }

}
