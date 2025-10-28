package deque;

import java.util.Comparator;

public class TestComparatorBook {
    private int bookPaper;
    private String bookName;
    public TestComparatorBook(int bookPaper, String bookName) {
        this.bookName = bookName;
        this.bookPaper = bookPaper;
    }
    public static class PaperComparator implements Comparator<TestComparatorBook> {
        @Override
        public int compare(TestComparatorBook n1, TestComparatorBook n2) {
            return n1.bookPaper - n2.bookPaper;
        }
    }
    public static class NameComparator implements Comparator<TestComparatorBook> {
        @Override
        public int compare(TestComparatorBook n1, TestComparatorBook n2) {
            return n1.bookName.compareTo(n2.bookName);
        }
    }

    public int bookPaper() {
        return bookPaper;
    }

    public String bookName() {
        return bookName;
    }
}
