package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item>{
    private Item[] items;
    private int capacity;
    private int size;
    private int first;
    private int last;

    /**Creates the empty ArrayDeque*/
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
        capacity = 8;
    }

    /** resize the items*/
    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
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
    /** Adds x to the first of the Deque*/
    public void addFirst(Item item) {
        if ((first - 1 + capacity) % capacity == last) {
            resize(items.length * 2);
        }
        first = (first - 1 + capacity) % capacity;
        items[first] = item;
        size++;
    }

    /** Adds x to the last of the Deque*/
    public void addLast(Item item) {
        if ((first - 1 + capacity) % capacity == last) {
            resize(items.length * 2);
        }
        items[last] = item;
        last = (last + 1) % capacity;
        size++;
    }

    /** returns the size*/
    public int size() {
        return size;
    }

    /** returns true if the Deque is empty*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Prints the Deque*/
    public void printDeque() {
        if (size == 0){
            return;
        }
        for (int i = first; i != last ;i = (i + 1) % capacity) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
        return;
    }

    /** Deletes the first of the Deque,returns the first, if the item not exists, returns null*/
    public Item removeFirst() {
        if (first == last) {
            return null;
        }
        if (size < items.length / 4 && items.length > 16) {
            resize(items.length / 2);
        }
        Item i = items[first];
        first = (first + 1) % capacity;
        size--;
        return i;
    }

    /** Deletes the back of the Deque, returns the back item, if the item not exists returns null*/
    public Item removeLast() {
        if (first == last) {
            return null;
        }
        if (size < items.length / 4 && items.length > 16) {
            resize(items.length / 2);
        }
        last = (last - 1 + capacity) % capacity;
        Item i = items[last];
        size--;
        return i;
    }

    /** returns the lengths just for test*/
    public int capacity() {
        return items.length;
    }

    /** returns the index item of the Deque, if the items not existed, returns the null*/
    public Item get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        int i = (first + index) % capacity;
        return items[i];
    }

    /** returns an iterator*/
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }


    private class ArrayDequeIterator implements Iterator<Item> {
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
        public Item next() {
            Item i = items[position];
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

        ArrayDeque<Item> other = (ArrayDeque<Item>) o;

        if (this.size() != other.size()) {
            return false;
        }

        int i = 0;
        for (Item x : this) {
            Item otherValue = other.get(i);
            if (!x.equals(otherValue)) {
                return false;
            }
            i++;
        }
        return true;
    }

}
