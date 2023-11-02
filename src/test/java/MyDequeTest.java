import interfaces.Deque;
import java.util.NoSuchElementException;

import hw_classes.MyDeque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyDequeTest {

    @Test
    void justWorks() {
        Deque<Integer> deque = new MyDeque<>();

        deque.addFirst(1);
        Assertions.assertEquals(1, deque.peekFirst());

        deque.addLast(2);
        Assertions.assertEquals(2, deque.peekLast());

        deque.addFirst(0);
        Assertions.assertEquals(0, deque.peekFirst());

        deque.addLast(3);
        Assertions.assertEquals(3, deque.peekLast());

        Assertions.assertEquals(4, deque.size());
        Assertions.assertFalse(deque.isEmpty());
    }

    @Test
    void removesWorks() {
        MyDeque<Integer> deque = new MyDeque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Assertions.assertEquals(1, deque.removeFirst());
        Assertions.assertEquals(2, deque.peekFirst());

        Assertions.assertEquals(3, deque.removeLast());
        Assertions.assertEquals(2, deque.peekLast());
    }

    @Test
    void emptyDequeWorks() {
        MyDeque<Integer> deque = new MyDeque<>();

        Assertions.assertEquals(0, deque.size());
        Assertions.assertTrue(deque.isEmpty());


        Assertions.assertThrows(NoSuchElementException.class, deque::removeFirst);
        Assertions.assertThrows(NoSuchElementException.class, deque::removeLast);

    }



}
