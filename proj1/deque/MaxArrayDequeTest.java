package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxTest1() {
        Comparator<TestComparatorBook> paperC = new TestComparatorBook.PaperComparator();
        MaxArrayDeque<TestComparatorBook> deque = new MaxArrayDeque<>(paperC);
        for (int i = 0; i < 100; i++) {
            TestComparatorBook book = new TestComparatorBook(i, "test");
            deque.addFirst(book);
        }
        TestComparatorBook bookMax = deque.max();
        assertEquals(99, bookMax.bookPaper());
    }
    @Test
    public void maxTest2() {
        Comparator<TestComparatorBook> paperC = new TestComparatorBook.PaperComparator();
        MaxArrayDeque<TestComparatorBook> deque = new MaxArrayDeque<>(paperC);
        for (int i = 0; i < 100; i++) {
            TestComparatorBook book = new TestComparatorBook(i, String.valueOf(i) + "test");
            deque.addFirst(book);
        }
        Comparator<TestComparatorBook> nameC = new TestComparatorBook.NameComparator();
        TestComparatorBook bookMax = deque.max(nameC);
        assertEquals("9test", bookMax.bookName());
    }
}
