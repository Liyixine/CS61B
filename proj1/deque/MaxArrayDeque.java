package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> innerComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        innerComparator = c;
    }

    public T max() {
        return max(innerComparator);
    }

    public T max(Comparator<T> c) {
        Comparator<T> comparator = c;
        if (this.size() == 0) {
            return null;
        }
        T max = get(0);
        for (int i = 0; i < this.size(); i++) {
            if (comparator.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
}
