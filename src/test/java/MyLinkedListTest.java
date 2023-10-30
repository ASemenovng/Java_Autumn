
import java.util.Iterator;
import java.util.List;

import iterator.MyIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.MyLinkedList;

public class MyLinkedListTest {

    private MyLinkedList<Integer> myList = new MyLinkedList<>();

    @BeforeEach
    void setUp() {
        myList.clear();
    }


    @Test
    void addWorks() {
        for (int i = 0; i < 10; ++i) {
            myList.add(i);
            Assertions.assertEquals(i, myList.size());
        }
    }

    @Test
    void getFirstAndLastWorks() {
        Assertions.assertTrue(myList.isEmpty());

        Assertions.assertNull(myList.getFirst());
        Assertions.assertNull(myList.getLast());

        for (int i = 0; i < 10; ++i) {
            myList.insertHead(i);
            myList.add(i * 2);
            Assertions.assertEquals(i * 2, myList.getLast());
            Assertions.assertEquals(i, myList.getFirst());
        }

        Assertions.assertFalse(myList.isEmpty());
        Assertions.assertEquals(20, myList.size());
    }


    @Test
    void addElementWorks() {
        Assertions.assertTrue(myList.isEmpty());

        for (int i = 0; i < 3; ++i) {
            myList.add(i);
        }

        Assertions.assertEquals(2, myList.size());
        Assertions.assertThrows(Exception.class, () -> myList.add(5, 11));

        myList.add(1, 34);

        Assertions.assertEquals(0, myList.get(0));
        Assertions.assertEquals(34, myList.get(1));
        Assertions.assertEquals(1, myList.get(2));
        Assertions.assertEquals(2, myList.get(3));

    }

    @Test
    void removeAllContainsAllWorks() {
        Assertions.assertTrue(myList.isEmpty());

        List<Integer> expectedList = List.of(1, 2, 3);
        myList.addAll(expectedList);

        Assertions.assertTrue(myList.containsAll(expectedList));

        Assertions.assertEquals(1, myList.getFirst());
        Assertions.assertEquals(3, myList.getLast());

        myList.removeAll(expectedList);
        Assertions.assertTrue(myList.isEmpty());

    }


    @Test
    void indexOfWorks() {
        Assertions.assertTrue(myList.isEmpty());

        List<Integer> expectedList = List.of(1, 2, 3);
        myList.addAll(expectedList);

        expectedList.forEach(i -> Assertions.assertEquals(i - 1, myList.indexOf(i)));

        myList.remove(2);
        Assertions.assertEquals(-1, myList.indexOf(3));
    }


    @Test
    void iteratorWorks() {
        List<Integer> expectedList = List.of(1, 2, 3);
        myList.addAll(expectedList);


        Iterator<Integer> myListIterator = myList.iterator();
        Iterator<Integer> expectedListIterator = expectedList.iterator();

        while (myListIterator.hasNext()) {
            Assertions.assertEquals(expectedListIterator.next(), myListIterator.next());
        }

        myList.removeAll(expectedList);
        myListIterator = myList.iterator();

        Assertions.assertFalse(myListIterator.hasNext());

        myList.addAll(expectedList);
        MyIterator<Integer> myIterator = (MyIterator<Integer>) myList.iterator();

        Assertions.assertTrue(myIterator.hasNext());
        Assertions.assertFalse(myIterator.hasPrevious());


        Assertions.assertEquals(expectedList.get(0), myIterator.next());
        Assertions.assertTrue(myIterator.hasPrevious());
        Assertions.assertEquals(expectedList.get(0), myIterator.previous());

    }



}
