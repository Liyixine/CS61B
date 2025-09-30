package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> Al = new AListNoResizing<>();
        BuggyAList<Integer> Bl = new BuggyAList<>();
        Al.addLast(4);
        Bl.addLast(4);
        Al.addLast(5);
        Bl.addLast(5);
        Al.addLast(6);
        Bl.addLast(6);


        assertEquals(Al.size(), Bl.size());
        assertEquals(Al.removeLast(), Bl.removeLast());
        assertEquals(Al.removeLast(), Bl.removeLast());
        assertEquals(Al.removeLast(), Bl.removeLast());

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bugL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bugL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                //getLast
                if (L.size() > 0){
                    int Last = L.getLast();
                    int bugLast = bugL.getLast();
                    System.out.println("getLast(" + Last +")");
                }
            } else if (operationNumber == 3) {
                //removeLast
                if (L.size() > 0) {
                    L.removeLast();
                    bugL.removeLast();
                }
            }
        }
    }
}
