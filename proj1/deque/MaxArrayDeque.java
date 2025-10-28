package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private Comparator<Item> innerComparator;

    public MaxArrayDeque(Comparator<Item> c) {
        super();
        innerComparator = c;
    }

    public Item max() {
        return max(innerComparator);
    }

    public Item max(Comparator<Item> c) {
        Comparator<Item> comparator = c;
        if (this.size() == 0) {
            return null;
        }
        Item max = get(0);
        for (int i = 0; i < this.size(); i++) {
            if (comparator.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
}
