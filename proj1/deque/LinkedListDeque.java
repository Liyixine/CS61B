package deque;

import java.security.PublicKey;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<Item> implements Iterable<Item> {
    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item i, Node next, Node prev) {
            this.item = i;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    /** create an empty LinkedListDeque*/
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds x to the first of the Deque*/
    public void addFirst(Item item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Adds x to the last of the Deque*/
    public void addLast(Item item) {
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** Returns the size of Deque*/
    public int size() {
        return size;
    }

    /** Returns the true if the Deque is empty*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Prints the Deque*/
    public void printDeque() {
        if(size() == 0) return;
        Node temp = sentinel.next;
        for (int i = 0; i < size() - 1; i++) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.print(temp.item);
        System.out.print("\n");
    }

    /** Deletes the first of the Deque,returns the first, if the item not exists, returns null*/
    public Item removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size -= 1;
        return item;
    }

    /** Deletes the back of the Deque, returns the back item, if the item not exists returns null*/
    public Item removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        Item item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size -= 1;
        return item;
    }

    /** returns the index item of the Deque, if the items not existed, returns the null*/
    public Item get(int index) {
        if (index < 0 || index >= size()){
            return null;
        }
        Node temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /** the help method that returns the item with  index distance of the temp */
    private Item getRecursiveHelp(int index, Node temp) {
        if (index == 0) {
            return temp.item;
        }
        return getRecursiveHelp(index - 1, temp.next);
    }

    /** returns the index item of the Deque, if the items not existed, returns the null*/
    public Item getRecursive(int index) {
        if (index < 0 || index >= size()){
            return null;
        }
        return getRecursiveHelp(index, sentinel.next);
    }

    /** returns an iterator*/
    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        private Node position;

        public LinkedListDequeIterator() {
            position = sentinel.next;
        }

        public boolean hasNext() {
            if (position != sentinel) {
                return true;
            }
            return false;
        }

        public Item next() {
            Item i = position.item;
            position = position.next;
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

        LinkedListDeque<Item> other = (LinkedListDeque<Item>) o;

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
