package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    /* test the add and size*/
    public void addTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad1.addLast(1);
            ad1.addFirst(2);
        }
        assertEquals(20, ad1.size());
    }

    @Test
    /* test the printDeque*/
    public void printDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.printDeque();
        ad1.addFirst(1);
        ad1.printDeque();
        ad1.addFirst(2);
        ad1.printDeque();
        ad1.addLast(0);
        ad1.printDeque();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.printDeque();
    }

    @Test
    /* test the remove*/
    public void removeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        assertEquals(null, ad1.removeLast());
        assertEquals(null, ad1.removeFirst());
        assertEquals(0, ad1.size());

        ad1.addLast(1);
        ad1.addFirst(2);
        assertEquals((double) 1, (double) ad1.removeLast(), 0.0);
        assertEquals((double) 2, (double) ad1.removeFirst(), 0.0);
        assertEquals(0, ad1.size());

        for (int i = 0; i < 10000; i++) {
            ad1.addFirst(1);
            ad1.addLast(2);
        }
        for (int i = 0; i < 10000; i++) {
            ad1.removeFirst();
            ad1.removeLast();
            int size = ad1.size();
            int capacity = ad1.capacity();
            System.out.println((double) size / (double) capacity);
        }
    }

    @Test
    /* test the get*/
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        assertEquals(null, ad1.get(1));
        for (int i = 0; i < 200; i++) {
            ad1.addFirst(i);
            ad1.addLast(-i);
        }
        for (int i = 0; i < 200; i++) {
            int item1 = ad1.get(i);
            assertEquals(199 - i, item1);
        }
        for (int i = 200; i < 400 ; i++) {
            int item2 = ad1.get(i);
            assertEquals(200 - i, item2);
        }
    }

    @Test
    /*Test the iterator of the deque*/
    public void iteratorTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            ad1.addLast(i);
        }
        int i = 0;
        for (int x : ad1) {
            assertEquals(i, x);
            i++;
        }
    }

    @Test
    /*Test the equals*/
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 129; i < 1000; i++) {
            ad1.addLast(i);
            ad2.addLast(i);
        }
        assertTrue(ad1.equals(ad2));
        assertTrue(ad1.equals(ad1));
        ad2.removeFirst();
        assertFalse(ad1.equals(ad2));
        assertFalse(ad1.equals(null));
    }
}
